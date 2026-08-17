package org.lesson17;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Домашнее задание 18, п.3.1 — дополнительные тесты эндпоинта /carrinhos,
 * который полностью не покрыт исходным ServeRestTest.
 */
public class CarrinhoTest {

    private static final Faker FAKER = new Faker();
    private static String adminToken;
    private static String userId;

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://serverest.dev";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        String userEmail = "carrinho_" + FAKER.number().digits(10) + "@qa.com";
        String password = "segredo123";

        // Регистрируем администратора (нужен для создания товара) и логинимся
        userId = given()
                .contentType(ContentType.JSON)
                .body("""
                        {
                          "nome": "Comprador Secretô",
                          "email": "%s",
                          "password": "%s",
                          "administrador": "true"
                        }
                        """.formatted(userEmail, password))
                .when().post("/usuarios")
                .then().statusCode(201)
                .extract().path("_id");

        adminToken = given()
                .contentType(ContentType.JSON)
                .body("""
                        {
                          "email": "%s",
                          "password": "%s"
                        }
                        """.formatted(userEmail, password))
                .when().post("/login")
                .then().statusCode(200)
                .extract().path("authorization");
    }

    private static String createProduct(int price) {
        String productName = "Mouse AI " + FAKER.number().digits(6);
        Response response = given()
                .header("Authorization", adminToken)
                .contentType(ContentType.JSON)
                .body("""
                        {
                          "nome": "%s",
                          "preco": %d,
                          "descricao": "Criado pelo teste de carrinho",
                          "quantidade": 5
                        }
                        """.formatted(productName, price))
                .when().post("/produtos");
        response.then().statusCode(201)
                .body("message", equalTo("Cadastro realizado com sucesso"));
        return response.then().extract().path("_id");
    }

    @Test
    @DisplayName("Happy path: создать корзину, проверить содержимое и завершить покупку")
    void shouldCreateAndCompleteCart() {
        int price = FAKER.number().numberBetween(100, 900);
        String productId = createProduct(price);

        // Добавляем в корзину 2 единицы
        int quantity = 2;
        Response cart = given()
                .header("Authorization", adminToken)
                .contentType(ContentType.JSON)
                .body("""
                        {
                          "produtos": [
                            { "idProduto": "%s", "quantidade": %d }
                          ]
                        }
                        """.formatted(productId, quantity))
                .when().post("/carrinhos");
        cart.then().statusCode(201)
                .body("message", equalTo("Cadastro realizado com sucesso"))
                .body("_id", notNullValue());
        String cartId = cart.then().extract().path("_id");

        // В списке корзин есть наша корзина (список глобальный, фильтруем по idUsuario)
        given()
                .queryParam("idUsuario", userId)
                .when().get("/carrinhos")
                .then().statusCode(200)
                .body("quantidade", equalTo(1))
                .body("carrinhos[0]._id", equalTo(cartId));

        // Проверяем, что precoTotal = preco * quantidade, quantidadeTotal сумма
        given()
                .pathParam("id", cartId)
                .when().get("/carrinhos/{id}")
                .then().statusCode(200)
                .body("produtos[0].idProduto", equalTo(productId))
                .body("produtos[0].quantidade", equalTo(quantity))
                .body("produtos[0].precoUnitario", equalTo(price))
                .body("precoTotal", equalTo(price * quantity))
                .body("quantidadeTotal", equalTo(quantity));

        // Завершаем покупку — корзина удаляется
        given()
                .header("Authorization", adminToken)
                .when().delete("/carrinhos/concluir-compra")
                .then().statusCode(200)
                .body("message", equalTo("Registro excluído com sucesso"));

        // Повторное завершение — корзина для этого юзера уже не найдена
        given()
                .header("Authorization", adminToken)
                .when().delete("/carrinhos/concluir-compra")
                .then().statusCode(200)
                .body("message", equalTo("Não foi encontrado carrinho para esse usuário"));
    }

    @Test
    @DisplayName("Создание корзины без токена возвращает 401")
    void shouldReturn401WithoutToken() {
        given()
                .contentType(ContentType.JSON)
                .body("""
                        {
                          "produtos": [
                            { "idProduto": "x", "quantidade": 1 }
                          ]
                        }
                        """)
                .when().post("/carrinhos")
                .then().statusCode(401);
    }

    @Test
    @DisplayName("Дубликат товара в корзине возвращает 400")
    void shouldRejectDuplicateProductInCart() {
        String id = createProduct(10);

        given()
                .header("Authorization", adminToken)
                .contentType(ContentType.JSON)
                .body("""
                        {
                          "produtos": [
                            { "idProduto": "%s", "quantidade": 1 },
                            { "idProduto": "%s", "quantidade": 3 }
                          ]
                        }
                        """.formatted(id, id))
                .when().post("/carrinhos")
                .then().statusCode(400)
                .body("message", equalTo("Não é permitido possuir produto duplicado"));
    }
}
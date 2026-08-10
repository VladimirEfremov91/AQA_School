package org.lesson17;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServeRestTest {
    private static String userEmail;
    private static String userId;
    private static String userToken;
    private static Usuario usuario;


//  Задание 1. Открываем магазин — настройка
    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://serverest.dev";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        Faker faker = new Faker();
        userEmail = faker.internet().emailAddress();
        usuario =  new Usuario("Ваня Пупкин", userEmail, "pass123", "true");
    }

//  Задание 2. Кто здесь уже покупал?
    @Test
    @Order(1)
    public void shouldGetAllUsers() {
        when().get("/usuarios")
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .body("quantidade", greaterThan(0))
                .body("usuarios.size()", greaterThan(0));
    }

    //  Задание 3. «Досье на клиента» — GET с query-параметром
    @Test
    @Order(2)
    public void shouldFindUserByEmail() {
        String email = when()
                .get("/usuarios")
                .then()
                .extract()
                .path("usuarios[0].email");

        given()
                .queryParam("email", email)
                .when().get("/usuarios")
                .then().statusCode(200)
                .body("quantidade", equalTo(1))
                .body("usuarios[0].email", equalTo(email));
    }

    // Задание 4. «Открываем новый аккаунт» — POST
    @Test
    @Order(3)
    public void shouldCreateNewUser() {
        String newUserRequestBody = """
        {
          "nome": "Секретный Проверятель",
          "email": "%s",
          "password": "customer777",
          "administrador": "true"
        }
        """.formatted(userEmail);
        Response response = given()
                .contentType(ContentType.JSON)
                .body(newUserRequestBody)
                .when().post("/usuarios");

        response.then()
                .statusCode(201)
                .body("message", equalTo("Cadastro realizado com sucesso"))
                .body("_id", notNullValue());
        userId  = response.then().extract().path("_id");
    }

//    Задание 5. «Смена данных клиента» — PUT
    @Test
    @Order(4)
    public void shouldUpdateUser() {
        String newUserRequestBody = """
        {
          "nome": "Совершенно Секретный Проверятель",
          "email": "%s",
          "password": "customer777",
          "administrador": "false"
        }
        """.formatted(userEmail);

        given()
                .pathParam("id", userId)
                .contentType(ContentType.JSON)
                .body(newUserRequestBody)
                .when().put("/usuarios/{id}")
                .then().statusCode(200)
                .body("message", equalTo("Registro alterado com sucesso"));

    }

//    Задание 6. «Ключ от служебного входа» — авторизация + DELETE (10 мин)
    @Test
    @Order(5)
    public void shouldLogin() {
        String loginRequestBody = """
                {
                  "email": "%s",
                  "password": "customer777"
                }""".formatted(userEmail);

        Response loginResponse = given().contentType(ContentType.JSON)
                .body(loginRequestBody)
                .when().post("/login");

        loginResponse.then().statusCode(200)
                .body("message", equalTo("Login realizado com sucesso"))
                .body("authorization", notNullValue());

        userToken = loginResponse.then().extract().path("authorization");
    }

    @Test
    @Order(6)
    public void shouldDeleteUser() {
        given().pathParam("id", userId)
                .header("Authorization", userToken)
                .when().delete("/usuarios/{id}")
                .then().statusCode(200)
                .body("message", equalTo("Registro excluído com sucesso"));

        given().pathParam("id", userId)
                .when().get("/usuarios/{id}")
                .then().statusCode(400)
                .body("message", equalTo("Usuário não encontrado"));
    }

//    Задание 7. «Каталог товаров» — GET + Hamcrest
    @Test
    @Order(7)
    public void shouldGetAllProducts() {
        when().get("/produtos")
                .then().statusCode(200)
                .body("quantidade", greaterThan(0))
                .body("produtos.preco", everyItem(greaterThan(0)))
                .body("produtos.nome", everyItem(notNullValue()))
                .body("produtos.nome", hasItem("Notebook Lenovo 3a81783c-a81d-4567-908a-523a85aa118d"));
    }

    // Задание со звёздочкой (бонус). «Первый DTO»
    @Test
    @Order(8)
    public void shouldCreateNewUserFromDto() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(usuario)
                .when().post("/usuarios");

        response.then()
                .statusCode(201)
                .body("message", equalTo("Cadastro realizado com sucesso"))
                .body("_id", notNullValue());
        userId  = response.then().extract().path("_id");
    }
}

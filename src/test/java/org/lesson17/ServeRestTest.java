package org.lesson17;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServeRestTest {
    private static String userId = "";

//  Задание 1. Открываем магазин — настройка
    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://serverest.dev";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

//  Задание 2. Кто здесь уже покупал?
    @Test
    @Order(1)
    public void shouldGetAllUsers() {
        when().get("/usuarios")
                .then().log().all()
                .statusCode(200)
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
                .then().log().all()
                .statusCode(200)
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
          "email": "spy_%d@qa123.com",
          "password": "customer777",
          "administrador": "true"
        }
        """.formatted(System.currentTimeMillis());
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
          "email": "spy_%d@qa123.com",
          "password": "customer777",
          "administrador": "false"
        }
        """.formatted(System.currentTimeMillis());

        given()
                .pathParam("id", userId)
                .contentType(ContentType.JSON)
                .body(newUserRequestBody)
                .when().put("/usuarios/{id}")
                .then().log().all()
                .statusCode(200)
                .body("message", equalTo("Registro alterado com sucesso"));

    }
}

package org.lesson17;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;


public class ServeRestTest {
    private static String userId;

//  Задание 1. Открываем магазин — настройка
    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://serverest.dev";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

//  Задание 2. Кто здесь уже покупал?
    @Test
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
}

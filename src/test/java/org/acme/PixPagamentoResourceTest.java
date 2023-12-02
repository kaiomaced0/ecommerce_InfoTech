package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.acme.dto.AuthUsuarioDTO;
import org.acme.dto.PixPagamentoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PixPagamentoResourceTest {

    private String token;

    @BeforeEach
    public void setAuth() {
        AuthUsuarioDTO auth = new AuthUsuarioDTO("joaojoao", "123");

        Response response = given()
                .contentType("application/json")
                .body(auth)
                .when().post("/auth")
                .then()
                .statusCode(200)
                .extract().response();

        token = response.header("Authorization");
    }

    @Test
    public void getAllTeste() {
        given()
                .header("Authorization", "Bearer " + token)
                .when().get("/pix")
                .then()
                .statusCode(200);
    }

    @Test
    public void getIdTeste() {
        Long testeId = 6L; // Substitua pelo ID de um pagamento Pix válido
        given()
                .header("Authorization", "Bearer " + token)
                .when().get("/pix/" + testeId)
                .then()
                .statusCode(200);
    }

    @Test
    public void insertTest() {
        PixPagamentoDTO dto = new PixPagamentoDTO("chave-aleatória", 100.0); // Substitua com dados válidos

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(dto)
                .when().post("/pix")
                .then()
                .statusCode(200);
    }

    @Test
    public void updateTest() {
        Long testeId = 6L; // Substitua pelo ID de um pagamento Pix válido
        PixPagamentoDTO dto = new PixPagamentoDTO("chave-atualizada", 150.0); // Substitua com dados válidos

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(dto)
                .when().patch("/pix/" + testeId)
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteTest() {
        Long testeId = 6L; // Substitua pelo ID de um pagamento Pix válido
        given()
                .header("Authorization", "Bearer " + token)
                .when().delete("/pix/delete/" + testeId)
                .then()
                .statusCode(200);
    }
}

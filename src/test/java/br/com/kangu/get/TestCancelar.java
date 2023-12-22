package br.com.kangu.get;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static br.com.kangu.GenerateUtils.getTokenReader;

public class TestCancelar {
    @BeforeEach
    public void setURL() {
        RestAssured.baseURI = "https://portal.kangu.com.br/tms/transporte";
    }

    @Test
    public void testeCancelar500() {
        RestAssured
                .given()
                .header("token", "")
                .contentType(ContentType.JSON)
                .when().pathParam("codigo", "UY OO AX XU GK")
                .get("/cancelar/{codigo}")
                .then()
                .statusCode(500)
                .body("Mensagem", Matchers.equalTo("Parametro 'token' não informado no cabeçalho do request."));
    }

    @Test
    public void testeCancelar676() {
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .when().pathParam("codigo", "")
                .get("/cancelar/{codigo}")
                .then()
                .statusCode(676)
                .body("Mensagem", Matchers.equalTo("Necessário entrar com código de cancelamento!"));
    }

    @Test
    public void testeCancelar200() {
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .when().pathParam("codigo", "UY OO AX XU GK")
                .get("/cancelar/{codigo}")
                .then()
                .statusCode(200);
    }
}

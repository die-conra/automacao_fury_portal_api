package br.com.kangu.get;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static br.com.kangu.GenerateUtils.getTokenReader;

public class TestRastrear {
    @BeforeEach
    public void setURL() {
        RestAssured.baseURI = "https://portal.kangu.com.br/tms/transporte";
    }

    @Test
    public void testRastrear200() {
        RestAssured
                .given().log().all()
                .header(getTokenReader())
                .contentType("application/json")
                .pathParam("codigo", "")
                .when()
                .get("/rastrear/{codigo}")
                .then()
                .statusCode(200)
                .body("mensagem", Matchers.equalTo(""));
    }

    @Test
    public void testRastrear500() {
        RestAssured
                .given().log().all()
                .header(getTokenReader())
                .contentType("application/json")
                .pathParam("codigo", "")
                .when()
                .get("/rastrear/{codigo}")
                .then()
                .statusCode(500)
                .body("mensagem", Matchers.equalTo(""));
    }

    @Test
    public void testRastrear510() {
        RestAssured
                .given().log().all()
                .header(getTokenReader())
                .contentType("application/json")
                .pathParam("codigo", "")
                .when()
                .get("/rastrear/{codigo}")
                .then()
                .statusCode(510)
                .body("mensagem", Matchers.equalTo(""));
    }
}

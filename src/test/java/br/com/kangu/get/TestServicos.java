package br.com.kangu.get;

import br.com.kangu.GenerateUtils;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestServicos {
    @BeforeEach
    public void setURL() {
        RestAssured.baseURI = "https://portal.kangu.com.br/tms/transporte";
    }

    @Test()
    public void getServicos200() {
        RestAssured
                .given()
                .header(GenerateUtils.getTokenReader())
                .contentType("application/json")
                .when()
                .get("/servicos")
                .then()
                .statusCode(200)
                .body("transportadora", Matchers.equalTo(13));
        System.out.println("return content com problemas 'Content-Type: application/json' não deveria ter esse Content-Type");
    }

    @Test
    public void getServicos500() {
        RestAssured
                .given()
                .header("token", "  ")
                .contentType("application/json")
                .when()
                .get("/servicos")
                .then()
                .statusCode(500);
    }
}

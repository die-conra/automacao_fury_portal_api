package br.com.kangu.get;

import br.com.kangu.GenerateUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static br.com.kangu.GenerateUtils.*;

public class TestAferir {
    @BeforeEach
    public void setURL() {
        RestAssured.baseURI = "https://portal.kangu.com.br/tms/transporte";
    }

    @Test
    public void testeAferir200() {
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .when().pathParam("codigo", "UY OO AX XU GK")
                .get("/aferir/{codigo}")
                .then()
                .statusCode(200)
                .body("codigo", Matchers.equalTo("UY OO AX XU GK"))
                .body("simulado", Matchers.notNullValue())
                .body("aferido", Matchers.notNullValue());
    }
    @Test
    public void testeAferir500() {
        RestAssured
                .given()
                .header("token","")
                .contentType(ContentType.JSON)
                .when().pathParam("codigo", "UY OO AX XU GK")
                .get("/aferir/{codigo}")
                .then()
                .statusCode(500)
                .body("codigo", Matchers.equalTo(500))
                .body("mensagem", Matchers.equalTo("Parametro 'token' não encontrado no header. Insira  a palavra 'token' como chave no header. Ex: \\n    \\t\\t\\t\\t curl -H 'Content-Type: application/json; token: fBF27...AS; ' ..... "));
    }
    @Test
    public void testeAferir682() {
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .when().pathParam("codigo", "UY OO AX XU")
                .get("/aferir/{codigo}")
                .then()
                .statusCode(682)
                .body("codigo", Matchers.equalTo(682))
                .body("error", Matchers.equalTo("Erro no retorno dos valores aferidos: solicitante não autorizado!"));
    }
}

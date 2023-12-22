package br.com.kangu.post;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static br.com.kangu.GenerateUtils.getTokenReader;

public class TestImprimirEtiquetaLote {
    @BeforeEach
    public void setURL() {
        RestAssured.baseURI = "https://portal.kangu.com.br/tms/transporte";
    }

    @Test
    public void testImprimirEtiqueta200() {
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType("application/json")
                .body("")
                .when()
                .post("/imprimir-etiqueta-lote")
                .then()
                .statusCode(200)
                .body("mensagem", Matchers.equalTo(""));
    }

    @Test
    public void testImprimirEtiqueta500() {
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType("application/json")
                .body("")
                .when()
                .post("/imprimir-etiqueta-lote")
                .then()
                .statusCode(500)
                .body("mensagem", Matchers.equalTo("Parametro 'token' não informado no cabeçalho do request."));
    }

    @Test
    public void testImprimirEtiqueta528() {
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType("application/json")
                .body("")
                .when()
                .post("/imprimir-etiqueta-lote")
                .then()
                .statusCode(528)
                .body("mensagem", Matchers.equalTo("Esta etiqueta se encontra em processo de entrega, Não foi possível retorná-la."));
    }

    @Test
    public void testImprimirEtiqueta529() {
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType("application/json")
                .body("")
                .when()
                .post("/imprimir-etiqueta-lote")
                .then()
                .statusCode(529)
                .body("mensagem", Matchers.equalTo("Não há volumes para impressão."));
    }

    @Test
    public void testImprimirEtiqueta530() {
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType("application/json")
                .body("")
                .when()
                .post("/imprimir-etiqueta-lote")
                .then()
                .statusCode(530)
                .body("mensagem", Matchers.equalTo("Não há etiqueta com esse código de envio."));
    }
}

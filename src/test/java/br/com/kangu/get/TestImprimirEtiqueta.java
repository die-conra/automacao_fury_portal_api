package br.com.kangu.get;

import br.com.kangu.GenerateUtils;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static br.com.kangu.GenerateUtils.*;

public class TestImprimirEtiqueta {
    @BeforeEach
    public void setURL() {
        RestAssured.baseURI = "https://portal.kangu.com.br/tms/transporte";
    }

    @Test
    public void testImprimirEtiqueta200() {
        RestAssured
                .given().log().all()
                .header(getTokenReader())
                .contentType("application/json")
                .pathParam("codigo", "f3")
                .when()
                .get("/imprimir-etiqueta/{codigo}").prettyPrint();
                /*.then()
                .statusCode(500)
                .body("mensagem", Matchers.equalTo("Não foi possível imprimir a etiqueta \"D\", pois esta não pertence a esse cliente!"));*/
    }

    @Test
    public void testImprimirEtiqueta500() {
        RestAssured
                .given().log().all()
                .header(getTokenReader())
                .contentType("application/json")
                .pathParam("codigo", "")
                .when()
                .get("/imprimir-etiqueta/{codigo}")
                .then()
                .statusCode(500)
                .body("mensagem", Matchers.equalTo("Parametro 'token' não informado no cabeçalho do request."));
    }

    @Test
    public void testImprimirEtiqueta528() {
        RestAssured
                .given().log().all()
                .header(getTokenReader())
                .contentType("application/json")
                .pathParam("codigo", "")
                .when()
                .get("/imprimir-etiqueta/{codigo}")
                .then()
                .statusCode(528)
                .body("mensagem", Matchers.equalTo("Esta etiqueta se encontra em processo de entrega, Não foi possível retorná-la."));
    }

    @Test
    public void testImprimirEtiqueta529() {
        RestAssured
                .given().log().all()
                .header(getTokenReader())
                .contentType("application/json")
                .pathParam("codigo", "")
                .when()
                .get("/imprimir-etiqueta/{codigo}")
                .then()
                .statusCode(529)
                .body("mensagem", Matchers.equalTo("Não há volumes para impressão."));
    }

    @Test
    public void testImprimirEtiqueta530() {
        RestAssured
                .given().log().all()
                .header(getTokenReader())
                .contentType("application/json")
                .pathParam("codigo", "")
                .when()
                .get("/imprimir-etiqueta/{codigo}")
                .then()
                .statusCode(530)
                .body("mensagem", Matchers.equalTo("Não há etiqueta com esse código de envio."));
    }
}


package br.com.kangu.get;

import br.com.kangu.GenerateUtils;
import br.com.kangu.etiqueta.Etiqueta;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestSolicitarEtiquetas {
    @BeforeEach
    public void setURL() {
        RestAssured.baseURI = "https://portal.kangu.com.br/tms/transporte";
    }

    @Test()
    public void getServicos200() {
        Etiqueta etiqueta = new Etiqueta();
        etiqueta.setModelo("TPS");
        etiqueta.setCodigo(List.of("D"));

        RestAssured
                .given()
                .header(GenerateUtils.getTokenReader())
                .contentType("application/json")
                .body(etiqueta)
                .when()
                .post("/imprimir-etiqueta-lote")
                .then()
                .statusCode(500)
                .body("mensagem", Matchers.equalTo("Não foi possível imprimir a etiqueta \"D\", pois esta não pertence a esse cliente!"));
    }
}

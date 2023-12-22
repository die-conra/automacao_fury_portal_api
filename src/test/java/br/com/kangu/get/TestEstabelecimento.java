package br.com.kangu.get;

import br.com.kangu.GenerateUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestEstabelecimento {

    @BeforeEach
    public void setURL() {
        RestAssured.baseURI = "https://portal.kangu.com.br/tms/transporte";
    }

    @Test
    public void getEstabelecimento200() {
        RestAssured
                .given()
                .header(GenerateUtils.getTokenReader())
                .contentType(ContentType.JSON)
                .pathParam("cep", "04253050")
                .when()
                .get("/estabelecimentos/{cep}")
                .then()
                .statusCode(200)
                .body("quantidade", Matchers.equalTo(49))
                .body("estabelecimentos", Matchers.hasSize(49));
    }

    @Test
    public void getEstabelecimentoCEPIncorreto200() {
        RestAssured
                .given()
                .header(GenerateUtils.getTokenReader())
                .contentType(ContentType.JSON)
                .pathParam("cep", "123456789")
                .when()
                .get("/estabelecimentos/{cep}")
                .then()
                .statusCode(200)
                .body("quantidade", Matchers.equalTo(0))
                .body("error.codigo", Matchers.equalTo("200"))
                .body("error.mensagem", Matchers.equalTo("O CEP informado é inválido!"));
    }

    @Test
    public void getEstabelecimentoVazio200() {
        RestAssured
                .given()
                .header(GenerateUtils.getTokenReader())
                .contentType(ContentType.JSON)
                .pathParam("cep", "69750000")
                .when()
                .get("/estabelecimentos/{cep}")
                .then()
                .statusCode(200)
                .body("quantidade", Matchers.equalTo(0))
                .body("error.codigo", Matchers.blankString())
                .body("error.mensagem", Matchers.blankString());
    }

    @Test
    public void getEstabelecimento500() {
        RestAssured
                .given()
                .header("token", " ")
                .contentType(ContentType.JSON)
                .pathParam("cep", "04253050")
                .when()
                .get("/estabelecimentos/{cep}")
                .then()
                .statusCode(500);
    }
}
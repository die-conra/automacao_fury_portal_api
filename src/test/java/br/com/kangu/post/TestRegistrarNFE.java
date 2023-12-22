package br.com.kangu.post;

import br.com.kangu.PostarPLP;
import br.com.kangu.RegistrarNFE;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static br.com.kangu.GenerateUtils.getTokenReader;

public class TestRegistrarNFE {
    @BeforeEach
    public void setURL() {
        RestAssured.baseURI = "https://portal.kangu.com.br/tms/transporte";
    }

    @Test
    public void postRegistrarNFE200() {
        RegistrarNFE registrarNFE = new RegistrarNFE();
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(registrarNFE)
                .when()
                .post("/registrar-nfe")
                .then()
                .statusCode(200)
                .body("", Matchers.equalTo(""));
    }

    @Test
    public void postRegistrarNFE404NaoEncontrado() {
        RegistrarNFE registrarNFE = new RegistrarNFE();
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(registrarNFE)
                .when()
                .post("/registrar-nfe")
                .then()
                .statusCode(404)
                .body("", Matchers.equalTo("Pedido não encontrado/cancelado!"));
    }

    @Test
    public void postRegistrarNFE404() {
        RegistrarNFE registrarNFE = new RegistrarNFE();
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(registrarNFE)
                .when()
                .post("/registrar-nfe")
                .then()
                .statusCode(404)
                .body("", Matchers.equalTo("Origem não encontrada!"));
    }


    @Test
    public void postRegistrarNFE422() {
        RegistrarNFE registrarNFE = new RegistrarNFE();
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(registrarNFE)
                .when()
                .post("/registrar-nfe")
                .then()
                .statusCode(422)
                .body("", Matchers.equalTo("XML não é valido, favor verificar o mesmo."));
    }

    @Test
    public void postRegistrarNFE422XMLInvalido() {
        RegistrarNFE registrarNFE = new RegistrarNFE();
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(registrarNFE)
                .when()
                .post("/registrar-nfe")
                .then()
                .statusCode(422)
                .body("", Matchers.equalTo("Arquivo(XML) inválido!"));
    }


    @Test
    public void postRegistrarNFE422CPNJDIF() {
        RegistrarNFE registrarNFE = new RegistrarNFE();
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(registrarNFE)
                .when()
                .post("/registrar-nfe")
                .then()
                .statusCode(422)
                .body("", Matchers.equalTo("O CNPJ do XML é diferente do cadastrado no portal, por favor insira uma Nota Fiscal correspondente ao seu cadastro na Kangu."));
    }

    @Test
    public void postRegistrarNFE422UFDIF() {
        RegistrarNFE registrarNFE = new RegistrarNFE();
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(registrarNFE)
                .when()
                .post("/registrar-nfe")
                .then()
                .statusCode(422)
                .body("", Matchers.equalTo("A UF do XML é diferente do cadastrado no portal, por favor insira uma Nota Fiscal correspondente ao seu cadastro na Kangu."));
    }

    @Test
    public void postRegistrarNFE422SemSeller() {
        RegistrarNFE registrarNFE = new RegistrarNFE();
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(registrarNFE)
                .when()
                .post("/registrar-nfe")
                .then()
                .statusCode(422)
                .body("", Matchers.equalTo("Seller não informado!"));
    }

    @Test
    public void postRegistrarNFE422SemNUMCli() {
        RegistrarNFE registrarNFE = new RegistrarNFE();
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(registrarNFE)
                .when()
                .post("/registrar-nfe")
                .then()
                .statusCode(422)
                .body("", Matchers.equalTo("NumeroCli não informado!"));
    }

    @Test
    public void postRegistrarNFE422XMLNaoInformado() {
        RegistrarNFE registrarNFE = new RegistrarNFE();
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(registrarNFE)
                .when()
                .post("/registrar-nfe")
                .then()
                .statusCode(422)
                .body("", Matchers.equalTo("XML da NF-e não informado!"));
    }

    @Test
    public void postRegistrarNFE422ChaveInvalida() {
        RegistrarNFE registrarNFE = new RegistrarNFE();
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(registrarNFE)
                .when()
                .post("/registrar-nfe")
                .then()
                .statusCode(422)
                .body("", Matchers.equalTo("Chave da NF-e inválida!"));
    }

    @Test
    public void postRegistrarNFE422OrigemDif() {
        RegistrarNFE registrarNFE = new RegistrarNFE();
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(registrarNFE)
                .when()
                .post("/registrar-nfe")
                .then()
                .statusCode(422)
                .body("", Matchers.equalTo("Origem não é de plataformas!"));
    }


    @Test
    public void postRegistrarNFE422SemOrigem() {
        RegistrarNFE registrarNFE = new RegistrarNFE();
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(registrarNFE)
                .when()
                .post("/registrar-nfe")
                .then()
                .statusCode(422)
                .body("", Matchers.equalTo("Origem não informada!"));
    }


    @Test
    public void postRegistrarNFE422PedidoComNFE() {
        RegistrarNFE registrarNFE = new RegistrarNFE();
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(registrarNFE)
                .when()
                .post("/registrar-nfe")
                .then()
                .statusCode(422)
                .body("", Matchers.equalTo("Pedido já tem uma NFe informada!"));
    }

    @Test
    public void postRegistrarNFE422EtiquetaImpressa() {
        RegistrarNFE registrarNFE = new RegistrarNFE();
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(registrarNFE)
                .when()
                .post("/registrar-nfe")
                .then()
                .statusCode(422)
                .body("", Matchers.equalTo("Etiqueta já foi impressa!"));
    }


    @Test
    public void postRegistrarNFE422SemToken() {
        RegistrarNFE registrarNFE = new RegistrarNFE();
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(registrarNFE)
                .when()
                .post("/registrar-nfe")
                .then()
                .statusCode(422)
                .body("", Matchers.equalTo("Parametro 'token' não encontrado no header!"));
    }

    @Test
    public void postRegistrarNFE422Token() {
        RegistrarNFE registrarNFE = new RegistrarNFE();
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(registrarNFE)
                .when()
                .post("/registrar-nfe")
                .then()
                .statusCode(422)
                .body("", Matchers.equalTo("Token inválido, não é possível fazer a autenticação!"));
    }


}

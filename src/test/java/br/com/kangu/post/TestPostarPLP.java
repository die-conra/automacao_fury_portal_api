package br.com.kangu.post;

import br.com.kangu.PostarPLP;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static br.com.kangu.GenerateUtils.getTokenReader;

public class TestPostarPLP {
    @BeforeEach
    public void setURL() {
        RestAssured.baseURI = "https://portal.kangu.com.br/tms/transporte";
    }

    @Test
    public void postPostarPLP200() {
        PostarPLP postarPLP = new PostarPLP();
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(postarPLP)
                .when()
                .post("https://portal.kangu.com.br/tms/transporte/solicitar")
                .then()
                .statusCode(200)
                .body("", Matchers.equalTo(""));
    }

    @Test
    public void postPostarPLP500() {
        PostarPLP postarPLP = new PostarPLP();
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(postarPLP)
                .when()
                .post("https://portal.kangu.com.br/tms/transporte/solicitar")
                .then()
                .statusCode(200)
                .body("Mensagem", Matchers.equalTo("Parametro 'token' não informado no cabeçalho do request."));
    }

    @Test
    public void postPostarPLP525() {
        PostarPLP postarPLP = new PostarPLP();
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(postarPLP)
                .when()
                .post("https://portal.kangu.com.br/tms/transporte/solicitar")
                .then()
                .statusCode(200)
                .body("Mensagem", Matchers.equalTo("Parâmetro XML não está presente no JSON"));
    }

    @Test
    public void postPostarPLP526() {
        PostarPLP postarPLP = new PostarPLP();
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(postarPLP)
                .when()
                .post("https://portal.kangu.com.br/tms/transporte/solicitar")
                .then()
                .statusCode(526)
                .body("Mensagem", Matchers.equalTo("XML inválido"));
    }

    @Test
    public void postPostarPLP527() {
        PostarPLP postarPLP = new PostarPLP();
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(postarPLP)
                .when()
                .post("https://portal.kangu.com.br/tms/transporte/solicitar")
                .then()
                .statusCode(527)
                .body("Mensagem", Matchers.equalTo("Os Campos do XML são obrigatórios"));
    }

    @Test
    public void postPostarPLP528() {
        PostarPLP postarPLP = new PostarPLP();
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(postarPLP)
                .when()
                .post("https://portal.kangu.com.br/tms/transporte/solicitar")
                .then()
                .statusCode(200)
                .body("Mensagem", Matchers.equalTo("Não foi possível determinar um ponto de postagem, favor informar o parâmetro pontoPostagem"));
    }

    @Test
    public void postPostarPLP801() {
        PostarPLP postarPLP = new PostarPLP();
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(postarPLP)
                .when()
                .post("https://portal.kangu.com.br/tms/transporte/solicitar")
                .then()
                .statusCode(801)
                .body("Mensagem", Matchers.equalTo("Ponto de entrega com CPNJ não encontrado!"));
    }

    @Test
    public void postPostarPLP802() {
        PostarPLP postarPLP = new PostarPLP();
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(postarPLP)
                .when()
                .post("https://portal.kangu.com.br/tms/transporte/solicitar")
                .then()
                .statusCode(200)
                .body("Mensagem", Matchers.equalTo("Pedido com o numero informado já existe!"));
    }

    @Test
    public void postPostarPLP803() {
        PostarPLP postarPLP = new PostarPLP();
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(postarPLP)
                .when()
                .post("https://portal.kangu.com.br/tms/transporte/solicitar")
                .then()
                .statusCode(803)
                .body("Mensagem", Matchers.equalTo("Numero do volume não encontrado, tente passar a solicitação sem o número dos volumes!"));
    }

    @Test
    public void postPostarPLP804() {
        PostarPLP postarPLP = new PostarPLP();
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(postarPLP)
                .when()
                .post("https://portal.kangu.com.br/tms/transporte/solicitar")
                .then()
                .statusCode(200)
                .body("Mensagem", Matchers.equalTo("Já existe um volume com o código informado!"));
    }
}

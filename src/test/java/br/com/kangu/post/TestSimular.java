package br.com.kangu.post;

import br.com.kangu.simular.Simular;
import br.com.kangu.solicitar.Volume;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static br.com.kangu.GenerateUtils.*;
import static org.hamcrest.Matchers.*;

public class TestSimular {
    @BeforeEach
    public void setURL() {
        RestAssured.baseURI = "https://portal.kangu.com.br/tms/transporte";
    }

    @Test
    public void postSimular200() {
        Simular simular =
                new Simular(
                        "01020010",
                        "03583030",
                        150.0,
                        5.0,
                        List.of(new Volume(5.000, 50.00, 50.00, 20.00, null, null, null, null, null, null)),
                        null,
                        null,
                        null);
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(simular)
                .when()
                .post("/simular")
                .then().contentType(ContentType.JSON)
                .statusCode(200)
                .body("vlrFrete", hasSize(7));
    }

    @Test
    public void postSimular815() {
        Simular simular =
                new Simular(
                        "01020010",
                        "03583030",
                        150.0,
                        5.0,
                        List.of(new Volume(5.000, 50.00, 50.00, 20.00, null, null, null, null, null, null),
                                new Volume(null, 50.00, 50.00, 20.00, null, null, null, null, null, null)),
                        null,
                        null,
                        null);
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(simular)
                .when()
                .post("/simular")
                .then().contentType(ContentType.JSON)
                .statusCode(200)
                .body("error.codigo", equalTo(815))
                .body("error.mensagem", equalTo("Um dos volumes não tem peso"));
    }

    @Test
    public void postSimular816() {
        Simular simular =
                new Simular(
                        "01020010",
                        "03583030",
                        150.0,
                        5.0,
                        List.of(new Volume(5.000, 50.00, 50.00, 20.00, null, null, null, null, null, null),
                                new Volume(5.000, null, 50.00, 20.00, null, null, null, null, null, null)),
                        null,
                        null,
                        null);
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(simular)
                .when()
                .post("/simular")
                .then().contentType(ContentType.JSON)
                .statusCode(200)
                .body("error.codigo", equalTo(816))
                .body("error.mensagem", equalTo("Um dos volumes não tem altura"));
    }

    @Test
    public void postSimular817() {
        Simular simular =
                new Simular(
                        "01020010",
                        "03583030",
                        150.0,
                        5.0,
                        List.of(new Volume(5.000, 50.00, 50.00, 20.00, null, null, null, null, null, null),
                                new Volume(5.000, 50.00, 50.00, null, null, null, null, null, null, null)),
                        null,
                        null,
                        null);
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(simular)
                .when()
                .post("/simular")
                .then().contentType(ContentType.JSON)
                .statusCode(200)
                .body("error.codigo", equalTo(817))
                .body("error.mensagem", equalTo("Um dos volumes não tem comprimento"));
    }

    @Test
    public void postSimular870() {
        Simular simular =
                new Simular(
                        "111111111",
                        "03583030",
                        150.0,
                        5.0,
                        List.of(new Volume(5.000, 50.00, 50.00, 20.00, null, null, null, null, null, null),
                                new Volume(5.000, 50.00, 50.00, 20.00, null, null, null, null, null, null)),
                        null,
                        null,
                        null);
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(simular)
                .when()
                .post("/simular")
                .then().contentType(ContentType.JSON)
                .statusCode(200)
                .body("error.codigo", equalTo(870))
                .body("error.mensagem", equalTo("Não foi possível determinar a origem da Mercadoria! Cep inválido"));
        System.out.println("Temos um bug aqui, mesmo com cep origem/destino invalido, ele não posta a mensagem correta");
    }
    @Test
    public void postSimular871() {
        Simular simular =
                new Simular(
                        "01020010",
                        "123456789",
                        150.0,
                        5.0,
                        List.of(new Volume(5.000, 50.00, 50.00, 20.00, null, null, null, null, null, null),
                                new Volume(5.000, 50.00, 50.00, 20.00, null, null, null, null, null, null)),
                        null,
                        null,
                        null);
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(simular)
                .when()
                .post("/simular")
                .then().contentType(ContentType.JSON)
                .statusCode(200)
                .body("error.codigo", equalTo(871))
                .body("error.mensagem", equalTo("Não foi possível determinar a destino da Mercadoria! Cep inválido"));
        System.out.println("Temos um bug aqui, mesmo com cep origem/destino invalido, ele não posta a mensagem correta");
    }
    @Test
    public void postSimular873() {
        Simular simular =
                new Simular(
                        "01020010",
                        "04253050",
                        150.0,
                        5.0,
                        List.of(new Volume(5.000, 50.00, 50.00, 20.00, null, null, null, null, null, null),
                                new Volume(5.000, 50.00, 50.00, 20.00, null, null, null, null, null, null)),
                        null,
                        List.of("P"),
                        null);
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(simular)
                .when()
                .post("/simular")
               .then().contentType(ContentType.JSON)
                .statusCode(200)
                .body("error.codigo", equalTo(873))
                .body("error.mensagem", equalTo("Não foi possível identificar o serviço de postagem. Código 'P'.!"));

    }
}

package br.com.kangu.post;

import br.com.kangu.eventos.Evento;
import br.com.kangu.eventos.Ocorrencia;
import br.com.kangu.eventos.Parceiro;
import br.com.kangu.eventos.Recebedor;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static br.com.kangu.GenerateUtils.*;

public class TestPostarEventos {
    @BeforeEach
    public void setURL() {
        RestAssured.baseURI = "https://portal.kangu.com.br/tms/transporte";
    }

    @Test
    public void postPostarEventos500() {

        Ocorrencia ocorrencia = new Ocorrencia();
        Recebedor recebedor = new Recebedor();
        Parceiro parceiro = new Parceiro();
        Evento evento = new Evento();
        evento.setOcorrencia(ocorrencia);
        evento.setRecebedor(recebedor);
        evento.setParceiro(parceiro);

        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(evento)
                .when()
                .post("https://portal.kangu.com.br/tms/transporte/solicitar")
                .then()
                .statusCode(500)
                .body("error.codigo", Matchers.equalTo(500))
                .body("error.mensagem", Matchers.equalTo("O parâmetro 'tipo' em pedido não está entre os valores permitidos: utilize 'D' para Declaração, 'N' para Nota Fiscal"));
    }

    @Test
    public void postPostarEventos200() {

        Ocorrencia ocorrencia = new Ocorrencia("codigo","descricao");
        Recebedor recebedor = new Recebedor("nome","documento","assinatura");
        Parceiro parceiro = new Parceiro("nome","cnpj", getEndereco());
        Evento evento = new Evento();
        evento.setOcorrencia(ocorrencia);
        evento.setRecebedor(recebedor);
        evento.setParceiro(parceiro);

        RestAssured
                .given().log().all()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(evento)
                .when()
                .post("https://portal.kangu.com.br/tms/transporte/solicitar").prettyPrint();
               /* .then()
                .statusCode(500)
                .body("error.codigo", Matchers.equalTo(500))
                .body("error.mensagem", Matchers.equalTo("O parâmetro 'tipo' em pedido não está entre os valores permitidos: utilize 'D' para Declaração, 'N' para Nota Fiscal"));
   */
    }
}

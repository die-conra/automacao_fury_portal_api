package br.com.kangu.post;

import br.com.kangu.commons.Endereco;
import br.com.kangu.solicitar.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static br.com.kangu.GenerateUtils.*;
import static java.lang.String.*;

public class TestSolicitar {

    @BeforeEach
    public void setURL() {
        RestAssured.baseURI = "https://portal.kangu.com.br/tms/transporte";
    }

    @Test
    public void postSolicitar200() {
        Remetente remetente =
                new Remetente("Use Vnda", "55728067000139",
                        new Endereco("Rua Germano Petersen Júnior", "508", null, "Auxiliadora", "90540140", "Porto Alegre", "RS"),
                        null, null, null, null);

        Destinatario destinatario = new Destinatario("Marco Nascimento", "27540409860",
                new Endereco("Rua Angelo Aparecido dos Santos Dias", "450", null, "Jardim São Jorge (Raposo Tavares)", "05568090", "São Paulo", "SP"),
                "contato", "marco.aniceto@gmail.com", null, "11980614258");

        Produto produto = new Produto(0.2, 5d, 16d, 20d, "Camiseta Kangu CAMISETA", 99d, 1);

        Pedido pedido = new Pedido("D", null, null, null, null, null, valueOf(getFaker().random().nextInt(1, 99999)), 0d, 0d);

        Solicitar solicitar = new Solicitar();
        solicitar.setOrigem("vnda")
                .setForceFury(false)
                .setForcePortal(false)
                .setPedido(pedido)
                .setRemetente(remetente)
                .setDestinatario(destinatario)
                .setProdutos(List.of(produto))
                .setGerarPdf(false)
                .setServicos(List.of("E"));

        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(solicitar)
                .when()
                .post("/solicitar")
                .then()
                .statusCode(200)
                .body("OK", Matchers.equalTo(200));
    }

    @Test
    public void postSolicitar500() {
        Remetente remetente =
                new Remetente("Use Vnda", "55728067000139",
                        new Endereco("Rua Germano Petersen Júnior", "508", null, "Auxiliadora", "90540140", "Porto Alegre", "RS"),
                        null, null, null, null);

        Destinatario destinatario = new Destinatario("Marco Nascimento", "27540409860",
                new Endereco("Rua Angelo Aparecido dos Santos Dias", "450", null, "Jardim São Jorge (Raposo Tavares)", "05568090", "São Paulo", "SP"),
                "contato", "marco.aniceto@gmail.com", null, "11980614258");

        Produto produto = new Produto(0.2, 5d, 16d, 20d, "Camiseta Kangu CAMISETA", 99d, 1);

        Pedido pedido = new Pedido("D", null, null, null, null, null, valueOf(getFaker().random().nextInt(1, 99999)), 0d, 0d);

        Solicitar solicitar = new Solicitar();
        solicitar.setOrigem("vnda")
                .setForceFury(false)
                .setForcePortal(false)
                .setPedido(pedido)
                .setRemetente(remetente)
                .setDestinatario(destinatario)
                .setProdutos(List.of(produto))
                .setGerarPdf(false)
                .setServicos(List.of("E"));

        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(solicitar)
                .when()
                .post("/solicitar")
                .prettyPrint();
                //.then()
        //.statusCode(500)
        //       .body("error.codigo", Matchers.equalTo(500))
        //      .body("error.mensagem", Matchers.equalTo("Parametro 'token' n\\u00e3o encontrado no header. Insira  a palavra 'token' como chave no header. Ex: \\n    \\t\\t\\t\\t curl -H 'Content-Type: application\\/json; token: fBF27...AS; ' ..... "));
    }

    @Test
    public void postSolicitar422() {
        //Contrato não permite {regra}
        Remetente remetente =
                new Remetente("nome", "cnpjCpf",
                        new Endereco("logradouro", "numero", "complemento", "bairro", "cep", "cidade", "uf"),
                        "contato", "email", "telefone", "celular");

        Destinatario destinatario = new Destinatario("nome", "cnpjCpf",
                new Endereco("logradouro", "numero", "complemento", "bairro", "cep", "cidade", "uf"),
                "contato", "email", "telefone", "celular");

        Produto produto = new Produto(1.2, 1.2, 1.2, 1.2, "produto", 1.2, 1);

        Pedido pedido = new Pedido("D", "numero", "serie", "chave", "chaveCTe", "xml", "numeroCli", 1.2, 1.2);

        Volume volume = new Volume(1.2, 1.2, 1.2, 1.2, "tipo", "produto", "ean", 1.2, 1, "numeroCli");

        Solicitar solicitar = new Solicitar();

        solicitar.setPedido(pedido);


        RestAssured
                .given().log().all()
                .header("token", "a63a46ed8cdac8e6f3f954f619d4a00d")
                .contentType(ContentType.JSON)
                .body(solicitar)
                .when()
                .post("https://portal.kangu.com.br/tms/transporte/solicitar")
                .prettyPrint();
                /*.then()
                .statusCode(500)
                .body("error.codigo", Matchers.equalTo(422))
                .body("error.mensagem", Matchers.contains("Contrato não permite {regra} "));*/
    }

    @Test
    @Tag("regressivo")
    public void postSolicitar501() {
        RestAssured
                .given().log().all()
                .header("token", "a63a46ed8cdac8e6f3f954f619d4a00d")
                .contentType(ContentType.JSON)
                .body("{}")
                .when()
                .post("/solicitar")
                .then()
                .statusCode(500)
                .body("error.codigo", Matchers.equalTo(422))
                .body("error.mensagem", Matchers.contains("Corpo do request inválido"));
    }

    @Test
    public void postSolicitar502() {
        //Número do pedido, série e chave obrigatórios para tipo de transporte identificado
        Remetente remetente =
                new Remetente("Use Vnda", "55728067000139",
                        new Endereco("Rua Germano Petersen Júnior", "508", null, "Auxiliadora", "90540140", "Porto Alegre", "RS"),
                        null, null, null, null);

        Destinatario destinatario = new Destinatario("Marco Nascimento", "27540409860",
                new Endereco("Rua Angelo Aparecido dos Santos Dias", "450", null, "Jardim São Jorge (Raposo Tavares)", "05568090", "São Paulo", "SP"),
                "contato", "marco.aniceto@gmail.com", null, "11980614258");

        Produto produto = new Produto(0.2, 5d, 16d, 20d, "Camiseta Kangu CAMISETA", 99d, 1);

        Pedido pedido = new Pedido("N", null, null, null, null, null, valueOf(getFaker().random().nextInt(1, 99999)), 0d, 0d);

        Solicitar solicitar = new Solicitar();
        solicitar.setOrigem("vnda")
                .setForceFury(false)
                .setForcePortal(false)
                .setPedido(pedido)
                .setRemetente(remetente)
                .setDestinatario(destinatario)
                .setProdutos(List.of(produto))
                .setGerarPdf(false)
                .setServicos(List.of("E"));

        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(solicitar)
                .when()
                .post("/solicitar")
                .then()
                .statusCode(500)
                .body("OK", Matchers.equalTo(502))
                .body("error.mensagem", Matchers.contains("Número do pedido, série e chave obrigatórios para tipo de transporte identificado"));
    }

    @Test
    public void postSolicitar503() {
        Remetente remetente =
                new Remetente("Use Vnda", "55728067000139",
                        new Endereco("Rua Germano Petersen Júnior", "508", null, "Auxiliadora", "90540140", "Porto Alegre", "RS"),
                        null, null, null, null);

        Produto produto = new Produto(0.2, 5d, 16d, 20d, "Camiseta Kangu CAMISETA", 99d, 1);

        Pedido pedido = new Pedido("D", null, null, null, null, null, valueOf(getFaker().random().nextInt(1, 99999)), 0d, 0d);

        Solicitar solicitar = new Solicitar();
        solicitar.setOrigem("vnda")
                .setForceFury(false)
                .setForcePortal(false)
                .setPedido(pedido)
                .setRemetente(remetente)
                .setProdutos(List.of(produto))
                .setGerarPdf(false)
                .setServicos(List.of("E"));

        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(solicitar)
                .when()
                .post("/solicitar")
                .then()
                .statusCode(500)
                .body("error.codigo", Matchers.equalTo(700))
                .body("error.mensagem", Matchers.contains("Parâmetro \"destinatario\" não informado."));
    }

    @Test
    public void postSolicitar504() {

        Remetente remetente =
                new Remetente("Use Vnda", "55728067000139",
                        new Endereco("Rua Germano Petersen Júnior", "508", null, "Auxiliadora", "90540140", "Porto Alegre", "RS"),
                        null, null, null, null);

        Destinatario destinatario = new Destinatario("Marco Nascimento", null,
                new Endereco("Rua Angelo Aparecido dos Santos Dias", "450", null, "Jardim São Jorge (Raposo Tavares)", "05568090", "São Paulo", "SP"),
                "contato", null, null, null);

        Produto produto = new Produto(0.2, 5d, 16d, 20d, "Camiseta Kangu CAMISETA", 99d, 1);

        Pedido pedido = new Pedido("D", null, null, null, null, null, valueOf(getFaker().random().nextInt(1, 99999)), 0d, 0d);

        Solicitar solicitar = new Solicitar();
        solicitar.setOrigem("vnda")
                .setPedido(pedido)
                .setRemetente(remetente)
                .setDestinatario(destinatario)
                .setProdutos(List.of(produto))
                .setGerarPdf(false)
                .setServicos(List.of("E"));

        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(solicitar)
                .when()
                .post("/solicitar")
                .then()
                .statusCode(500)
                .body("error.codigo", Matchers.equalTo(700))
                .body("error.mensagem", Matchers.contains("Parâmetro \"cnpjCpf/email/celular\" não informado."));
    }

    @Test
    public void postSolicitar505() {
        //Código do cliente, email ou celular do destinatário obrigatórios

        Remetente remetente =
                new Remetente("Use Vnda", "55728067000139",
                        new Endereco("Rua Germano Petersen Júnior", "508", null, "Auxiliadora", "90540140", "Porto Alegre", "RS"),
                        null, null, null, null);

        Destinatario destinatario = new Destinatario("Marco Nascimento", "27540409860",
                new Endereco("Rua Angelo Aparecido dos Santos Dias", "450", null, "Jardim São Jorge (Raposo Tavares)", "05568090", "São Paulo", "SP"),
                "contato", null, null, null);

        Produto produto = new Produto(0.2, 5d, 16d, 20d, "Camiseta Kangu CAMISETA", 99d, 1);

        Pedido pedido = new Pedido("D", null, null, null, null, null, valueOf(getFaker().random().nextInt(1, 99999)), 0d, 0d);

        Solicitar solicitar = new Solicitar();
        solicitar.setOrigem("vnda")
                .setForceFury(false)
                .setForcePortal(false)
                .setPedido(pedido)
                .setRemetente(remetente)
                .setDestinatario(destinatario)
                .setProdutos(List.of(produto))
                .setGerarPdf(false)
                .setServicos(List.of("E"));
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(solicitar)
                .when()
                .post("/solicitar")
                .then()
                .statusCode(500)
                .body("error.codigo", Matchers.equalTo(505))
                .body("error.mensagem", Matchers.contains("Código do cliente, email ou celular do destinatário obrigatórios"));
    }

    @Test
    public void postSolicitar506() {
        Remetente remetente =
                new Remetente("Use Vnda", "55728067000139",
                        null, null, null, null, null);

        Destinatario destinatario = new Destinatario("Marco Nascimento", "27540409860",
                new Endereco("Rua Angelo Aparecido dos Santos Dias", "450", null, "Jardim São Jorge (Raposo Tavares)", "05568090", "São Paulo", "SP"),
                "contato", null, null, null);

        Produto produto = new Produto(0.2, 5d, 16d, 20d, "Camiseta Kangu CAMISETA", 99d, 1);

        Pedido pedido = new Pedido("D", null, null, null, null, null, valueOf(getFaker().random().nextInt(1, 99999)), 0d, 0d);

        Solicitar solicitar = new Solicitar();
        solicitar.setOrigem("vnda")
                .setForceFury(false)
                .setForcePortal(false)
                .setPedido(pedido)
                .setRemetente(remetente)
                .setDestinatario(destinatario)
                .setProdutos(List.of(produto))
                .setGerarPdf(false)
                .setServicos(List.of("E"));
        RestAssured
                .given()
                .header("token", "a63a46ed8cdac8e6f3f954f619d4a00d")
                .contentType(ContentType.JSON)
                .body(solicitar)
                .when()
                .post("https://portal.kangu.com.br/tms/transporte/solicitar")
                .then()
                .statusCode(500)
                .body("error.codigo", Matchers.equalTo(506))
                .body("error.mensagem", Matchers.contains("Dados do Endereço do remetente são obrigatórios"));
    }

    @Test
    public void postSolicitar507() {
        Remetente remetente =
                new Remetente("Use Vnda", "55728067000139",
                        new Endereco("Rua Germano Petersen Júnior", "508", null, "Auxiliadora", "90540140", "Porto Alegre", "RS"),
                        null, null, null, null);

        Destinatario destinatario = new Destinatario("Marco Nascimento", "27540409860",
                null, "contato", "marco.aniceto@gmail.com", null, "11980614258");

        Produto produto = new Produto(0.2, 5d, 16d, 20d, "Camiseta Kangu CAMISETA", 99d, 1);

        Pedido pedido = new Pedido("D", null, null, null, null, null, valueOf(getFaker().random().nextInt(1, 99999)), 0d, 0d);

        Solicitar solicitar = new Solicitar();
        solicitar.setOrigem("vnda")
                .setForceFury(false)
                .setForcePortal(false)
                .setPedido(pedido)
                .setRemetente(remetente)
                .setDestinatario(destinatario)
                .setProdutos(List.of(produto))
                .setGerarPdf(false)
                .setServicos(List.of("E"));
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(solicitar)
                .when()
                .post("/solicitar")
                .then()
                .statusCode(500)
                .body("error.codigo", Matchers.equalTo(507))
                .body("error.mensagem", Matchers.contains("Dados do endereço do destinatário são obrigatórios"));
    }

    @Test
    public void postSolicitar508() {
        //Dados dos volumes são obrigatórios
        Remetente remetente =
                new Remetente("Use Vnda", "55728067000139",
                        new Endereco("Rua Germano Petersen Júnior", "508", null, "Auxiliadora", "90540140", "Porto Alegre", "RS"),
                        null, null, null, null);

        Destinatario destinatario = new Destinatario("Marco Nascimento", "27540409860",
                new Endereco("Rua Angelo Aparecido dos Santos Dias", "450", null, "Jardim São Jorge (Raposo Tavares)", "05568090", "São Paulo", "SP"),
                "contato", "marco.aniceto@gmail.com", null, "11980614258");

        Produto produto = new Produto(0.2, 5d, 16d, 20d, "Camiseta Kangu CAMISETA", 99d, 1);

        Pedido pedido = new Pedido("D", null, null, null, null, null, valueOf(getFaker().random().nextInt(1, 99999)), 0d, 0d);

        Solicitar solicitar = new Solicitar();
        solicitar.setOrigem("vnda")
                .setPedido(pedido)
                .setRemetente(remetente)
                .setDestinatario(destinatario)
                .setProdutos(List.of(produto))
                .setGerarPdf(false)
                .setServicos(List.of("E"));
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(solicitar)
                .when()
                .post("/solicitar")
                .then()
                .statusCode(500)
                .body("error.codigo", Matchers.equalTo(508))
                .body("error.mensagem", Matchers.contains("Dados dos volumes são obrigatórios"));
    }

    @Test
    public void postSolicitar512() {
        Destinatario destinatario = new Destinatario("Marco Nascimento", "27540409860",
                new Endereco("Rua Angelo Aparecido dos Santos Dias", "450", null, "Jardim São Jorge (Raposo Tavares)", "05568090", "São Paulo", "SP"),
                "contato", "marco.aniceto@gmail.com", null, "11980614258");

        Produto produto = new Produto(0.2, 5d, 16d, 20d, "Camiseta Kangu CAMISETA", 99d, 1);

        Pedido pedido = new Pedido("D", null, null, null, null, null, valueOf(getFaker().random().nextInt(1, 99999)), 0d, 0d);

        Solicitar solicitar = new Solicitar();
        solicitar.setOrigem("vnda")
                .setPedido(pedido)
                .setDestinatario(destinatario)
                .setProdutos(List.of(produto))
                .setGerarPdf(false)
                .setServicos(List.of("E"));

        RestAssured
                .given()
                .header("token", "a63a46ed8cdac8e6f3f954f619d4a00d")
                .contentType(ContentType.JSON)
                .body(solicitar)
                .when()
                .post("https://portal.kangu.com.br/tms/transporte/solicitar")
                .then()
                .statusCode(500)
                .body("error.codigo", Matchers.equalTo(512))
                .body("error.mensagem", Matchers.contains("Remetente Obrigatório"));
    }

    @Test
    public void postSolicitar513() {
        Destinatario destinatario = new Destinatario("Marco Nascimento", "27540409860",
                new Endereco("Rua Angelo Aparecido dos Santos Dias", "450", null, "Jardim São Jorge (Raposo Tavares)", "05568090", "São Paulo", "SP"),
                "contato", "marco.aniceto@gmail.com", null, "11980614258");

        Produto produto = new Produto(0.2, 5d, 16d, 20d, "Camiseta Kangu CAMISETA", 99d, 1);

        Remetente remetente =
                new Remetente("Use Vnda", "55728067000139",
                        new Endereco("Rua Germano Petersen Júnior", "508", null, "Auxiliadora", "90540140", "Porto Alegre", "RS"),
                        null, null, null, null);
        Pedido pedido = new Pedido("D", null, null, null, null, null, valueOf(getFaker().random().nextInt(1, 99999)), 0d, 0d);

        Solicitar solicitar = new Solicitar();
        solicitar.setOrigem("vnda")
                .setPedido(pedido)
                .setVolume(new Volume())
                .setRemetente(remetente)
                .setDestinatario(destinatario)
                .setProdutos(List.of(produto))
                .setGerarPdf(false)
                .setServicos(List.of("E"));

        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(solicitar)
                .when()
                .post("/solicitar")
                .then()
                .statusCode(500)
                .body("error.codigo", Matchers.equalTo(513))
                .body("error.mensagem", Matchers.contains("Parâmetro volumes deve ser obrigatóriamente do tipo array ou não é válido"));
    }

    @Test
    public void postSolicitar514() {
        Remetente remetente =
                new Remetente("Use Vnda", "55728067000139",
                        new Endereco("Rua Germano Petersen Júnior", "508", null, "Auxiliadora", "90540140", "Porto Alegre", "RS"),
                        null, null, null, null);

        Destinatario destinatario = new Destinatario("Marco Nascimento", "27540409860",
                new Endereco("Rua Angelo Aparecido dos Santos Dias", "450", null, "Jardim São Jorge (Raposo Tavares)", "05568090", "São Paulo", "SP"),
                "contato", "marco.aniceto@gmail.com", null, "11980614258");

        Produto produto = new Produto(0.2, 5d, 16d, 20d, "Camiseta Kangu CAMISETA", 99d, 1);
        Volume volume = new Volume(0.2, 5d, 16d, 20d, "J", "Camiseta Kangu CAMISETA", null, 99d, 1, null);
        Pedido pedido = new Pedido("D", null, null, null, null, null, valueOf(getFaker().random().nextInt(1, 99999)), 0d, 0d);

        Solicitar solicitar = new Solicitar();
        solicitar.setOrigem("vnda")
                .setForceFury(false)
                .setVolumes(List.of(volume))
                .setForcePortal(false)
                .setPedido(pedido)
                .setRemetente(remetente)
                .setDestinatario(destinatario)
                .setProdutos(List.of(produto))
                .setGerarPdf(false)
                .setServicos(List.of("E"));

        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(solicitar)
                .when()
                .post("/solicitar")
                .then()
                .statusCode(500)
                .body("error.codigo", Matchers.equalTo(700))
                .body("error.mensagem", Matchers.contains("O parâmetro 'tipo' de um dos volumes não está entre os valores permitidos: utilize 'C' para Caixa, 'E' para Envelope"));
    }

    @Test
    public void postSolicitar515() {
        Remetente remetente =
                new Remetente("Use Vnda", "55728067000139",
                        new Endereco("Rua Germano Petersen Júnior", "508", null, "Auxiliadora", "90540140", "Porto Alegre", "RS"),
                        null, null, null, null);

        Destinatario destinatario = new Destinatario("Marco Nascimento", "27540409860",
                new Endereco("Rua Angelo Aparecido dos Santos Dias", "450", null, "Jardim São Jorge (Raposo Tavares)", "05568090", "São Paulo", "SP"),
                "contato", "marco.aniceto@gmail.com", null, "11980614258");

        Produto produto = new Produto(0.2, 5d, 16d, 20d, "Camiseta Kangu CAMISETA", 99d, 1);

        Pedido pedido = new Pedido("D", null, null, null, null, null, valueOf(getFaker().random().nextInt(1, 99999)), 0d, 0d);

        Solicitar solicitar = new Solicitar();
        solicitar.setOrigem("vnda")
                .setForceFury(false)
                .setForcePortal(false)
                .setPedido(pedido)
                .setRemetente(remetente)
                .setDestinatario(destinatario)
                .setProdutos(List.of(produto))
                .setGerarPdf(false);

        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(solicitar)
                .when()
                .post("/solicitar")
                .then()
                .statusCode(500)
                .body("error.codigo", Matchers.equalTo(500))
                .body("error.mensagem", Matchers.contains("O parâmetro 'servicos' precisa ser um array"));
    }

    @Test
    public void postSolicitar516() {
        //Serviço deve ser Coleta (C), Postagem (P), Retira (R), Entrega (E) ou Entrega Expressa (X)
        Remetente remetente =
                new Remetente("Use Vnda", "55728067000139",
                        new Endereco("Rua Germano Petersen Júnior", "508", null, "Auxiliadora", "90540140", "Porto Alegre", "RS"),
                        null, null, null, null);

        Destinatario destinatario = new Destinatario("Marco Nascimento", "27540409860",
                new Endereco("Rua Angelo Aparecido dos Santos Dias", "450", null, "Jardim São Jorge (Raposo Tavares)", "05568090", "São Paulo", "SP"),
                "contato", "marco.aniceto@gmail.com", null, "11980614258");

        Produto produto = new Produto(0.2, 5d, 16d, 20d, "Camiseta Kangu CAMISETA", 99d, 1);

        Pedido pedido = new Pedido("D", null, null, null, null, null, valueOf(getFaker().random().nextInt(1, 99999)), 0d, 0d);

        Solicitar solicitar = new Solicitar();
        solicitar.setOrigem("vnda")
                .setForceFury(false)
                .setForcePortal(false)
                .setPedido(pedido)
                .setRemetente(remetente)
                .setDestinatario(destinatario)
                .setProdutos(List.of(produto))
                .setGerarPdf(false)
                .setServicos(List.of("J"));

        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(solicitar)
                .when()
                .post("/solicitar")
                .then()
                .statusCode(500)
                .body("error.codigo", Matchers.equalTo(700))
                .body("error.mensagem", Matchers.contains("Valor do parâmetro \"servico\" inválido."));
    }

    @Test
    public void postSolicitar517() {
        //A lista de serviços pode conter Coleta (C) ou Postagem (P) mas não ambos
        Remetente remetente =
                new Remetente("Use Vnda", "55728067000139",
                        new Endereco("Rua Germano Petersen Júnior", "508", null, "Auxiliadora", "90540140", "Porto Alegre", "RS"),
                        null, null, null, null);

        Destinatario destinatario = new Destinatario("Marco Nascimento", "27540409860",
                new Endereco("Rua Angelo Aparecido dos Santos Dias", "450", null, "Jardim São Jorge (Raposo Tavares)", "05568090", "São Paulo", "SP"),
                "contato", "marco.aniceto@gmail.com", null, "11980614258");

        Produto produto = new Produto(0.2, 5d, 16d, 20d, "Camiseta Kangu CAMISETA", 99d, 1);

        Pedido pedido = new Pedido("D", null, null, null, null, null, valueOf(getFaker().random().nextInt(1, 99999)), 0d, 0d);

        Solicitar solicitar = new Solicitar();
        solicitar.setOrigem("vnda")
                .setForceFury(false)
                .setForcePortal(false)
                .setPedido(pedido)
                .setRemetente(remetente)
                .setDestinatario(destinatario)
                .setProdutos(List.of(produto))
                .setGerarPdf(false)
                .setServicos(List.of("C","P"));
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(solicitar)
                .when()
                .post("/solicitar")
                .then()
                .statusCode(500)
                .body("error.codigo", Matchers.equalTo(500))
                .body("error.mensagem", Matchers.contains("A lista de serviços pode conter Coleta (C) ou Postagem (P) mas não ambos!"));

    }

    @Test
    public void postSolicitar518() {
        //A lista de serviços pode conter Retira (R), Entrega (E) ou Entrega Expressa (X) mas não ambos!
        Remetente remetente =
                new Remetente("Use Vnda", "55728067000139",
                        new Endereco("Rua Germano Petersen Júnior", "508", null, "Auxiliadora", "90540140", "Porto Alegre", "RS"),
                        null, null, null, null);

        Destinatario destinatario = new Destinatario("Marco Nascimento", "27540409860",
                new Endereco("Rua Angelo Aparecido dos Santos Dias", "450", null, "Jardim São Jorge (Raposo Tavares)", "05568090", "São Paulo", "SP"),
                "contato", "marco.aniceto@gmail.com", null, "11980614258");

        Produto produto = new Produto(0.2, 5d, 16d, 20d, "Camiseta Kangu CAMISETA", 99d, 1);

        Pedido pedido = new Pedido("D", null, null, null, null, null, valueOf(getFaker().random().nextInt(1, 99999)), 0d, 0d);

        Solicitar solicitar = new Solicitar();
        solicitar.setOrigem("vnda")
                .setForceFury(false)
                .setForcePortal(false)
                .setPedido(pedido)
                .setRemetente(remetente)
                .setDestinatario(destinatario)
                .setProdutos(List.of(produto))
                .setGerarPdf(false)
                .setServicos(List.of("R","E","X"));
        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(solicitar)
                .when()
                .post("/solicitar")
              .then()
                .statusCode(500)
                .body("error.codigo", Matchers.equalTo(500))
                .body("error.mensagem", Matchers.contains("A lista de serviços pode conter Retira (R), Reversa (V), Entrega normal (E), Entrega expressa (X) e Mini envios (M) mas não ambos!"));

    }

    @Test
    public void postSolicitar519() {
        Remetente remetente =
                new Remetente("Use Vnda", "55728067000139",
                        new Endereco("Rua Germano Petersen Júnior", "508", null, "Auxiliadora", "90540140", "Porto Alegre", "RS"),
                        null, null, null, null);

        Destinatario destinatario = new Destinatario("Marco Nascimento", "27540409860",
                new Endereco("Rua Angelo Aparecido dos Santos Dias", "450", null, "Jardim São Jorge (Raposo Tavares)", "05568090", "São Paulo", "SP"),
                "contato", "marco.aniceto@gmail.com", null, "11980614258");

        Produto produto = new Produto(0.2, 5d, 16d, 20d, "Camiseta Kangu CAMISETA", 99d, 1);

        Pedido pedido = new Pedido("X", null, null, null, null, null, valueOf(getFaker().random().nextInt(1, 99999)), 0d, 0d);

        Solicitar solicitar = new Solicitar();
        solicitar.setOrigem("vnda")
                .setForceFury(false)
                .setForcePortal(false)
                .setPedido(pedido)
                .setRemetente(remetente)
                .setDestinatario(destinatario)
                .setProdutos(List.of(produto))
                .setGerarPdf(false)
                .setServicos(List.of("E"));

        RestAssured
                .given()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(solicitar)
                .when()
                .post("/solicitar")
                .then()
                .statusCode(500)
                .body("error.codigo", Matchers.equalTo(500))
                .body("error.mensagem", Matchers.contains("O parâmetro 'tipo' em pedido não está entre os valores permitidos: utilize 'D' para Declaração, 'N' para Nota Fiscal"));

    }

    @Test
    public void postSolicitar805() {
        //Não temos fretes disponíveis para pacotes com as dimensões e/ou valor informados.
        Remetente remetente =
                new Remetente("Use Vnda", "55728067000139",
                        new Endereco("Rua Germano Petersen Júnior", "508", null, "Auxiliadora", "90540140", "Porto Alegre", "RS"),
                        null, null, null, null);

        Destinatario destinatario = new Destinatario("Marco Nascimento", "27540409860",
                new Endereco("Rua Angelo Aparecido dos Santos Dias", "450", null, "Jardim São Jorge (Raposo Tavares)", "05568090", "São Paulo", "SP"),
                "contato", "marco.aniceto@gmail.com", null, "11980614258");

        Produto produto = new Produto(155.0, 155.0, 155.0, 20d, "Camiseta Kangu CAMISETA", 99d, 1);

        Pedido pedido = new Pedido("D", null, null, null, null, null, valueOf(getFaker().random().nextInt(1, 99999)), 0d, 0d);

        Solicitar solicitar = new Solicitar();
        solicitar.setOrigem("vnda")
                .setForceFury(false)
                .setForcePortal(false)
                .setPedido(pedido)
                .setRemetente(remetente)
                .setDestinatario(destinatario)
                .setProdutos(List.of(produto))
                .setGerarPdf(false)
                .setServicos(List.of("E"));
        RestAssured
                .given().log().all()
                .header(getTokenReader())
                .contentType(ContentType.JSON)
                .body(solicitar)
                .when()
                .post("/solicitar").prettyPrint();
               /* .then()
                .statusCode(500)
                .body("error.codigo", Matchers.equalTo(520)).body("error.mensagem", Matchers.contains("Não temos fretes disponíveis para pacotes com as dimensões e/ou valor informados."));
*/

    }
}

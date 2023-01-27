package rest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;


public class Simulations extends BaseAPI {

    @Test
    public void ConsultarTodasSimulacoesVazia(){

        given()
                .when()
                    .get("/simulacoes")
                .then()
                    .log().all()
                    .statusCode(204);
    }

    @Test
    public void InserirUmaSimulacaoCorretamente(){

        given()
                .body("{\n" +
                        "  \"nome\": \"Vinicius Ferraz\",\n" +
                        "  \"cpf\": 55402394075,\n" +
                        "  \"email\": \"email@email.com\",\n" +
                        "  \"valor\": 1200,\n" +
                        "  \"parcelas\": 3,\n" +
                        "  \"seguro\": true\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                    .post("/simulacoes")
                .then()
                    .statusCode(201)
                .log().all();
    }

    @Test
    public void InserirUmaSimulacaoComCPFInvalido(){

        given()
                .body("{\n" +
                        "  \"nome\": \"Vinicius Ferraz\",\n" +
                        "  \"cpf\": 123,\n" +
                        "  \"email\": \"email@email.com\",\n" +
                        "  \"valor\": 1200,\n" +
                        "  \"parcelas\": 3,\n" +
                        "  \"seguro\": true\n" +
                        "}")
                    .contentType(ContentType.JSON)
                .when()
                    .post("/simulacoes")
                .then()
                    .statusCode(400)
                    .log().all();
    }

    @Test
    public void InserirUmaSimulacaoComValorMenor(){

        given()
                .body("{\n" +
                        "  \"nome\": \"Vinicius Ferraz\",\n" +
                        "  \"cpf\": 12345,\n" +
                        "  \"email\": \"email@email.com\",\n" +
                        "  \"valor\": 999,\n" +
                        "  \"parcelas\": 3,\n" +
                        "  \"seguro\": true\n" +
                        "}")
                    .contentType(ContentType.JSON)
                .when()
                    .post("/simulacoes")
                .then()
                    .log().all()
                    .statusCode(400);
    }

    @Test
    public void InserirUmaSimulacaoComValorMaior(){

        given()
                .body("{\n" +
                        "  \"nome\": \"Vinicius Ferraz\",\n" +
                        "  \"cpf\": 123456,\n" +
                        "  \"email\": \"email@email.com\",\n" +
                        "  \"valor\": 41000,\n" +
                        "  \"parcelas\": 3,\n" +
                        "  \"seguro\": true\n" +
                        "}")
                    .contentType(ContentType.JSON)
                .when()
                    .post("/simulacoes")
                .then()
                    .log().all()
                    .statusCode(400);
    }

    @Test
    public void InserirUmaSimulacaoComParcelasMenor(){

        given()
                .body("{\n" +
                        "  \"nome\": \"Vinicius Ferraz\",\n" +
                        "  \"cpf\": 1234567,\n" +
                        "  \"email\": \"email@email.com\",\n" +
                        "  \"valor\": 1200,\n" +
                        "  \"parcelas\": 1,\n" +
                        "  \"seguro\": true\n" +
                        "}")
                    .contentType(ContentType.JSON)
                .when()
                    .post("/simulacoes")
                .then()
                    .log().all()
                    .statusCode(400);
    }

    @Test
    public void InserirUmaSimulacaoComParcelasMaior(){

        given()
                .body("{\n" +
                        "  \"nome\": \"Vinicius Ferraz\",\n" +
                        "  \"cpf\": 1234567890,\n" +
                        "  \"email\": \"email@email.com\",\n" +
                        "  \"valor\": 1200,\n" +
                        "  \"parcelas\": 49,\n" +
                        "  \"seguro\": true\n" +
                        "}")
                    .contentType(ContentType.JSON)
                .when()
                    .post("/simulacoes")
                .then()
                    .log().all()
                    .statusCode(400);
    }

    @Test
    public void InserirUmaSimulacaoComSeguroInvalido(){

        given()
                .body("{\n" +
                        "  \"nome\": \"Vinicius Ferraz\",\n" +
                        "  \"cpf\": 12345678901,\n" +
                        "  \"email\": \"email@email.com\",\n" +
                        "  \"valor\": 1200,\n" +
                        "  \"parcelas\": 3,\n" +
                        "  \"seguro\": errado\n" +
                        "}")
                    .contentType(ContentType.JSON)
                .when()
                    .post("/simulacoes")
                .then()
                    .log().all()
                    .statusCode(400);

    }

    @Test
    public void InserirUmaSimulacaoComEmailErrado(){

        given()
                .body("{\n" +
                        "  \"nome\": \"Vinicius \",\n" +
                        "  \"cpf\": 12345678900,\n" +
                        "  \"email\": \"email@\",\n" +
                        "  \"valor\": 1200,\n" +
                        "  \"parcelas\": 3,\n" +
                        "  \"seguro\": true\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                    .post("/simulacoes")
                .then()
                    .statusCode(400)
                    .log().all();
    }

    @Test
    public void InserirUmaSimulacaoJaExistente(){

        given()
                .body("{\n" +
                        "  \"nome\": \"Vinicius Ferraz\",\n" +
                        "  \"cpf\": 55402394075,\n" +
                        "  \"email\": \"email@email.com\",\n" +
                        "  \"valor\": 1200,\n" +
                        "  \"parcelas\": 3,\n" +
                        "  \"seguro\": true\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                    .post("/simulacoes")
                .then()
                    .log().all()
                    .statusCode(409);
    }

    @Test
    public void AlterarUmaSimulacaoJaExistente(){

        given()
                .body("{\n" +
                        "  \"nome\": \"Alteracao\",\n" +
                        "  \"cpf\": 55402394075,\n" +
                        "  \"email\": \"emailalterado@email.com\",\n" +
                        "  \"valor\": 40.000,\n" +
                        "  \"parcelas\": 30,\n" +
                        "  \"seguro\": false\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                    .put("/simulacoes/55402394075")
                .then()
                    .log().all()
                    .statusCode(200);
    }

    @Test
    public void AlterarUmaSimulacaoNaoExistente(){

        given()
                .body("{\n" +
                        "  \"nome\": \"Alterar nao existente\",\n" +
                        "  \"cpf\": 333999222,\n" +
                        "  \"email\": \"emailalterado@email.com\",\n" +
                        "  \"valor\": 40.000,\n" +
                        "  \"parcelas\": 30,\n" +
                        "  \"seguro\": false\n" +
                        "}")
                    .contentType(ContentType.JSON)
                .when()
                    .put("/simulacoes/333999222")
                .then()
                    .log().all()
                    .statusCode(404);
    }


    @Test
    public void ConsultarTodasSimulacoes(){
        consultarSimulacoes();
    }

    @Test
    public void ConsultarSimulacaoPeloCPF(){

        given()
                .when()
                .get("/simulacoes/55402394075")
                    .then()
                    .log().all();
    }

    @Test
    public void ConsultarSimulacaoPeloCPFNaoExistente(){

        given()
                .when()
                    .get("/simulacoes/353535353")
                .then()
                    .log().all()
                    .statusCode(404);
    }

    @Test
    public void RemoverUmaSimulacao(){

        Integer id = InserirSimulacaoEextrairID();

        given()
                .when()
                    .delete("/simulacoes/"+ id + "")
                .then()
                    .log().all()
                    .statusCode(200);
        consultarSimulacoes();
    }

    @Test
    public void RemoverUmaSimulacaoNaoExistente(){

        given()
                .when()
                    .delete("/simulacoes/01")
                .then()
                    .log().all()
                    .statusCode(404);
    }

    public void consultarSimulacoes() {

        given()
                .when()
                    .get("/simulacoes")
                .then()
                    .log().all();
    }

    public Integer InserirSimulacaoEextrairID(){

        Integer id = given()
                .body("{\n" +
                        "  \"nome\": \"Vinicius Ferraz\",\n" +
                        "  \"cpf\": 55402345353,\n" +
                        "  \"email\": \"email@email.com\",\n" +
                        "  \"valor\": 1200,\n" +
                        "  \"parcelas\": 3,\n" +
                        "  \"seguro\": true\n" +
                        "}")
                    .contentType(ContentType.JSON)
                .when()
                    .post("/simulacoes")
                .then()
                    .statusCode(201)
                .log().all()
                    .extract()
                        .path("id");
        return id;
    }
}


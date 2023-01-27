package rest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

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
        Map<String, Object> params = new HashMap<String, Object>();
            params.put("nome", "Vinicius Ferraz");
            params.put("cpf", 55402394075L);
            params.put("email", "email@email.com");
            params.put("valor", 1200);
            params.put("parcelas", 3);
            params.put("seguro", true);

        given()
                .contentType(ContentType.JSON)
                .body(params)
                .when()
                    .post("/simulacoes")
                .then()
                    .statusCode(201)
                .log().all();
    }

    @Test
    public void InserirUmaSimulacaoComCPFInvalido(){
        Map<String, Object> params = new HashMap<String, Object>();
            params.put("nome", "Vinicius Ferraz");
            params.put("cpf", 123);
            params.put("email", "email@email.com");
            params.put("valor", 1200);
            params.put("parcelas", 3);
            params.put("seguro", true);

        given()
                .body(params)
                    .contentType(ContentType.JSON)
                .when()
                    .post("/simulacoes")
                .then()
                    .statusCode(400)
                    .log().all();
    }

    @Test
    public void InserirUmaSimulacaoComEmailErrado(){
        Map<String, Object> params = new HashMap<String, Object>();
            params.put("nome", "Vinicius");
            params.put("cpf", 12345678900L);
            params.put("email", "email@");
            params.put("valor", 1200);
            params.put("parcelas", 3);
            params.put("seguro", true);

        given()
                .body(params)
                .contentType(ContentType.JSON)
                .when()
                .post("/simulacoes")
                .then()
                .statusCode(400)
                .log().all();
    }

    @Test
    public void InserirUmaSimulacaoComValorMenor(){
        Map<String, Object> params = new HashMap<String, Object>();
            params.put("nome", "Vinicius Ferraz");
            params.put("cpf", 12345);
            params.put("email", "email@email.com");
            params.put("valor", 999);
            params.put("parcelas", 3);
            params.put("seguro", true);

        given()
                .body(params)
                    .contentType(ContentType.JSON)
                .when()
                    .post("/simulacoes")
                .then()
                    .log().all()
                    .statusCode(400);
    }

    @Test
    public void InserirUmaSimulacaoComValorMaior(){
        Map<String, Object> params = new HashMap<String, Object>();
            params.put("nome", "Vinicius Ferraz");
            params.put("cpf", 123456);
            params.put("email", "email@email.com");
            params.put("valor", 41000);
            params.put("parcelas", 3);
            params.put("seguro", true);

        given()
                .body(params)
                    .contentType(ContentType.JSON)
                .when()
                    .post("/simulacoes")
                .then()
                    .log().all()
                    .statusCode(400);
    }

    @Test
    public void InserirUmaSimulacaoComParcelasMenor(){
        Map<String, Object> params = new HashMap<String, Object>();
            params.put("nome", "Vinicius Ferraz");
            params.put("cpf", 1234567);
            params.put("email", "email@email.com");
            params.put("valor", 1200);
            params.put("parcelas", 1);
            params.put("seguro", true);

        given()
                .body(params)
                    .contentType(ContentType.JSON)
                .when()
                    .post("/simulacoes")
                .then()
                    .log().all()
                    .statusCode(400);
    }

    @Test
    public void InserirUmaSimulacaoComParcelasMaior(){
        Map<String, Object> params = new HashMap<String, Object>();
            params.put("nome", "Vinicius Ferraz");
            params.put("cpf", 1234567890);
            params.put("email", "email@email.com");
            params.put("valor", 1200);
            params.put("parcelas", 49);
            params.put("seguro", true);

        given()
                .body(params)
                    .contentType(ContentType.JSON)
                .when()
                    .post("/simulacoes")
                .then()
                    .log().all()
                    .statusCode(400);
    }

    @Test
    public void InserirUmaSimulacaoComSeguroInvalido(){
        Map<String, Object> params = new HashMap<String, Object>();
            params.put("nome", "Vinicius Ferraz");
            params.put("cpf", 12345678901L);
            params.put("email", "email@email.com");
            params.put("valor", 1200);
            params.put("parcelas", 49);
            params.put("seguro", "errado");

        given()
                .body(params)
                    .contentType(ContentType.JSON)
                .when()
                    .post("/simulacoes")
                .then()
                    .log().all()
                    .statusCode(400);
    }

    @Test
    public void InserirUmaSimulacaoJaExistente(){
        Map<String, Object> params = new HashMap<String, Object>();
            params.put("nome", "Vinicius Ferraz");
            params.put("cpf", 55402394075L);
            params.put("email", "email@email.com");
            params.put("valor", 1200);
            params.put("parcelas", 2);
            params.put("seguro", true);

        given()
                .body(params)
                .contentType(ContentType.JSON)
                .when()
                    .post("/simulacoes")
                .then()
                    .log().all()
                    .statusCode(409);
    }

    @Test
    public void AlterarUmaSimulacaoJaExistente(){
        Map<String, Object> params = new HashMap<String, Object>();
            params.put("nome", "Alteracao");
            params.put("cpf", 55402394075L);
            params.put("email", "emailalterado@email.com");
            params.put("valor", 3000);
            params.put("parcelas", 5);
            params.put("seguro", false);

        given()
                .body(params)
                .contentType(ContentType.JSON)
                .when()
                    .put("/simulacoes/55402394075")
                .then()
                    .log().all()
                    .statusCode(200);
    }

    @Test
    public void AlterarUmaSimulacaoNaoExistente(){
        Map<String, Object> params = new HashMap<String, Object>();
            params.put("nome", "Alterar nao existente");
            params.put("cpf", 333999222);
            params.put("email", "emailalterado@email.com");
            params.put("valor", 40000);
            params.put("parcelas", 30);
            params.put("seguro", true);

        given()
                .body(params)
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
        Map<String, Object> params = new HashMap<String, Object>();
            params.put("nome", "Vinicius Ferraz");
            params.put("cpf", 55402345353L);
            params.put("email", "email@email.com");
            params.put("valor", 1200);
            params.put("parcelas", 3);
            params.put("seguro", true);

        Integer id = given()
                .body(params)
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


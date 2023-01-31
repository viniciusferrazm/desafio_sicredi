package rest;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;


import static io.restassured.RestAssured.given;

public class Metodos extends BaseAPI{

    public static ValidatableResponse consultarSimulacoes() {
        ValidatableResponse response = given()
                .when()
                    .get("/simulacoes")
                .then()
                    .log().all();
        return response;
    }

    public static ValidatableResponse consultarSimulacaoPeloCPF(Long cpf){
        ValidatableResponse response = given()
                .when()
                    .get("/simulacoes/"+ cpf +"")
                .then()
                    .log().all();
        return response;
    }

    public static ValidatableResponse alterarSimualcao(Object simulacao, Long cpf){
        ValidatableResponse response = given()
                    .body(simulacao)
                    .contentType(ContentType.JSON)
                .when()
                    .put("/simulacoes/"+ cpf +"")
                .then()
                    .log().all();
        return response;
    }

    public static ValidatableResponse inserirSimulacao(Object simulacao) {
        ValidatableResponse response = given()
                    .body(simulacao)
                    .contentType(ContentType.JSON)
                .when()
                    .post("/simulacoes")
                .then()
                    .log().all();
        return response;
    }

    public static ValidatableResponse removerSimulacoes(String id){
        given()
                .when()
                    .delete("/simulacoes/"+ id +"")
                .then()
                    .log().all();
        return null;
    }
    public static Integer inserirSimulacaoExtrairID(Object simulacao){
        Integer id = given()
                    .body(simulacao)
                    .contentType(ContentType.JSON)
                .when()
                    .post("/simulacoes")
                .then()
                    .log().all()
                    .extract()
                        .path("id");
        return id;
    }
}

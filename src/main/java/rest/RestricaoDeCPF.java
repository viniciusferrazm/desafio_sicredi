package rest;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class RestricaoDeCPF extends BaseAPI {

    @ParameterizedTest
    @CsvSource({"97093236014", "60094146012", "84809766080", "62648716050", "26276298085", "01317496094", "55856777050", "19626829001", "24094592008", "58063164083"})
    public void CPFComRestricao(String cpf) {

        given()
                .when()
                    .get("/restricoes/" + cpf + " ")
                .then()
                    .statusCode(200)
                    .body("mensagem", is("O CPF " + cpf + " tem problema"));

    }

    @ParameterizedTest
    @CsvSource({"77879086021", "84833689006", "12696328099", "31386359033", "35637642044", "98498534097", "21394036060"})
    public void CPFSemRestricao(String cpf) {

        given()
                .when()
                    .get("/restricoes/" + cpf + " ")
                .then()
                    .statusCode(204);

    }
}

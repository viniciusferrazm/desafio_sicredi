package rest;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static rest.Metodos.*;

public class Simulations extends BaseAPI {

    @Test
    public void InserirUmaSimulacaoCorretamente() {
        CriadorDeSimulacao simulacao = new CriadorDeSimulacao("Vinicius", 39163318652L, "viniciusferraz@email.com", 1500, 3, true);
        inserirSimulacao(simulacao).statusCode(201);
    }

    @Test
    public void InserirUmaSimulacaoComCPFInvalido() {
        CriadorDeSimulacao simulacao = new CriadorDeSimulacao("Vinicius", 1234L, "viniciusferraz@email.com", 1500, 3, true);
        inserirSimulacao(simulacao).statusCode(400);
    }

    @Test
    public void InserirUmaSimulacaoComEmailErrado() {
        CriadorDeSimulacao simulacao = new CriadorDeSimulacao("Vinicius", 12345678900L, "@email.cm", 1500, 3, false);
        inserirSimulacao(simulacao).statusCode(400);
    }

    @Test
    public void InserirUmaSimulacaoComValorMenor() {
        CriadorDeSimulacao simulacao = new CriadorDeSimulacao("Vinicius", 12345943021L, "email@email.com", 999, 3, false);
        inserirSimulacao(simulacao).statusCode(400);
    }

    @Test
    public void InserirUmaSimulacaoComValorMaior() {
        CriadorDeSimulacao simulacao = new CriadorDeSimulacao("Vinicius", 36054821529L, "email@email.com", 41000, 3, false);
        inserirSimulacao(simulacao).statusCode(400);
    }

    @Test
    public void InserirUmaSimulacaoComParcelasMenor() {
        CriadorDeSimulacao simulacao = new CriadorDeSimulacao("Vinicius", 14354851526L, "email@email.com", 35000, 1, false);
        inserirSimulacao(simulacao).statusCode(400);
    }

    @Test
    public void InserirUmaSimulacaoComParcelasMaior() {
        CriadorDeSimulacao simulacao = new CriadorDeSimulacao("Vinicius", 67354839526L, "email@email.com", 21000, 49, false);
        inserirSimulacao(simulacao).statusCode(400);
    }

    @Test
    public void InserirUmaSimulacaoJaExistente() {
        CriadorDeSimulacao simulacao = new CriadorDeSimulacao("Vinicius Ferraz", 83792169525L, "email@email.com", 3000, 5, false);
        inserirSimulacao(simulacao);
        ValidatableResponse response = inserirSimulacao(simulacao);
        response.statusCode(409)
                .body("mensagem", is("CPF duplicado"));
    }

    @Test
    public void AlterarUmaSimulacaoJaExistente() {
        CriadorDeSimulacao simulacao = new CriadorDeSimulacao("Vinicius Ferraz", 55530629525L, "vinicius@email.com", 1950, 3, true);
        Long cpf = simulacao.getCpf();
        inserirSimulacao(simulacao);
        simulacao.setEmail("alterarjaexistente@email.com");
        simulacao.setNome("Alterado");
        alterarSimualcao(simulacao, cpf);
    }

    @Test
    public void AlterarUmaSimulacaoNaoExistente() {
        CriadorDeSimulacao simulacao = new CriadorDeSimulacao("Vinicius", 23394169525L, "simulacaoNaoExiste@email.com", 2400, 4, true);
        Long cpf = simulacao.getCpf();
        alterarSimualcao(simulacao, cpf).statusCode(404)
                .body("mensagem", is("CPF " + cpf + " não encontrado"));
    }

    @Test
    public void ConsultarTodasSimulacoes() {
        consultarSimulacoes();
    }

    @Test
    public void ConsultarTodasSimulacoesVazia() {
        removerSimulacoes("11");
        consultarSimulacoes();
    }

    @Test
    public void ConsultarSimulacaoPeloCPF() {
        CriadorDeSimulacao simulacao = new CriadorDeSimulacao("Vinicius", 46027271078L, "email@email.com", 2400, 4, true);
        Long cpf = simulacao.getCpf();
        inserirSimulacao(simulacao);
        consultarSimulacaoPeloCPF(cpf);
    }

    @Test
    public void ConsultarSimulacaoPeloCPFNaoExistente() {
        CriadorDeSimulacao simulacao = new CriadorDeSimulacao("Vinicius", 46027271078L, "email@email.com", 2400, 4, true);
        Long cpf = simulacao.getCpf();
        consultarSimulacaoPeloCPF(cpf).statusCode(404);
    }

    @Test
    public void RemoverUmaSimulacao() {
        CriadorDeSimulacao simulacao = new CriadorDeSimulacao("Vinicius", 55402345353L, "email@email.com", 1500, 3, true);
        Integer id = inserirSimulacaoExtrairID(simulacao);
        consultarSimulacoes();
        removerSimulacoes(String.valueOf(id)).statusCode(204);
        consultarSimulacoes();
    }

    @Test
    public void RemoverUmaSimulacaoNaoExistente() {
        consultarSimulacoes();
        removerSimulacoes("01").statusCode(404)
                .body(is("Simulação não encontrada"));
    }

    @ParameterizedTest
    @CsvSource({"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"})
    public void RemoverTodasSimulacoes(String id) {
        removerSimulacoes(id);
    }

    @Test
    public void TesteRemover() {
        ValidatableResponse response = consultarSimulacoes();
        JsonPath extrator = response.extract().jsonPath();
        ArrayList <String> id = extrator.get("id");

        System.out.println(id);

    }
}



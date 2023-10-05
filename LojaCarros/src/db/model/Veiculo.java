package db.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Veiculo implements Serializable {
    
    private String renavam;

    private String nome;

    private String modelo;

    private String placa;

    private LocalDate dataFabricacao;

    private Condutor condutor;

    public Veiculo(){}

    public Veiculo(String renavam, String nome, String modelo, String placa, LocalDate dataFabricacao, Condutor condutor){
        this.renavam = renavam;
        this.nome = nome;
        this.modelo = modelo;
        this.placa = placa;
        this.dataFabricacao = dataFabricacao;
        this.condutor = condutor;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Condutor getCondutor() {
        return condutor;
    }

    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }

    public String getRenavam() {
        return renavam;
    }

    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(LocalDate ano) {
        this.dataFabricacao = ano;
    }

    @Override
    public String toString() {
        return "Veiculo [renavam=" + renavam + ", nome=" + nome + ", modelo=" + modelo + ", placa=" + placa
                + ", dataFabricacao=" + dataFabricacao + ", nome do condutor=" + condutor.getNome() + ", cpf do condutor=" + condutor.getCpf() +  "]";
    }
    
}

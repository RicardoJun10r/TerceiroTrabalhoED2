package db.model;

import java.io.Serializable;

public class Condutor implements Serializable {

    private String nome;

    private String cpf;

    public Condutor(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Condutor [nome=" + nome + ", cpf=" + cpf + "]";
    }
    
}

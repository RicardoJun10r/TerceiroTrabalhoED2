package util.Huffman;

import java.io.Serializable;

public class HuffNode implements Serializable {
    
    private String caractere;

    private Integer frequencia;

    private HuffNode esq;

    private HuffNode dir;

    public HuffNode(String caractere, Integer frequencia){
        this.caractere = caractere;
        this.frequencia = frequencia;
        this.dir = null;
        this.esq = null;
    }

    public String getCaractere() {
        return caractere;
    }

    public void setCaractere(String caractere) {
        this.caractere = caractere;
    }

    public Integer getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(Integer frequencia) {
        this.frequencia = frequencia;
    }

    public HuffNode getEsq() {
        return esq;
    }

    public void setEsq(HuffNode esq) {
        this.esq = esq;
    }

    public HuffNode getDir() {
        return dir;
    }

    public void setDir(HuffNode dir) {
        this.dir = dir;
    }
    
}

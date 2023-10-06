package util;

import java.io.Serializable;

import util.Huffman.HuffTree;

public class ResponseDTO implements Serializable {
    
    private String resposta;
    
    private HuffTree huffTree;

    public ResponseDTO(String resposta, HuffTree huffTree) {
        this.resposta = resposta;
        this.huffTree = huffTree;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public HuffTree getHuffTree() {
        return huffTree;
    }

    public void setHuffTree(HuffTree huffTree) {
        this.huffTree = huffTree;
    }

}

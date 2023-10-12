package util;

import java.io.Serializable;

import util.Huffman.HuffTree;

public class ResponseDTO implements Serializable {
    
    private Character[] resposta;
    
    private HuffTree huffTree;

    public ResponseDTO(Character[] resposta, HuffTree huffTree) {
        this.resposta = resposta;
        this.huffTree = huffTree;
    }

    public Character[] getResposta() {
        return resposta;
    }

    public void setResposta(Character[] resposta) {
        this.resposta = resposta;
    }

    public HuffTree getHuffTree() {
        return huffTree;
    }

    public void setHuffTree(HuffTree huffTree) {
        this.huffTree = huffTree;
    }

}

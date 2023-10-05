package db.handlers;

public class VeiculoNaoEncontrado extends RuntimeException {
    public VeiculoNaoEncontrado(String msg){
        super(msg);
    }
}
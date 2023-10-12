package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import util.ResponseDTO;
import util.Huffman.HuffTree;

public interface IServer extends Remote {

    void adicionar(Character[] veiculo, HuffTree huffTree) throws RemoteException;
    ResponseDTO remover(Character[] renavam, HuffTree huffTree) throws RemoteException;
    ResponseDTO listar(Integer posicao) throws RemoteException;
    ResponseDTO buscarMF(Character[] renavam, HuffTree huffTree) throws RemoteException;
    ResponseDTO buscarTR(Character[] renavam, HuffTree huffTree) throws RemoteException;
    ResponseDTO buscarCF(Character[] renavam, HuffTree huffTree) throws RemoteException;
    void atualizar(Character[] novo, Character[] renavam, HuffTree huffTree) throws RemoteException;
    ResponseDTO quantidadeDeCarros() throws RemoteException;
    ResponseDTO fatorDeCarga() throws RemoteException;
    
}
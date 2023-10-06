package db.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;

import Server.IServer;
import db.model.Condutor;
import db.model.Veiculo;
import util.ResponseDTO;
import util.HashTable.Table;
import util.Huffman.HuffTree;

public class VeiculoService extends UnicastRemoteObject implements IServer {

    private Table<Veiculo, Integer> tabela;

    public VeiculoService() throws RemoteException {
        this.tabela = new Table<>();
    }

    @Override
    public void adicionar(String veiculo, HuffTree huffTree) throws RemoteException {
        String decompess = huffTree.Decompress(veiculo);
        String[] resposta = decompess.split(";");
        LocalDate localDate = LocalDate.parse(resposta[4]);
        Veiculo novo = new Veiculo(resposta[0], resposta[1], resposta[2], resposta[3], localDate, new Condutor(resposta[5], resposta[6]));
        this.tabela.Adicionar(novo, Integer.parseInt(novo.getRenavam()));
    }

    @Override
    public ResponseDTO remover(String renavam, HuffTree huffTree) throws RemoteException {
        String res = huffTree.Decompress(renavam);
        this.tabela.Remover(Integer.parseInt(res));
        String compressed = huffTree.Compress("Removido veículo [ " + res + " ]");
        return new ResponseDTO(compressed, huffTree);
    }

    @Override
    public ResponseDTO listar(Integer posicao) throws RemoteException {
        ResponseDTO lista = this.tabela.Print(posicao);
        return lista;
    }

    @Override
    public ResponseDTO buscarMF(String renavam, HuffTree huffTree) throws RemoteException {

        String decompressed = huffTree.Decompress(renavam);

        huffTree.Clear();

        String compressed = huffTree.Compress(this.tabela.BuscarMF(Integer.parseInt(decompressed)).toString());

        return new ResponseDTO(compressed, huffTree);
    }

    @Override
    public ResponseDTO buscarTR(String renavam, HuffTree huffTree) throws RemoteException {

        String decompressed = huffTree.Decompress(renavam);

        huffTree.Clear();

        String compressed = huffTree.Compress(this.tabela.BuscarTR(Integer.parseInt(decompressed)).toString());

        return new ResponseDTO(compressed, huffTree);

    }

    @Override
    public ResponseDTO buscarCF(String renavam, HuffTree huffTree) throws RemoteException {

        String decompressed = huffTree.Decompress(renavam);

        huffTree.Clear();

        String compressed = huffTree.Compress(this.tabela.BuscarCF(Integer.parseInt(decompressed)).toString());

        return new ResponseDTO(compressed, huffTree);

    }

    @Override
    public void atualizar(String novo, String renavam, HuffTree huffTree) throws RemoteException {
        String decompess = huffTree.Decompress(novo);
        String[] resposta = decompess.split(";");
        LocalDate localDate = LocalDate.parse(resposta[4]);
        Veiculo novoVeiculo = new Veiculo(resposta[0], resposta[1], resposta[2], resposta[3], localDate, new Condutor(resposta[5], resposta[6]));
        Veiculo velhoVeiculo = this.tabela.BuscarMF(Integer.parseInt(huffTree.Decompress(renavam))).getValor();
        if(attCampos(novoVeiculo, velhoVeiculo)){
            this.tabela.Atualizar(velhoVeiculo, Integer.parseInt(velhoVeiculo.getRenavam()));
        }
    }

    @Override
    public ResponseDTO quantidadeDeCarros() throws RemoteException {
        HuffTree huffTree = new HuffTree();
        String compressed = huffTree.Compress(String.valueOf( this.tabela.Tamanho() ));
        return new ResponseDTO(compressed, huffTree);
    }

    @Override
    public ResponseDTO fatorDeCarga() throws RemoteException {
        HuffTree huffTree = new HuffTree();
        String compressed = huffTree.Compress(String.valueOf( this.tabela.FatorDeCarga() ));
        return new ResponseDTO(compressed, huffTree);
    }

    private Boolean attCampos(Veiculo novo, Veiculo velho){

        boolean hasAtt = false;

        if(novo.getNome() != null && !novo.getNome().isEmpty() && !novo.getNome().equalsIgnoreCase("*")){
            velho.setNome(novo.getNome());
            hasAtt = true;
        }
        if(novo.getModelo() != null && !novo.getModelo().isEmpty() && !novo.getModelo().equalsIgnoreCase("*")){
            velho.setModelo(novo.getModelo());
            hasAtt = true;
        }
        if(novo.getPlaca() != null && !novo.getPlaca().isEmpty() && !novo.getPlaca().equalsIgnoreCase("*")){
            velho.setPlaca(novo.getPlaca());
            hasAtt = true;
        }
        if(novo.getDataFabricacao() != null && !novo.getDataFabricacao().equals("*")){
            velho.setDataFabricacao(novo.getDataFabricacao());
            hasAtt = true;
        }
        if(novo.getCondutor().getNome() != null && !novo.getCondutor().getNome().isEmpty() && !novo.getCondutor().getNome().equalsIgnoreCase("*")){
            velho.getCondutor().setNome(novo.getCondutor().getNome());
            hasAtt = true;
        }
        if(novo.getCondutor().getCpf() != null && !novo.getCondutor().getCpf().isEmpty() && !novo.getCondutor().getCpf().equalsIgnoreCase("*")){
            velho.getCondutor().setCpf(novo.getCondutor().getCpf());
            hasAtt = true;
        }

        return hasAtt;
        
    }
    
}

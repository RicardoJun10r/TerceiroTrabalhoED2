package util.HashTable;

import java.text.DecimalFormat;

import db.handlers.VeiculoNaoEncontrado;

public class Table<V, K> {
    
    private Node<V, K>[] tabela;

    private int M;

    private Integer size;

    private DecimalFormat decimalFormat;

    public Table(){
        this.M = 5;
        this.tabela = new Node[this.M];
        this.size = 0;
        this.decimalFormat = new DecimalFormat("0.00");
    }

    private Integer Hash(Integer chave){
        return chave % this.M;
    }

    private Integer ProximoPrimo(Integer num){
        for (int i = num + 1; i < num*2; i++) {
            if(isPrime(i)) return i;
        }
        return -1;
    }

    private Boolean isPrime(Integer num){
        int cont = 0;
        for (int i = num; i >= 1; i--) {
            if((num % i) == 0) cont++;
        }

        if(cont == 2) return true;
        else return false;
    }

    public void Adicionar(V valor, K chave){

        Integer posicao = Hash((Integer) chave);

        if(this.tabela[posicao] == null){
            this.tabela[posicao] = new Node<>(valor, chave);
        } else {

            Node<V, K> noHash = this.tabela[posicao];
    
            Node<V, K> no_ant = noHash;
    
            while(noHash != null){
                if(noHash.getValor().equals(valor)) break;
                no_ant = noHash;
                noHash = noHash.getProx();
            }
    
            if(noHash == null){
                noHash = new Node<>(valor, chave);
                no_ant.setProx(noHash);
                noHash.setAnt(no_ant);
            } else return;

        }

        this.size++;

        Double fator = FatorDeCarga();

        System.out.println("Fator de carga = " + this.decimalFormat.format(fator));

        System.out.println("Adicionando " + valor.toString());

        if(fator >= 0.7d){
            Redimensionar();
        }

    }

    private void Redimensionar(){
        
        this.M = ProximoPrimo(this.M*2);
        Node<V, K>[] nova_tabela = new Node[this.M];

        for (int i = 0; i < this.tabela.length; i++) {

            if(this.tabela[i] != null){

                Integer posicao = Hash((Integer) this.tabela[i].getChave());
    
                nova_tabela[posicao] = this.tabela[i];

            }
            
        }

        this.tabela = nova_tabela;

    }

    public Node<V, K> BuscarCF(K chave){
        Integer posicao = Hash((Integer)chave);
        Node<V, K> noHash = this.tabela[posicao];
        System.out.println("Buscando");
        while (noHash != null) {
            if(noHash.getChave().equals(chave)) break;
            noHash = noHash.getProx();
        }
        
        if(noHash != null){
            noHash.setFrequencia(noHash.getFrequencia()+1);
            CF(noHash, posicao);
            return noHash;
        }

        throw new VeiculoNaoEncontrado("Veículo não encontrado !");
    }

    public Node<V, K> BuscarMF(K chave){
        Integer posicao = Hash((Integer)chave);
        Node<V, K> noHash = this.tabela[posicao];
        System.out.println("Buscando");
        while (noHash != null) {
            if(noHash.getChave().equals(chave)) break;
            noHash = noHash.getProx();
        }

        if(noHash != null){
            MF(noHash, posicao);
            return noHash;
        }
        
        throw new VeiculoNaoEncontrado("Veículo não encontrado !");
    }

    public Node<V, K> BuscarTR(K chave){
        Integer posicao = Hash((Integer)chave);
        Node<V, K> noHash = this.tabela[posicao];
        System.out.println("Buscando");
        while (noHash != null) {
            if(noHash.getChave().equals(chave)) break;
            noHash = noHash.getProx();
        }

        if(noHash != null){
            TR(noHash, posicao);
            return noHash;
        }

        throw new VeiculoNaoEncontrado("Veículo não encontrado !");
    }

    private void MF(Node<V, K> no, Integer posicao){
        if(this.tabela[posicao].equals(no)) return;
        else{
            if(ProxNull(no)){
                no.getAnt().setProx( no.getProx() );
            } else{
                no.getAnt().setProx( no.getProx() );
                no.getProx().setAnt( no.getAnt() );
            }
            no.getAnt().setProx( no.getProx() );
            no.setAnt( null );
            no.setProx(this.tabela[posicao]);
            this.tabela[posicao] = no;
        }
    }

    private void TR(Node<V, K> no, Integer posicao){
        if(this.tabela[posicao].equals(no)) return;
        else{
            V temp = no.getAnt().getValor();
            K chave = no.getAnt().getChave();
            int freq = no.getAnt().getFrequencia();
            no.getAnt().setValor( no.getValor() );
            no.getAnt().setFrequencia( no.getFrequencia() );
            no.getAnt().setChave(no.getChave());
            no.setChave( chave );
            no.setValor(temp);
            no.setFrequencia(freq);
        }
    }

    private void CF(Node<V, K> no, Integer posicao){
        if(this.tabela[posicao].equals(no)) return;
        else{
            while(no.getFrequencia() > no.getAnt().getFrequencia()){
                V temp = no.getAnt().getValor();
                Integer freq = no.getAnt().getFrequencia();
                K chave = no.getAnt().getChave();
                no.getAnt().setValor( no.getValor() );
                no.getAnt().setFrequencia( no.getFrequencia() );
                no.getAnt().setChave(no.getChave());
                no.setChave( chave );
                no.setValor( temp );
                no.setFrequencia( freq );
                if(no.getAnt() != null) no = no.getAnt();
            }
            return;
        }
    }

    private Boolean ProxNull(Node<V, K> no){
        if(no.getProx() == null) return true;
        else return false;
    }

    public void Atualizar(V valor, K chave){

        Integer posicao = Hash((Integer) chave);

        Node<V, K> noHash = this.tabela[posicao];

        while(noHash != null){
            if(noHash.getChave().equals(chave)) break;
            noHash = noHash.getProx();
        }

        if(noHash != null){
            System.out.println("Atualizando");
            noHash.setValor(valor);
        } else throw new VeiculoNaoEncontrado("Veículo não encontrado !");

    }

    public Double FatorDeCarga(){
        int total = 0;
        Node<V, K> index;
        for(int i = 0; i < this.M; i++){
            index = this.tabela[i];
            while (index != null) {
                index = index.getProx();
                total++;
            }
        }
        return (double) total / this.M;
    }

    public void Remover(Integer chave){
        Integer posicao = Hash((Integer)chave);
        if(this.tabela[posicao] == null) throw new VeiculoNaoEncontrado("Veículo não encontrado !");
        Node<V, K> noHash = this.tabela[posicao];
        System.out.println("Removendo");
        if(noHash.getChave().equals(chave)){
            this.tabela[posicao] = noHash.getProx();
            this.tabela[posicao].setAnt(null);
            noHash.setProx(null);
            noHash = null;
            this.size--;
            System.out.println("Fator de carga = " + this.decimalFormat.format(FatorDeCarga()));
            return;
        }
        while (noHash != null) {
            if(noHash.getChave().equals(chave)) break;
            noHash = noHash.getProx();
        }

        if(noHash == null) throw new VeiculoNaoEncontrado("Veículo não encontrado !");

        if(ProxNull(noHash)){
            noHash.getAnt().setProx(null);
        } else {
            noHash.getProx().setAnt(noHash.getAnt());
            noHash.getAnt().setProx(noHash.getProx());
        }
        

        noHash.setProx(null);
        noHash.setAnt(null);
        noHash = null;

        this.size--;
        System.out.println("Fator de carga = " + this.decimalFormat.format(FatorDeCarga()));
    }

    public String Print(){
        System.out.println("Listando");
        String res = "";
        Node<V, K> index;
        for(int i = 0; i < this.M; i++){
            index = this.tabela[i];
            res += i;
            while (index != null) {
                res += " -- " + index.getValor();
                index = index.getProx();
            }
            res += "\n";
        }
        return res;
    }

    public Integer Tamanho(){
        return this.size;
    }

}

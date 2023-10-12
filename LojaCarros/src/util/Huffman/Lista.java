package util.Huffman;

import java.io.Serializable;

public class Lista<T> implements Serializable {

        private class No<T> implements Serializable {
            
            private int posicao;
    
            private T valor;
    
            private No<T> prox;
    
            private No<T> ant;
    
            public No(T valor, int posicao){
                this.valor = valor;
                this.prox = null;
                this.ant = null;
                this.posicao = posicao;
            }
    
            public T GetValor(){ return this.valor; }
    
        }
    
        private No<T> cabeca;
    
        private No<T> cauda;
    
        private int size;
    
        public Lista(){
            this.cabeca = null;
            this.cauda = null;
            this.size = 0;
        }
    
        public void Add(T valor){
            No<T> novo = new No<T>(valor, this.size);
            if(Empty()){
                this.cabeca = novo;
                this.cauda = novo;
            } else {
                this.cauda.prox = novo;
                novo.ant = this.cauda;
                this.cauda = novo;
            }
            this.size++;
        }
    
        public void DeleteTail(){
            if(Empty()) return;
            if(this.cabeca == this.cauda){
                this.cabeca = null;
                this.cauda = null;
                this.size = 0;
            } else {
                No<T> remover = this.cauda;
                this.cauda = this.cauda.ant;
                this.cauda.prox = null;
                remover.ant = null;
                remover = null;
                this.size--;
            }
        }
    
        private No<T>[] ListToVector(){
            No<T>[] vetor_lista = new No[this.size];
            No<T> index = this.cabeca;
            while(index != null){
                vetor_lista[index.posicao] = index;
                index = index.prox;
            }
            return vetor_lista;
        }
    
        public T Get(int posicao){
            No<T>[] vetor_lista = ListToVector();
            return vetor_lista[posicao].GetValor();
        }
    
        public void Set(int posicao, T valor){
            No<T>[] vetor_lista = ListToVector();
            vetor_lista[posicao].valor = valor;
        }
        
        public boolean Empty(){
            if(this.cabeca == null && this.cauda == null) return true;
            else return false;
        }
    
        public int Size(){
            return this.size;
        } 
    
    }

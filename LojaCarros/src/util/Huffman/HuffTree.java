package util.Huffman;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class HuffTree implements Serializable {

    private Map<Character, Integer> map;

    private HuffNode[] vetor;

    private HuffNode raiz;

    private String texto;

    public HuffTree(){

        this.map = new HashMap<>();
        this.raiz = null;
    }

    public void Clear(){
        ClearVector();
        ClearTree(this.raiz);
        this.map.clear();
        this.texto = null;
    }

    private void ClearVector(){
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = null;
        }
    }


    private void ClearTree(HuffNode no){
        if(no != null){
            ClearTree(no.getEsq());
            ClearTree(no.getDir());
            no = null;
        }
    }

    private void Freq(){

        int freq = 1;
        for(int i = 0; i < texto.length(); i++){
            if(this.map.containsKey(texto.charAt(i))){
                this.map.replace(texto.charAt(i), this.map.get(texto.charAt(i)), (this.map.get(texto.charAt(i)) + 1));
            } else {
                this.map.put(texto.charAt(i), freq);
            }
        }

        this.vetor = new HuffNode[this.map.size()];

    }

    private void BuildVector(){

        Set<Entry<Character, Integer>> set = this.map.entrySet();

        int cont = 0;
        for (Entry<Character,Integer> entry : set) {
            this.vetor[cont] = new HuffNode(String.valueOf(entry.getKey()), entry.getValue());
            cont++;
        }

    }

    public String Compress(String texto){

        this.texto = texto;
        
        // this.Clear();

        BuildTree();

        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < texto.length(); i++) {
            Search(String.valueOf(texto.charAt(i)), stringBuffer);
        }

        return stringBuffer.toString();
    }

    private void PrintRaiz(){
        System.out.println(this.raiz.getCaractere() + " = " + this.raiz.getFrequencia());
    }

    public String Decompress(String code){
        // PrintRaiz();
        StringBuffer stringBuffer = new StringBuffer();
        Integer contador = 0;
        Decompress(this.raiz, code, contador, stringBuffer);
        return stringBuffer.toString();
    }

    private void Decompress(HuffNode no, String texto, Integer contador, StringBuffer stringBuffer){

        if(no == null) return;
        
        if(isNode(no)){
            stringBuffer.append(no.getCaractere());
            Decompress(this.raiz, texto, contador, stringBuffer);
        }

        if(contador == (texto.length())) return;


        if(String.valueOf(texto.charAt(contador)).equals("0")){
            contador++;
            Decompress(no.getEsq(), texto, contador, stringBuffer);
        } else if(String.valueOf(texto.charAt(contador)).equals("1")){
            contador++;
            Decompress(no.getDir(), texto, contador, stringBuffer);
        }
    }

    private void Search(String caractere, StringBuffer stringBuffer){
        Search(raiz, caractere, stringBuffer);
    }

    private void Search(HuffNode no, String caractere, StringBuffer stringBuffer){
        if(isNode(no)) return;
        if(no.getDir().getCaractere().indexOf(caractere) == -1){
            stringBuffer.append("0");
            Search(no.getEsq(), caractere, stringBuffer);
        } else {
            stringBuffer.append("1");
            Search(no.getDir(), caractere, stringBuffer);
        }
    }

    private void shellSort(){

        int H = 1;

        while(H < this.vetor.length){ H = 3*H+1; }

        while(H > 0){

            for(int i = H; i < this.vetor.length; i++){
                HuffNode aux = this.vetor[i];
                int j = i;
                while(j > H - 1 && this.vetor[j - H].getFrequencia() > aux.getFrequencia()){
                    this.vetor[j] = this.vetor[j - H];
                    j = j - H;
                }
                this.vetor[j] = aux;
            }

            H = (int) Math.floor(H / 3);
        }

    }

    private void BuildTree(){

        Freq();

        BuildVector();

        shellSort();
        
        for (int i = 0; i < vetor.length; i++) {
            Add(this.vetor[i]);
        }

    }

    private void Add(HuffNode valor){
        if(Empty()) {
            this.raiz = valor;
        } else {
            this.raiz = Add(raiz, valor);
        }
    }

    private HuffNode Add(HuffNode no, HuffNode novo){

        StringBuffer stringBuffer = new StringBuffer();
        Integer cont = 0;

        stringBuffer.append(novo.getCaractere());
        stringBuffer.append(no.getCaractere());
        cont += no.getFrequencia() + novo.getFrequencia();
        
        HuffNode novaRaiz = new HuffNode(stringBuffer.toString(), cont);

        novaRaiz.setDir(no);
        novaRaiz.setEsq(novo);

        return novaRaiz;

    }

    public void PrintTree(){
        PrintTree(this.raiz);
    }

    private void PrintTree(HuffNode no){
        if(no != null){
            System.out.println("Caractere = " + no.getCaractere() + " Frequencia = " + no.getFrequencia());
            PrintTree(no.getEsq());
            PrintTree(no.getDir());
        }
    }

    private Boolean isNode(HuffNode no){
        if(no.getEsq() == null && no.getDir() == null) return true;
        else return false;
    }

    private Boolean Empty(){
        if(this.raiz == null) return true;
        else return false;
    }

    public void PrintVector(){
        System.out.println("=================");
        for (int i = 0; i < this.vetor.length; i++) {
            System.out.println(this.vetor[i].getCaractere() + " = " + this.vetor[i].getFrequencia());
        }
        System.out.println("=================");
    }

    public void Print(){
        System.out.println(this.map.toString());
    }

}

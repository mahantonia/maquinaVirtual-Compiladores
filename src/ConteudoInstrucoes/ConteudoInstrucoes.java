package ConteudoInstrucoes;

import java.util.ArrayList;

public class ConteudoInstrucoes {

    ArrayList<String> programaP = new ArrayList<>();
    ArrayList<Integer> dadosM = new ArrayList<>();

    public ConteudoInstrucoes() { }

    public ConteudoInstrucoes(ArrayList<String> programaP, ArrayList<Integer> dadosM){
        this.programaP = programaP;
        this.dadosM = dadosM;
    }

//    public ArrayList<String> getProgramaP() {
//        return programaP;
//    }

//    public void setProgramaP(ArrayList<String> programaP) {
//        this.programaP = programaP;
//    }

    public void addProgramaP(String nome){
        programaP.add(nome);
    }

    public ArrayList<Integer> getDadosM() {
        return dadosM;
    }

//    public void setDadosM(ArrayList<Integer> dadosM) {
//        this.dadosM = dadosM;
//    }

    public void addDadosM(int numero){
        dadosM.add(numero);
    }

    public void setDadosM(int posicao, int conteudo){
        dadosM.set(posicao, conteudo);
    }

    public void removeDadosM(int numero){
        dadosM.remove(numero);
    }
}

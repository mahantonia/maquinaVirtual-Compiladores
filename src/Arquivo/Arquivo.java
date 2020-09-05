package Arquivo;

import Instrucoes.Instrucoes;
import MaquinaVirtual.MaquinaVirtual;
import Matriz.Matriz;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Arquivo extends Matriz {
    Instrucoes instrucao = new Instrucoes();

    ArrayList<String> matrizInstrucoes;
    ArrayList<String> matrizAuxiliar;

    public Arquivo(){ super();}

    public ArrayList<String> getMatrizInstrucoes() { return matrizInstrucoes; }
    public Instrucoes getInstrucao() { return  instrucao; }

    public void selecionaArquivo() throws Exception {
        String caminhoArquivo;

        JFileChooser selecionarArquivo = new JFileChooser();

        selecionarArquivo.setFileFilter(null);
        int returnVal = selecionarArquivo.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("Voce escolheu o arquivo: "+ selecionarArquivo.getSelectedFile().getName());
            caminhoArquivo = selecionarArquivo.getSelectedFile().getAbsolutePath();

            if(!caminhoArquivo.equals("null")){
                System.out.println("Arquivo Selecionado com sucesso");
                leArquivo(caminhoArquivo);
            }
        }
    }

    private void leArquivo(String caminho) throws Exception {
        matrizInstrucoes = new ArrayList<>();

        BufferedReader conteudo = new BufferedReader(new FileReader(caminho));
        String linha;
        while (conteudo.ready()) {
            linha = conteudo.readLine();
            matrizInstrucoes.add(linha);
        }
        conteudo.close();
        matrizSecundaria(matrizInstrucoes);
    }

    private void matrizSecundaria(ArrayList<String> matrizInstrucoes) {
        matrizAuxiliar = new ArrayList<>();
        String[] separaConteudo;

        for(int i = 0; i < matrizInstrucoes.size(); i++){
            separaConteudo = matrizInstrucoes.get(i).split(" ");
            if(separaConteudo.length == 2){
                if(separaConteudo[1].equals("NULL")){
                    String numeroPosicao = Integer.toString(i);
                    String posicaoNomeLinha = separaConteudo[0] + " " + separaConteudo[1] + " " + numeroPosicao;
                    matrizAuxiliar.add(posicaoNomeLinha);
                }
            }
        }
        geraMatrizFinal();
    }

    private void geraMatrizFinal() {
        for(int i = 0; i < matrizInstrucoes.size(); i++){
            String[] palavra = matrizInstrucoes.get(i).split(" ");
            switch (palavra.length) {
                case 1:
                    super.addMatriz(palavra[0]);
                    break;
                case 2:
                    if (palavra[1].equals("NULL")) {
                        setPosicaoLinha(palavra[0], palavra[1]);
                    } else {
                        if((palavra[0].equals("CALL")) || (palavra[0].equals("JMP")) || (palavra[0].equals("JMPF"))){
                            setPosicaoLinha(palavra[1], palavra[0]);
                        }else{
                            String instrucao = palavra[0] + " " + palavra[1];
                            super.addMatriz(instrucao);
                        }
                    }
                    break;
                case 3:
                    String instrucao = palavra[0] + " " + palavra[1] + " " + palavra[2];
                    super.addMatriz(instrucao);
                    break;
            }
        }
    }

    private void setPosicaoLinha(String palavraPosicao, String palavra) {
        String concatenacao;

        for(int i = 0; i < matrizAuxiliar.size(); i++){
            String[] posicao = matrizAuxiliar.get(i).split(" ");
            if(palavraPosicao.equals(posicao[0])){
                if(palavra.equals("NULL")){
                    concatenacao = posicao[2] + " " + palavra;
                    super.addMatriz(concatenacao);
                }else{
                    concatenacao =  palavra + " " + posicao[2];
                    super.addMatriz(concatenacao);
                }
            }
        }
    }

    public int separaConteudoLinha() {
        String conteudo;

        conteudo = super.getPosicaoMatriz(instrucao.getI());
        String[] conteudoLinhaSeparado = conteudo.split(" ");

        switch(conteudoLinhaSeparado.length){
            case 1:
                if(conteudoLinhaSeparado[0].equals("RD")){
                    return 1;
                }
                if(conteudoLinhaSeparado[0].equals("PRN")){
                    return 2;
                }

                instrucao.intrucoesTipo1(conteudoLinhaSeparado[0]);
                System.out.println(conteudoLinhaSeparado[0]);
                break;
            case 2:
                if(conteudoLinhaSeparado[1].equals("NULL")){
                    instrucao.intrucoesTipo1(conteudoLinhaSeparado[1]);
                    System.out.println(conteudoLinhaSeparado[1]);
                }else{
                    instrucao.intrucoesTipo2(conteudoLinhaSeparado[0], Integer.parseInt(conteudoLinhaSeparado[1]));
                    System.out.println(conteudoLinhaSeparado[0]);
                }
                break;
            case 3:
                instrucao.intrucoesTipo3(conteudoLinhaSeparado[0], Integer.parseInt(conteudoLinhaSeparado[1]), Integer.parseInt(conteudoLinhaSeparado[2]));
                System.out.println(conteudoLinhaSeparado[0]);
                break;
        }
        return 0;
    }

    public void leituraInserida(int numero) {
        instrucao.intrucoesTipo2("RD", numero);
    }
}

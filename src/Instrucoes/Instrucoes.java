package Instrucoes;

import ConteudoInstrucoes.ConteudoInstrucoes;
import java.util.ArrayList;

public class Instrucoes extends ConteudoInstrucoes {
    int s, i = 0, k, valor1, valor2, resultado;

    public Instrucoes() { super(); }
    public Instrucoes(ArrayList<String> programaP, ArrayList<Integer> dadosM){
        super(programaP, dadosM);
    }

    public void intrucoesTipo1(String opcao){
        switch (opcao) {
            case "START":
                start(opcao);
                break;
            case "ADD":
                adicionar(opcao);
                break;
            case "SUB":
                subtrair(opcao);
                break;
            case "MULT":
                multiplicacao(opcao);
                break;
            case "DIVI":
                divisao(opcao);
                break;
            case "INV":
                inverteSinal(opcao);
                break;
            case "AND":
                conjuncao(opcao);
                break;
            case "OR":
                disjuncao(opcao);
                break;
            case "NEG":
                negacao(opcao);
                break;
            case "CME":
                comparaMenor(opcao);
                break;
            case "CMA":
                comparaMaior(opcao);
                break;
            case "CEQ":
                comparaIgual(opcao);
                break;
            case "CDIF":
                comparaDesigual(opcao);
                break;
            case "CMEQ":
                comparaMenorIgual(opcao);
                break;
            case "CMAQ":
                comparaMaiorIgual(opcao);
                break;
            case "HLT":
                para(opcao);
                break;
            case "NULL":
                nada(opcao);
                break;
            case "PRN":
                impressao(opcao);
                break;
            case "RETURN":
                retornoProcedimento(opcao);
                break;
            default:
                System.out.println("Instrucao nao existe");
                break;
        }
    }

    public void intrucoesTipo2(String opcao, int registrador1) {
        switch (opcao) {
            case "LDC":
                carregaConstante(opcao, registrador1);
                break;
            case "LDV":
                carregaValor(opcao, registrador1);
                break;
            case "STR":
                armazenaValor(opcao, registrador1);
                break;
            case "JMP":
                desviaSempre(opcao, registrador1);
                break;
            case "JMPF":
                desviaSeFalso(opcao, registrador1);
                break;
            case "CALL":
                chamarProcedimentoOuFuncao(opcao, registrador1);
                break;
            case "RD":
                leitura(opcao, registrador1);
                break;
            default:
                System.out.println("Instrucao nao existe");
                break;
        }
    }

    public void intrucoesTipo3(String opcao, int registrador1, int registrador2){
        switch (opcao) {
            case "ALLOC":
                alocarMemoria(opcao, registrador1, registrador2);
                break;
            case "DALLOC":
                desalocarMemoria(opcao, registrador1, registrador2);
                break;
            default:
                System.out.println("Instrucao nao existe");
                break;
        }
    }


    private void desalocarMemoria(String instrucao, int registrador1, int registrador2){

        for(k = registrador2 - 1; k >= 0; k--){
            resultado = registrador1 + k;
            dalloc(resultado);
         }

        i += 1;
        super.addProgramaP(instrucao);
    }

    private void alocarMemoria(String instrucao, int registrador1, int registrador2){

        for(k = 0; k < registrador2; k++){
            resultado = registrador1 + k;
            alloc(resultado);
        }

        i += 1;
        super.addProgramaP(instrucao);
    }

    private void alloc(int numero){
        s += 1;
        super.addDadosM(numero);
        valor1 = retornaValor(numero);
        adicionarPosicaoPilha(s,valor1);
        adicionarPosicaoPilha(numero, numero);
    }

    private void dalloc(int numero) {
        valor1 = retornaValor(s);
        adicionarPosicaoPilha(numero, valor1);
        removePilha(s);
    }

    /*****************************************************************************************************************/
    private void leitura(String instrucao, int registrador1) {
        adicionaPilha(registrador1);

        i += 1;
        super.addProgramaP(instrucao);
    }

    private void chamarProcedimentoOuFuncao(String instrucao, int registrador1) {
       adicionaPilha(i+1);

       i = registrador1;

        super.addProgramaP(instrucao);
    }

    private void desviaSeFalso(String instrucao, int registrador1) {
        valor1 = retornaValor(s);

        if(valor1 == 0) {
            i = registrador1;
        } else {
            i += 1;
        }

        removePilha(s);

        super.addProgramaP(instrucao);
    }

    private void desviaSempre(String instrucao, int registrador1) {
        i = registrador1;

        super.addProgramaP(instrucao);
    }

    private void armazenaValor(String instrucao, int registrador1) {
        valor1 = retornaValor(s);

        adicionarPosicaoPilha(registrador1, valor1);
        removePilha(s);

        i += 1;
        super.addProgramaP(instrucao);
    }
    /******************************************************************************************************************/

    private void retornoProcedimento(String instrucao) {
      resultado = retornaValor(s);

      i = resultado;

      removePilha(s);

        super.addProgramaP(instrucao);
    }

    public int impressao(String instrucao) {
        valor1 = retornaValor(s);

        System.out.println("PRINTAR: " + valor1);

        removePilha(s);

        i +=1;
        super.addProgramaP(instrucao);
        return  valor1;
    }

//    private void leitura(String instrucao, int registrador1) {
//        adicionaPilha(registrador1);
//
//        i += 1;
//        super.addProgramaP(instrucao);
//    }

    private void nada(String instrucao) {
        i += 1;

        super.addProgramaP(instrucao);
    }

    private void para(String instrucao) {
        i  = -1;
        super.addProgramaP(instrucao);
    }

    private void comparaMaiorIgual(String instrucao) {
        valor1 = retornaValor(s);
        valor2 = retornaValor(s-1);

        if(valor2 >= valor1) {
            adicionarPosicaoPilha(s-1, 1);
            removePilha(s);
        } else {
            adicionarPosicaoPilha(s-1, 0);
            removePilha(s);
        }

        i += 1;
        super.addProgramaP(instrucao);
    }

    private void comparaMenorIgual(String instrucao) {
        valor1 = retornaValor(s);
        valor2 = retornaValor(s-1);

        if(valor2 <= valor1) {
            adicionarPosicaoPilha(s-1, 1);
            removePilha(s);
        } else {
            adicionarPosicaoPilha(s-1, 0);
            removePilha(s);
        }

        i += 1;
        super.addProgramaP(instrucao);
    }

    private void comparaDesigual(String instrucao) {
        valor1 = retornaValor(s);
        valor2 = retornaValor(s-1);

        if(valor2 != valor1) {
            adicionarPosicaoPilha(s-1, 1);
            removePilha(s);
        } else {
            adicionarPosicaoPilha(s-1, 0);
            removePilha(s);
        }

        i += 1;
        super.addProgramaP(instrucao);
    }

    private void comparaIgual(String instrucao) {
        valor1 = retornaValor(s);
        valor2 = retornaValor(s-1);

        if(valor2 == valor1) {
            adicionarPosicaoPilha(s-1, 1);
            removePilha(s);
        } else {
            adicionarPosicaoPilha(s-1, 0);
            removePilha(s);
        }

        i += 1;
        super.addProgramaP(instrucao);
    }

    private void comparaMaior(String instrucao) {
        valor1 = retornaValor(s);
        valor2 = retornaValor(s-1);

        if(valor2 > valor1) {
            adicionarPosicaoPilha(s-1, 1);
            removePilha(s);
        } else {
            adicionarPosicaoPilha(s-1, 0);
            removePilha(s);
        }

        i += 1;
        super.addProgramaP(instrucao);
    }

    private void comparaMenor(String instrucao) {
        valor1 = retornaValor(s);
        valor2 = retornaValor(s-1);

        if(valor2 < valor1) {
            adicionarPosicaoPilha(s-1, 1);
            removePilha(s);
        } else {
            adicionarPosicaoPilha(s-1, 0);
            removePilha(s);
        }

        i += 1;
        super.addProgramaP(instrucao);
    }

    private void negacao(String instrucao) {
        valor1 = retornaValor(s);

        resultado = 1 - valor1;

        adicionarPosicaoPilha(s, resultado);

        i += 1;
        super.addProgramaP(instrucao);
    }

    private void disjuncao(String instrucao) {
        valor1 = retornaValor(s);
        valor2 = retornaValor(s-1);

        if((valor1 == 1) || (valor2 == 1)) {
            adicionarPosicaoPilha(s-1, 1);
            removePilha(s);
        } else{
            adicionarPosicaoPilha(s-1, 0);
            removePilha(s);
        }

        i += 1;
        super.addProgramaP(instrucao);
    }

    private void conjuncao(String instrucao) {
        valor1 = retornaValor(s);
        valor2 = retornaValor(s-1);

        if((valor2 == 1) && (valor1 == 1)) {
            adicionarPosicaoPilha(s-1, 1);
            removePilha(s);
        } else{
            adicionarPosicaoPilha(s-1, 0);
            removePilha(s);
        }

        i += 1;
        super.addProgramaP(instrucao);
    }

    private void inverteSinal(String instrucao) {
        valor1 = retornaValor(s);

        resultado = valor1 * -1;

        adicionarPosicaoPilha(s, resultado);

        i += 1;
        super.addProgramaP(instrucao);
    }

    private void divisao(String instrucao) {
        valor1 = retornaValor(s);
        valor2 = retornaValor(s-1);

        resultado = valor2 / valor1;

        adicionarPosicaoPilha(s-1, resultado);
        removePilha(s);

        i += 1;
        super.addProgramaP(instrucao);
    }

    private void multiplicacao(String instrucao) {
        valor1 = retornaValor(s);
        valor2 = retornaValor(s-1);

        resultado = valor2 * valor1;

        adicionarPosicaoPilha(s-1, resultado);
        removePilha(s);

        i += 1;
        super.addProgramaP(instrucao);
    }

    private void subtrair(String instrucao) {
        valor1 = retornaValor(s);
        valor2 = retornaValor(s-1);

        resultado = valor2 - valor1;

        adicionarPosicaoPilha(s-1, resultado);
        removePilha(s);

        i += 1;
        super.addProgramaP(instrucao);
    }

    private void adicionar(String instrucao) {
        valor1 = retornaValor(s);
        valor2 = retornaValor(s-1);

        resultado = valor2 + valor1;

        adicionarPosicaoPilha(s-1, resultado);
        removePilha(s);

        i += 1;
        super.addProgramaP(instrucao);
    }

    private void carregaValor(String instrucao, int posicao) {
        int conteudo = retornaValor(posicao);

        adicionaPilha(conteudo);

        i += 1;
        super.addProgramaP(instrucao);
    }

    private void carregaConstante(String instrucao, int constante) {
        adicionaPilha(constante);

        i += 1;
        super.addProgramaP(instrucao);
    }

    private void start(String instrucao) {
        s = -1;
        i += 1;
        super.addProgramaP(instrucao);
    }

    /***************************************************************************************************************/
    public int retornaValor(int posicao){
        return super.getDadosM().get(posicao);
    }

    public void adicionaPilha(int conteudo){
        s += 1;
        super.addDadosM(conteudo);
    }

    public void adicionarPosicaoPilha(int posicao, int conteudo){
        super.setDadosM(posicao, conteudo);
    }

    public void removePilha(int posicao){
        super.removeDadosM(posicao);
        s -= 1;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

}
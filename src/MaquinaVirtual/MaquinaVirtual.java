package MaquinaVirtual;

import ConteudoInstrucoes.ConteudoInstrucoes;
import Instrucoes.Instrucoes;
import Interface.Interface;

public class MaquinaVirtual {
    String codigo;

    Interface interfaceGrafica = new Interface();
    Instrucoes a = new Instrucoes();

    public void start(){
        codigo = "continua";
        interfaceGrafica.start();
    }

}

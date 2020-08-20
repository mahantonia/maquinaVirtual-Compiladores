package Interface;

import Arquivo.Arquivo;

import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Interface extends JFrame{

    private JPanel painelInstrucao;
    private JPanel painelDados;
    private JPanel painelSaida;
    private JPanel painelEntrada;
    private JButton botaoExecutar;
    private JButton botaoDeburar;
    private JButton botaoArquivo;

    Arquivo arquivo = new Arquivo();

    public void start() {
        painelInstrucao = new JPanel();
        painelDados = new JPanel();
        painelSaida = new JPanel();
        painelEntrada = new JPanel();
        botaoExecutar = new JButton("Executar");
        botaoDeburar = new JButton("Depurar");
        botaoArquivo = new JButton("Arquivo");
        criaComponente();
    }
    public void iniciarTela(){
        this.setSize(1200,800);
        this.setTitle("Maquina Virtual");
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        //Ajusta automaticamente o tamanho da janela, alternativa ao setSize()
//        pack();
//        setLocationRelativeTo(null);

    }

    public void criaComponente(){
        painelInstrucao.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Instrucoes"));
        painelInstrucao.setBounds(10,10,500,740);
        this.add(painelInstrucao);

        painelDados.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Dados"));
        painelDados.setBounds(540,10,300,630);
        this.add(painelDados);

        painelSaida.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Saida"));
        painelSaida.setBounds(870,10,300,300);
        this.add(painelSaida);

        painelEntrada.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Entrada de Dados"));
        painelEntrada.setBounds(870,340,300,300);
        this.add(painelEntrada);

        botaoExecutar.setBounds(540, 695, 200, 50);
        botaoExecutar.setBackground(Color.GRAY);

        this.add(botaoExecutar);

        botaoDeburar.setBounds(752, 695, 200, 50);
        botaoDeburar.setBackground(Color.GRAY);

        this.add(botaoDeburar);

        botaoArquivo.setBounds(965, 695, 200, 50);
        botaoArquivo.setBackground(Color.GRAY);

        this.add(botaoArquivo);

        iniciarTela();
        criaEventoClick();
    }

    public void criaEventoClick(){
        botaoExecutar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                executaArquivo();
            }
        });

        botaoDeburar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("clicou no botao Deburar");
            }
        });

        botaoArquivo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    abreArquivo();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    public void abreArquivo() throws Exception {
        arquivo.selecionaArquivo();
    }

    public void executaArquivo() {
        arquivo.separaConteudoLinha();
    }
}

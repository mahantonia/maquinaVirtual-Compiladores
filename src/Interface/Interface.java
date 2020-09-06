package Interface;

import Arquivo.Arquivo;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private JTable tabela;
    private JTable tabelaDados;
    private JScrollPane rolagemTabela;
    private JScrollPane rolagemTabelaDados;
    private JScrollPane rolagemEntrada;
    private JScrollPane rolagemSaida;
    private DefaultTableModel tabelaModelo;
    private DefaultTableModel tabelaModeloDados;
    private JList entrada;
    private JList saida;

    DefaultListModel<String> modeloEntrada = new DefaultListModel<>();
    DefaultListModel<String> modeloSaida = new DefaultListModel<>();

    final String[] colunas = new String[]{ "Debug", "Linha", "Intrucao", "Regis1", "Regis2"};
    final String[] colunaDados = new String[]{"Endereco", "Valor"};
    Object[] linhaConteudo = new Object[5];
    Object[] linhaDados = new Object[2];

    Arquivo arquivo = new Arquivo();

    public void start() {
        painelInstrucao = new JPanel();
        painelDados = new JPanel();
        painelSaida = new JPanel();
        painelEntrada = new JPanel();
        botaoExecutar = new JButton("Executar");
        botaoDeburar = new JButton("Depurar");
        botaoArquivo = new JButton("Arquivo");
        iniciarTela();
        criaPainel();
        criaBotao();
        criaTabelaInstrucao();
        criaTabelaDados();
        criarTabelaSaida();
        criarTabelaEntrada();
    }
    public void iniciarTela(){
        this.setSize(1200,800);
        this.setTitle("Maquina Virtual");
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void criaPainel(){
        painelInstrucao.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Instrucoes"));
        setPosicaoPainel(painelInstrucao, 10, 10, 500, 740);

        painelDados.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Dados"));
        setPosicaoPainel(painelDados, 540, 10, 300, 630);

        painelSaida.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Saida"));
        setPosicaoPainel(painelSaida, 870, 10, 300, 300);

        painelEntrada.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Entrada de Dados"));
        setPosicaoPainel(painelEntrada, 870, 340, 300, 300);
    }

    public void criaBotao() {
        setPosicaoBotao(botaoExecutar, 540, 695, 200, 50);

        setPosicaoBotao(botaoDeburar, 752, 695, 200, 50);

        setPosicaoBotao(botaoArquivo, 965, 695, 200, 50);

        criaEventoClick();
    }

    public void setPosicaoPainel(JPanel name, int x, int y, int width, int height) {
        name.setBounds(x, y, width, height);
        this.add(name);
    }

    public void setPosicaoBotao(JButton button, int x, int y, int width, int height) {
        button.setBounds(x, y, width, height);
        button.setBackground(Color.GRAY);
        this.add(button);
    }

    public void criaTabelaInstrucao(){
        tabelaModelo = new DefaultTableModel(colunas, 0);
        tabelaModelo.setNumRows(0);
        tabela = new JTable(tabelaModelo);
        rolagemTabela = new JScrollPane(tabela);

        tabela.getColumnModel().getColumn(0).setPreferredWidth(2);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(2);

        rolagemTabela.setBounds(12,30, 495,718);

        add(rolagemTabela);
    }

    public void criaTabelaDados(){
        tabelaModeloDados = new DefaultTableModel(colunaDados, 0);
        tabelaModeloDados.setNumRows(0);
        tabelaDados = new JTable(tabelaModeloDados);
        rolagemTabelaDados = new JScrollPane(tabelaDados);

        rolagemTabelaDados.setBounds(542,32, 296,620);

        add(rolagemTabelaDados);
    }

    public void criarTabelaEntrada(){
        entrada = new JList<>();
        entrada.setModel(modeloEntrada);

        rolagemEntrada = new JScrollPane(entrada);
        rolagemEntrada.setBounds(872,360,296,276);
        add(rolagemEntrada);
    }

    public void criarTabelaSaida(){
        saida = new JList<>();
        saida.setModel(modeloSaida);

        rolagemSaida = new JScrollPane(saida);
        rolagemSaida.setBounds(872,30,296,278);
        add(rolagemSaida);
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
                debugArquivoInstrucao();
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
        criaTabelaInstrucao();
        criaTabelaDados();
        modeloEntrada.removeAllElements();
        modeloSaida.removeAllElements();
        arquivo.selecionaArquivo();
        adicionaArquivoInterface();
    }

    public void executaArquivo() {
        while (arquivo.getInstrucao().getI() != -1) {
            int retornoArquivo = arquivo.separaConteudoLinha();
            if(retornoArquivo == 1){
                String valor = recebeValor();
                int numero = Integer.parseInt(valor);
                System.out.println("Valor: " + numero);
                arquivo.leituraInserida(numero);
                modeloEntrada.addElement(valor);
            }
            if(retornoArquivo == 2){
                int numero = arquivo.getInstrucao().impressao("PRN");
                String valor = Integer.toString(numero);
                modeloSaida.addElement(valor);
            }
           insereTabelaDados();
        }
    }

    public void debugArquivoInstrucao() {
        int valor1 = arquivo.getInstrucao().getI();

        tabela.clearSelection();
        tabela.changeSelection(valor1,2, false, false);
        if(valor1 != -1){
            int retornoArquivo = arquivo.separaConteudoLinha();
            if(retornoArquivo == 1){
                String valor = recebeValor();
                int numero = Integer.parseInt(valor);
                System.out.println("Valor: " + numero);
                arquivo.leituraInserida(numero);
                modeloEntrada.addElement(valor);
            }
            if(retornoArquivo == 2){
                int numero = arquivo.getInstrucao().impressao("PRN");
                String valor = Integer.toString(numero);
                modeloSaida.addElement(valor);
            }
            insereTabelaDados();
        }
    }

    private void adicionaArquivoInterface() {
        for(int i = 0; i < arquivo.getMatrizInstrucoes().size(); i++) {
            String[] palavra = arquivo.getMatrizInstrucoes().get(i).split(" ");
            switch (palavra.length) {
                case 1:
                    linhaConteudo[0] = "";
                    linhaConteudo[1] = "";
                    linhaConteudo[2] = palavra[0];
                    linhaConteudo[3] = "";
                    linhaConteudo[4] = "";
                    break;
                case 2:
                    if(palavra[1].equals("NULL")){
                        linhaConteudo[0] = "";
                        linhaConteudo[1] = palavra[0];
                        linhaConteudo[2] = palavra[1];
                        linhaConteudo[3] = "";
                        linhaConteudo[4] = "";
                    }else {
                        linhaConteudo[0] = "";
                        linhaConteudo[1] = "";
                        linhaConteudo[2] = palavra[0];
                        linhaConteudo[3] = palavra[1];
                        linhaConteudo[4] = "";
                    }
                    break;
                case 3:
                    linhaConteudo[0] = "";
                    linhaConteudo[1] = "";
                    linhaConteudo[2] = palavra[0];
                    linhaConteudo[3] = palavra[1];
                    linhaConteudo[4] = palavra[2];
                    break;
            }
            tabelaModelo.addRow(linhaConteudo);
        }
    }

    public void insereTabelaDados(){
        tabelaModeloDados.setNumRows(0);
        for(int i  = 0; i < arquivo.getInstrucao().getDadosM().size(); i++){
            String valor = Integer.toString(arquivo.getInstrucao().getDadosM().get(i));
            linhaDados[0] = i;
            linhaDados[1] = valor;
            tabelaModeloDados.addRow(linhaDados);
        }
    }

    public String recebeValor(){
        return JOptionPane.showInputDialog("Digite um valor: ");
    }
}

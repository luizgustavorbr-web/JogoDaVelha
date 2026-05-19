package VIEW;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JogoDaVelhaGUI extends JFrame implements ActionListener {
    //atributos
    JButton[][] botoes = new JButton[3][3];
    JButton botaoReiniciar;
    JLabel labelPlacar;
    JogoDaVelha jogo = new JogoDaVelha();
    private int idJogador1;
    private int idJogador2;
    private Jogador jogador1;
    private Jogador jogador2;
    private Jogador jogadorAtual;
    private boolean jogoAtivo = true;
    private int vitoriasJogador1 = 0;
    private int vitoriasJogador2 = 0; 
    
    public JogoDaVelhaGUI(int idJogador1, int idJogador2, String nomeJogador1, String nomeJogador2) {
    this.idJogador1 = idJogador1;
    this.idJogador2 = idJogador2;
    this.jogadorAtual = jogador1;

    // Inicialização da interface aqui...
}

    //construtor
    public JogoDaVelhaGUI() {
        definirNomesDosJogadores();

        setTitle("Jogo da Velha");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
                
        JPanel painelJogo = new JPanel(new GridLayout(3, 3));
        inicializarBotoes(painelJogo);
        add(painelJogo, BorderLayout.CENTER);

        botaoReiniciar = new JButton("Reiniciar Jogo");
        botaoReiniciar.addActionListener(e -> reiniciarJogo());
        botaoReiniciar.setFocusPainted(false);

        labelPlacar = new JLabel();
        atualizarPlacar();
        labelPlacar.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel painelInferior = new JPanel(new BorderLayout());
        painelInferior.add(botaoReiniciar, BorderLayout.SOUTH);
        painelInferior.add(labelPlacar, BorderLayout.NORTH);

        add(painelInferior, BorderLayout.SOUTH);

        setVisible(true);
    }
//pede ao usuario os nomes dos jogadores com JOptionPane
    
    private void definirNomesDosJogadores() {
        String nome1 = JOptionPane.showInputDialog(this, "Digite o nome do Jogador 1 (X):");
        String nome2 = JOptionPane.showInputDialog(this, "Digite o nome do Jogador 2 (O):");

        if (nome1 == null || nome1.trim().isEmpty()) nome1 = "Jogador 1";
        if (nome2 == null || nome2.trim().isEmpty()) nome2 = "Jogador 2";

        jogador1 = new Jogador(nome1, 'X');
        jogador2 = new Jogador(nome2, 'O');
        jogadorAtual = jogador1;
    }
//criando os botoões do tabuleiro e adicionando ao painel
    private void inicializarBotoes(JPanel painel) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j] = new JButton("");
                botoes[i][j].setFont(new Font("Arial", Font.BOLD, 60));
                botoes[i][j].setFocusPainted(false);
                botoes[i][j].addActionListener(this);
                painel.add(botoes[i][j]);
            }
        }
    }
// caso o jogo acabe 
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!jogoAtivo) return;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (e.getSource() == botoes[i][j] && botoes[i][j].getText().equals("")) {
                    if (jogo.fazerJogada(i, j, jogadorAtual.getSimbolo())) {
                        botoes[i][j].setText(String.valueOf(jogadorAtual.getSimbolo()));

                        if (jogo.verificarVencedor(jogadorAtual.getSimbolo())) {
                            JOptionPane.showMessageDialog(this, jogadorAtual.getNome() + " venceu!");
                            jogoAtivo = false;
                            atualizarVitorias();
                        } else if (jogo.tabuleiroCompleto()) {
                            JOptionPane.showMessageDialog(this, "Empate!");
                            jogoAtivo = false;
                        } else {
                            trocarJogador();
                        }
                    }
                }
            }
        }
    }
//criando método trocarJogador
    private void trocarJogador() {
        jogadorAtual = (jogadorAtual == jogador1) ? jogador2 : jogador1;
    }
//criando método atualizarVitorias
    private void atualizarVitorias() {
        if (jogadorAtual == jogador1) {
            vitoriasJogador1++;
        } else {
            vitoriasJogador2++;
        }
        atualizarPlacar();
    }
//criando método atualizarPlacar
    private void atualizarPlacar() {
        labelPlacar.setText(jogador1.getNome() + " (X): " + vitoriasJogador1 + "  |  " +
                            jogador2.getNome() + " (O): " + vitoriasJogador2);
    }
//criando método reiniciarJogo
    private void reiniciarJogo() {
        jogo.reiniciarJogo();
        jogoAtivo = true;
        jogadorAtual = jogador1;

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                botoes[i][j].setText("");
    }
//chama o swing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JogoDaVelhaGUI());
    }
}
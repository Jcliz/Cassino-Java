import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Entidades.*;
import Jogos.*;
import Utilidades.SaldoInvalidoException;
import Utilidades.ValorInvalidoException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class Cassino {
    public static void main(String[] args) throws IOException, ClassNotFoundException, ValorInvalidoException,
            SaldoInvalidoException{
        Scanner leitor = new Scanner(System.in);

        //Leitor de TXT
        try {
            FileReader arquivo = new FileReader("C:/Users/gabri/OneDrive/Documentos/GitHub/POO/Cassino-Java/code/src/Utilidades/dados.txt");
            BufferedReader buffer = new BufferedReader(arquivo);
            String str;
            while ((str = buffer.readLine()) != null) {
                System.out.println(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("""
                -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-
                      S E J A  -  B E M - V I N D O (a) !
                -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-
                
                Para começarmos, digite seu nome:""");

        String nomeUsuario = leitor.nextLine();

        System.out.println("Agora, sua data de nascimento (dd-mm-aaaa):");
        String dataNascUsuario = leitor.nextLine();

        Jogador usuarioJogador = new Jogador(nomeUsuario, dataNascUsuario, 0);
        Serializacao.salvarPessoa(usuarioJogador);
        Jogador j = Serializacao.carregarJogador();
        System.out.println(j);

        Dealer dealer = new Dealer();

        Jogo[] jogos = new Jogo[] {
                new CacaNiquel(0, false, 1),
                new Roleta(0, false),
                new Blackjack(usuarioJogador, dealer)
        };

        boolean acesso = true;

        System.out.println("Tudo pronto! Vamos para a diversão." + "\n");
        while (acesso) {
            System.out.println("""
                    Opções:
                    
                    [1] - Depósito de créditos
                    [2] - Bingo
                    [3] - Caça níquel
                    [4] - Roleta
                    [5] - BlackJack
                    [6] - Imprimir resultados
                    
                    [0] - Fechar o programa
                    """);

            switch (leitor.nextInt()) {
                case 1:
                    System.out.println("""
                            -_-_-_-_-_- D E P Ó S I T O -_-_-_-_-_-
                            
                            Valor para o depósito:""");

                    double saldoDeposito = leitor.nextDouble();

                    if (saldoDeposito > 0) {
                        System.out.println("Realizando operação de depósito....");
                        usuarioJogador.setCreditos(saldoDeposito);

                        System.out.println("Créditos depositados!" + "\n" +
                                "Total: " + usuarioJogador.getCreditos() + "\n");
                    } else {
                        throw new SaldoInvalidoException("Valor inválido, tente novamente.");
                    }
                    break;

                case 2:
                    System.out.println("Quantos jogadores?: ");
                    int numJogadores = leitor.nextInt();

                    leitor.nextLine();

                    for (int i = 1; i <= numJogadores; i++) {
                        System.out.println("Nome do jogador " + i + ":");
                        String nome = leitor.nextLine();

                        System.out.println("Valor apostado pelo jogador " + i + ":");
                        double aposta = leitor.nextDouble();
                        System.out.println("\n");
                        leitor.nextLine();

                        Cartela cartela = new Cartela(false, nome, aposta);

                        Bingo bingo = new Bingo(false);
                        bingo.adicionarJogadorBingo(cartela);
                        cartela.imprimirCartela();

                        bingo.jogar(leitor);
                    }
                    break;

                case 3:
                    System.out.println("-_-_-_-_-_- C A Ç A  N Í Q U E I S -_-_-_-_-_-");
                    jogos[0].jogar(leitor);

                case 4:
                    jogos[1].jogar(leitor);

                case 5:


                case 6:


                case 0:


                default:
            }
        }
    }
}

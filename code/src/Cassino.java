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
import java.util.*;


public class Cassino {
    public static void main(String[] args) throws IOException, ClassNotFoundException, ValorInvalidoException {
        Scanner leitor = new Scanner(System.in);

        //Leitor de TXT
        try {
            FileReader arquivo = new FileReader("/Utilidades/dados.txt");
            BufferedReader buffer = new BufferedReader(arquivo);
            String str;
            while ((str = buffer.readLine()) != null) {
                System.out.println(str);
            }
        } catch (FileNotFoundException e) {
            System.out.println("\n" + "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.println(e.getMessage());
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -" + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("""
                -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-
                      S E J A  -  B E M - V I N D O (a) !
                -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-
                """);

        try {
            System.out.println("Primeiramente, nos informe seu nome:");
            String nomeUsuario = leitor.nextLine();

            System.out.println("Agora, a sua idade:");
            int idadeUsuario = leitor.nextInt();

            Jogador usuarioJogador = new Jogador(nomeUsuario, idadeUsuario, 0);
            Serializacao.salvarPessoa(usuarioJogador);
            Jogador j = Serializacao.carregarJogador();
            System.out.println(j);

            Dealer dealer = new Dealer();

            Jogo[] jogos = new Jogo[]{
                    new CacaNiquel(1, false, 1),
                    new Roleta(1, false),
                    new Blackjack(usuarioJogador, dealer)
            };
            Bingo bingo = new Bingo(false);

            boolean acesso = true;

            System.out.println("Tudo pronto " + usuarioJogador.getNome() + "! Vamos para a diversão." + "\n");
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
                try {
                    switch (leitor.nextInt()) {
                        case 1:
                            System.out.println("""
                                    -_-_-_-_-_- D E P Ó S I T O -_-_-_-_-_-
                                    
                                    Valor para o depósito:""");

                            double saldoDeposito = leitor.nextDouble();

                            try {
                                if (saldoDeposito > 0) {
                                    System.out.println("Realizando operação de depósito....");
                                    usuarioJogador.depositarCreditos(saldoDeposito);

                                    System.out.println("Créditos depositados!" + "\n" +
                                            "Total: " + usuarioJogador.getCreditos() + "\n");

                                } else {
                                    throw new SaldoInvalidoException("Valor inválido, tente novamente.");
                                }
                            } catch (SaldoInvalidoException e) {
                                System.out.println(e.getMessage());
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
                                if (i == 1) {
                                    bingo.setAposta(aposta);
                                }
                                System.out.println("\n");
                                leitor.nextLine();

                                Cartela cartela = new Cartela(false, nome, aposta);

                                bingo.adicionarJogadorBingo(cartela);
                                cartela.imprimirCartela();
                            }
                            bingo.jogar(leitor, usuarioJogador);
                            break;

                        case 3:
                            System.out.println("-_-_-_-_-_- C A Ç A  N Í Q U E I S -_-_-_-_-_-");
                            jogos[0].jogar(leitor, usuarioJogador);
                            break;

                        case 4:
                            jogos[1].jogar(leitor, usuarioJogador);
                            break;

                        case 5:
                            System.out.println("EM REVISÃO, SELECIONE OUTRA OPÇÃO.");
                            break;

                        case 6:
                            System.out.println("""
                                    -_-_-_-_-_-_- I M P R E S S Ã O -_-_-_-_-_-_-
                                    
                                    Selecione um dos jogos que você deseja imprimir os resultados:
                                    """);

                            boolean impressao = true;

                            while (impressao) {
                                System.out.println("""
                                        Opções:
                                        
                                        [1] - Bingo
                                        [2] - Caça níquel
                                        [3] - Roleta
                                        [4] - BlackJack
                                        
                                        [0] - Voltar
                                        """);
                                leitor.nextLine();
                                try {
                                    switch (leitor.nextInt()) {
                                        case 1:
                                            System.out.println(bingo.imprimir());
                                            break;

                                        case 2:
                                            System.out.println(jogos[0].imprimir());
                                            break;

                                        case 3:
                                            System.out.println(jogos[1].imprimir());
                                            break;

                                        case 4:
                                            System.out.println("JOGO EM REVISÃO, TENTE NOVAMENTE MAIS TARDE.");
                                            break;

                                        case 0:
                                            System.out.println("Voltando à página principal.....");
                                            impressao = false;
                                            break;

                                        default:
                                            throw new ValorInvalidoException("Opção inválida, tente novamente.");
                                    }
                                } catch (ValorInvalidoException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            break;

                        case 0:
                            System.out.println("Foi bom ter você conosco, até a próxima! :D");
                            acesso = false;
                            System.exit(0);
                            break;

                        default:
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida! Por favor, insira um número inteiro." + "\n");
                    leitor.nextLine();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Por favor, insira um valor válido.");
        }
    }
}

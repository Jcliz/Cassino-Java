import Entidades.*;
import Jogos.*;
import Utilidades.Utils;
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
            FileReader arquivo = new FileReader("code/src/Utilidades/dados.txt");
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
            String nomeUsuario = Utils.capitalize(leitor.nextLine());

            System.out.println("Agora, a sua idade:");
            int idadeUsuario = leitor.nextInt();

            if (idadeUsuario <= 18) {
                System.out.println("\n" + "O acesso é liberado apenas para maiores de idade.");
                System.exit(0);
            } else {
                Jogador usuarioJogador = new Jogador(nomeUsuario, idadeUsuario, 0);
                Serializacao.salvarPessoa(usuarioJogador);
                Jogador j = Serializacao.carregarJogador();
                System.out.println(j);

                Jogo[] jogos = new Jogo[]{
                        new Bingo(false),
                        new CacaNiquel(1, false, 1),
                        new Roleta(1, false),
                        new Blackjack(1, false)
                };

                boolean acesso = true;

                System.out.println("Tudo pronto " + usuarioJogador.getNome() +
                        "! Vamos para a diversão." + "\n");
                while (acesso) {
                    System.out.println("""
                            Opções:
                            
                            [1] - Depósito de créditos
                            [2] - Bingo
                            [3] - Caça níquel
                            [4] - Roleta
                            [5] - BlackJack
                            [6] - Impressões
                            
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
                                                "Total: " + usuarioJogador.getCreditos() + " créditos" + "\n");

                                    } else {
                                        throw new ValorInvalidoException("Valor inválido, tente novamente.");
                                    }
                                } catch (ValorInvalidoException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;

                            case 2:
                                jogos[0].jogar(leitor, usuarioJogador);
                                break;

                            case 3:
                                jogos[1].jogar(leitor, usuarioJogador);
                                break;

                            case 4:
                                jogos[2].jogar(leitor, usuarioJogador);
                                break;

                            case 5:
                                jogos[3].jogar(leitor, usuarioJogador);
                                break;

                            case 6:
                                System.out.println("""
                                        -_-_-_-_-_-_- I M P R E S S Ã O -_-_-_-_-_-_-
                                        
                                        Selecione uma opção de impressão:
                                        """);

                                boolean impressao = true;

                                while (impressao) {
                                    System.out.println("""
                                            Opções:
                                            
                                            [1] - Bingo
                                            [2] - Caça níquel
                                            [3] - Roleta
                                            [4] - BlackJack
                                            [5] - Créditos atuais
                                            
                                            [0] - Voltar
                                            """);
                                    leitor.nextLine();
                                    try {
                                        switch (leitor.nextInt()) {
                                            case 1:
                                                System.out.println(jogos[0].imprimir());
                                                break;

                                            case 2:
                                                System.out.println(jogos[1].imprimir());
                                                break;

                                            case 3:
                                                System.out.println(jogos[2].imprimir());
                                                break;

                                            case 4:
                                                System.out.println(jogos[3].imprimir());
                                                break;

                                            case 5:
                                                System.out.println(usuarioJogador);
                                                break;

                                            case 0:
                                                System.out.println("Voltando à página principal....." + "\n");
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
            }
        } catch (InputMismatchException e) {
            System.out.println("Digite um valor válido.");
        }
    }
}

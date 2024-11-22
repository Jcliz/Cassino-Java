package Jogos;

import Entidades.Jogador;
import Entidades.Usuario;
import Utilidades.SaldoInvalidoException;
import Utilidades.Utils;
import Utilidades.ValorInvalidoException;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Roleta extends Jogo {
    private double resultado; //Resultado da jogada
    private int cor; //1 == VERMELHO; 2 == PRETO
    private String corStr; //Transforma a cor em String para impressão
    private int aposta; //Número de aposta
    private int numeroRandom; //Número randomico
    private int corRandom; //Cor randomica
    private double valorApostado;
    private double apostaRodada;

    public Roleta (int numeroJogadores, boolean estadoDoJogo) {
        super(numeroJogadores, estadoDoJogo);
    }

    @Override
    public void jogar(Scanner leitor, Jogador jogador) throws ValorInvalidoException {
        cor = -1; //NENHUMA APOSTA DEFINIDA
        Random r = new Random(System.currentTimeMillis());
        iniciarJogo();
        //Inicia a variável que recebe o valor de resultado para cada sessão de jogatina em 0
        resultado = 0;

        System.out.println("-_-_-_-_-_-_-_-_-_- R O L E T A -_-_-_-_-_-_-_-_-_-");

        while (super.getEstado()) {
            System.out.println("""
                    Opções:
                    
                    [1] - Apostar
                    [2] - Começar o jogo

                    [0] - Voltar ao início""");

            //Try Catch para a verificação de InputMismatch
            try {
                switch (leitor.nextInt()) {
                    case 1:
                        System.out.print("""
                                
                                Opções para aposta:
                                
                                [1] - Selecionar cores
                                
                                [0] - Voltar
                                """);
                        switch (leitor.nextInt()) {
                            case 1:
                                System.out.println("""
                                
                                Opções:
                                [1] - Vermelho
                                [2] - Preto
                                
                                [0] - Voltar
                                """);
                                leitor.nextLine();
                                cor = leitor.nextInt();

                                try {
                                    int apostaTentativa; //Número apostado antes da verificação

                                    if (cor == 1) {
                                        this.corStr = "VERMELHO";
                                        System.out.println("Escolha um número par de 0 a 36:");
                                        apostaTentativa = leitor.nextInt();
                                        verificarValorPar(apostaTentativa);

                                    } else if (cor == 2) {
                                        this.corStr = "PRETO";
                                        System.out.println("Escolha um número ímpar de 0 a 36:");
                                        apostaTentativa = leitor.nextInt();
                                        verificarValorImpar(apostaTentativa);

                                    } else if (cor == 0) {
                                        System.out.println("Voltando para a tela inicial.");
                                        super.setEstado(false);

                                    } else {
                                        throw new ValorInvalidoException("Opção inválida! Tente novamente!");
                                    }

                                    leitor.nextLine();

                                    try {
                                        System.out.println("Qual é o valor que você deseja apostar?:");
                                        double valor = leitor.nextDouble();

                                        if (Utils.verificarSaldoAposta(valor, jogador)) {
                                            apostaRodada = valor;
                                            jogador.retirarCreditos(valor);
                                            System.out.println("Aposta realizada com sucesso!" + "\n");
                                            valorApostado += valor;
                                            resultado += valor;

                                        } else {
                                            throw new SaldoInvalidoException("Saldo insuficiente, ou valor inválido.");
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Entrada inválida! Por favor, insira um número.");
                                        leitor.nextLine();
                                        break;

                                    } catch (SaldoInvalidoException e) {
                                        System.out.println(e.getMessage());
                                        super.setEstado(false);
                                        break;
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Entrada inválida! Por favor, insira um número inteiro.");
                                    leitor.nextLine();
                                    break;

                                } catch (ValorInvalidoException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                break;

                            case 0:
                                System.out.println("Voltando para a tela inicial.");
                                super.setEstado(false);
                                break;

                            default:
                                System.out.println("Opção inválida!");
                                break;
                        }
                        break;

                    case 2:
                        if (cor > 0 && cor < 3) {
                            sortearValores(r);
                            System.out.println("RESULTADOS:" + "\n" +
                                    "Número: " + numeroRandom + "\n" +
                                    "Cor: " + corStr + "\n");
                            this.resultado += verificarResultados(apostaRodada);
                        } else {
                            System.out.println("\n" + "Nenhuma aposta feita!");
                        }
                        break;

                    case 0:
                        System.out.println("Voltando à página inicial....");
                        setEstado(false);
                        break;

                    default:
                        throw new ValorInvalidoException("Opção inválida, tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um número inteiro." + "\n");
                leitor.nextLine();
            }
        }
    }

    public void verificarValorPar(int tentativa) throws ValorInvalidoException {
        try {
            if (tentativa % 2 == 0) {
                System.out.println("Valor par válido!");
                this.aposta = tentativa;
            } else {
                throw new ValorInvalidoException("Valor inválido!");
            }
        } catch (ValorInvalidoException e) {
            System.out.println(e.getMessage());
            super.setEstado(false);
        }
    }

    public void verificarValorImpar(int tentativa) throws ValorInvalidoException {
        try {
            if (tentativa % 3 == 0) {
                System.out.println("Valor ímpar válido!");
                this.aposta = tentativa;
            } else {
                throw new ValorInvalidoException("Valor inválido!");
            }
        } catch (ValorInvalidoException e) {
            System.out.println(e.getMessage());
            super.setEstado(false);
        }
    }

    private boolean ehPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        if (numero == 2) {
            return true; // 2 é primo
        }
        if (numero % 2 == 0) {
            return false; // números pares maiores que 2 não são primos
        }
        for (int i = 3; i <= Math.sqrt(numero); i += 2) { // verifica apenas até a raiz quadrada
            if (numero % i == 0) return false;
        }
        return true;
    }

    public double verificarResultados (double valorAposta) {
        //Verifica se o número apostado é 0 e se o número randomico é 0
        if ((this.aposta == 0) && (this.numeroRandom == 0)) {
            resultado = valorAposta * 10;
            System.out.println("Ganhou 10x o Valor da Aposta: R$" + resultado);
        }
        //Caso em que o número é acertado
        else if (numeroRandom == aposta) {
            resultado = valorAposta * 3;
            System.out.println("Ganhou 3x o valor: R$" + resultado);
        }
        //Caso em que a cor é acertada
        else if (cor == corRandom) {
            resultado = valorAposta * 1.5;
            System.out.println("Ganhou 1.5x o valor: R$" + resultado);
        }
        //Caso em que nada é acertado
        else {
            resultado = 0;
            System.out.println("Número e cor errados! Perdeu tudo.");
        }

        finalizarJogo();
        return resultado;
    }

    public void sortearValores (Random r) {
        this.numeroRandom = r.nextInt(36) + 1;
        this.corRandom = r.nextInt(2) + 1;  // 1 == Vermelho;  2 == Preto;
    }

    @Override
    public void finalizarJogo() {
        super.setEstado(false);
        System.out.println("Terminando sua jogada na roleta!");
    }

    @Override
    public void iniciarJogo() {
        super.setEstado(true);
        System.out.println("Vamos começar o jogo! Boa sorte.");
    }

    @Override
    public String imprimir () {
        return "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + "\n" +
                "Valor apostado: " + valorApostado + "\n" +
                "Último número e cor apostados: " + aposta + ", " + corStr + "\n" +
                "Resultado (em créditos): " + resultado + "\n" +
                "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + "\n";
    }
}
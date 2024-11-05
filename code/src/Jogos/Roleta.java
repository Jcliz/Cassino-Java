package Jogos;

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

    public Roleta (int numeroJogadores, boolean estadoDoJogo) {
        super(numeroJogadores, estadoDoJogo);
    }

    public double jogar(Scanner leitor, double valorAposta) throws ValorInvalidoException {
        Random r = new Random();
        iniciarJogo();
        //Inicia a variável que recebe o valor de resultado para cada sessão de jogatina em 0
        resultado = 0;

        while (super.getEstado()) {
            System.out.println("""
                    +-+-+-+-+-+-+-+-+ ROLETA +-+-+-+-+-+-+-+-+
                    Opções:
                    
                    [1] - Começar o jogo
                    
                    [0] - Voltar ao início""");

            sortearNumeros(r);
            //Try Catch para a verificação de InputMismatch
            try {
                switch (leitor.nextInt()) {
                    case 1:
                        System.out.print("""
                                Opções para aposta:
                                [1] - Vermelho
                                [2] - Preto
                                """);
                        this.cor = leitor.nextInt();

                        int apostaTentativa; //Número apostado antes da verificação

                        try {
                            if (this.cor == 1) {
                                this.corStr = "VERMELHO";
                                System.out.println("Escolha um número par de 0 a 36:");
                                apostaTentativa = leitor.nextInt();
                                verificarValorValidoPar(apostaTentativa);
                            } else if (this.cor == 2) {
                                this.corStr = "PRETO";
                                System.out.println("Escolha um número ímpar de 0 a 36:");
                                apostaTentativa = leitor.nextInt();
                                verificarValorValidoImpar(apostaTentativa);
                            } else {
                                System.out.println("Opção inválida!");
                            }
                        } catch (ValorInvalidoException e) {
                            System.out.println(e.getMessage());
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida! Por favor, insira um número inteiro.");
                        }
                        break;

                    case 0:
                        System.out.println("Voltando à página inicial....");
                        finalizarJogo();
                        break;

                    default:
                        throw new ValorInvalidoException("Opção inválida, tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
        finalizarJogo();
        return verificarResultados(valorAposta);
    }

    public void verificarValorValidoPar(int tentativa) throws ValorInvalidoException {
        if (tentativa % 2 == 0) {
            System.out.println("Aposta válida!");
            this.aposta = tentativa;
        } else {
            throw new ValorInvalidoException("Valor inválido! O número deve ser par.");
        }
    }
    public void verificarValorValidoImpar(int tentativa) throws ValorInvalidoException{
        if (tentativa % 3 == 0) {
            System.out.println("Aposta válida!");
            this.aposta = tentativa;
        } else {
            throw new ValorInvalidoException("Valor inválido! O número deve ser ímpar.");
        }
    }

    public double verificarResultados (double valorAposta) {
        //Verifica se o número apostado é 0 e se o número randomico é 0
        if ((this.aposta == 0) && (this.numeroRandom == 0)){
            resultado = valorAposta * 10;// Se for 0 ganha 5x o valor apostado.
            corStr = " ";
            System.out.println("Ganhou 10x o Valor da Aposta: R$" + resultado);
        }
        //Caso em que nada é acertado
        else if ((cor != corRandom) && (numeroRandom != aposta)) {
            resultado = 0; //Perde o valor apostado
            System.out.println("Número e cor errados! Perdeu tudo.");
        }
        //Caso em que o número é acertado
        else if (numeroRandom == aposta) {
            resultado = valorAposta * 3; //Se o número apostado for igual ao sorteado, ganha o valor da aposta * 3
            System.out.println("Ganhou 3x o valor: R$" + resultado);
        }
        //Caso em que a cor é acertada
        else {
            resultado = valorAposta * 1.5; //Se a cor apostada for igual à sorteada, ganha o valor da aposta * 1.5
            System.out.println("Ganhou 1.5x o valor: R$" + resultado);
        }
        return resultado;
    }

    public void sortearNumeros (Random r) {
        this.numeroRandom = r.nextInt(35) + 1;
        this.corRandom = r.nextInt(1) + 1;  // 1 == Vermelho;  2 == Preto;
    }

    public void finalizarJogo() {
        super.setEstado(false);
        System.out.println("Terminando sua jogada na roleta!");
    }

    public void iniciarJogo() {
        super.setEstado(true);
        System.out.println("Vamos começar o jogo! Boa sorte.");
    }

    public String imprimir (double valorApostado) {
        return "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + "\n" +
                "Valor apostado: " + valorApostado + "\n" +
                "Número e cor apostados: " + aposta + ", " + corStr + "\n" +
                "Resultado (em créditos): " + resultado + "\n" +
                "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + "\n";
    }
}
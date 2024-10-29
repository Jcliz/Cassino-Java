package Jogos;

import java.util.Random;
import java.util.Scanner;

public class Roleta extends Jogo {
    private double resultado; // Resultado da jogada
    private int cor; // 1 == VERMELHO; 2 == PRETO
    private String corStr; // Transforma a cor em String para impressão
    private int aposta; // Número de aposta
    private int numeroRandom; // Número randomico
    private int corRandom; // Cor randomica

    public double Apostar(Scanner leitor, double valorAposta){
        Random r = new Random();
        this.numeroRandom = r.nextInt(35) + 1;
        this.corRandom = r.nextInt(1) + 1;  // 1 == Vermelho;  2 == Preto;

        System.out.print("""
                +-+-+-+-+-+-+-+-+ ROLETA +-+-+-+-+-+-+-+-+
                
                Opções para aposta:
                [1] - Vermelho
                [2] - Preto
                """);
        this.cor = leitor.nextInt();

        int apostaTentativa; // Numéro apostado antes da verificação

        if (this.cor == 1) {
            this.corStr = "VERMELHO";
            System.out.println("Escolha um número par de 0 a 36:");
            apostaTentativa = leitor.nextInt();

            //TRATAMENTO DE ERROS
            if (apostaTentativa % 2 == 0) {
                System.out.println("Aposta válida!");
                apostaTentativa = this.aposta;
            } else {
                System.out.println("Valor inválido!");
            }

        } else if (this.cor == 2) {
            this.corStr = "PRETO";
            System.out.println("Escolha um número ímpar de 0 a 36:");
            apostaTentativa = leitor.nextInt();

            //TRATAMENTO DE ERROS
            if (apostaTentativa % 3 == 0) {
                System.out.println("Aposta válida!");
                apostaTentativa = this.aposta;
            } else {
                System.out.println("Valor inválido!");
            }

        } else {
            //TRATAMENTO DE ERRO
            System.out.println("Opção inválida!");
        }
        return this.verificacaoResultados(valorAposta);
    }

    public double verificacaoResultados (double valorAposta) {
        //Verifica se o número apostado é 0 e se o número randomico é 0
        if ((this.aposta == 0) && (this.numeroRandom == 0)){
            resultado = valorAposta * 10;// Se for 0 ganha 5x o valor apostado.
            corStr = " ";
            System.out.println("Ganhou 10x o Valor da Aposta: R$" + resultado);
        }
        // Caso em que nada é acertado
        else if ((cor != corRandom) && (numeroRandom != aposta)) {
            resultado = 0; //Perde o valor apostado
            System.out.println("Número e cor errados! Perdeu tudo.");
        }
        // Caso em que o número é acertado
        else if (numeroRandom == aposta) {
            resultado = valorAposta * 3; //Se o número apostado for igual ao sorteado, ganha o valor da aposta * 3
            System.out.println("Ganhou 3x o valor: R$" + resultado);
        }
        // Caso em que a cor é acertada
        else if (cor == corRandom) {
            resultado = valorAposta * 1.5; //Se a cor apostada for igual à sorteada, ganha o valor da aposta * 1.5
            System.out.println("Ganhou 1.5x o valor: R$" + resultado);
        }
        return resultado;
    }
}
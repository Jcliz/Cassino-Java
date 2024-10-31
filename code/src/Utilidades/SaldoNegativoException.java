package Utilidades;

public class SaldoNegativoException extends Exception{
    public SaldoNegativoException(){
        super("Saldo Negativo");
    }
}
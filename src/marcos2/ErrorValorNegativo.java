package marcos2;

import java.util.InputMismatchException;

public class ErrorValorNegativo extends InputMismatchException {

    public ErrorValorNegativo(String s) {
        super(s);
        System.err.println("Valor negativo");
    }
}

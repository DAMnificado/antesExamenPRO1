package marcos2;

import java.util.InputMismatchException;

public class ErrorCabecera extends InputMismatchException {

    public ErrorCabecera(String s) {
        super(s);
        System.err.println("Cabecera de código incorrecta");
    }
}

package marcos2;

import java.util.InputMismatchException;

public class ErrorLongitud extends InputMismatchException {

    public ErrorLongitud(String s) {
        super(s);
        System.err.println("Longitud de código incorrecta");
    }
}

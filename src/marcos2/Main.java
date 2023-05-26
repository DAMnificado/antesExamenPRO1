package marcos2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        Scanner sc;
        int opcion = 0;

        while (true) {

            sc = new Scanner(System.in);

            System.out.println("*******************************************************************");
            System.out.println("****                Programa ej2                        ****");
            System.out.println("****     Para introducir códigos postales pulse 1              ****");
            System.out.println("****     Para listar provincias y códigos postales pulse 2     ****");
            System.out.println("****                Para salir pulse 3                         ****");
            System.out.println("*******************************************************************");

            try {
                opcion = sc.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Eso no era un número, vuelve a intentarlo");
                menu();
            }
            switch (opcion) {
                case 1:
                    ej2.addCodigoPostal();
                    break;
                case 2:
                    ej2.listarFicheroProvincias();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Número no válido");
            }
        }
    }
}

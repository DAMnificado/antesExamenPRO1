package ejercicio3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        Scanner sc;
        int num = 0;
        while (true){
            System.out.println("*************** Opcion 1: Añadir libros al fichero ***************");
            System.out.println("*************** Opcion 2: Listar Libro ***************");
            System.out.println("*************** Opcion 3: Consultar libro ***************");
            System.out.println("*************** Opcion 4: Eliminar Libro por título ***************");
            System.out.println("*************** Opcion 5: Modificar Libro ***************");

            sc = new Scanner(System.in);
            System.out.println("Introduce una opcion");
            try {
                num = sc.nextInt();
            }catch (InputMismatchException ex){
                System.out.println("Dato introducido incorrecto");
                menu();
            }
            switch (num){
                case 0:
                    System.out.println("Fin del programa");
                    return;
                case 1:
                    ej3.añadirLibro();
                    break;
                case 2:
                    ej3.listarLibro();
                    break;
                case 3:
                    ej3.consultarLibroPorTitulo();
                    break;
                case 4:
                    ej3.borrarLibro();
                    break;
                case 5:
                    ej3.modificarLibro();
                    break;
                default:
                    System.out.println("El numero no coincide con ninguna opcion");
            }
        }
    }
}




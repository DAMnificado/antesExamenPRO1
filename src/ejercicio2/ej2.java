package ejercicio2;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Desarrolla un programa que: (3 puntos)
 * a. Para cada elemento de un array con nombres de provincias españolas,
 * solicite al usuario el código postal asociado y almacenar dicho contenido en un fichero,
 * donde cada línea constará de nombre de provincia y código postal.
 *
 * Se deberá comprobar que los datos leídos de teclado sean correctos y lanzar una excepción en los siguientes casos:
 *
 * • En caso de introducir un valor negativo.
 *
 * • En caso de que el código postal no empiece por alguno de los valores comprendidos
 * entre 01 y 52.
 *
 * • En caso de que el código postal posea una longitud distinta de 5 caracteres.
 *
 * Para ello, se creará una clase para cada excepción y se lanzará la que corresponda en cada caso.
 *
 * En caso de que se produzca alguna de estas circunstancias, se deberá volver a solicitar el dato.
 *
 * b. Conste de un método que liste los elementos del fichero.
 */

public class ej2 {
    static File archivo = new File(".src/ejercicio2/ej2.java");
    static Scanner sc = new Scanner(System.in);

    public static void llenarArchivo() {

        //creo arrays con 4 posiciones
        ArrayList<String> listaProvincias = new ArrayList<>();
        ArrayList<Integer> listaCodigos = new ArrayList<>();

        //llena las arrays
        for (int i = 0; i < 4; i++) {

                System.out.println("Dime una provincia de España");
                String provincia = sc.nextLine();
                boolean correcto = false;
                Scanner sc = new Scanner(System.in);
                while (!correcto) {

                    try {
                            System.out.println("Introduce un codigo postal: ");
                            int codigoPostal = sc.nextInt();

                            comprobaciones(codigoPostal);
                            correcto = true;
                            listaProvincias.add(provincia);
                            listaCodigos.add(codigoPostal);

                    } catch(valoresComprendidos|valorNegativo|longitud e){
                        correcto = false;
                        System.out.println("CÓDIGO POSTAL ERRÓNEO");
                        System.out.println(e.getMessage());
                    }
                }
            }

        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(archivo))) {

            for(int i=0;i < listaProvincias.size();i++){
                dos.writeUTF(listaProvincias.get(i) + listaCodigos.get(i));
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        }

    public static void leerArchivo(){
        try(DataInputStream dis = new DataInputStream(new FileInputStream(archivo))) {
            while (true) {
                int cont = 1;
                dis.read();
                System.out.println("Provincia número" + cont + dis.read());
                cont++;
            }

        }catch(EOFException e){
            System.out.println("Fin del archivo");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void comprobaciones(int cp) {

        if (cp < 0) {
            throw new valorNegativo("El valor no puede ser negativo");
        }
        if (cp < 01 | cp > 530000) {
            throw new valoresComprendidos("Los dos primeros números solo pueden estar comprendidos entre 0 y 52");
        }

        String codigo = String.valueOf(cp);
        if (codigo.length() != 5) {
            throw new longitud("La longitud tiene que ser de 5 caracteres");
        }
    }
}



package marcos2;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public final class ej2 {

    private static final File file = new File("./src/marcos2/FicheroProvincias.txt");
    private static final String[] listaProvincias = {"Coruña", "Lugo", "Ourense", "Pontevedra"};

    public static void addCodigoPostal() {
        ficheroExiste(file);
        Scanner sc;
        int codigo;

        for (String provincia : listaProvincias) {
            while (true) {
                try {
                    sc = new Scanner(System.in);
                    System.out.println("Introduce el código postal de: " + provincia);
                    codigo = sc.nextInt();

                    if (codigo < 0) {
                        throw new ErrorValorNegativo("Valor negativo");
                    } else if (codigo / 1000 < 1 || codigo / 1000 > 52) {
                        throw new ErrorCabecera("Cabecera de código incorrecta");
                    } else if (codigo / 10000 < 1 || codigo / 10000 > 9) {
                        throw new ErrorCabecera("Longitud de código incorrecta");

                    } else break;
                } catch (InputMismatchException ex) {
                    ex.getMessage();
                }
            }
            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file, true))) {
                dos.writeUTF(provincia + " " + codigo);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void listarFicheroProvincias() {
        ficheroExiste(file);
        int cont = 0;
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            while (true) {
                System.out.println(dis.readUTF());
                cont++;
            }
        } catch (EOFException e) {
            System.out.println("Fin del archivo");
            if (cont == 0) System.out.println("Archivo vacío");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void ficheroExiste(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

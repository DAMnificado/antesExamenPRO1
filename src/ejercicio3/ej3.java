package ejercicio3;

import java.io.*;
import java.util.Scanner;

/**

 * Título, autor, editorial y número de páginas.
 * Se empleará la persistencia de objetos en ficheros.
 * El programa dispondrá de un menú con las siguientes
 * funcionalidades: (5 puntos)
 * a. Añadir libros al fichero.
 * b. Consultar Libro por título.
 * c. Listar libros.
 * d. Eliminar Libro por título.
 * La información de cada alta, baja o modificación debe ser persistente, lo que implica que debe realizarse sobre el fichero.
 */
public class ej3 {

    static File archivo = new File("src/ejercicio3");
    static File archivoTemp = new File("src/ejercicio3");
    static Scanner sc = new Scanner(System.in);

    public static void añadirLibro() {

        crearArchivo(archivo);
        sc = new Scanner(System.in);
        System.out.println("Dame un titulo");
        String titulo = sc.next();
        System.out.println("Dame un autor");
        String autor = sc.next();
        System.out.println("Dame una editorial");
        String editorial = sc.next();
        sc = new Scanner(System.in);
        System.out.println("Dame un número de páginas");
        int numPag = sc.nextInt();

        Libro libro = new Libro(titulo, autor, editorial, numPag);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo, true))) {
            oos.writeObject(libro);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void listarLibro() {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {

            while (true) {
                System.out.println(ois.readObject());
            }
        } catch (EOFException e) {
            System.out.println("Fin de archivo");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void consultarLibroPorTitulo() {
        sc = new Scanner(System.in);
        System.out.println("Que libro quieres consultar?");
        String libroAConsultar = sc.next();
        int cont = 0;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            Libro libro;
            while (true) {
                libro = (Libro) ois.readObject();
                if (libroAConsultar.equalsIgnoreCase(libro.getTitulo())) {
                    System.out.println("Si, tenemos este libro disponible");
                }
                System.out.println("Este libro no se encuentra en nuestra biblioteca");
                cont++;
            }
        } catch (StreamCorruptedException e) {
            System.out.println("Error de Stream");
        } catch (EOFException e) {
            System.out.println("Fin del archivo");
            if (cont == 0) {
                System.out.println("No hay libros");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static void borrarLibro() {
        sc = new Scanner(System.in);
        try (ObjectInputStream oisA = new ObjectInputStream(new FileInputStream(archivo));
             ObjectOutputStream oisT = new ObjectOutputStream(new FileOutputStream(archivoTemp, true))) {
            Libro libro;
            while (true) {
                System.out.println("Dime el titulo del libro que quieres borrar");
                String tituloABorrar = sc.next();
                libro = (Libro) oisA.readObject();
                if (tituloABorrar.equalsIgnoreCase(libro.getTitulo())) {
                    System.out.println("Eliminado");
                }
                oisT.writeObject(libro);
            }
        }catch (EOFException e){
            System.out.println("Fin de archivo");
            archivo.delete();
            archivoTemp.renameTo(archivo);
        }catch (StreamCorruptedException e){
            System.out.println("Stream corrupto");
            archivo.delete();
            archivoTemp.renameTo(archivo);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static void modificarLibro() {
        sc = new Scanner(System.in);
        try (ObjectInputStream oisA = new ObjectInputStream(new FileInputStream(archivo));
             ObjectOutputStream oisT = new ObjectOutputStream(new FileOutputStream(archivoTemp))) {
            Libro libro;
            while (true) {
                System.out.println("Que libro quieres modificar?");
                String libroAModificar = sc.next();
                libro = (Libro) oisA.readObject();
                if (libroAModificar.equalsIgnoreCase(libro.getTitulo())) {
                    sc = new Scanner(System.in);
                    System.out.println("Muy bien, dime por qué libro lo cambiamos?");
                    String tituloNuevo = sc.next();
                    System.out.println("Que autor lo escribio?");
                    String autorNuevo = sc.next();
                    System.out.println("De que editorial?");
                    String editorialNueva = sc.next();
                    sc = new Scanner(System.in);
                    System.out.println("Cuantas páginas tiene?");
                    int numPagNuevo = sc.nextInt();
                    Libro libroMod = new Libro(tituloNuevo, autorNuevo, editorialNueva, numPagNuevo);
                    oisT.writeObject(libroMod);
                }
                oisT.writeObject(libro);
            }
        }catch(StreamCorruptedException e){
            System.out.println("Stream corrupto");
            archivo.delete();
            archivoTemp.renameTo(archivo);
        }catch(EOFException e){
            System.out.println("Fin del archivo");
            archivo.delete();
            archivoTemp.renameTo(archivo);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

            private static void crearArchivo (File archivo){
                if (!archivo.exists()) {
                    try {
                        archivo.createNewFile();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }
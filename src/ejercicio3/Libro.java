package ejercicio3;

import java.io.Serializable;

/**
 *Entre los datos a almacenar de cada Libro está el título, autor, editorial y número de páginas.

 */
public class Libro implements Serializable {

    String titulo;
    String autor;
    String editorial;
    int numPag;

    public Libro() {
        this.titulo = "nombre";
        this.autor = "apellidos";
        this.editorial = "apellidos";
        this.numPag = 0;

    }

    public Libro(String titulo, String autor, String editorial, int numPag) {
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.numPag = numPag;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getNumPag() {
        return numPag;
    }

    public void setNumPag(int numPag) {
        this.numPag = numPag;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", editorial='" + editorial + '\'' +
                ", numPag=" + numPag +
                '}';
    }
}

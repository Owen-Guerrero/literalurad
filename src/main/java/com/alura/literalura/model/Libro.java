package com.alura.literalura.model;


import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Enumerated(EnumType.STRING)
    private Idioma idioma;
    private Integer numeroDeDescargas;
    @ManyToOne
    private Autor autor;

    public Libro(){
    }

    public Libro (DatosLibros datosLibros){
        this.titulo = datosLibros.titulo();
        this.numeroDeDescargas = datosLibros.numeroDeDescargas();
        this.idioma = Idioma.fromString(datosLibros.idiomas().toString().split(",")[0].trim());


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Integer numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        String nombreAutor = (autor != null) ? autor.getNombreAutor() : "Desconocido";
        return String.format("%n═══════════════════════════════════════" +
                        "%nLibro: %s" +
                        "%nAutor: %s" +
                        "%nIdioma: %s" +
                        "%nNúmero de Descargas: %d" +
                        "%n═══════════════════════════════════════",
                titulo,
                nombreAutor,
                idioma,
                numeroDeDescargas);
    }

}

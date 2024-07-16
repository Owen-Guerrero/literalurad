package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String nombre;
    private String fechaDeNacimiento;
    private String fechaDeFallecimiento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libro;

    public Autor(){
    }

    public Autor(DatosAutor autor) {
        this.nombre = autor.nombre();
        this.fechaDeNacimiento = autor.fechaDeNacimiento();
        this.fechaDeFallecimiento= autor.fechaDeFallecimiento();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreAutor() {
        return nombre;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombre = nombreAutor;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getFechaDeFallecimiento() {
        return fechaDeFallecimiento;
    }

    public void setFechaDeFallecimiento(String fechaDeFallecimiento) {
        this.fechaDeFallecimiento = fechaDeFallecimiento;
    }

    public List<Libro> getLibro() {
        return libro;
    }

    public void setLibro(List<Libro> libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        StringBuilder librosStr = new StringBuilder();
        librosStr.append("Libros: ");

        for(int i = 0; i < libro.size() ; i++) {
            librosStr.append(libro.get(i).getTitulo());
            if (i < libro.size() - 1 ){
                librosStr.append(", ");
            }
        }
        return String.format("%n═══════════════════════════════════════" +
                        "%nAutor: %s%n" +
                        "Fecha de Nacimiento: %s%n" +
                        "Fecha de Fallecimiento: %s%n" +
                        "%s%n" +
                        "═══════════════════════════════════════",
                nombre,
                fechaDeNacimiento,
                fechaDeFallecimiento != null ? fechaDeFallecimiento : "N/A",
                librosStr.toString());
    }


}

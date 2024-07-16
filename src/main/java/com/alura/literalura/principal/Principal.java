package com.alura.literalura.principal;

import com.alura.literalura.model.*;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner sc = new Scanner(System.in);
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;
    private List<Autor> autores;
    private List<Libro> libros;

    public Principal (LibroRepository libroRepository,AutorRepository autorRepository ){
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }


    public void muestraElMenu(){
        int opcion = -1;
        while (opcion!=0){
            String menu ="""         
            ╔══════════════════════════════════════╗
            ║               LITERALURA             ║
            ╚══════════════════════════════════════╝
            1 - Buscar Libros por Título
            2 - Listar Libros Registrados
            3 - Listar Autores Registrados
            4 - Listar Autores vivos en un determinado año
            5 - Listar Libros por Idioma
            0 - Salir del Programa
            Elija una opción:
            """;
            System.out.println(menu);
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEnUnDeterminadoAnio();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.println("""
                Escriba el idioma del libro:
                EN: Ingles
                ES: Español
                FR: Frances
                PT: Portugues
                """);
        var idiomaSelecionado = sc.nextLine();
        try {
            List<Libro> libroPorIdioma = libroRepository.findByIdioma(Idioma.valueOf(idiomaSelecionado.toUpperCase()));
            libroPorIdioma.forEach(n -> System.out.println(
                    "═════════════════════ LIBRO ═════════════════════" +
                            "\nTitulo: " + n.getTitulo() +
                            "\nIndioma: " + n.getIdioma() +
                            "\nAutor: " + n.getAutor().getNombreAutor() +
                            "\nNumero de descargas: " + n.getNumeroDeDescargas() +
                            "\n"
            ));
        } catch (IllegalArgumentException e){
            System.out.println("Idioma no existe...\n");
        }
    }

    private void listarAutoresVivosEnUnDeterminadoAnio() {
        System.out.println("Ingresa el año vivo de autor(es) que desea buscar: ");
        var anio = sc.nextInt();
        autores = autorRepository.listaAutoresVivosPorAnio(anio);

        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + anio);
        } else {
            System.out.println("Autores vivos en el año " + anio + ":");
            autores.forEach(System.out::println);
        }
    }

    private void listarAutoresRegistrados() {
        autores = autorRepository.findAll();
        autores.stream().forEach(System.out::println);
    }

    private void listarLibrosRegistrados() {
        List<Libro> mostrarListaLibros = libroRepository.findAll();
        mostrarListaLibros.forEach(l -> System.out.println(
                "═════════════════════LIBRO ═════════════════════" +
                        "\nTítulo: " + l.getTitulo()+
                        "\nIdioma: " + l.getIdioma()+
                        "\nAutor: " + l.getAutor().getNombreAutor()+
                        "\nNúmero de descargas: " + l.getNumeroDeDescargas() +
                        "\n"
        ));
    }


    private Datos Buscar() {
        System.out.println("Ingrese el nombre del libro que desea buscar: ");
        var tituloLibro = sc.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE+"?search="+tituloLibro.replace(" ","+"));

        Datos datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        return datosBusqueda;

    }
    private void buscarLibroPorTitulo() {
        Datos datosBusqueda = Buscar();

        if (datosBusqueda != null && !datosBusqueda.resultados().isEmpty()) {
            DatosLibros primerLibro = datosBusqueda.resultados().get(0);

            Libro libro = new Libro(primerLibro);

            Optional<Libro> libroExistente = libroRepository.findByTituloContainsIgnoreCase(libro.getTitulo());

            if (libroExistente.isPresent()) {
                System.out.println("El libro ya se encuentra registrado");
                libro = libroExistente.get(); // Usamos el libro existente
            } else {
                if (!primerLibro.autor().isEmpty()) {
                    DatosAutor datosAutor = primerLibro.autor().get(0);
                    Autor autor = new Autor(datosAutor);
                    Optional<Autor> autorExistente = autorRepository.findByNombre(autor.getNombreAutor());

                    if (autorExistente.isPresent()) {
                        autor = autorExistente.get();
                    } else {
                        autor = autorRepository.save(autor);
                    }
                    libro.setAutor(autor);
                    libro = libroRepository.save(libro);
                } else {
                    System.out.println("Sin autor");
                    libro = libroRepository.save(libro);
                }
                System.out.println("Nuevo libro guardado en la base de datos");
            }

            // Imprimir detalles del libro (sea nuevo o existente)
            System.out.println(
                    "\n══════════════════════ LIBRO  ═════════════════════" +
                            "\nTítulo: " + libro.getTitulo() +
                            "\nAutor: " + (libro.getAutor() != null ? libro.getAutor().getNombreAutor() : "Desconocido") +
                            "\nIdioma: " + libro.getIdioma() +
                            "\nNúmero de descargas: " + (libro.getNumeroDeDescargas() != null ? libro.getNumeroDeDescargas() : 0) +
                            "\n═══════════════════════════════════════════════════════════════\n"
            );
        } else {
            System.out.println("Libro no encontrado :/");
        }
    }
}

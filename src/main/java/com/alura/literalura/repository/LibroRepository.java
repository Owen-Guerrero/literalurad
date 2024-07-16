package com.alura.literalura.repository;

import com.alura.literalura.model.Idioma;
import com.alura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface LibroRepository extends JpaRepository<Libro,Long> {

    Optional<Libro> findByTituloContainsIgnoreCase(String titulo);
    List<Libro> findByIdioma(Idioma idioma);





}

package com.alura.literalura.model;

public enum Idioma {
    EN("[en]", "Ingles"),
    ES("[es]", "Español"),
    FR("[fr]", "Frances"),
    PT("[pt]", "Portugues");

    private String idiomaAPI;


    Idioma(String idiomaGutendex, String idiomaEspanol){
        this.idiomaAPI = idiomaGutendex;


    }

    public static Idioma fromString(String text){
        for (Idioma idioma : Idioma.values()){
            if (idioma.idiomaAPI.equalsIgnoreCase(text)){
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ningun idioma encontrado: " + text);
    }

    public String getIdiomaAPI() {
        return idiomaAPI;
    }


}
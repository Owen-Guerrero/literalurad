# LiteraLura - Sistema de Gestión de Autores y Libros

## Descripción
LiteraLura es un sistema de gestión para autores y sus libros. Este proyecto utiliza Spring Boot con JPA para manejar la persistencia de datos en una base de datos relacional, interactúa con una API externa para obtener información de libros, y proporciona una interfaz de línea de comandos para la interacción del usuario.

## Características principales
- Búsqueda de libros por título utilizando una API externa
- Gestión de autores y libros en una base de datos local
- Listado de libros y autores registrados
- Búsqueda de autores vivos en un año específico
- Listado de libros por idioma
- Interfaz de línea de comandos para interacción del usuario

## Estructura del proyecto
El proyecto contiene las siguientes clases y interfaces principales:

- Modelos:
  - `Autor`: Representa a un autor en el sistema
  - `Libro`: Representa un libro en el sistema
  - `Idioma`: Enumeración de idiomas disponibles
  - `Datos`, `DatosAutor`, `DatosLibros`: Records para procesamiento de JSON

- Repositorios:
  - `AutorRepository`: Interfaz para operaciones de base de datos relacionadas con autores
  - `LibroRepository`: Interfaz para operaciones de base de datos relacionadas con libros

- Servicios:
  - `ConsumoAPI`: Clase para consumir la API externa
  - `ConvierteDatos`: Clase para convertir datos JSON a objetos Java
  - `IConvierteDatos`: Interfaz para la conversión de datos

- Principal:
  - `Principal`: Clase que maneja la lógica del programa y la interacción del usuario
  - `LiteraluraApplication`: Clase principal de Spring Boot que inicia la aplicación

## Tecnologías utilizadas
- Java
- Spring Boot
- Spring Data JPA
- Jackson (para procesamiento de JSON)
- API externa: Gutendex (https://gutendex.com/)
- Base de datos relacional (configurada en Spring Boot)

## Funcionalidades destacadas
- Integración con API externa para búsqueda de libros
- Persistencia de datos de libros y autores usando Spring Data JPA
- Consultas personalizadas usando @Query en repositorios
- Manejo de relaciones entre entidades (Autor-Libro)
- Búsquedas y filtros avanzados (por año, idioma, etc.)
- Interfaz de usuario basada en consola

## Cómo utilizar
1. Asegúrese de tener Java y Maven instalados en su sistema
2. Clone el repositorio
3. Configure la base de datos en `application.properties`
4. Ejecute la aplicación usando Maven: `mvn spring-boot:run`
5. Siga las instrucciones en el menú para interactuar con el sistema

## Uso
1. Ejecuta el programa.
2. Sigue las instrucciones en pantalla

## Configuración
1. Configurar la base de datos en `src/main/resources/application.properties`
2. Asegurarse de que todas las dependencias en el `pom.xml` estén correctamente configuradas

## Contacto
[Owen Guerrero] - [guerrerofranklinowen@gmail.com]

class Libro( val titulo: String,
             val autor: String,
             val numPaginas: Int,
             val calificacion: Int){


    init {
        require(calificacion in 0..10){"La calificación debe ser un valor entre 0 y 10."}
    }

    override fun toString(): String {
        return "El libro '$titulo' de $autor, tiene $numPaginas número de páginas y una calificación de $calificacion"
    }

}

class ConjuntoLibros(val cantidad: Int){

    val libros = Array<Libro?>(cantidad){null}

    fun agregarLibro(libro: Libro){

        for(i in libros.indices){
            if(libros[i] == null){
                libros[i] = libro

                println("El libro '${libro.titulo}' ha sido agregado al conjunto.")
                return
            }
        }

        println("No hay espacio suficiente en el conjunto.")
    }

    fun eliminarLibroA(autor: String){

        for(i in libros.indices){
            if(libros[i]?.autor == autor)
                libros[i] = null

            println("El libro del autor $autor ha sido eliminado")
            return
        }

        println("No se ha encontrado ningún libro del autor $autor")
    }

    fun eliminarLibroT(titulo: String){

        for(i in libros.indices){
            if(libros[i]?.titulo == titulo)
                libros[i] = null

            println("El libro '$titulo' ha sido eliminado")
            return
        }

        println("No se ha encontrado el libro  '$titulo'")
    }

    fun mostrarMaxMin(){

        val librosValidos = libros.filterNotNull()

        if (librosValidos.isNotEmpty()) {
            var libroMax = librosValidos[0]
            var libroMin = librosValidos[0]

            for (libro in librosValidos) {
                if (libro.calificacion > libroMax.calificacion) {
                    libroMax = libro
                }
                if (libro.calificacion < libroMin.calificacion) {
                    libroMin = libro
                }
            }

            println("Libro con la mayor calificación: ${libroMax.titulo} por ${libroMax.autor}, Calificación: ${libroMax.calificacion}")

            println("Libro con la menor calificación: ${libroMin.titulo} por ${libroMin.autor}, Calificación: ${libroMin.calificacion}")
        } else {
            println("No hay libros en el conjunto.")
        }
    }

    fun mostrarContenido() {
        println("Conjunto de libros: ")

        libros.filterNotNull().forEach {
            println("Título: ${it.titulo}, Autor: ${it.autor}, Páginas: ${it.numPaginas}, Calificación: ${it.calificacion}")
        }
    }
}

fun main() {

    val libro1 = Libro("El Alquimista", "Paulo Coelho", 190, 8)
    val libro2 = Libro("Cien Años de Soledad", "Fran", 417, 10)

    val conjuntoLibros = ConjuntoLibros(5)

    conjuntoLibros.agregarLibro(libro1)
    conjuntoLibros.agregarLibro(libro2)


    println("\nContenido del conjunto de libros tras agregar:")
    conjuntoLibros.mostrarContenido()


    conjuntoLibros.eliminarLibroA("Fran")


    println("\nContenido del conjunto de libros tras eliminar por autor:")
    conjuntoLibros.mostrarContenido()


    conjuntoLibros.eliminarLibroT("El Alquimista")


    println("\nContenido del conjunto de libros tras eliminar por título:")
    conjuntoLibros.mostrarContenido()


    val libro3 = Libro("Hola Diego", "Luque", 328, 9)
    conjuntoLibros.agregarLibro(libro3)


    println("\nContenido final del conjunto de libros tras añadir un libro:")
    conjuntoLibros.mostrarContenido()

    conjuntoLibros.mostrarMaxMin()
}


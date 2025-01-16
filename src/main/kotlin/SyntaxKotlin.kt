package angel.parraga

import java.util.UUID

class SyntaxKotlin {
    fun simpleTest() {
        // var es mutable
        var mutable = "Esto es mutable"
        mutable = "String mutado"

        // val es inmutable
        val inmutable = mutable

        // Declaración data class. No hace falta {} si no hay más código
        data class Person(val name: String, val age: Int)

        // No new para instanciar clase
        val person = Person("John", 30)

        // Data class copy
        val personCopy = person.copy("Ángel")

        // Desestructuración de clases
        val (name, age) = personCopy

        // String interpolation
        val message = "Hello, $name! You've ${20 + 10} years old"

        // Print más sencillo
        println(message)

        // Asignaciones con control de flujo. Vale para returns.
        val ifVariable = if (person.name == "John") "yes" else "no"
    }

    fun collections() {
        // Lista inmutable. No tiene métodos para modificar la lista
        val inmutableList = listOf(1, 2, 3, 4, 5)

        // Lista mutable
        val mutableList = mutableListOf(1, 2, 3, 4, 5)
        mutableList.removeAt(1)

        // No streams. No collect.
        // Lambda fuera de paréntesis
        val times2 = mutableList.map { element -> element * 2 }

        // "it" representa el elemento
        val newTimes2 = mutableList.map { it * 2 }

        mutableList.isEmpty()
        mutableList.isNotEmpty()
    }

    // Funciones compactas con =
    // No es necesario indicar el retorno
    fun compactFunction() = "New compact function"

    // Funciones de extensión
    fun String.isUUID(): Boolean = try {
        UUID.fromString(this)
        true
    } catch (e: IllegalArgumentException) {
        false
    }

    fun <T> List<T>.mostCommonElement(): T? {
        return this.groupingBy { it }.eachCount().maxBy { it.value }.key
    }

    fun mostCommonElement() {
        val list = listOf(1, 2, 3, 4, 5, 5)
        val mostCommonElement = list.mostCommonElement()
    }
}
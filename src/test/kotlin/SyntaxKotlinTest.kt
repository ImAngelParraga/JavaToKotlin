import org.junit.jupiter.api.Test
import java.util.UUID
import kotlin.test.assertEquals


@Suppress("UnusedVariable", "unused")
class SyntaxKotlinTest {

    @Test
    fun simpleTest() {
        data class Person(val name: String)
        // No new para instanciar clase
        val person = Person("John")

        // var es mutable
        var name = person.name
        name = "Ángel"

        // val es inmutable
        // String interpolation
        val message = "Hello, $name! You've ${20 + 10} years old"

        println(message)

        // Asignaciones con control de flujo. Vale para returns.
        val ifVariable = if (person.name == "John") "yes" else "no"
    }

    @Test
    fun collectionTest() {
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

    @Test
    fun mostCommonElementTest() {
        val list = listOf(1, 2, 3, 4, 5, 5)

        assertEquals(5, list.mostCommonElement())
    }


}
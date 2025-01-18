package angel.parraga

import kotlin.random.Random

@Suppress("unused")
class ScopeFunctions {
    /*
    Son funciones para ejecutar código en el contexto de un objeto con un scope temporal. Son funciones útiles para
    hacer el código más conciso y legible.
    */

    /*
    Funciones que hacen referencia al objeto del contexto con "it" (lambda argument). Estas funciones son recomendadas
    cuando se usa el objeto como argumento en llamadas a funciones; también si se utilizan múltiples variables en el
    bloque de código.
    */

    /* LET
    Recomendable para ejecutar un code block que contenga valores no nulos y para invocar funciones sobre el
    resultado de una cadena de llamadas. Retorna el resultado del lambda.
    */
    fun scopeLet() {
        val str: String? = "Hello"
        //processNonNullString(str)     // compilation error: str can be null
        val lengthLet = str?.let {
            println("let() called on $it")
            processNonNullString(it)      // OK: 'it' is not null inside '?.let { }'
            it.length
        }

        var lengthNoLet: Int? = null
        if (str != null) {
            println("let() called on $str")
            processNonNullString(str)
            lengthNoLet = str.length
        }


        // Without let
        val numbers = mutableListOf("one", "two", "three", "four", "five")
        val resultList = numbers.map { it.length }.filter { it > 3 }
        println(resultList)

        // With let
        numbers.map { it.length }.filter { it > 3 }.let { println(it) }
        // Using method reference
        numbers.map { it.length }.filter { it > 3 }.let(::println)
    }

    fun processNonNullString(str: String) {}

    /* ALSO
    Retorna el propio objeto. Se puede entender como " and also do the following with the object. ".
     */
    fun scopeAlso() {
        val numbers = mutableListOf("one", "two", "three")
        numbers
            .also { println("The list elements before adding new one: $it") }
            .add("four")

        var a = 1
        var b = 2
        a = b.also { b = a }
    }


    /*
    Funciones que hacen referencia al objeto del contexto con "this". Estas funciones son recomendadas para lambdas que
    operan principalmente con los miembros del objeto.
    */

    /* WITH
    Recomendable para llamar funciones sobre un objeto cuando no necesitas usar el resultado.
    Retorna el resultado del lambda.
     */
    fun scopeWith() {
        val numbers = mutableListOf("one", "two", "three")
        // Hay que pasar el objeto como argumento.
        with(numbers) {
            println("'with' is called with argument $this")
            println("It contains $size elements")
        }

        val firstAndLast = with(numbers) {
            "The first element is ${first()}, the last element is ${last()}"
        }
        println(firstAndLast)
    }

    /* RUN
    Recomendable cuando el lambda inicializa objetos y computa el valor de retorno. Retorna el resultado del lambda.
     */
    fun scopeRun() {
        class MultiportService(var url: String, var port: Int) {
            fun prepareRequest(): String = "Default request"
            fun query(request: String): String = "Result for query '$request'"
        }

        val service = MultiportService("https://example.kotlinlang.org", 80)

        val result = service.run {
            port = 8080
            query(prepareRequest() + " to port $port")
        }
    }

    /* APPLY
    Recomendable para code blocks que no devuelvan un valor y que operen con los miembros del objeto. Se suele usar
    para la configuración del objeto. Retorna el propio objeto.
     */
    fun scopeApply() {
        data class Person(var name: String, var age: Int = 0, var city: String = "")

        val adam = Person("Adam").apply {
            age = 32
            city = "London"
        }

        println(adam)
    }

    fun arrayOfMinusOnes(size: Int): IntArray {
        return IntArray(size).apply { fill(-1) }
    }

    /* TakeIf, TakeUnless
    No son scope functions pero se suelen usar junto a ellas. TakeIf devuelve el objeto sobre el que se usa si se
    satisface el predicado, devuelve null en caso contrario. TakeUnless hace lo contrario.
     */
    fun takeIfTakeUnless() {
        val number = Random.nextInt(100)

        val evenOrNull = number.takeIf { it % 2 == 0 }
        val oddOrNull = number.takeUnless { it % 2 == 0 }
        println("even: $evenOrNull, odd: $oddOrNull")

        val str = "Hello"
        val caps = str.takeIf { it.isNotEmpty() }?.uppercase()
        println(caps)
    }

    fun displaySubstringPositionWithoutTakeIf(input: String, sub: String) {
        val index = input.indexOf(sub)
        if (index >= 0) {
            println("The substring $sub is found in $input.")
            println("Its start position is $index.")
        }
    }

    fun displaySubstringPositionWithTakeIf(input: String, sub: String) {
        input.indexOf(sub).takeIf { it >= 0 }?.let {
            println("The substring $sub is found in $input.")
            println("Its start position is $it.")
        }
    }

}
package angel.parraga

class KotlinNullability {
    // Todos los tipos son no nullables por defecto. "a" no puede ser null, así que esta función nunca puede dar
    // NullPointerException
    fun stringLength(a: String): Int = a.length

    // Para hacer un tipo nullable hay que añadir "?" al tipo.
    fun stringLengthSafe(a: String?): Int = if (a != null) a.length else 0

    // Safe call "?." y elvis operator "?:".
    fun stringLengthSafeShort(a: String?): Int = a?.length ?: 0

    // IntelliJ ofrece ayudas para "kotlinizar" el código, aunque de primeras lo escribas como en Java.
    fun ideHelpsToShorten(b: String?): Int {
        if (b == null) {
            return 0
        } else {
            return b.length
        }
    }

    // Cadena de safe calls
    data class Trabajador(val departamento: Departamento?, val nombre: String?)
    data class Departamento(val jefe: Trabajador?)
    fun workerBossName(trabajador: Trabajador?): String? {
        return trabajador?.departamento?.jefe?.nombre
    }

    // Not-null assertion operator "!!" y smart cast
    fun workerBossNameNotNull(trabajador: Trabajador?): String? {
        val workerName = trabajador!!.nombre ?: "Paco"

        return trabajador.departamento!!.jefe!!.nombre
    }

    // Get or null
    fun getOrNull(numbers: List<Int>, i: Int) {
        val iValue = numbers.getOrNull(i)

        // Check orNull functions: "numbers.orNull"
        val max = numbers.maxOrNull()
    }

    // Filter not null
    fun filterNotNull() {
        val nullableList: List<Int?> = listOf(1, 2, null, 4)

        val intList: List<Int> = nullableList.filterNotNull()

        println(intList)
    }

}
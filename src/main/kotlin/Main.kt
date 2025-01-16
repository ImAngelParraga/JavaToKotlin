package angel.parraga

import SyntaxJava

fun main() {
    val javaSyntax = SyntaxJava()
    javaSyntax.simpleTest()
    javaSyntax.collections()

    val kotlinSyntax = SyntaxKotlin()
    kotlinSyntax.simpleTest()
}
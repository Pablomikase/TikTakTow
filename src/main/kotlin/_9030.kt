fun main() {

    val chars = charArrayOf('H', 'Y', 'P', 'E', 'R', '-', 'S', 'K', 'I', 'L', 'L' )

    val stringFromChars = String(chars)

    val strings = stringFromChars.split("-")

    println(strings)
}
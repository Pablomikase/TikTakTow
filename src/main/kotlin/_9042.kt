fun main() {

    //println(readln().split("-").asReversed().joinToString("/"))
    val userInput = readln()
    val arrayDate = userInput.split("-")
    println("${arrayDate[1]}/${arrayDate[2]}/${arrayDate[0]}")

}
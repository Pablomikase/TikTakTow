fun main() {

    val userInput = readln()

    val firstLetter = userInput.first()
    val lastLetter = userInput.last()

    println("$lastLetter${userInput.substring(1,userInput.length-1)}$firstLetter")

}
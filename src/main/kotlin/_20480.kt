fun main() {

    val userInput = readln()
    if (userInput.contains('u')) {
        println(
            userInput.substring(
                0,
                userInput.lastIndexOf('u') + 1
            ) + userInput.substring(userInput.lastIndexOf('u') + 1, userInput.length).uppercase()
        )
    } else {
        println(userInput)
    }

}
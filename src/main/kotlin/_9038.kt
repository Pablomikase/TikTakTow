fun main() {

    val userInput = readln().toCharArray()
    val result = mutableListOf<String>()
    var charCount = 1
    var previousChar = '&'

    for (currentChar in 0 until userInput.size-1) {

            if(userInput[currentChar] == userInput[currentChar+1]){
                charCount++
            } else {
                result.add(userInput[currentChar].toString())
                result.add(charCount.toString())
                charCount = 1
            }

        }

    result.add(userInput.last().toString())
    result.add(charCount.toString())

    println(result.joinToString(""))
}
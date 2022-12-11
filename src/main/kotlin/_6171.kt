fun main() {

    val userInput = readln()
   /* alphabet@for(letter in 'a' .. 'z'){

        for(currentLetter in userInput){
            if (currentLetter == letter) continue@alphabet
        }
        print(letter)
    }*/

    alphabet@for(letter in 'a' .. 'z'){
        if (userInput.contains(letter)) continue@alphabet
        print(letter)
    }

}
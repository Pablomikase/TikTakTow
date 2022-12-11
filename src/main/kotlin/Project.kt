import java.lang.Exception
import java.lang.NumberFormatException
import kotlin.math.abs

enum class GameStatus {
    NOT_FINISHED, DRAW, X_WINS, O_WINS, IMPOSSIBLE
}

fun main() {
    val grid = getEmptyGrid()
    var isGameOver = false
    printGrid(grid)
    startLoop(isGameOver, grid)

}

fun startLoop(isGameOver: Boolean, grid: MutableList<MutableList<Char>>) {

    while (!isGameOver){
        askUserNextMove(grid, 'X')
        printGrid(grid)
        val firstGameStatus = getStatusGame(grid)
        if (firstGameStatus != GameStatus.NOT_FINISHED){
            printGameStatus(firstGameStatus)
            break
        }
        printGameStatus(firstGameStatus)
        askUserNextMove(grid, 'O')
        printGrid(grid)
        val secondGameStatus = getStatusGame(grid)
        if (secondGameStatus != GameStatus.NOT_FINISHED){
            printGameStatus(secondGameStatus)
            break
        }
        printGameStatus(firstGameStatus)
    }

}

fun askUserNextMove(grid: MutableList<MutableList<Char>>, player: Char) {

    var isTheCurrentInputCorrect = false
    while (!isTheCurrentInputCorrect){
        val userInput = readln().split(" ")
        var rowValue = -1
        var columnValue = -1
        try {
            rowValue = userInput.first().toInt() - 1
            columnValue = userInput.last().toInt() - 1
        } catch (ex: NumberFormatException) {
            println("You should enter numbers!")
            isTheCurrentInputCorrect = false
            continue
        }
        if (rowValue !in 0..2 || columnValue !in 0..2) {
            println("Coordinates should be from 1 to 3!")
            isTheCurrentInputCorrect = false
            continue
        }
        if (grid[rowValue][columnValue] == 'X' || grid[rowValue][columnValue] == 'O') {
            println("This cell is occupied! Choose another one!")
            isTheCurrentInputCorrect = false
            continue
        }
        if (grid[rowValue][columnValue] == '_'){
            grid[rowValue][columnValue] = player
            isTheCurrentInputCorrect = true
            continue
        }
    }


}

fun printGameStatus(statusGame: GameStatus) {
    when (statusGame) {
        GameStatus.NOT_FINISHED -> return
        GameStatus.X_WINS -> println("X wins")
        GameStatus.O_WINS -> println("O wins")
        GameStatus.DRAW -> println("Draw")
        else -> println("Impossible")
    }
}

fun getStatusGame(grid: MutableList<MutableList<Char>>): GameStatus {

    val xWins = checkIfXWins(grid)
    val oWins = checkIfOWins(grid)

    //Imposible
    if (xWins && oWins) return GameStatus.IMPOSSIBLE

    //Winers
    if (xWins) return GameStatus.X_WINS
    if (oWins) return GameStatus.O_WINS

    //Draw or uncompleted
    var xNumber = 0U
    var oNumber = 0U
    var blankNumber = 0U
    grid.forEach { row ->
        row.forEach {
            if (it == 'X') xNumber++
            if (it == 'O') oNumber++
            if (it == '_') blankNumber++
        }
    }

    when {
        blankNumber > 0U && abs(xNumber.toInt() - oNumber.toInt()) <= 1 -> return GameStatus.NOT_FINISHED
        blankNumber == 0U && abs(xNumber.toInt() - oNumber.toInt()) <= 1 -> return GameStatus.DRAW
        abs(xNumber.toInt() - oNumber.toInt()) > 1 -> return GameStatus.IMPOSSIBLE
    }


    return GameStatus.IMPOSSIBLE
}

fun checkIfXWins(grid: MutableList<MutableList<Char>>): Boolean {
    for (rowIndex in grid.indices) {
        if (grid[rowIndex][0] == 'X' &&
            grid[rowIndex][1] == 'X' &&
            grid[rowIndex][2] == 'X'
        ) return true
    }
    for (columnIndex in grid.indices) {
        when {
            (grid[0][columnIndex] == 'X' &&
                    grid[1][columnIndex] == 'X' &&
                    grid[2][columnIndex] == 'X') -> return true

            (grid[0][columnIndex] == 'X' &&
                    grid[1][columnIndex] == 'X' &&
                    grid[2][columnIndex] == 'X') -> return true
        }
    }
    when {
        (grid[0][0] == 'X' &&
                grid[1][1] == 'X' &&
                grid[2][2] == 'X') -> return true

        (grid[2][0] == 'X' &&
                grid[1][1] == 'X' &&
                grid[0][2] == 'X') -> return true
    }
    return false
}

fun checkIfOWins(grid: MutableList<MutableList<Char>>): Boolean {
    for (rowIndex in grid.indices) {
        if (grid[rowIndex][0] == 'O' &&
            grid[rowIndex][1] == 'O' &&
            grid[rowIndex][2] == 'O'
        ) return true
    }
    for (columnIndex in grid.indices) {
        when {
            (grid[0][columnIndex] == 'O' &&
                    grid[1][columnIndex] == 'O' &&
                    grid[2][columnIndex] == 'O') -> return true

            (grid[0][columnIndex] == 'O' &&
                    grid[1][columnIndex] == 'O' &&
                    grid[2][columnIndex] == 'O') -> return true
        }
    }
    when {
        (grid[0][0] == 'O' &&
                grid[1][1] == 'O' &&
                grid[2][2] == 'O') -> return true

        (grid[2][0] == 'O' &&
                grid[1][1] == 'O' &&
                grid[0][2] == 'O') -> return true
    }
    return false
}

fun printGrid(grid: MutableList<MutableList<Char>>) {
    println("---------")
    grid.forEach { row ->
        print("| ")
        row.forEach { column ->
            print("$column ")
        }
        print("|\n")
    }
    println("---------")
}

fun getEmptyGrid(): MutableList<MutableList<Char>> {
    val userInputList = try {
        "_________"
    } catch (ex: Exception) {
        "_________"
    }.map { it.uppercaseChar() }
    val generalException = Exception("The input is not correct")
    when {
        //userInputList.size != 9 -> throw generalException
        //areValidElements(userInputList) -> throw generalException
    }
    return mutableListOf(
        userInputList.subList(0, 3).toMutableList(),
        userInputList.subList(3, 6).toMutableList(),
        userInputList.subList(6, 9).toMutableList()
    )
}

fun areValidElements(userInputList: List<Char>): Boolean {
    val result = true
    userInputList.forEach {
        if (it != 'X' && it != 'O' && it != '_') {
            return false
        }
    }
    return result
}
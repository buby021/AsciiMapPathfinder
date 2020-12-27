package AsciiMap

import java.lang.Exception
import java.lang.IndexOutOfBoundsException
import java.lang.StringBuilder
import enums.Direction
import enums.Direction.DOWN
import enums.Direction.UP
import enums.Direction.LEFT
import enums.Direction.RIGHT
import enums.Characters
import utils.Constants

class MapSolver(val map: String) {

    var mapMatrix = map.split("\n")
    var position: Position? = null
    var visitedPositions: ArrayList<Position> = arrayListOf()
    var direction: Direction? = null
    var characterAtPosition: Char = '\u0000'
    var path: StringBuilder = StringBuilder()
    var letters: StringBuilder = StringBuilder()


    fun solve(): Result {
        val mapMatrix = map.split("\n")

        val starts = map.filter { it.equals(Characters.START.char) }
        if (starts.length > 1)
            throw MapSolverError(Constants.ERROR_MULTIPLE_STARTS)

        val ends = map.filter { it.equals(Characters.END.char) }
        if (ends.length > 1)
            throw MapSolverError(Constants.ERROR_MULTIPLE_ENDS)


        //find START
        mapMatrix.forEachIndexed() { index, row ->
            if (row.find { it.equals(Characters.START.char) } != null) {
                position = Position(row.indexOf(Characters.START.char), index)
                return@forEachIndexed
            }
        }


        if (position == null)
            throw MapSolverError(Constants.ERROR_NO_STARTING_POINT)

        //loop go to next position until END
        var counter = 0
        while (position != null) {
            counter++
            characterAtPosition = mapMatrix[position!!.y][position!!.x]
            if (counter > 1 && characterAtPosition == Characters.START.char) {
                throw MapSolverError(Constants.ERROR_MULTIPLE_STARTS)
            }
            path.append(characterAtPosition)
            val contains = visitedPositions.find { it.x == position?.x && it.y == position?.y }
            if (contains == null && characterAtPosition.isUpperCase() && characterAtPosition.isLetter()) {
                letters.append(characterAtPosition)
            }
            visitedPositions.add(position!!)


            //find direction
            var directionChoice = emptyList<Direction>()
            if (direction == null) {
                //checkAllDirections
                directionChoice = listOf(Direction.RIGHT, Direction.LEFT, DOWN, Direction.UP)
            } else {
                val ortogonalDirection = getOrtogonalDirection(direction!!)
                if (characterAtPosition == Characters.CORNER.char) {
                    directionChoice = listOf(ortogonalDirection, getOppositeDirection(ortogonalDirection))
                } else if (characterAtPosition.isUpperCase() && characterAtPosition.isLetter()) {
                    directionChoice = listOf(direction!!, ortogonalDirection, getOppositeDirection(ortogonalDirection))
                } else {
                    directionChoice = listOf(direction!!)
                }
            }

            if (characterAtPosition == Characters.END.char) {
                position = null
            } else {
                try {
                    direction = getNextDirection(directionChoice)
                } catch (e: Exception) {
                    throw MapSolverError(e.message, e)
                }
                position = getRelativePosition(direction!!, position!!)
            }


        }
        return Result(path.toString(), letters.toString())
    }

    fun getRelativePosition(direction: Direction, currentPosition: Position): Position? {

        if (direction == null) return null

        if (direction == LEFT) {
            return Position(currentPosition.x - 1, currentPosition.y)
        } else if (direction == RIGHT) {
            return Position(currentPosition.x + 1, currentPosition.y)
        } else if (direction == UP) {
            return Position(currentPosition.x, currentPosition.y - 1)
        } else {
            return Position(currentPosition.x, currentPosition.y + 1)
        }
    }

    fun getOppositeDirection(direction: Direction): Direction {
        if (direction == UP) return DOWN
        if (direction == DOWN) return UP
        if (direction == LEFT) return RIGHT
        return LEFT
    }

    fun getOrtogonalDirection(direction: Direction): Direction {
        if (direction == LEFT || direction == RIGHT) return UP
        return LEFT
    }

    fun getNextDirection(directions: List<Direction>): Direction? {
        directions.forEach { direction ->
            val position = getRelativePosition(direction, position!!)
            try {
                val tempChar = mapMatrix[position!!.y][position.x]
                if (!tempChar.equals(Characters.EMPTY.char)) {
                    return direction
                }
            } catch (error: IndexOutOfBoundsException) {

            }
        }
        throw MapSolverError(Constants.ERROR_NO_END)
    }
}
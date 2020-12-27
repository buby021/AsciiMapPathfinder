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

class MapSolver(private val map: String) {

    private var mapMatrix = map.split("\n")
    private var position: Position? = null
    private var visitedPositions: ArrayList<Position> = arrayListOf()
    private var direction: Direction? = null
    private var characterAtPosition: Char = '\u0000'
    var path: StringBuilder = StringBuilder()
    var letters: StringBuilder = StringBuilder()


    fun solve(): Result {
        val mapMatrix = map.split("\n")

        val starts = map.filter { it == (Characters.START.char) }
        if (starts.length > 1)
            throw MapSolverError(Constants.ERROR_MULTIPLE_STARTS)

        val ends = map.filter { it == (Characters.END.char) }
        if (ends.length > 1)
            throw MapSolverError(Constants.ERROR_MULTIPLE_ENDS)


        //find START
        mapMatrix.forEachIndexed { index, row ->
            if (row.find { it == (Characters.START.char) } != null) {
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
            val directionChoice: List<Direction> = if (direction == null) {
                listOf(RIGHT, LEFT, DOWN, UP)
            } else {
                val ortogonalDirection = getOrtogonalDirection(direction!!)
                if (characterAtPosition == Characters.CORNER.char) {
                    listOf(ortogonalDirection, getOppositeDirection(ortogonalDirection))
                } else if (characterAtPosition.isUpperCase() && characterAtPosition.isLetter()) {
                    listOf(direction!!, ortogonalDirection, getOppositeDirection(ortogonalDirection))
                } else {
                    listOf(direction!!)
                }
            }

            if (characterAtPosition == Characters.END.char) {
                position = null
            } else {
                try {
                    direction = getNextDirection(directionChoice, characterAtPosition == Characters.CORNER.char)
                } catch (e: Exception) {
                    throw MapSolverError(e.message, e)
                }
                position = getRelativePosition(direction!!, position!!)
            }


        }
        return Result(path.toString(), letters.toString())
    }

    private fun getRelativePosition(direction: Direction, currentPosition: Position): Position {

        return when (direction) {
            LEFT -> {
                Position(currentPosition.x - 1, currentPosition.y)
            }
            RIGHT -> {
                Position(currentPosition.x + 1, currentPosition.y)
            }
            UP -> {
                Position(currentPosition.x, currentPosition.y - 1)
            }
            else -> {
                Position(currentPosition.x, currentPosition.y + 1)
            }
        }
    }

    private fun getOppositeDirection(direction: Direction): Direction {
        if (direction == UP) return DOWN
        if (direction == DOWN) return UP
        if (direction == LEFT) return RIGHT
        return LEFT
    }

    private fun getOrtogonalDirection(direction: Direction): Direction {
        if (direction == LEFT || direction == RIGHT) return UP
        return LEFT
    }

    private fun getNextDirection(directions: List<Direction>, checkForTfork: Boolean = false): Direction {
        var counter = 0
        var cornerDirection = directions.first()
        directions.forEach { direction ->
            val position = getRelativePosition(direction, position!!)
            try {
                val nextCharacter = mapMatrix[position.y][position.x]
                if (nextCharacter != Characters.EMPTY.char) {
                    if (checkForTfork) {
                        counter++
                        cornerDirection = direction
                    } else {
                        return direction
                    }
                }
            } catch (error: IndexOutOfBoundsException) {

            }
        }
        if (counter > 1) {
            throw MapSolverError(Constants.ERROR_T_FORK)
        } else if (counter > 0) {
            return cornerDirection
        }
        throw MapSolverError(Constants.ERROR_NO_END)
    }
}
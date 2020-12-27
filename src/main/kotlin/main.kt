import AsciiMap.MapSolver
import AsciiMap.MapSolverError
import utils.MapFiles.Companion.MAP1
import utils.MapFiles.Companion.MAP7
import utils.MapFiles.Companion.MAP8

fun main(args: Array<String>) {


    println(MAP8)
    val mapSolver = MapSolver(MAP8)
    try {
        val result = mapSolver.solve()
        println(result.path)
        println(result.letters)
    } catch (error: MapSolverError) {
        println(error.message)
    }

}




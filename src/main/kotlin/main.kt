import AsciiMap.MapSolver
import AsciiMap.MapSolverError
import utils.MapFiles.Companion.MAP1
import utils.MapFiles.Companion.MAP2
import utils.MapFiles.Companion.MAP3
import utils.MapFiles.Companion.MAP4
import utils.MapFiles.Companion.MAP5
import utils.MapFiles.Companion.MAP6
import utils.MapFiles.Companion.MAP7
import utils.MapFiles.Companion.MAP8
import utils.MapFiles.Companion.MAP9
import utils.MapFiles.Companion.MAP10

fun main() {


    println(MAP1)
    val mapSolver = MapSolver(MAP1)
    try {
        val result = mapSolver.solve()
        println(result.path)
        println(result.letters)
    } catch (error: MapSolverError) {
        println(error.message)
    }

}




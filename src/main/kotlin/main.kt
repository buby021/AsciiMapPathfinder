import AsciiMap.MapSolver
import AsciiMap.MapSolverError
import utils.MapFiles
import utils.MapFiles.Companion.MAP1


fun main(args: Array<String>) {
    val mapName = if (!args.isNullOrEmpty()) args[0] else "MAP7"
    val map = MapFiles.getHardcodedMapByName(mapName)
    println(map)
    val mapSolver = MapSolver(map)
    try {
        val result = mapSolver.solve()
        println(result.path)
        println(result.letters)
    } catch (error: MapSolverError) {
        println(error.message)
    }
}


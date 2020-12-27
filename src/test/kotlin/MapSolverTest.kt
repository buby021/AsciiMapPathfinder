import AsciiMap.MapSolver
import AsciiMap.MapSolverError
import utils.MapFiles.Companion.MAP1
import utils.MapFiles.Companion.MAP2
import utils.MapFiles.Companion.MAP3
import utils.MapFiles.Companion.MAP4
import utils.MapFiles.Companion.MAP5
import utils.MapFiles.Companion.MAP6
import utils.MapFiles.Companion.MAP7
import org.junit.Test
import utils.Constants
import utils.MapFiles.Companion.MAP10
import utils.MapFiles.Companion.MAP8
import utils.MapFiles.Companion.MAP9
import kotlin.test.assertEquals

class MapSolverTest {

    @Test
    fun testMap1() {
        val mapSolver = MapSolver(MAP1)
        mapSolver.solve()
        assertEquals("ACB", mapSolver.letters.toString())
        assertEquals("@---A---+|C|+---+|+-B-x", mapSolver.path.toString())
    }

    @Test
    fun testMap2() {
        val mapSolver = MapSolver(MAP2)
        mapSolver.solve()
        assertEquals("ABCD", mapSolver.letters.toString())
        assertEquals("@|A+---B--+|+--C-+|-||+---D--+|x", mapSolver.path.toString())
    }


    @Test
    fun testMap3() {
        val mapSolver = MapSolver(MAP3)
        mapSolver.solve()
        assertEquals("ACB", mapSolver.letters.toString())
        assertEquals("@---A---+|||C---+|+-B-x", mapSolver.path.toString())
    }

    @Test
    fun testMap4() {
        val mapSolver = MapSolver(MAP4)
        mapSolver.solve()
        assertEquals("ABCD", mapSolver.letters.toString())
        assertEquals("@--A-+|+-+|A|+--B--+C|+-+|+-C-+|D|x", mapSolver.path.toString())
    }


    @Test
    fun testMap5() {
        val mapSolver = MapSolver(MAP5)
        mapSolver.solve()
        assertEquals("ABCD", mapSolver.letters.toString())
        assertEquals("@A+++A|+-B-+C+++C-+Dx", mapSolver.path.toString())
    }

    @Test
    fun testMap6() {
        val mapSolver = MapSolver(MAP6)
        try {
            val result = mapSolver.solve()
        } catch (error : MapSolverError) {
            assertEquals(MapSolverError(Constants.ERROR_NO_STARTING_POINT).localizedMessage, error.localizedMessage)
        }

    }

    @Test
    fun testMap7() {
        val mapSolver = MapSolver(MAP7)
        try {
            val result = mapSolver.solve()
        } catch (error: MapSolverError) {
            assertEquals(MapSolverError(Constants.ERROR_NO_END).message, error.message)
        }
    }

    @Test
    fun testMap8() {
        val mapSolver = MapSolver(MAP8)
        try {
            val result = mapSolver.solve()
        } catch (error: MapSolverError) {
            assertEquals(MapSolverError(Constants.ERROR_MULTIPLE_STARTS).message, error.message)
        }
    }

    @Test
    fun testMap9() {
        val mapSolver = MapSolver(MAP9)
        try {
            val result = mapSolver.solve()
        } catch (error: MapSolverError) {
            assertEquals(MapSolverError(Constants.ERROR_MULTIPLE_ENDS).message, error.message)
        }
    }

    @Test
    fun testMap10() {
        val mapSolver = MapSolver(MAP10)
        try {
            val result = mapSolver.solve()
        } catch (error: MapSolverError) {
            assertEquals(MapSolverError(Constants.ERROR_MULTIPLE_ENDS).message, error.message)
        }
    }

}
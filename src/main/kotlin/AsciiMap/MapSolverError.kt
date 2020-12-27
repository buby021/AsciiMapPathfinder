package AsciiMap

class MapSolverError : Exception {
    protected var id = 0

    constructor(message: String?) : super(message) {
        id = 0
    }

    constructor(message: String?, throwable: Throwable) : super(message, throwable) {
        id = 0
    }

}
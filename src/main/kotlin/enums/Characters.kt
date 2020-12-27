package enums

enum class Characters(val char: Char) {
    START('@'),
    END('x'),
    HORIZONTAL('-'),
    VERTICAL('|'),
    CORNER('+'),
    EMPTY(' ')
}
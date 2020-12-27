package utils

class MapFiles {

    companion object {

        fun getHardcodedMapByName(name: String): String {
            return when (name) {
                "MAP1" -> {
                    MAP1
                }
                "MAP2" -> {
                    MAP2
                }
                "MAP3" -> {
                    MAP3
                }
                "MAP4" -> {
                    MAP4
                }
                "MAP5" -> {
                    MAP5
                }
                "MAP6" -> {
                    MAP6
                }
                "MAP7" -> {
                    MAP7
                }
                "MAP8" -> {
                    MAP8
                }
                "MAP9" -> {
                    MAP9
                }
                "MAP10" -> {
                    MAP10
                }

                else -> MAP1
            }
        }

        val MAP1 = "@---A---+\n" +
                "        |\n" +
                "x-B-+   C\n" +
                "    |   |\n" +
                "    +---+"

        val MAP2 = "  @         \n" +
                "  | +-C--+  \n" +
                "  A |    |  \n" +
                "  +---B--+  \n" +
                "    |      x\n" +
                "    |      |\n" +
                "    +---D--+ "

        val MAP3 = "  @---A---+\n" +
                "          |\n" +
                "  x-B-+   |\n" +
                "      |   |\n" +
                "      +---C"

        val MAP4 = "    +--B--+  \n" +
                "    |   +-C-+\n" +
                " @--A-+ | | |\n" +
                "    | | +-+ D\n" +
                "    +-+     |\n" +
                "            x "

        val MAP5 = " +-B-+  \n" +
                " |  +C-+\n" +
                "@A+ ++ D\n" +
                " ++    x "

        val MAP6 = "     -A---+\n" +
                "          |\n" +
                "  x-B-+   C\n" +
                "      |   |\n" +
                "      +---+ "

        val MAP7 = "   @--A---+\n" +
                "          |\n" +
                "    B-+   C\n" +
                "      |   |\n" +
                "      +---+ "

        val MAP8 = "   @--A-@-+\n" +
                "          |\n" +
                "  x-B-+   C\n" +
                "      |   |\n" +
                "      +---+ "

        val MAP9 = "   @--A---+\n" +
                "          |\n" +
                "  x-Bx+   C\n" +
                "      |   |\n" +
                "      +---+ "

        val MAP10 = "        x-B\n" +
                "          |\n" +
                "   @--A---+\n" +
                "          |\n" +
                "     x+   C\n" +
                "      |   |\n" +
                "      +---+ "
    }
}
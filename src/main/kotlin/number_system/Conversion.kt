package number_system

object Conversion {
    /*
     * Write a function that will return the binary value of parameter.
     * Bits are represented from MSB to LSB and are separated by a period (.) after each byte.
     * Please refer to the tests for expected output.
     */
    fun decToBinary(value: Int): String {
        if (value == 0) {
            return "0"
        } else if (value == 1) {
            return "1"
        }

        var shiftedValue = 1
        val sb = StringBuilder()
        var count = 0

        for (x in 0 until Int.SIZE_BITS) {
            val result = shiftedValue and value

            if (value > 0 && (x == Int.SIZE_BITS - 1 || shiftedValue > value)) {
                break
            }

            if (count == 8) {
                sb.append('.')

                count = 1
            } else {
                count++
            }

            sb.append(if (result != 0) '1' else '0')

            shiftedValue = shiftedValue.shl(1)
        }

        return sb.reverse().toString()
    }
}

fun main() {
    println(Conversion.decToBinary(12357))
}
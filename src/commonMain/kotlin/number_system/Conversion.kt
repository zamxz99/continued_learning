package number_system
import kotlin.math.abs
object Conversion {
    /*
     * Write a function that will return the binary value of parameter.
     * Bits are represented from MSB to LSB and are separated by a period (.) after each byte.
     * Please refer to the tests for expected output.
     */
    fun decToBinary(value: Int): String {
        //format
        val res = decToBinaryConversion(value)
        //take every 8 chars
        var final = ""
        for (i in 1 .. res.length){
            //get last char
            final = "${res.get(res.length - i)}$final"
            if (i % 8 == 0 && i !=res.length){
                final = ".$final"
            }
        }
        return final
    }

    fun decToBinaryConversion(value: Int): String {
        //handle negatives
        if (value < 0){
            return twosComplement(decToBinaryConversion(abs(value)))
        }
        // base case
        if (value == 0)
            return "0"
        if (value == 1)
            return "1"
        else {
            //mod and recurse
            val sig = decToBinaryConversion(value / 2)
            var remainder = decToBinaryConversion(value % 2)
            return sig + remainder
        }
    }

    fun twosComplement(value: String): String {
        // pad if not 32 bits
        var padded = value
        if (padded.length < 32){
            for (i in padded.length .. 31){
                padded = "0$padded"
            }
        }
        // flip all the bits
        var flipped = ""
        for (i in 0 .. padded.length-1){
            val flippedBit = if (padded.get(i) == '1') "0" else "1"
            flipped += flippedBit
        }

        //add one
        for (i in flipped.length-1 downTo 0){
            if (flipped[i]=='0'){
                flipped = flipped.replaceRange(i,i+1, "1")
                break
            }
            else { // flip it to 0 and "carry" the 1 (go to the next integer)
                flipped = flipped.replaceRange(i,i+1, "0")
            }
        }
        return flipped
    }
}

fun main() {
    println(Conversion.decToBinary(12357))
}
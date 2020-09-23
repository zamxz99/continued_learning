package number_system

import kotlin.test.Test
import kotlin.test.assertEquals

class ConversionTest {
    @Test
    fun zeroCase() {
        val value = Conversion.decToBinary(0)
        assertEquals("0" , value)
    }

    @Test
    fun oneCase() {
        val value = Conversion.decToBinary(1)
        assertEquals("1" , value)
    }

    @Test
    fun sevenCase() {
        val value = Conversion.decToBinary(7)
        assertEquals("111" , value)
    }

    @Test
    fun greaterThanOneCase() {
        val value = Conversion.decToBinary(12357)
        assertEquals("110000.01000101" , value)
    }

    @Test
    fun lessThanOneCase() {
        val value = Conversion.decToBinary(-12357)
        assertEquals("11111111.11111111.11001111.10111011" , value)
    }


    @Test
    fun maxValueCase() {
        val value = Conversion.decToBinary(Int.MAX_VALUE)
        assertEquals("1111111.11111111.11111111.11111111" , value)
    }

    @Test
    fun minValueCase() {
        val value = Conversion.decToBinary(Int.MIN_VALUE)
        assertEquals("10000000.00000000.00000000.00000000" , value)
    }



    @Test
    fun negativeOneCase() {
        val value = Conversion.decToBinary(-1)
        assertEquals("11111111.11111111.11111111.11111111" , value)
    }
}
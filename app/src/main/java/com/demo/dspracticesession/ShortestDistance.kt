package com.demo.dspracticesession

import kotlin.math.abs

class ShortestDistance {

    fun shortestToChar(s: String, c: Char): IntArray {
        val arrayChar: CharArray = s.toCharArray()
        val array = IntArray(arrayChar.size)
        var charCurrentIndex = -1
        arrayChar.forEachIndexed { index, charItem ->
            if(charItem == c) {
                array[index] = 0
                charCurrentIndex = index
                //loop to update distance array
                for(i in 0..charCurrentIndex ) {
                    val value = array[i]
                    val distance = abs(i-charCurrentIndex)
                    if(value == -1 || value > distance) {
                        array[i] = distance
                    }
                }
            } else {
                if(charCurrentIndex > -1) {
                    array[index] = abs(index-charCurrentIndex)
                } else {
                    array[index] = -1
                }
            }
        }
        return array
    }

}
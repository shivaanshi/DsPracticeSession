package com.demo.dspracticesession

class StoneGame {

    fun stoneGame(piles: IntArray): Boolean {
        var startSum = 0
        var endSum = 0
        var bobTurn = true
        piles.forEach { i ->
            if(bobTurn) {
                startSum +=i
                bobTurn = false
            } else {
                endSum +=i
                bobTurn = true
            }
        }
        return true
    }

}
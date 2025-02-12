package com.demo.dspracticesession

class BuySellStock {

    fun maxProfit(prices: IntArray): Int {
        var maxProfit = 0
        var currentBuy = 0
        prices.forEachIndexed{index, item ->
            if(index != 0 && currentBuy<item) {
                maxProfit += item-currentBuy
            }
            currentBuy = item
        }
        return maxProfit
    }

}
package com.demo.dspracticesession

class GasStation {

    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
        var index = 0
        var startingIndex = -1
        var availableFuel = 0
        var requiredFuel = 0
        while (index > -1) {
            if(index == startingIndex) {
                break
            }
            if (gas[index] >= cost[index] && startingIndex == -1) {
                startingIndex = index
                availableFuel = gas[index]
                requiredFuel = cost[index]
                index = if(index == gas.size-1) 0 else index+1
            } else if(startingIndex != -1) {
                availableFuel = availableFuel + gas[index] - requiredFuel
                requiredFuel = cost[index]
                if(availableFuel < requiredFuel) {
                    availableFuel = 0
                    requiredFuel = 0
                    if(startingIndex >= index) {
                        startingIndex = -1
                        break
                    }
                    startingIndex = -1
                    if(index == gas.size-1)
                        break
                }
                index = if(index == gas.size-1) 0 else index+1
            } else {
                if(index == gas.size-1)
                    break
                else index++
            }
        }
        return startingIndex
    }


}
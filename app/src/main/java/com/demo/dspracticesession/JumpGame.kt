package com.demo.dspracticesession

class JumpGame {
    fun canJump(nums: IntArray): Boolean {
        var canJump = false
        var currentIndex = 0
        var nextIndex = 0
        while (currentIndex < nums.size) {
            val value = nums[currentIndex]
            if(value+currentIndex > nextIndex) {
                nextIndex = value+currentIndex
            }
            if(nextIndex == currentIndex) {
                if(nums[currentIndex] == 0) {
                    if (nextIndex == nums.size - 1) {
                        canJump = true
                        break
                    }
                    canJump = false
                    break
                }
                nextIndex = value+currentIndex
            }
            if (nextIndex >= nums.size - 1) {
                canJump = true
                break
            }
            currentIndex++
        }
        return canJump
    }

}
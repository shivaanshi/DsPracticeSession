package com.demo.dspracticesession

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MergeSortedArray {

    companion object {
        fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): IntArray {

            if (n == 0) {
                return nums1
            }
            nums2.forEachIndexed { index, value ->
                for (i in 0..m + index) {
                    if (nums1[i] == 0 && i == m + index) {
                        nums1[i] = nums2[index]
                    } else if (nums1[i] > nums2[index]) {
                        val temp = nums1[i]
                        nums1[i] = nums2[index]
                        nums2[index] = temp
                    }
                }
            }
            return nums1
        }

        suspend fun asyncWay() = coroutineScope {
            val allItems = listOf(
            /*val message = */async {
                delay(5000)
                println("asyncWay This is function first before")
                return@async "This is function first"
            },
            /*val value = */async {
                delay(10000)
                println("asyncWay This is function second before")
                return@async "This is function second"
            }
            ).awaitAll()

            launch {println("asyncWay this is neutral")}

            val messageResult = allItems[0]//message.await()
            val valueResult = allItems[1]//value.await()



            println("asyncWay: $messageResult $valueResult")

        }

    }

}
import kotlin.random.Random

class HeapSort {
    fun <T : Comparable<T>> sort(iterable: MutableIterable<T>): MutableIterable<T> {
        val source = iterable.toCollection(ArrayList())
        var length = source.size
        if (length > 1) {
            val lastTrunk = length / 2 - 1
            for (trunk in lastTrunk downTo 0) {
                justify(source, trunk, length)
            }
            source.swap(0, --length)
            while (length > 1) {
                tidy(source, 0, length)
                source.swap(0, --length)
            }
        }

        return source
    }

    private fun <T : Comparable<T>> tidy(data: ArrayList<T>, trunk: Int, length: Int) {
        var targetIndex = trunk
        while (targetIndex < length) {
            val leftIndex = targetIndex * 2 + 1
            val rightIndex = targetIndex * 2 + 2

            if (leftIndex < length && rightIndex < length) {
                targetIndex = if (data[leftIndex] > data[rightIndex]) {
                    if (data[targetIndex] < data[leftIndex]) {
                        data.swap(leftIndex, targetIndex)
                        leftIndex
                    } else {
                        return
                    }
                } else {
                    if (data[targetIndex] < data[rightIndex]) {
                        data.swap(rightIndex, targetIndex)
                        rightIndex
                    } else {
                        return
                    }
                }
            } else if (leftIndex < length) {
                if (data[targetIndex] < data[leftIndex]) {
                    data.swap(targetIndex, leftIndex)
                    targetIndex = leftIndex
                } else {
                    return
                }
            } else if (rightIndex < length) {
                if (data[targetIndex] < data[rightIndex]) {
                    data.swap(targetIndex, rightIndex)
                    targetIndex = rightIndex
                } else {
                    return
                }
            } else {
                return
            }
        }
    }

    private fun <T : Comparable<T>> justify(data: ArrayList<T>, trunk: Int, length: Int) {
        val leftIndex = (1 + trunk) * 2 - 1
        val rightIndex = (1 + trunk) * 2


        if (leftIndex < length && rightIndex < length) {
            if (data[leftIndex] > data[rightIndex]) {
                if (data[leftIndex] > data[trunk]) {
                    data.swap(leftIndex, trunk)
                    tidy(data, leftIndex, length)
                }
            } else {
                if (data[rightIndex] > data[trunk]) {
                    data.swap(rightIndex, trunk)
                    tidy(data, rightIndex, length)
                }
            }
        } else if (leftIndex < length) {
            if (data[leftIndex] > data[trunk]) {
                data.swap(leftIndex, trunk)
            }

        } else if (rightIndex < length) {
            if (data[rightIndex] > data[trunk]) {
                data.swap(rightIndex, trunk)
            }
        }
    }

    companion object {
        private fun <T> ArrayList<T>.swap(posA: Int, posB: Int) {
            val data = this[posA]
            this[posA] = this[posB]
            this[posB] = data
        }
    }
}

fun main() {
    val data = mutableListOf(54, 32, 1, 2, 4, 32, 435, 12123123, 123, 2323, 343, 54, 76, 0)
    println(data)
    println(HeapSort().sort(data))

    val random = Random(0)
    data.clear()
    val DATA_LENGTH = 100_0000
    for (c in 0..DATA_LENGTH) {
        data.add(random.nextInt())
    }
    val startTime = System.currentTimeMillis()
    HeapSort().sort(data)
    val endTime = System.currentTimeMillis()
    println("Sort $DATA_LENGTH integers in ${(endTime - startTime) / 1000.0}s")
}
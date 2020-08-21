import kotlin.random.Random

/**
 * 一个十分简单的希尔排序(shell排序)
 */
fun shell(list: MutableList<Int>): MutableList<Int> {
    var increment = list.size
    do {
        increment /= 2
        if (increment <= 1) {
            increment = 1
        }
        var segmentCount = list.size / increment
        if (list.size % increment != 0) {
            segmentCount += 1
        }
        for (i in 0 until increment) {
            for (j in 1 until segmentCount) {
                sortPrevious@
                for (nj in j downTo 1) {
                    val sortPreviousCurrentIndex = nj * increment + i
                    val sortPreviousPreviousIndex = (nj - 1) * increment + i
                    if (sortPreviousCurrentIndex < list.size) {
                        if (list[sortPreviousCurrentIndex] < list[sortPreviousPreviousIndex]) {
                            val data = list[sortPreviousCurrentIndex]
                            list[sortPreviousCurrentIndex] = list[sortPreviousPreviousIndex]
                            list[sortPreviousPreviousIndex] = data
                        } else {
                            //排好了
                            break@sortPrevious
                        }
                    }
                }

            }
        }
    } while (increment != 1)
    return list
}

fun main() {
    val data = mutableListOf<Int>(32, 322, 12, 4, 5, 6, 12, 1, 2, 3, 67, 9)
    println(data)
    println(shell(data))

    val random = Random(0)
    data.clear()
    val DATA_LENGTH = 100_0000
    for (c in 0..DATA_LENGTH) {
        data.add(random.nextInt())
    }
    val startTime = System.currentTimeMillis()
    shell(data)
    val endTime = System.currentTimeMillis()
    println("Sort $DATA_LENGTH integers in ${(endTime - startTime)/1000.0}s")
}
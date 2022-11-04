package movement.time

import model.ClockData


object NineMatrix : DigitMatrix() {
    override fun getRow1(): List<ClockData?> {
        return listOf(
            time_3_30(),
            time_3_45(),
            time_6_45()
        )
    }

    override fun getRow2(): List<ClockData?> {
        return listOf(
            time_6(),
            time_6_30(),
            time_6()
        )
    }

    override fun getRow3(): List<ClockData?> {
        return listOf(
            time_6(),
            time_12(),
            time_6()
        )
    }

    override fun getRow4(): List<ClockData?> {
        return listOf(
            time_3(),
            time_6_45(),
            time_6()
        )
    }

    override fun getRow5(): List<ClockData?> {
        return listOf(
            time_3_30(),
            time_9(),
            time_6()
        )
    }

    override fun getRow6(): List<ClockData?> {
        return listOf(
            time_3(),
            time_3_45(),
            time_9()
        )
    }
}
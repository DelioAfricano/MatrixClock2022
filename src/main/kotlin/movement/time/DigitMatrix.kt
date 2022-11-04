package movement.time

import model.ClockData
import movement.core.BaseMatrix

abstract class DigitMatrix : BaseMatrix() {

    abstract fun getRow1(): List<ClockData?>
    abstract fun getRow2(): List<ClockData?>
    abstract fun getRow3(): List<ClockData?>
    abstract fun getRow4(): List<ClockData?>
    abstract fun getRow5(): List<ClockData?>
    abstract fun getRow6(): List<ClockData?>

    override fun getMatrix(): List<List<ClockData?>> {
        return listOf(
            getRow1(),
            getRow2(),
            getRow3(),
            getRow4(),
            getRow5(),
            getRow6(),
        )
    }
}
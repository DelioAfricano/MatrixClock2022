package movement.alphabet

import model.ClockData
import movement.core.BaseMatrix

abstract class AlphabetMatrix : BaseMatrix() {
    abstract fun getRow1(): List<ClockData?>
    abstract fun getRow2(): List<ClockData?>
    abstract fun getRow3(): List<ClockData?>

    override fun getMatrix(): List<List<ClockData?>> {
        return listOf(
            getRow1(),
            getRow2(),
            getRow3(),
        )
    }
}
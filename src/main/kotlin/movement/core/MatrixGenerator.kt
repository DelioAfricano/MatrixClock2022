package movement.core

import model.ClockData
import utils.COLUMNS
import utils.ROWS

abstract class MatrixGenerator<out T : Movement>(
    val data: T,
) {
    companion object {
        private fun verifyIntegrityAndReturn(degreeMatrix: List<List<ClockData>>): List<List<ClockData>> {
            for (matrix in degreeMatrix) {
                require(matrix.size == COLUMNS) { "Column size should be $COLUMNS but found ${matrix.size}" }
            }
            require(degreeMatrix.size == ROWS) { "Row size should be $ROWS but found ${degreeMatrix.size}" }
            return degreeMatrix
        }

    }

    protected abstract fun generateMatrix(): List<List<ClockData>>
    protected open fun getUnitRowCount(): Int = -1
    protected open fun getUnitColumnCount(): Int = -1

    fun replace(
        fullMatrix: MutableList<MutableList<ClockData>>,
        replacementMatrix: List<List<ClockData?>>,
        replacementCoordinate: Pair<Int, Int>,
    ) {
        require(getUnitRowCount() != -1 && getUnitColumnCount() != -1) {
            "You should override getUnitRowCount() and getUnitColumnCount() to use `replace`"
        }

        repeat(getUnitRowCount()) { i ->
            repeat(getUnitColumnCount()) { j ->
                val element = replacementMatrix[i][j]
                if (element != null) {
                    val targetX = replacementCoordinate.first + i
                    val targetY = replacementCoordinate.second + j
                    fullMatrix[targetX][targetY] = element
                }
            }
        }
    }

    fun getVerifiedMatrix(): List<List<ClockData>> {
        return verifyIntegrityAndReturn(generateMatrix())
    }
}
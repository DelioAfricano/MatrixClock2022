package movement.ripple


import model.ClockData
import movement.core.MatrixGenerator
import movement.core.Movement
import utils.COLUMNS
import utils.ROWS
import kotlin.math.ceil

class RippleMatrixGenerator(data: Movement.Ripple) : MatrixGenerator<Movement.Ripple>(data) {
    companion object {
        const val ROW_CLOCK_COUNT = ROWS / 2
        val COLUMN_CLOCK_COUNT = ceil(COLUMNS / 2.toDouble()).toInt()

        private val rippleGrid by lazy {
            RippleData(
                startOne = 45f,
                startTwo = 135f,
                endOne = 135f,
                endTwo = 135f
            )
        }

        private val timeTableGrid by lazy {
            RippleData(
                startOne = 0f,
                startTwo = 0f,
                endOne = 360f,
                endTwo = 360f
            )
        }

        fun getRippleMatrix(ripple: Movement.Ripple): List<List<ClockData>> {
            return mutableListOf<List<ClockData>>().apply {

                repeat(ROW_CLOCK_COUNT) { rowIndex ->

                    val list = when (ripple.to) {
                        Movement.Ripple.To.START -> mergeHorizontally(
                            grid1 = rippleGrid.grid00,
                            grid2 = rippleGrid.grid01,
                            rowIndex = rowIndex
                        )
                        Movement.Ripple.To.END -> mergeHorizontallyAndFlip(
                            grid1 = rippleGrid.grid00,
                            grid2 = rippleGrid.grid01,
                            rowIndex = rowIndex
                        )
                        Movement.Ripple.To.TIME_TABLE -> mergeHorizontally(
                            grid1 = timeTableGrid.grid00,
                            grid2 = timeTableGrid.grid01,
                            rowIndex = rowIndex
                        )
                    }

                    add(list)
                }

                repeat(ROW_CLOCK_COUNT) { i ->
                    val list = when (ripple.to) {
                        Movement.Ripple.To.START -> mergeHorizontally(
                            grid1 = rippleGrid.grid10,
                            grid2 = rippleGrid.grid11,
                            rowIndex = i
                        )
                        Movement.Ripple.To.END -> mergeHorizontallyAndFlip(
                            grid1 = rippleGrid.grid10,
                            grid2 = rippleGrid.grid11,
                            rowIndex = i
                        )
                        Movement.Ripple.To.TIME_TABLE -> mergeHorizontally(
                            grid1 = timeTableGrid.grid10,
                            grid2 = timeTableGrid.grid11,
                            rowIndex = i
                        )
                    }
                    add(list)
                }
            }
        }

        private fun mergeHorizontally(
            grid1: MutableList<List<ClockData>>,
            grid2: MutableList<List<ClockData>>,
            rowIndex: Int,
        ): List<ClockData> {
            return mutableListOf<ClockData>().apply {
                val row1 = grid1[rowIndex]
                val row2 = grid2[rowIndex]
                addAll(row1)
                addAll(row2)
            }.subList(0, COLUMNS)
        }

        private fun mergeHorizontallyAndFlip(
            grid1: MutableList<List<ClockData>>,
            grid2: MutableList<List<ClockData>>,
            rowIndex: Int,
        ): List<ClockData> {
            return mutableListOf<ClockData>().apply {
                val row1 = grid1[rowIndex]
                val row2 = grid2[rowIndex]
                addAll(row1)
                addAll(row2)
            }.subList(0, COLUMNS).map {

                ClockData(
                    degreeOne = it.degreeOne + 360,
                    degreeTwo = it.degreeTwo - 360,
                )
            }
        }

    }

    override fun generateMatrix(): List<List<ClockData>> {
        return getRippleMatrix(data)
    }
}
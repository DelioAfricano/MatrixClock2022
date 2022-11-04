package movement

import model.ClockData
import movement.core.MatrixGenerator
import movement.core.Movement
import utils.COLUMNS
import utils.ROWS


const val DEFAULT_STAND_BY_DEGREE = 225f

val globalStandByClockData by lazy {
    ClockData(
        degreeOne = DEFAULT_STAND_BY_DEGREE,
        degreeTwo = DEFAULT_STAND_BY_DEGREE
    )
}

class StandByMatrixGenerator(data: Movement.StandBy) : MatrixGenerator<Movement.StandBy>(data) {
    companion object {
        private fun getStandByMatrix(
            standBy: Movement.StandBy,
        ): MutableList<MutableList<ClockData>> {
            return mutableListOf<MutableList<ClockData>>().apply {
                repeat(ROWS) {
                    val list = mutableListOf<ClockData>()
                    repeat(COLUMNS) {
                        list.add(globalStandByClockData)
                    }
                    add(list)
                }
            }
        }
    }

    override fun generateMatrix(): List<List<ClockData>> {
        return getStandByMatrix(data)
    }
}
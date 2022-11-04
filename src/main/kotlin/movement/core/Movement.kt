package movement.core

import movement.StandByMatrixGenerator
import movement.TranceMatrixGenerator
import movement.alphabet.TextMatrixGenerator
import movement.ripple.RippleMatrixGenerator
import movement.snake.WaveMatrixGenerator
import movement.time.TimeMatrixGenerator
import java.util.*

const val DEFAULT_ANIMATION_DURATION = 4000

sealed class Movement(
    val durationInMillis: Int,
) {

    abstract fun getMatrixGenerator(): MatrixGenerator<Movement>

    /**
     * To move clocks to stand by position
     */
    object StandBy : Movement(durationInMillis = DEFAULT_ANIMATION_DURATION) {
        override fun getMatrixGenerator(): MatrixGenerator<Movement> {
            return StandByMatrixGenerator(this)
        }

        override fun toString(): String {
            return "StandBy"
        }
    }

    /**
     * To play trance looking animations
     */
    data class Trance(
        val to: To = To.SQUARE,
    ) : Movement(
        durationInMillis = DEFAULT_ANIMATION_DURATION
    ) {
        enum class To {
            SQUARE, FLOWER, FLY, STAR
        }

        override fun getMatrixGenerator(): MatrixGenerator<Movement> {
            return TranceMatrixGenerator(this)
        }
    }

    /**
     * Ripple effect
     */
    data class Ripple(
        val to: To = To.START,
    ) : Movement(durationInMillis = DEFAULT_ANIMATION_DURATION) {

        override fun getMatrixGenerator(): MatrixGenerator<Movement> {
            return RippleMatrixGenerator(this)
        }

        enum class To {
            START, END, TIME_TABLE
        }
    }

    /**
     * To show time
     */
    data class Time(
        val date: Date = Date(),
    ) : Movement(durationInMillis = DEFAULT_ANIMATION_DURATION) {
        override fun getMatrixGenerator(): MatrixGenerator<Time> {
            return TimeMatrixGenerator(this)
        }
    }

    /**
     * TODO :WIP
     */
    data class Text(
        val text: String,
    ) : Movement(durationInMillis = DEFAULT_ANIMATION_DURATION) {
        override fun getMatrixGenerator(): MatrixGenerator<Movement> {
            return TextMatrixGenerator(this)
        }

        enum class Alignment {
            CENTER, LEFT
        }
    }

    data class Wave(
        val state: State,
    ) : Movement(durationInMillis = DEFAULT_ANIMATION_DURATION) {
        override fun getMatrixGenerator(): MatrixGenerator<Movement> {
            return WaveMatrixGenerator(this)
        }

        enum class State {
            START, END
        }
    }
}
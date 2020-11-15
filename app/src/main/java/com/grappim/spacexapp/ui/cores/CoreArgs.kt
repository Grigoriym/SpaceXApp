package com.grappim.spacexapp.ui.cores

enum class CoreArgs(val value: String) {
    ALL_CORES("arg_cores_all"),
    UPCOMING_CORES("arg_cores_upcoming"),
    PAST_CORES("arg_cores_past");

    companion object {
        fun fromStringToArgs(value: String): CoreArgs =
            when (value) {
                ALL_CORES.value -> ALL_CORES
                UPCOMING_CORES.value -> UPCOMING_CORES
                PAST_CORES.value -> PAST_CORES
                else -> throw IllegalArgumentException("wrong core value")
            }

        fun fromArgToString(args: CoreArgs): String =
            when (args) {
                CoreArgs.ALL_CORES -> ALL_CORES.value
                CoreArgs.UPCOMING_CORES -> UPCOMING_CORES.value
                CoreArgs.PAST_CORES -> PAST_CORES.value
            }
    }
}
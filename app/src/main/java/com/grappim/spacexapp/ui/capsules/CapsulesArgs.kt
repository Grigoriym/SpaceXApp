package com.grappim.spacexapp.ui.capsules

enum class CapsulesArgs(val value: String) {
    ALL_CAPSULES("arg_capsules_all"),
    UPCOMING_CAPSULES("arg_capsules_upcoming"),
    PAST_CAPSULES("arg_capsules_past");

    companion object{
        fun fromStringToArgs(value: String): CapsulesArgs =
            when (value) {
                ALL_CAPSULES.value -> ALL_CAPSULES
                UPCOMING_CAPSULES.value -> UPCOMING_CAPSULES
                PAST_CAPSULES.value -> PAST_CAPSULES
                else -> throw IllegalArgumentException("wrong capsule value")
            }

        fun fromArgToString(args: CapsulesArgs): String =
            when (args) {
                ALL_CAPSULES -> ALL_CAPSULES.value
                UPCOMING_CAPSULES -> UPCOMING_CAPSULES.value
                PAST_CAPSULES -> PAST_CAPSULES.value
            }
    }
}
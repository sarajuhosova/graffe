package com.sarajuhosova.graffe.interaction.options

import com.sarajuhosova.graffe.exception.InvalidProgramArgumentsException

object IgnoreRestrictionsOption: Option(
    "ignore-restrictions",
    "",
    "ignores the restrictions applied to the graph"
) {

    var ignore = false
        private set

    override fun default() {
        ignore = false
    }

    override fun validate(args: List<String>) {
        if (args.isNotEmpty())
            throw InvalidProgramArgumentsException(
                "$this does not take any arguments",
                InvalidProgramArgumentsException.Type.INCORRECT_NUMBER_OF_ARGUMENTS
            )
    }

    override fun config(args: List<String>) {
        ignore = true
    }

}

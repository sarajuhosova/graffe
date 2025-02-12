package com.sarajuhosova.graffe.interaction.options

import com.sarajuhosova.graffe.exception.InvalidProgramArgumentsException
import com.sarajuhosova.graffe.interaction.ui.Interface
import com.sarajuhosova.graffe.model.graph.Graph

object InterfaceOption: Option(
    "interface",
    "{${Interface.OPTIONS.joinToString("|")}}",
    "requires exactly one argument specifying the interface for exploring the graph"
) {

    var choice = Interface.Type.COMMAND_LINE
        private set

    override fun default() {
        choice = Interface.Type.COMMAND_LINE
    }

    override fun validate(args: List<String>) {
        if (args.size != 1)
            throw InvalidProgramArgumentsException(
                "$this requires exactly one argument" +
                        "\n    you provided the following arguments: ${args.joinToString(" ")}",
                InvalidProgramArgumentsException.Type.INCORRECT_NUMBER_OF_ARGUMENTS
            )
        if (args[0] !in Interface.Type)
            throw InvalidProgramArgumentsException(
                "${args[0]} is not a valid interface",
                InvalidProgramArgumentsException.Type.INVALID_ARGUMENT
            )
    }

    override fun config(args: List<String>) {
        choice = Interface.Type[args[0]]!!
    }

    fun exploreGraph(graph: Graph) {
        choice.i.exploreGraph(graph)
    }

}

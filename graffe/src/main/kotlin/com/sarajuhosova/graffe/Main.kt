package com.sarajuhosova.graffe

import com.sarajuhosova.graffe.exception.InvalidProgramArgumentsException
import com.sarajuhosova.graffe.helper.indent
import com.sarajuhosova.graffe.interaction.options.InterfaceOption
import com.sarajuhosova.graffe.interaction.options.Option
import com.sarajuhosova.graffe.parser.Parser
import java.io.File

val USAGE: String = buildString {
    appendLine("Usage: graffe <file> [options]")
    appendLine("Options:")
    for (option in Option.values()) appendLine(option.usage().indent())
}

fun main(args: Array<String>) {
    println("Hello, GRaffe!")

    try {
        // resolved the program arguments
        if (args.isEmpty())
            throw InvalidProgramArgumentsException("Expected filename as argument")
        val filename = args[0]
        Option.compileOptions(args.drop(1))

        // parse the file
        val parsed = Parser.parseProgram(File(filename).readText())

        if (parsed == null) {
            println("File $filename could not be parsed!")
            return
        }

        println("File $filename successfully parsed!")

        // generate the graph
        val graph = parsed.generate()

        println("Graph successfully generated!")

        // explore the graph
        InterfaceOption.exploreGraph(graph)
    } catch (e: InvalidProgramArgumentsException) {
        System.err.println(e.message)
        println()
        println(USAGE)
    }
}

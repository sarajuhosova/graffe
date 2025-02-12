package com.sarajuhosova.graffe

import com.sarajuhosova.graffe.exception.InvalidProgramArgumentsException
import com.sarajuhosova.graffe.interaction.options.Options
import com.sarajuhosova.graffe.interaction.ui.Interface
import com.sarajuhosova.graffe.parser.Parser
import java.io.File

val USAGE: String = buildString {
    appendLine("Usage: graffe <file>")
}

fun main(args: Array<String>) {
    println("Hello, GRaffe!")

    try {
        if (args.isEmpty())
            throw InvalidProgramArgumentsException("Expected filename as argument")
        val filename = args[0]
        val options = Options.compileOptions(args.drop(1))

        val parsed = Parser.parseProgram(File(filename).readText())

        if (parsed == null) {
            println("File $filename could not be parsed!")
            return
        }

        println("File $filename successfully parsed!")

        val graph = parsed.generate()

        println("Graph successfully generated!")

        if (Options.INTERFACE in options) {
            Interface.exploreGraph(graph, options[Options.INTERFACE]!![0])
        }
    } catch (e: InvalidProgramArgumentsException) {
        println(e.message)
        println()
        println(USAGE)
    }
}

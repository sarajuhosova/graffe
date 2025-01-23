package com.sarajuhosova.graffe

import com.sarajuhosova.graffe.parser.Parser
import java.io.File

fun main(args: Array<String>) {
    println("Hello, GRaffe!")

    if (args.isEmpty()) {
        println("Usage: graffe <file>")
        return
    }

    val program = Parser.parseProgram(File(args[0]).readText())

    println(program)
}

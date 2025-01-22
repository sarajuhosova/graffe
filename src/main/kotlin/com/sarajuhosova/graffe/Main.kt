package com.sarajuhosova.graffe

import com.sarajuhosova.graffe.parser.ASTBuilder
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream

private fun readSampleFile(filename: String): CharStream {
    val contextClassLoader = Thread.currentThread().contextClassLoader
    return contextClassLoader.getResourceAsStream(filename).use { input ->
        CharStreams.fromStream(input)
    }
}

private fun example(filename: String) {
    val lexer = GRaffeLexer(readSampleFile(filename))
    val tokens = CommonTokenStream(lexer)
    val parser = GRaffeParser(tokens)

    println(parser.parse().accept(ASTBuilder))
}

fun main() {
    println("Welcome to GRaffe!")

    example("sample.grf")
}

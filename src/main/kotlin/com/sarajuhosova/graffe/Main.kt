package com.sarajuhosova.graffe

import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream

private fun readSampleFile(): CharStream {
    val contextClassLoader = Thread.currentThread().contextClassLoader
    return contextClassLoader.getResourceAsStream("sample.grf").use { input ->
        CharStreams.fromStream(input)
    }
}

fun main() {
    println("Welcome to GRaffe!")

    val lexer = GRaffeLexer(readSampleFile())
    val tokens = CommonTokenStream(lexer)
    val parser = GRaffeParser(tokens)

    while (parser.currentToken.type != GRaffeParser.EOF) {
        val nextLine = parser.line()
        val kw = nextLine.keyValue()
        val key = kw.key().text
        val value = kw.separatorAndValue().chars()
            .joinToString(separator = "") { it.text }

        println("$key = $value")
    }
}

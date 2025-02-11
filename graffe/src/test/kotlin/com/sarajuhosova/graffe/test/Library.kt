package com.sarajuhosova.graffe.test

import com.sarajuhosova.graffe.model.ast.GRaffeProgram
import com.sarajuhosova.graffe.parser.Parser
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.assertj.core.api.Assertions.assertThat

fun readToCharStream(filename: String): CharStream =
    Thread.currentThread()
        .contextClassLoader
        .getResourceAsStream(filename)
        .use { input -> CharStreams.fromStream(input) }

private fun parseProgramAsserted(parsed: GRaffeProgram?): GRaffeProgram {
    assertThat(parsed).isNotNull()
    return parsed!!
}

fun Parser.parseProgramAsserted(input: String): GRaffeProgram =
    parseProgramAsserted(this.parseProgram(input))

fun Parser.parseProgramAsserted(input: CharStream): GRaffeProgram =
    parseProgramAsserted(this.parseProgram(input))

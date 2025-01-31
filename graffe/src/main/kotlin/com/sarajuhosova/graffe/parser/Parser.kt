package com.sarajuhosova.graffe.parser

import com.sarajuhosova.graffe.GRaffeLexer
import com.sarajuhosova.graffe.GRaffeParser
import com.sarajuhosova.graffe.exception.parsing.NotAProgramException
import com.sarajuhosova.graffe.model.ast.GRaffeElement
import com.sarajuhosova.graffe.model.ast.GRaffeProgram
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream

object Parser {

    fun parseProgram(input: String): GRaffeProgram =
        parseProgram(CharStreams.fromString(input))

    fun parseProgram(stream: CharStream): GRaffeProgram {
        val parsed = parse(stream)
        if (parsed !is GRaffeProgram)
            throw NotAProgramException()
        return parsed
    }

    private fun parse(stream: CharStream): GRaffeElement {
        val lexer = com.sarajuhosova.graffe.GRaffeLexer(stream)
        val tokens = CommonTokenStream(lexer)
        val parser = com.sarajuhosova.graffe.GRaffeParser(tokens)

        return parser.parse().accept(ASTBuilder)
    }

}

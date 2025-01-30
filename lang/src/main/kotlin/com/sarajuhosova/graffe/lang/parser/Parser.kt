package com.sarajuhosova.graffe.lang.parser

import com.sarajuhosova.graffe.lang.GRaffeLexer
import com.sarajuhosova.graffe.lang.GRaffeParser
import com.sarajuhosova.graffe.lang.exception.parsing.NotAProgramException
import com.sarajuhosova.graffe.lang.model.ast.GRaffeElement
import com.sarajuhosova.graffe.lang.model.ast.GRaffeProgram
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
        val lexer = GRaffeLexer(stream)
        val tokens = CommonTokenStream(lexer)
        val parser = GRaffeParser(tokens)

        return parser.parse().accept(ASTBuilder)
    }

}

package com.sarajuhosova.graffe.parser

import com.sarajuhosova.graffe.exception.parsing.NotAProgramException
import com.sarajuhosova.graffe.model.ast.GRaffeElement
import com.sarajuhosova.graffe.model.ast.GRaffeProgram
import com.sarajuhosova.graffe.parser.error.GRaffeErrorListener
import com.sarajuhosova.graffe.parser.error.GRaffeErrorStrategy
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams

object Parser {

    fun parseProgram(input: String): GRaffeProgram? =
        parseProgram(CharStreams.fromString(input))

    fun parseProgram(stream: CharStream): GRaffeProgram? {
        val parsed = parse(stream) ?: return null
        if (parsed !is GRaffeProgram)
            throw NotAProgramException()
        return parsed
    }

    private fun parse(stream: CharStream): GRaffeElement? {
        val errorListener = GRaffeErrorListener()

        val parser = GRaffeParserBuilder(stream)
            .withOnlyErrorListeners(errorListener)
            .withErrorStrategy(GRaffeErrorStrategy)
            .build()

        val context = parser.program()

        if (errorListener.reported()) return null

        return context.accept(ASTBuilder)
    }

}

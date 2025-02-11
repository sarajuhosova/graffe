package com.sarajuhosova.graffe.parser

import com.sarajuhosova.graffe.GRaffeLexer
import com.sarajuhosova.graffe.GRaffeParser
import com.sarajuhosova.graffe.exception.CannotEditBuiltBuilderException
import org.antlr.v4.runtime.ANTLRErrorListener
import org.antlr.v4.runtime.ANTLRErrorStrategy
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CommonTokenStream

class GRaffeParserBuilder(stream: CharStream) {

    private val lexer = GRaffeLexer(stream)
    private val tokens = CommonTokenStream(lexer)
    private val parser = GRaffeParser(tokens)

    private var editable = true
    private fun checkEditable() {
        if (!editable) throw CannotEditBuiltBuilderException()
    }

    private fun addErrorListeners(vararg listeners: ANTLRErrorListener) {
        for (listener in listeners) {
            lexer.addErrorListener(listener)
            parser.addErrorListener(listener)
        }
    }

    fun withOnlyErrorListeners(vararg listeners: ANTLRErrorListener): GRaffeParserBuilder {
        checkEditable()

        lexer.removeErrorListeners()
        parser.removeErrorListeners()

        addErrorListeners(*listeners)

        return this
    }

    fun withErrorListeners(vararg listeners: ANTLRErrorListener): GRaffeParserBuilder {
        checkEditable()

        addErrorListeners(*listeners)

        return this
    }

    fun withErrorStrategy(strategy: ANTLRErrorStrategy): GRaffeParserBuilder {
        checkEditable()

        parser.errorHandler = strategy
        return this
    }

    fun build(): GRaffeParser {
        editable = false
        return parser
    }

}

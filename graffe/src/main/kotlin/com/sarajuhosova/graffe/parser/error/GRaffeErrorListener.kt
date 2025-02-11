package com.sarajuhosova.graffe.parser.error

import org.antlr.v4.runtime.BaseErrorListener
import org.antlr.v4.runtime.RecognitionException
import org.antlr.v4.runtime.Recognizer

class GRaffeErrorListener : BaseErrorListener() {

    private val messages = mutableListOf<String>()

    fun reported(): Boolean = messages.isNotEmpty()

    override fun syntaxError(
        recognizer: Recognizer<*, *>,
        offendingSymbol: Any,
        line: Int,
        charPositionInLine: Int,
        msg: String,
        e: RecognitionException?
    ) {
        messages.add("line $line:$charPositionInLine $msg")
    }

}

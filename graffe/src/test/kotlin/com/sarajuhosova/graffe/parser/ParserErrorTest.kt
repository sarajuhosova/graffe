package com.sarajuhosova.graffe.parser

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class ParserErrorTest {

    @Test
    fun `single semicolon throws a parse error`() {
        val input = ";"

        val parsed = Parser.parseProgram(input)

        assertThat(parsed).isNull()
    }

}

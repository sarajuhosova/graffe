package com.sarajuhosova.graffe.parser

import com.sarajuhosova.graffe.test.GRaffeTest
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class ParserErrorTest : GRaffeTest() {

    @Test
    fun `single semicolon throws a parse error`() {
        val input = ";"

        val parsed = Parser.parseProgram(input)

        assertThat(parsed).isNull()
    }

}

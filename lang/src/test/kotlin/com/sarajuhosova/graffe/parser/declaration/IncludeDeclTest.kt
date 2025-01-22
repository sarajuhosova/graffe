package com.sarajuhosova.graffe.parser.declaration

import com.sarajuhosova.graffe.model.ast.statement.declaration.IncludeDeclaration
import com.sarajuhosova.graffe.parser.Parser
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class IncludeDeclTest {

    @Test
    fun `include declaration parses with one include`() {
        val input = "include a;"

        val parsed = Parser.parseProgram(input)

        assertThat(parsed.declarations)
            .containsExactly(IncludeDeclaration(listOf("a")))
    }

    @Test
    fun `include declaration parses with multiple includes`() {
        val input = "include a bb cd;"

        val parsed = Parser.parseProgram(input)

        assertThat(parsed.declarations)
            .containsExactly(IncludeDeclaration(listOf("a", "bb", "cd")))
    }

}
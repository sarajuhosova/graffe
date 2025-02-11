package com.sarajuhosova.graffe.parser.declaration

import com.sarajuhosova.graffe.model.QName
import com.sarajuhosova.graffe.model.ast.statement.declaration.IncludeDeclaration
import com.sarajuhosova.graffe.parser.Parser
import com.sarajuhosova.graffe.test.parseProgramAsserted
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class IncludeDeclTest {

    @Test
    fun `include declaration parses with one include`() {
        val input = "include a;"

        val parsed = Parser.parseProgramAsserted(input)

        assertThat(parsed.declarations)
            .containsExactly(IncludeDeclaration("a"))
    }

    @Test
    fun `include declaration parses with multiple includes`() {
        val input = "include a bb cd;"

        val parsed = Parser.parseProgramAsserted(input)

        assertThat(parsed.declarations)
            .containsExactly(IncludeDeclaration("a", "bb", "cd"))
    }

    @Test
    fun `include declaration parses with one qualified include`() {
        val input = "include a.b.c;"

        val parsed = Parser.parseProgramAsserted(input)

        assertThat(parsed.declarations)
            .containsExactly(IncludeDeclaration("a.b.c"))
    }

    @Test
    fun `include declaration parses with multiple qualified includes`() {
        val input = "include a.b.c c.d;"

        val parsed = Parser.parseProgramAsserted(input)

        assertThat(parsed.declarations)
            .containsExactly(IncludeDeclaration(listOf(
                QName("a", "b", "c"),
                QName("c", "d")
            )))
    }

    @Test
    fun `include declaration parses with multiple (un)qualified includes`() {
        val input = "include f a.b.c c.d g;"

        val parsed = Parser.parseProgramAsserted(input)

        assertThat(parsed.declarations)
            .containsExactly(IncludeDeclaration(
                "f", "a.b.c", "c.d", "g"
            ))
    }

}

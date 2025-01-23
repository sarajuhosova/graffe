package com.sarajuhosova.graffe.parser

import com.sarajuhosova.graffe.enums.Arrow
import com.sarajuhosova.graffe.model.ast.statement.declaration.ComponentDeclaration
import com.sarajuhosova.graffe.model.ast.statement.declaration.IncludeDeclaration
import com.sarajuhosova.graffe.model.ast.statement.declaration.RelationshipDeclaration
import com.sarajuhosova.graffe.model.ast.statement.property.StringProperty
import com.sarajuhosova.graffe.readToCharStream
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class ParserTest {

    @Test
    fun `comment is valid but ignored`() {
        val input = "// This is a comment."

        val parsed = Parser.parseProgram(input)

        assertThat(parsed.declarations).isEmpty()
    }

    @Test
    fun `example program parses`() {
        val input = readToCharStream("samples/example.grf")

        val parsed = Parser.parseProgram(input)

        assertThat(parsed.declarations).containsExactly(
            ComponentDeclaration("a", listOf(
                StringProperty("hello", "world"),
                IncludeDeclaration("c.a", "f"),
                ComponentDeclaration("b", listOf(
                    StringProperty("hoi", "13")
                )),
                RelationshipDeclaration("b", "c", Arrow.BOTH, emptyList()),
                RelationshipDeclaration("c", "f", Arrow.NONE, emptyList()),
            )),
            IncludeDeclaration("d"),
            RelationshipDeclaration(
                "a", "d", Arrow.RIGHT, listOf(
                    StringProperty("prop", "hello")
                )
            ),
            RelationshipDeclaration("a", "d", Arrow.LEFT, emptyList()),
        )
    }

}

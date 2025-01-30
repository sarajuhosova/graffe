package com.sarajuhosova.graffe.lang.parser

import com.sarajuhosova.graffe.lang.model.ast.statement.GRaffeProperty
import com.sarajuhosova.graffe.lang.model.ast.statement.declaration.ComponentDeclaration
import com.sarajuhosova.graffe.lang.model.ast.statement.declaration.IncludeDeclaration
import com.sarajuhosova.graffe.lang.model.ast.statement.declaration.RelationshipDeclaration
import com.sarajuhosova.graffe.lang.model.property.StringProperty
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
                GRaffeProperty("hello", StringProperty("world")),
                IncludeDeclaration("c.a", "f"),
                ComponentDeclaration("b", listOf(
                    GRaffeProperty("hoi", StringProperty("13"))
                )),
                RelationshipDeclaration("b", "c", RelationshipDeclaration.Arrow.BOTH, emptyList()),
                RelationshipDeclaration("c", "f", RelationshipDeclaration.Arrow.NONE, emptyList()),
            )),
            IncludeDeclaration("d"),
            RelationshipDeclaration(
                "a", "d", RelationshipDeclaration.Arrow.RIGHT, listOf(
                    GRaffeProperty("prop", StringProperty("hello"))
                )
            ),
            RelationshipDeclaration("a", "d", RelationshipDeclaration.Arrow.LEFT, emptyList())
        )
    }

}

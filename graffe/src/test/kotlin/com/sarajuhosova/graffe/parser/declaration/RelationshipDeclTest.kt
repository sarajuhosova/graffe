package com.sarajuhosova.graffe.parser.declaration

import com.sarajuhosova.graffe.model.ast.statement.GRaffeProperty
import com.sarajuhosova.graffe.model.ast.statement.declaration.RelationshipDeclaration
import com.sarajuhosova.graffe.model.property.StringProperty
import com.sarajuhosova.graffe.parser.Parser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.Test

class RelationshipDeclTest {

    @TestFactory
    fun `empty relationship is valid with all arrows`() =
        RelationshipDeclaration.Arrow.entries
            .map { arrow ->
                DynamicTest.dynamicTest(
                    "arrow ${arrow.symbol} is valid"
                ) {
                    val input = "a ${arrow.symbol} b;"
                    val parsed = Parser.parseProgram(input)
                    assertThat(parsed.declarations)
                        .containsOnly(RelationshipDeclaration("a", "b", arrow))
                }
            }

    @Test
    fun `relationship can have empty brackets`() {
        val input = "a <> b {}"
        val parsed = Parser.parseProgram(input)
        assertThat(parsed.declarations)
            .containsOnly(RelationshipDeclaration("a", "b", RelationshipDeclaration.Arrow.BOTH))
    }

    @Test
    fun `relationship can have properties`() {
        val input = """
            a -> b {
                key1: "value1";
                key2: "value2";
            }
        """.trimIndent()
        val parsed = Parser.parseProgram(input)
        assertThat(parsed.declarations)
            .containsOnly(
                RelationshipDeclaration(
                    "a",
                    "b",
                    RelationshipDeclaration.Arrow.RIGHT,
                    listOf(
                        GRaffeProperty("key1", StringProperty("value1")),
                        GRaffeProperty("key2", StringProperty("value2"))
                    )
                )
            )
    }

}

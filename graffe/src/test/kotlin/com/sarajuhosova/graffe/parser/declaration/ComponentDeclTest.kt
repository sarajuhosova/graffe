package com.sarajuhosova.graffe.parser.declaration

import com.sarajuhosova.graffe.model.ast.statement.GRaffeProperty
import com.sarajuhosova.graffe.model.ast.statement.declaration.ComponentDeclaration
import com.sarajuhosova.graffe.model.property.StringProperty
import com.sarajuhosova.graffe.parser.Parser
import com.sarajuhosova.graffe.test.parseProgramAsserted
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class ComponentDeclTest {

    @Test
    fun `empty declaration is valid`() {
        val input = "a;"

        val parsed = Parser.parseProgramAsserted(input)

        assertThat(parsed.declarations)
            .containsOnly(ComponentDeclaration("a"))
    }

    @Test
    fun `brackets declaration is valid`() {
        val input = "a {}"

        val parsed = Parser.parseProgramAsserted(input)

        assertThat(parsed.declarations)
            .containsOnly(ComponentDeclaration("a"))
    }

    @Test
    fun `declaration can have properties`() {
        val input = """
            a {
                key1: "value1";
                key2: "value2";
            }
        """.trimIndent()

        val parsed = Parser.parseProgramAsserted(input)

        assertThat(parsed.declarations)
            .containsOnly(ComponentDeclaration("a", listOf(
                GRaffeProperty("key1", StringProperty("value1")),
                GRaffeProperty("key2", StringProperty("value2"))
            )))
    }

    @Test
    fun `declaration can have nested declaration`() {
        val input = """
            a {
                b;
            }
        """.trimIndent()

        val parsed = Parser.parseProgramAsserted(input)

        assertThat(parsed.declarations)
            .containsOnly(ComponentDeclaration("a", listOf(
                ComponentDeclaration("b")
            )))
    }

    @Test
    fun `declaration can have properties and nested declarations`() {
        val input = """
            a {
                key1: "value1";
                b;
                c {
                    key2: "value2";
                }
                key3: "value3";
                d {}
            }
        """.trimIndent()

        val parsed = Parser.parseProgramAsserted(input)

        val expected = ComponentDeclaration("a", listOf(
            GRaffeProperty("key1", StringProperty("value1")),
            ComponentDeclaration("b"),
            ComponentDeclaration("c", listOf(
                GRaffeProperty("key2", StringProperty("value2"))
            )),
            GRaffeProperty("key3", StringProperty("value3")),
            ComponentDeclaration("d")
        ))

        assertThat(parsed.declarations).containsOnly(expected)
    }

}

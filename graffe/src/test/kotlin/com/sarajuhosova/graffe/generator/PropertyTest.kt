package com.sarajuhosova.graffe.generator

import com.sarajuhosova.graffe.model.ast.GRaffeProgram
import com.sarajuhosova.graffe.model.ast.statement.GRaffeProperty
import com.sarajuhosova.graffe.model.ast.statement.declaration.ComponentDeclaration
import com.sarajuhosova.graffe.model.property.StringProperty
import com.sarajuhosova.graffe.test.GRaffeTest
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class PropertyTest : GRaffeTest() {

    @Test
    fun `component has defined property`() {
        val graph = GRaffeProgram(
            ComponentDeclaration("a", listOf(
                GRaffeProperty("key1", StringProperty("value1")),
                GRaffeProperty("key2", StringProperty("value2"))
            ))
        ).generate()

        val a = graph.getNode("a")
        assertThat(a).isNotNull()
        val properties = a!!.properties
        assertThat(properties.size()).isEqualTo(2)
        assertThat(properties["key1"]).containsExactly(StringProperty("value1"))
        assertThat(properties["key2"]).containsExactly(StringProperty("value2"))
    }

    @Test
    fun `component merges properties correctly`() {
        val graph = GRaffeProgram(
            ComponentDeclaration("a", listOf(
                GRaffeProperty("key1", StringProperty("value1")),
                GRaffeProperty("key2", StringProperty("value2a"))
            )),
            ComponentDeclaration("a", listOf(
                GRaffeProperty("key2", StringProperty("value2b")),
                GRaffeProperty("key3", StringProperty("value3"))
            ))
        ).generate()

        val a = graph.getNode("a")
        assertThat(a).isNotNull()
        val properties = a!!.properties
        assertThat(properties.size()).isEqualTo(3)
        assertThat(properties["key1"]).containsExactly(StringProperty("value1"))
        assertThat(properties["key2"]).containsExactly(
            StringProperty("value2a"), StringProperty("value2b")
        )
        assertThat(properties["key3"]).containsExactly(StringProperty("value3"))
    }

}

package com.sarajuhosova.graffe.generator

import com.sarajuhosova.graffe.model.ast.GRaffeProgram
import com.sarajuhosova.graffe.model.ast.statement.declaration.ComponentDeclaration
import com.sarajuhosova.graffe.model.ast.statement.declaration.RelationshipDeclaration
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class GeneratorTest {

    @Test
    fun `empty program compiles to empty graph`() {
        val graph = GRaffeProgram().generate()

        assertThat(graph.size()).isZero()
    }

    @Test
    fun `one component compiles to single node`() {
        val graph = GRaffeProgram(ComponentDeclaration("a")).generate()

        assertThat(graph.size()).isEqualTo(1)
        val data = graph.get("a")
        assertThat(data).isNotNull()
        val (node, edges) = data!!

        assertThat(node.name).isEqualTo("a")
        assertThat(node.child).isNull()
        assertThat(node.properties.isEmpty()).isTrue()

        assertThat(edges).isEmpty()
    }

    @Test
    fun `two connected components compile correctly`() {
        val graph = GRaffeProgram(
            ComponentDeclaration("a"),
            ComponentDeclaration("b"),
            RelationshipDeclaration("a", "b", RelationshipDeclaration.Arrow.RIGHT)
        ).generate()

        assertThat(graph.size()).isEqualTo(2)
        assertThat(graph.getNode("a")).isNotNull()
        assertThat(graph.getNode("b")).isNotNull()

        val aEdges = graph.getEdges("a")
        assertThat(aEdges).hasSize(1)
        val edge = aEdges.first()
        assertThat(edge.hasSource("a")).isTrue()
        assertThat(edge.hasTarget("b")).isTrue()

        val bEdges = graph.getEdges("b")
        assertThat(bEdges).hasSize(1)
        assertThat(bEdges.first()).isEqualTo(edge)
    }

}

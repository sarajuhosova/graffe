package com.sarajuhosova.graffe.lang.model.dto

import com.sarajuhosova.graffe.lang.helper.indent
import com.sarajuhosova.graffe.lang.model.graph.Edge
import com.sarajuhosova.graffe.lang.model.graph.Graph
import com.sarajuhosova.graffe.lang.model.graph.Node

data class Component(
    val node: Node,
    val edges: List<Edge>,
    val graph: Graph,
    val parent: com.sarajuhosova.graffe.lang.model.dto.Component? = null
) {

    override fun toString(): String = buildString {
        appendLine("\"${node.name}\"\n---------------")

        appendLine("Properties:")
        appendLine(node.properties.toString().indent())
        appendLine("Edges:")
        var i = 1
        for (edge in edges) {
            appendLine("    ($i) ${edge.relationship.draw(node.name)}")
            if (edge.properties.isNotEmpty()) {
                appendLine(edge.properties.toString().indent().indent())
            }
            i++
        }
    }

}

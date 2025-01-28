package com.sarajuhosova.graffe.model.graph

import com.sarajuhosova.graffe.model.ast.statement.declaration.ComponentDeclaration
import com.sarajuhosova.graffe.model.ast.statement.declaration.GRaffeDeclaration
import com.sarajuhosova.graffe.model.ast.statement.declaration.IncludeDeclaration
import com.sarajuhosova.graffe.model.ast.statement.declaration.RelationshipDeclaration

data class Graph(
    private val nodes: MutableMap<String, Node> = mutableMapOf(),
    private val edges: MutableList<Edge> = mutableListOf()
) : GRaffe() {

    fun size(): Int = nodes.size

    fun get(name: String): Pair<Node, List<Edge>>? {
        if (name !in nodes) return null
        val edges = edges.filter { it.connectsNode(name) }
        return nodes[name]!! to edges
    }

    fun addNode(node: Node) {
        if (node.name !in nodes) nodes[node.name] = node
        else nodes[node.name]?.merge(node)
    }

    fun addEdge(edge: Edge) { edges.add(edge) }

    fun merge(other: Graph?) {
        if (other == null) return

        // merge the nodes
        for ((_, node) in other.nodes) addNode(node)
        // merge the edges
        edges.addAll(other.edges)
    }

    companion object {
        fun fromDeclarations(declarations: List<GRaffeDeclaration>): Graph {
            val result = Graph()
            for (declaration in declarations) {
                when (declaration) {
                    is ComponentDeclaration -> result.addNode(
                        declaration.generate()
                    )
                    is IncludeDeclaration -> TODO()
                    is RelationshipDeclaration -> result.addEdge(
                        declaration.generate()
                    )
                }
            }
            return result
        }
    }

}

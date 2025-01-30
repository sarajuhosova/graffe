package com.sarajuhosova.graffe.model.graph

import com.sarajuhosova.graffe.exception.generation.MissingComponentsException
import com.sarajuhosova.graffe.model.ast.statement.declaration.ComponentDeclaration
import com.sarajuhosova.graffe.model.ast.statement.declaration.GRaffeDeclaration
import com.sarajuhosova.graffe.model.ast.statement.declaration.IncludeDeclaration
import com.sarajuhosova.graffe.model.ast.statement.declaration.RelationshipDeclaration

data class Graph(
    private val parent: Node?,
    private val nodes: MutableMap<String, Node> = mutableMapOf(),
    private val edges: MutableList<Edge> = mutableListOf()
) : GRaffe() {

    fun size(): Int = nodes.size

    operator fun get(name: String): com.sarajuhosova.graffe.model.dto.Component? =
        if (name !in nodes) null else com.sarajuhosova.graffe.model.dto.Component(
            getNode(name)!!,
            getEdges(name),
            this
        )

    fun getParent(): com.sarajuhosova.graffe.model.dto.Component? =
        // TODO: fix the empty list here!!
        if (parent == null) null else com.sarajuhosova.graffe.model.dto.Component(parent, emptyList(), this)

    fun getNode(name: String): Node? = nodes[name]
    fun getEdges(name: String): List<Edge> =
        edges.filter { it.connectsNode(name) }

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

    fun validate() {
        val missing = mutableListOf<String>()
        for (edge in edges) {
            if (edge.relationship.source !in nodes) missing.add(edge.relationship.source)
            if (edge.relationship.target !in nodes) missing.add(edge.relationship.target)
        }
        if (missing.isNotEmpty()) {
            throw MissingComponentsException(missing)
        }
    }

    fun summary(): List<Pair<String, Int>> =
        nodes.map { it.value.name to getEdges(it.key).size }

    companion object {
        fun fromDeclarations(declarations: List<GRaffeDeclaration>, parent: Node?): Graph {
            val result = Graph(parent = parent)
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
            result.validate()
            return result
        }
    }

}

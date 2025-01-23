package com.sarajuhosova.graffe.model.graph

data class Graph(
    private val nodes: MutableMap<String, Node>,
    private val edges: MutableList<Edge>
) {

    fun addNode(name: String, node: Node) {
        if (name !in nodes) nodes[name] = node
        else nodes[name]?.merge(node)
    }

    fun addEdge(edge: Edge) { edges.add(edge) }

}

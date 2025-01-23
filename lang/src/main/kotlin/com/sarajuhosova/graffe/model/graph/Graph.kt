package com.sarajuhosova.graffe.model.graph

data class Graph(val nodes: MutableMap<String, Node>) {

    fun addNode(name: String, node: Node) {
        val current = nodes[name]
//        nodes[name] =
//            if (current == null) node
//            else Node(current.properties + node.properties)
    }

}

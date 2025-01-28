package com.sarajuhosova.graffe.model.graph.structure

data class Relationship(
    val source: String,
    val target: String,
    val type: Arrow
) {

    constructor(type: Arrow, nodes: Pair<String, String>) : this(nodes.first, nodes.second, type)

    enum class Arrow {
        DIRECTED,
        REFLEXIVE,
        UNDIRECTED
    }

}

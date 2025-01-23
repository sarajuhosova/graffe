package com.sarajuhosova.graffe.model.graph

data class Edge(
    val source: Node,
    val target: Node,
    val properties: PropertyMap
) {
}

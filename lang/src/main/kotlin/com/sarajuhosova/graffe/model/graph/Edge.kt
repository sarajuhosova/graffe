package com.sarajuhosova.graffe.model.graph

import com.sarajuhosova.graffe.enums.Arrow

data class Edge(
    val source: Node,
    val target: Node,
    val arrow: Arrow,
    val properties: PropertyMap
)

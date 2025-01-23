package com.sarajuhosova.graffe.model.graph

import com.sarajuhosova.graffe.model.ast.statement.property.GRaffeProperty

data class Edge(
    val source: Node,
    val target: Node,
    val properties: List<GRaffeProperty>
) {
}

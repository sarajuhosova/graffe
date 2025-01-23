package com.sarajuhosova.graffe.model.graph

import com.sarajuhosova.graffe.model.ast.statement.property.GRaffeProperty

data class Node(
    val name: String,
    val properties: List<GRaffeProperty>
) {
}

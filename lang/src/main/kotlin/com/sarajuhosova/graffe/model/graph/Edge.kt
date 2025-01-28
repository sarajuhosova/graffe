package com.sarajuhosova.graffe.model.graph

import com.sarajuhosova.graffe.model.graph.structure.PropertyMap
import com.sarajuhosova.graffe.model.graph.structure.Relationship

data class Edge(
    val relationship: Relationship,
    val properties: PropertyMap
) : GRaffe() {

    fun connectsNode(name: String): Boolean =
        relationship.source == name || relationship.target == name

}

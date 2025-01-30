package com.sarajuhosova.graffe.model.graph

import com.sarajuhosova.graffe.model.graph.structure.PropertyMap
import com.sarajuhosova.graffe.model.graph.structure.Relationship

data class Edge(
    val relationship: Relationship,
    val properties: PropertyMap
) : GRaffe() {

    fun hasSource(name: String) = relationship.isSource(name)
    fun hasTarget(name: String) = relationship.isTarget(name)

    fun getOpposite(name: String): String? = relationship.other(name)

    fun connectsNode(name: String): Boolean =
        relationship.connectsTo(name)

}

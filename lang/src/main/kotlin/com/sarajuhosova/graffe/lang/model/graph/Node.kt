package com.sarajuhosova.graffe.lang.model.graph

import com.sarajuhosova.graffe.lang.model.graph.structure.PropertyMap

data class Node(
    val name: String,
    var child: Graph? = null,
    val properties: PropertyMap = PropertyMap(),
) : GRaffe() {

    fun addProperty(property: Property) { properties.addProperty(property) }

    /**
     * Merge another node into this node.
     */
    fun merge(other: Node?) {
        if (other == null) return

        // merge the properties
        properties.addProperties(other.properties)

        // merge the child graph
        if (child == null) child = other.child
        else child?.merge(other.child)
    }

}

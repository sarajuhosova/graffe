package com.sarajuhosova.graffe.model.graph

data class Node(
    val properties: PropertyMap = PropertyMap(),
    val children: MutableList<Graph> = mutableListOf()
) {

    /**
     * Merge another node into this node.
     */
    fun merge(other: Node?) {
        if (other == null) return

        properties.addProperties(other.properties)
        children.addAll(other.children)
    }

}

package com.sarajuhosova.graffe.model.graph.structure

import com.sarajuhosova.graffe.helper.Direction

data class Relationship(
    val source: String,
    val target: String,
    val type: Arrow
) {

    constructor(type: Arrow, nodes: Pair<String, String>) : this(nodes.first, nodes.second, type)

    enum class Arrow(
        val symbol: (Direction) -> String
    ) {
        DIRECTED({ when (it) {
            Direction.LEFT -> "<-"
            Direction.RIGHT -> "->"
        } }),
        REFLEXIVE({ "<>" }),
        UNDIRECTED({ "--" })
    }

    fun connectsTo(name: String): Boolean = source == name || target == name

    private fun isX(name: String, x: String): Boolean = when (type) {
        Arrow.DIRECTED -> x == name
        Arrow.REFLEXIVE -> connectsTo(name)
        Arrow.UNDIRECTED -> false
    }
    fun isSource(name: String): Boolean = isX(name, source)
    fun isTarget(name: String): Boolean = isX(name, target)

    fun other(name: String): String? = when (name) {
        source -> target
        target -> source
        else -> null
    }

    fun direction(name: String): Direction? = when (name) {
        source -> Direction.RIGHT
        target -> Direction.LEFT
        else -> null
    }

    fun draw(name: String): String? =
        if (!connectsTo(name)) null
        else "${type.symbol(direction(name)!!)} ${other(name)!!}"

}

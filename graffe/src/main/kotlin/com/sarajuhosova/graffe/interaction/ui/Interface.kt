package com.sarajuhosova.graffe.interaction.ui

import com.sarajuhosova.graffe.model.graph.Graph

interface Interface {

    fun exploreGraph(graph: Graph)

    enum class Type(
        val string: String,
        val i: Interface
    ) {
        COMMAND_LINE("cli", CLI);

        override fun toString(): String = string

        companion object {
            private val map = Type.entries.associateBy { it.string }

            operator fun contains(string: String): Boolean = map.containsKey(string)

            operator fun get(string: String): Type? = map[string]
        }
    }

    companion object {
        fun exploreGraph(graph: Graph, type: String) {
            if (type !in Type)
                throw IllegalArgumentException("Invalid interface type: $type")
            Type[type]!!.i.exploreGraph(graph)
        }

    }

}

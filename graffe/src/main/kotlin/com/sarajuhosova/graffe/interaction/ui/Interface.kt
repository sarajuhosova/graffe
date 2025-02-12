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
        val OPTIONS: List<String> = Type.entries.map { it.string }
    }

}

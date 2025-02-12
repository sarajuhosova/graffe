package com.sarajuhosova.graffe.interaction.ui

import com.sarajuhosova.graffe.model.graph.Graph

object CLI : Interface {

    private enum class Prompt(
        val symbol: String,
        val action: String
    ) {
        EXPLORE_NODE("a number", "explore a node"),
        EXPLORE_EDGE("a number", "explore an edge"),
        ZOOM_IN("+", "zoom in"),
        ZOOM_OUT("-", "zoom out"),
        FULL_GRAPH("g", "see the full graph"),
        QUIT("q", "quit");

        override fun toString(): String =
            "    (Enter '$symbol' to $action)"
    }

    private fun parseChoice(input: String): Int =
        try { input.toInt() } catch (_: NumberFormatException) { -1 }

    private fun exploreComponent(component: com.sarajuhosova.graffe.model.dto.Component) {
        println(component.toString())

        var choice = -1
        while (choice !in 1..component.edges.size) {
            println("While edge would you like to follow?")
            println(Prompt.EXPLORE_EDGE)
            if (component.node.child != null) println(Prompt.ZOOM_IN)
            if (component.parent != null) println(Prompt.ZOOM_OUT)
            println(Prompt.FULL_GRAPH)
            println(Prompt.QUIT)

            when (val input = readln()) {
                Prompt.ZOOM_IN.symbol -> if (component.node.child != null) {
                    exploreGraph(component.node.child!!)
                    return
                }
                Prompt.ZOOM_OUT.symbol -> if (component.parent != null) {
                    exploreComponent(component.parent)
                    return
                }
                Prompt.FULL_GRAPH.symbol -> {
                    exploreGraph(component.graph)
                    return
                }
                Prompt.QUIT.symbol -> return
                else -> choice = parseChoice(input)
            }
        }

        val opposite = component.edges[choice - 1].getOpposite(component.node.name)!!
        exploreComponent(component.graph[opposite]!!)
    }

    override fun exploreGraph(graph: Graph) {
        println("The following nodes are available for exploration:")
        var i = 1
        val nodes = graph.summary()
        for ((name, edges) in nodes) {
            println("  ($i) $name ($edges edges)")
            i++
        }

        val parent = graph.getParent()

        var choice = -1
        while (choice !in 1..nodes.size) {
            println("Which node would you like to start exploring at?")
            println(Prompt.EXPLORE_NODE)
            if (parent != null) println(Prompt.ZOOM_IN)
            println(Prompt.QUIT)

            when (val input = readln()) {
                Prompt.ZOOM_OUT.symbol -> if (parent != null) {
                    exploreComponent(parent)
                    return
                }
                Prompt.QUIT.symbol -> return
                else -> choice = parseChoice(input)
            }
        }
        exploreComponent(graph[nodes[choice - 1].first]!!)
    }

}

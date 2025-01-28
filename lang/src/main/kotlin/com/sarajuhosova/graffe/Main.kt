package com.sarajuhosova.graffe

import com.sarajuhosova.graffe.model.dto.Component
import com.sarajuhosova.graffe.model.graph.Graph
import com.sarajuhosova.graffe.parser.Parser
import java.io.File

fun parseChoice(input: String): Int =
    try { input.toInt() } catch (_: NumberFormatException) { -1 }

fun exploreGraph(graph: Graph) {
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
        println("    (Enter a number to explore a node)")
        if (parent != null) println("    (Enter - to zoom in)")
        println("    (Enter 'q' to quit)")

        when (val input = readln()) {
            "-" -> if (parent != null) {
                exploreComponent(parent)
                return
            }
            "q" -> return
            else -> choice = parseChoice(input)
        }
    }
    exploreComponent(graph[nodes[choice - 1].first]!!)
}

fun exploreComponent(component: Component) {
    println(component.toString())

    var choice = -1
    while (choice !in 1..component.edges.size) {
        println("While edge would you like to follow?")
        println("    (Enter a number to follow an edge)")
        if (component.node.child != null) println("    (Enter + to zoom in)")
        if (component.parent != null) println("    (Enter - to zoom in)")
        println("    (Enter g to see the full graph)")
        println("    (Enter 'q' to quit)")

        when (val input = readln()) {
            "+" -> if (component.node.child != null) {
                exploreGraph(component.node.child!!)
                return
            }
            "-" -> if (component.parent != null) {
                exploreComponent(component.parent)
                return
            }
            "g" -> {
                exploreGraph(component.graph)
                return
            }
            "q" -> return
            else -> choice = parseChoice(input)
        }
    }

    val opposite = component.edges[choice - 1].getOpposite(component.node.name)!!
    exploreComponent(component.graph[opposite]!!)
}

fun main(args: Array<String>) {
    println("Hello, GRaffe!")

    if (args.isEmpty()) {
        println("Usage: graffe <file>")
        return
    }
    val filename = args[0]

    val graph = Parser.parseProgram(File(filename).readText()).generate()

    println("File $filename loaded!")

    exploreGraph(graph)
}

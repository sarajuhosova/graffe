package com.sarajuhosova.graffe

import com.sarajuhosova.graffe.exception.InvalidProgramArgumentException
import com.sarajuhosova.graffe.model.dto.Component
import com.sarajuhosova.graffe.model.graph.Graph
import com.sarajuhosova.graffe.parser.Parser
import java.io.File

val USAGE: String = buildString {
    appendLine("Usage: graffe <file> [--parse-tree <output file>]")
    appendLine("    --parse-tree: Print the parse tree of the input file into the defined output file")
}

val OPTIONS = mapOf(
    "--parse-tree" to 1
)

@Throws(InvalidProgramArgumentException::class)
fun optionsToMap(options: List<String>): Map<String, List<String>> {
    if (options.isEmpty()) return emptyMap()

    val result = mutableMapOf<String, List<String>>()

    fun addOption(key: String, values: List<String>) {
        if (key !in OPTIONS.keys)
            throw InvalidProgramArgumentException("$key is not a valid option")

        if (OPTIONS[key]!! != values.size)
            throw InvalidProgramArgumentException("Expected ${OPTIONS[key]} arguments for $key")

        if (key in result)
            throw InvalidProgramArgumentException("$key is defined multiple times")

        // else
        result[key] = values
    }

    var key = options.first()
    var i = 1
    var values = mutableListOf<String>()
    while (i < options.size) {
        val option = options[i]
        if (option.startsWith("--")) {
            addOption(key, values)

            key = option
            values = mutableListOf()
        } else values.add(option)
        i++
    }
    addOption(key, values)
    return result
}

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

    try {
        if (args.isEmpty())
            throw InvalidProgramArgumentException("Expected filename as argument")
        val filename = args[0]
        val options = optionsToMap(args.drop(1))

        val parsed = Parser.parseProgram(File(filename).readText())
        if ("--parse-tree" in options) {
            // generate the parse tree and save to file
            val output = options["--parse-tree"]!!.first()
            File(output).writeText(parsed.getParseTree().toString())
            println("Parse tree saved to $output")
        } else {
            // interpret the program
            val graph = parsed.generate()
            println("File $filename loaded!")
            exploreGraph(graph)
        }
    } catch (e: InvalidProgramArgumentException) {
        println(e.message)
        println()
        println(USAGE)
    }
}

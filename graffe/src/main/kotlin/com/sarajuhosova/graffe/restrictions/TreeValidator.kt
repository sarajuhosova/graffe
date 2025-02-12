package com.sarajuhosova.graffe.restrictions

import com.sarajuhosova.graffe.model.graph.Graph
import com.sarajuhosova.graffe.model.graph.Node
import com.sarajuhosova.graffe.model.graph.structure.Relationship

object TreeValidator: Validator {

    override fun validate(graph: Graph): Boolean {
        if (graph.isEmpty()) return true
        val visited = mutableSetOf<Node>()

        val stack = mutableListOf(graph.first())
        visited.add(stack.first())

        while (stack.isNotEmpty()) {
            val node = stack.removeLast()

            val edges = graph.getEdges(node)
            if (edges.any { it.relationship.type != Relationship.Arrow.DIRECTED })
                return false

            val neighbours = edges
                .filter { it.hasSource(node.name) }
                .map { graph.getNode(it.relationship.target)!! }
            for (neighbour in neighbours) {
                if (neighbour in visited) return false
                visited.add(neighbour)
                stack.add(neighbour)
            }
        }

        return visited.size == graph.size()
    }

}

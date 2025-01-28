package com.sarajuhosova.graffe.model.ast

import com.sarajuhosova.graffe.model.ast.statement.declaration.ComponentDeclaration
import com.sarajuhosova.graffe.model.ast.statement.declaration.GRaffeDeclaration
import com.sarajuhosova.graffe.model.graph.Graph

data class GRaffeProgram(
    val declarations: List<GRaffeDeclaration> = emptyList()
): GRaffeElement() {

    constructor(vararg declarations: GRaffeDeclaration) : this(declarations.toList())

    override fun type(): String = "Program"

    override fun generate(): Graph =
        Graph.fromDeclarations(declarations, null)

    override fun toGRaffeParseTree(): Pair<ComponentDeclaration, List<GRaffeDeclaration>> =
        toGRaffeParseTree(declarations.map { it.toGRaffeParseTree() })

    fun getParseTree(): GRaffeProgram {
        val (root, children) = toGRaffeParseTree()
        return GRaffeProgram(listOf(root) + children)
    }

    override fun toString(): String =
        declarations.joinToString("\n\n")

}

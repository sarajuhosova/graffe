package com.sarajuhosova.graffe.model.ast

import com.sarajuhosova.graffe.model.ast.statement.declaration.GRaffeDeclaration
import com.sarajuhosova.graffe.model.graph.Graph
import com.sarajuhosova.graffe.playground.ParseTreeBuilder

data class GRaffeProgram(
    val declarations: List<GRaffeDeclaration> = emptyList()
): GRaffeElement() {

    constructor(vararg declarations: GRaffeDeclaration) : this(declarations.toList())

    override fun generate(): Graph =
        Graph.fromDeclarations(declarations, null)

    fun getParseTree(): GRaffeProgram {
        val (root, children) = ParseTreeBuilder().visit(this)
        return GRaffeProgram(listOf(root) + children)
    }

    override fun toString(): String =
        declarations.joinToString("\n\n")

}

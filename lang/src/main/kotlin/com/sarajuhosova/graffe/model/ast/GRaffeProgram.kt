package com.sarajuhosova.graffe.model.ast

import com.sarajuhosova.graffe.helper.ASTid
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

    override fun toGRaffeParseTree(
        id: String
    ): Pair<ComponentDeclaration, List<GRaffeDeclaration>> {
        val children = declarations.mapIndexed { i, d -> d.toGRaffeParseTree("$id-${i + 1}") }
        return toGRaffeParseTree(children, id)
    }

    fun getParseTree(): GRaffeProgram {
        val (root, children) = toGRaffeParseTree("")
        return GRaffeProgram(listOf(root) + children)
    }

    override fun toString(): String =
        declarations.joinToString("\n\n")

}

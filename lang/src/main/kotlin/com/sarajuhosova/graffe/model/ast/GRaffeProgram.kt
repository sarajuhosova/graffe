package com.sarajuhosova.graffe.model.ast

import com.sarajuhosova.graffe.model.ast.statement.declaration.GRaffeDeclaration
import com.sarajuhosova.graffe.model.graph.Graph

data class GRaffeProgram(
    val declarations: List<GRaffeDeclaration> = emptyList()
): GRaffeElement() {

    constructor(vararg declarations: GRaffeDeclaration) : this(declarations.toList())

    override fun generate(): Graph =
        Graph.fromDeclarations(declarations)

    override fun toString(): String =
        declarations.joinToString("\n\n")

}

package com.sarajuhosova.graffe.model.ast

import com.sarajuhosova.graffe.model.ast.statement.declaration.GRaffeDeclaration

data class GRaffeProgram(
    val declarations: List<GRaffeDeclaration> = emptyList()
): GRaffeElement() {

    override fun toString(): String =
        declarations.joinToString("\n\n")

}

package com.sarajuhosova.graffe.model.ast.statement.declaration

import com.sarajuhosova.graffe.helper.buildIndented
import com.sarajuhosova.graffe.model.ast.statement.GRaffeStatement

data class ComponentDeclaration(
    val name: String,
    val statements: List<GRaffeStatement> = emptyList()
): GRaffeDeclaration() {

    override fun toString(): String =
        buildIndented(name, statements)

}

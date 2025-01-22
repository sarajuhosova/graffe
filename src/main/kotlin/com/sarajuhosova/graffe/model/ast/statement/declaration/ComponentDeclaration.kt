package com.sarajuhosova.graffe.model.ast.statement.declaration

import com.sarajuhosova.graffe.helper.indentedBlock
import com.sarajuhosova.graffe.model.ast.statement.GRaffeStatement

data class ComponentDeclaration(
    val name: String,
    val statements: List<GRaffeStatement> = emptyList()
): GRaffeDeclaration() {

    override fun toString(): String =
        """
            $name {
                ${statements.indentedBlock()}
            }
        """.trimIndent()

}

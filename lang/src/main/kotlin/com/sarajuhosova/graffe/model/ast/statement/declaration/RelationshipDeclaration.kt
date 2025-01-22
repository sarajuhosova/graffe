package com.sarajuhosova.graffe.model.ast.statement.declaration

import com.sarajuhosova.graffe.enums.Arrow
import com.sarajuhosova.graffe.helper.indentedBlock
import com.sarajuhosova.graffe.model.ast.statement.property.GRaffeProperty

data class RelationshipDeclaration(
    val source: String,
    val target: String,
    val arrow: Arrow,
    val properties: List<GRaffeProperty> = emptyList()
): GRaffeDeclaration() {

    override fun toString(): String =
        """
            $source $arrow $target {
                ${properties.indentedBlock()}
            }
        """.trimIndent()

}

package com.sarajuhosova.graffe.model.ast.statement.declaration

import com.sarajuhosova.graffe.enums.Arrow
import com.sarajuhosova.graffe.helper.buildIndented
import com.sarajuhosova.graffe.model.ast.statement.GRaffeProperty

data class RelationshipDeclaration(
    val source: String,
    val target: String,
    val arrow: Arrow,
    val properties: List<GRaffeProperty> = emptyList()
): GRaffeDeclaration() {

    override fun toString(): String = buildIndented(
        "$source $arrow $target",
        properties
    )

}

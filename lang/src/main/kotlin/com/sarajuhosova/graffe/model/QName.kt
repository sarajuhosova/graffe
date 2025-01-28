package com.sarajuhosova.graffe.model

import com.sarajuhosova.graffe.model.ast.statement.GRaffeProperty
import com.sarajuhosova.graffe.model.ast.statement.declaration.ComponentDeclaration
import com.sarajuhosova.graffe.model.ast.statement.declaration.GRaffeDeclaration
import com.sarajuhosova.graffe.model.ast.statement.declaration.RelationshipDeclaration
import com.sarajuhosova.graffe.model.property.StringProperty

data class QName(val path: List<String>) {

    constructor(vararg path: String) : this(path.toList())

    fun toGRaffeParseTree(
        id: String
    ): Pair<ComponentDeclaration, List<GRaffeDeclaration>> {
        val qname = ComponentDeclaration("QName$id")

        val names = path.mapIndexed { i, n -> ComponentDeclaration(
            "Name$id-${i + 1}", listOf(
                GRaffeProperty("value", StringProperty(n))
            )
        ) }
        val relationships = names.map { RelationshipDeclaration(
            qname.name, it.name, RelationshipDeclaration.Arrow.RIGHT
        ) }

        return qname to names + relationships
    }

    override fun toString(): String =
        path.joinToString(".")

}

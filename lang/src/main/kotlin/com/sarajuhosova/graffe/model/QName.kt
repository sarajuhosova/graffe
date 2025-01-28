package com.sarajuhosova.graffe.model

import com.sarajuhosova.graffe.model.ast.statement.GRaffeProperty
import com.sarajuhosova.graffe.model.ast.statement.declaration.ComponentDeclaration
import com.sarajuhosova.graffe.model.ast.statement.declaration.GRaffeDeclaration
import com.sarajuhosova.graffe.model.ast.statement.declaration.RelationshipDeclaration
import com.sarajuhosova.graffe.model.property.StringProperty

data class QName(val path: List<String>) {

    constructor(vararg path: String) : this(path.toList())

    fun toGRaffeParseTree(): Pair<ComponentDeclaration, List<GRaffeDeclaration>> {
        val qname = ComponentDeclaration("QName")

        val names = path.map { ComponentDeclaration(
            "Name", listOf(
                GRaffeProperty("value", StringProperty(it))
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

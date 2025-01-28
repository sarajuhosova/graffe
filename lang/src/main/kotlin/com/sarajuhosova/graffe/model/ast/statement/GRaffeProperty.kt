package com.sarajuhosova.graffe.model.ast.statement

import com.sarajuhosova.graffe.model.ast.statement.declaration.ComponentDeclaration
import com.sarajuhosova.graffe.model.ast.statement.declaration.GRaffeDeclaration
import com.sarajuhosova.graffe.model.graph.Property
import com.sarajuhosova.graffe.model.property.PropertyValue
import com.sarajuhosova.graffe.model.property.StringProperty

data class GRaffeProperty(
    val name: String,
    val value: PropertyValue
): GRaffeStatement() {

    override fun generate(): Property = Property(name, value)
    override fun type(): String = "Property"

    override fun toGRaffeParseTree(): Pair<ComponentDeclaration, List<GRaffeDeclaration>> {
        val key = ComponentDeclaration("Name", listOf(
            GRaffeProperty("value", StringProperty(name))
        ))
        val value = ComponentDeclaration("Value", listOf(
            GRaffeProperty("value", value)
        ))
        return toGRaffeParseTree(listOf(key, value).map { it to emptyList() })
    }

    override fun toString(): String {
        return "$name: $value;"
    }

}

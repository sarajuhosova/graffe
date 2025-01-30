package com.sarajuhosova.graffe.lang.model.ast.statement

import com.sarajuhosova.graffe.lang.model.graph.Property
import com.sarajuhosova.graffe.lang.model.property.PropertyValue

data class GRaffeProperty(
    val name: String,
    val value: PropertyValue
): GRaffeStatement() {

    override fun generate(): Property = Property(name, value)

    override fun toString(): String {
        return "$name: $value;"
    }

}

package com.sarajuhosova.graffe.model.ast.statement

import com.sarajuhosova.graffe.model.property.PropertyValue

data class GRaffeProperty(
    val name: String,
    val value: PropertyValue
): GRaffeStatement() {

    override fun toString(): String {
        return "$name: $value;"
    }

}

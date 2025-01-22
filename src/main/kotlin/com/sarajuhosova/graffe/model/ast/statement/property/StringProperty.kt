package com.sarajuhosova.graffe.model.ast.statement.property

data class StringProperty(
    override val name: String,
    override val value: String
): GRaffeProperty() {

    override fun valueToString(): String = "\"$value\""

}

package com.sarajuhosova.graffe.model.ast.property

data class StringProperty(
    override val name: String,
    override val value: String
): GRaffeProperty()

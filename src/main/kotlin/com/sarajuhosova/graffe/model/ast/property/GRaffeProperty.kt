package com.sarajuhosova.graffe.model.ast.property

import com.sarajuhosova.graffe.model.ast.GRaffeElement

abstract class GRaffeProperty: GRaffeElement() {
    abstract val name: String
    abstract val value: Any

    override fun toString(): String {
        return "$name: $value"
    }

}
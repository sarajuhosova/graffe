package com.sarajuhosova.graffe.model.ast.statement.property

import com.sarajuhosova.graffe.model.ast.statement.GRaffeStatement

abstract class GRaffeProperty: GRaffeStatement() {
    abstract val name: String
    abstract val value: Any

    protected open fun valueToString(): String = value.toString()

    override fun toString(): String = "$name: ${valueToString()};"

}
package com.sarajuhosova.graffe.model.ast

data class GRaffeString(val value: String): GRaffeElement() {

    override fun toString(): String {
        return "\"$value\""
    }

}

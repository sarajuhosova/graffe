package com.sarajuhosova.graffe.model.property

data class StringProperty(val value: String) : PropertyValue() {

    override fun toString(): String {
        return "\"$value\""
    }

}

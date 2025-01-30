package com.sarajuhosova.graffe.lang.model.property

data class StringProperty(val value: String) : PropertyValue() {

    override fun toString(): String {
        return "\"$value\""
    }

}

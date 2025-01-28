package com.sarajuhosova.graffe.exception.generation

class MissingComponentsException(private val names: List<String>) : Exception() {
    override val message: String
        get() = "The following components have a relationship " +
                "but are not defined:\n    ${names.joinToString(", ")}"
}

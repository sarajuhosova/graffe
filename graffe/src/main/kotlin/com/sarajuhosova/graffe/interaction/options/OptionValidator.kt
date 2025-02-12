package com.sarajuhosova.graffe.interaction.options

interface OptionValidator {

    fun validate(vararg arguments: String): Boolean = validate(arguments.toList())

    fun validate(arguments: List<String>): Boolean

}

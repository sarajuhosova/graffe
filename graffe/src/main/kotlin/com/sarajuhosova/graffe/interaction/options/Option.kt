package com.sarajuhosova.graffe.interaction.options

import com.sarajuhosova.graffe.exception.InvalidProgramArgumentsException
import com.sarajuhosova.graffe.helper.indent

sealed class Option(
    protected val alias: String,
    private val arguments: String,
    protected val description: String
) {

    var configured: Boolean = false
        private set

    protected abstract fun validate(args: List<String>)
    protected abstract fun config(args: List<String>)

    fun configure(args: List<String>) {
        if (configured)
            throw InvalidProgramArgumentsException("$alias is already configured")
        validate(args)
        config(args)
        configured = true
    }

    fun usage(): String = buildString {
        appendLine("${this@Option} $arguments")
        appendLine(description.indent())
    }

    override fun toString(): String = "--$alias"

    companion object {
        private val ALL: List<Option> = listOf(
            InterfaceOption
        ).sortedBy { it.alias }

        fun values(): List<Option> = ALL

        private val ALIAS_MAP: Map<String, Option> = ALL.associateBy { it.toString() }

        operator fun get(option: String): Option? = ALIAS_MAP[option]

        @Throws(InvalidProgramArgumentsException::class)
        fun compileOptions(options: List<String>) {
            fun addOption(key: String, values: List<String>) {
                val option = Option[key] ?: throw InvalidProgramArgumentsException("$key is not a valid option")

                option.configure(values)
            }

            var key = options.first()
            var i = 1
            var values = mutableListOf<String>()
            while (i < options.size) {
                val option = options[i]
                if (option.startsWith("--")) {
                    addOption(key, values)

                    key = option
                    values = mutableListOf()
                } else values.add(option)
                i++
            }
            addOption(key, values)
        }

    }

}

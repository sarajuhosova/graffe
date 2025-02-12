package com.sarajuhosova.graffe.interaction.options

import com.sarajuhosova.graffe.exception.InvalidProgramArgumentsException

enum class Options(
    private val alias: String,
    private val arguments: Int,
    private val validator: OptionValidator
) {
    INTERFACE("interface", 1, UIOptionValidator);

    fun validate(args: List<String>): Boolean =
        args.size == arguments && validator.validate(args)

    companion object {
        private val ALIAS_MAP = entries.associateBy { it.alias }

        operator fun contains(string: String): Boolean = string in ALIAS_MAP
        operator fun get(option: String): Options? = ALIAS_MAP[option]

        @Throws(InvalidProgramArgumentsException::class)
        fun compileOptions(options: List<String>): Map<Options, List<String>> {
            if (options.isEmpty()) return emptyMap()

            val result = mutableMapOf<Options, List<String>>()

            fun addOption(key: String, values: List<String>) {
                val option = Options[key] ?: throw InvalidProgramArgumentsException("$key is not a valid option")

                if (!option.validate(values))
                    throw InvalidProgramArgumentsException("Expected ${option.arguments} arguments for $key")

                if (option in result)
                    throw InvalidProgramArgumentsException("$key is defined multiple times")

                // else
                result[option] = values
            }

            var key = options.first().substring(2)
            var i = 1
            var values = mutableListOf<String>()
            while (i < options.size) {
                val option = options[i]
                if (option.startsWith("--")) {
                    addOption(key, values)

                    key = option.substring(2)
                    values = mutableListOf()
                } else values.add(option)
                i++
            }
            addOption(key, values)
            return result
        }

    }

}

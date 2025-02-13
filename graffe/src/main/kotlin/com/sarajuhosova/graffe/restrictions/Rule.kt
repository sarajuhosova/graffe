package com.sarajuhosova.graffe.restrictions

import com.sarajuhosova.graffe.exception.generation.RestrictionViolatedException
import com.sarajuhosova.graffe.exception.parsing.InvalidRuleException
import com.sarajuhosova.graffe.interaction.options.IgnoreRestrictionsOption
import com.sarajuhosova.graffe.model.graph.Graph

enum class Rule(
    private val validator: Validator
) {
    TREE(TreeValidator);

    fun validate(graph: Graph) {
        if (IgnoreRestrictionsOption.ignore) return
        if (!validator.validate(graph))
            throw RestrictionViolatedException(this)
    }

    companion object {
        private val map: Map<String, Rule> = Rule.entries.associateBy { it.toString().lowercase() }

        fun fromString(value: String): Rule =
            map[value] ?: throw InvalidRuleException(value)
    }
}

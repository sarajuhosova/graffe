package com.sarajuhosova.graffe.model.ast.statement.declaration

import com.sarajuhosova.graffe.exception.parsing.InvalidRuleException
import com.sarajuhosova.graffe.model.graph.GRaffe
import com.sarajuhosova.graffe.model.graph.Graph
import com.sarajuhosova.graffe.restrictions.TreeValidator

class RestrictionDeclaration(val rule: Rule): GRaffeDeclaration() {

    constructor(rule: String): this(Rule.fromString(rule))

    enum class Rule(
        val validator: (Graph) -> Boolean
    ) {
        TREE(TreeValidator::validate);

        companion object {
            private val map: Map<String, Rule> = Rule.entries.associateBy { it.toString().lowercase() }

            fun fromString(value: String): Rule =
                map[value] ?: throw InvalidRuleException(value)
        }
    }

    override fun generate(): GRaffe {
        TODO("Not yet implemented")
    }

}

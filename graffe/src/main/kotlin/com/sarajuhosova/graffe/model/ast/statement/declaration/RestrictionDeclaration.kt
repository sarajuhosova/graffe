package com.sarajuhosova.graffe.model.ast.statement.declaration

import com.sarajuhosova.graffe.exception.parsing.InvalidRuleException
import com.sarajuhosova.graffe.model.graph.GRaffe
import com.sarajuhosova.graffe.model.graph.Graph
import com.sarajuhosova.graffe.restrictions.TreeValidator
import com.sarajuhosova.graffe.restrictions.Validator

class RestrictionDeclaration(val rule: Rule): GRaffeDeclaration() {

    constructor(rule: String): this(Rule.fromString(rule))

    enum class Rule(
        private val validator: Validator
    ) {
        TREE(TreeValidator);

        fun validate(graph: Graph) = this.validator.validate(graph)

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

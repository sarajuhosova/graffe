package com.sarajuhosova.graffe.model.ast.statement.declaration

import com.sarajuhosova.graffe.exception.generation.RestrictionShouldNotGenerateException
import com.sarajuhosova.graffe.model.graph.GRaffe
import com.sarajuhosova.graffe.restrictions.Rule

class RestrictionDeclaration(val rule: Rule): GRaffeDeclaration() {

    constructor(rule: String): this(Rule.fromString(rule))

    override fun generate(): GRaffe =
        throw RestrictionShouldNotGenerateException()

}

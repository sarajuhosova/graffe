package com.sarajuhosova.graffe.model.ast.statement.declaration

import com.sarajuhosova.graffe.model.QName
import com.sarajuhosova.graffe.model.graph.GRaffe

data class IncludeDeclaration(
    val includes: List<QName>
): GRaffeDeclaration() {

    override fun generate(): GRaffe {
        TODO("Not yet implemented")
    }

    constructor(vararg includes: String): this(includes.map { QName(it.split(".")) })

    init {
        require(includes.isNotEmpty()) { "Include declaration must contain at least one include" }
    }

    override fun toString(): String =
        "include ${includes.joinToString(" ")};"

}

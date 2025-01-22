package com.sarajuhosova.graffe.model.ast.statement.declaration

data class IncludeDeclaration(
    val includes: List<String>
): GRaffeDeclaration() {

    init {
        require(includes.isNotEmpty()) { "Include declaration must contain at least one include" }
    }

    override fun toString(): String =
        "include ${includes.joinToString(" " )};"

}

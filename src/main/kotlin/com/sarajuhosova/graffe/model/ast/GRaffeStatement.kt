package com.sarajuhosova.graffe.model.ast

import com.sarajuhosova.graffe.model.ast.property.GRaffeProperty

data class GRaffeStatement(
    val name: String,
    val properties: List<GRaffeProperty>
): GRaffeElement() {

    override fun toString(): String {
        return """
            $name {
                ${properties.joinToString("\n")}
            }
        """.trimIndent()
    }

}
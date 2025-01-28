package com.sarajuhosova.graffe.helper

data class ASTid(
    val depth: Int,
    val count: Int
) {

    override fun toString(): String = "$depth-$count"

}

package com.sarajuhosova.graffe.model

data class QName(val path: List<String>) {

    constructor(vararg path: String) : this(path.toList())

    override fun toString(): String =
        path.joinToString(".")

}

package com.sarajuhosova.graffe.helper

fun <T> List<T>.indentedBlock(): String =
    this.joinToString("\n") { it.toString() }
        .replace("\n", "\t\n")

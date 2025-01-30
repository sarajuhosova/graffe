package com.sarajuhosova.graffe.lang.helper

fun <T> T.indent(): String =
    this.toString().split("\n").joinToString("\n") { "    $it" }

fun <T> List<T>.indentedBlock(): String =
    this.joinToString("\n\n") { it.indent() }

fun <T> buildIndented(first: String, body: List<T>): String =
    buildString {
        append(first)
        if (body.isEmpty()) append(";")
        else {
            append(" {\n")
            append(body.indentedBlock())
            append("\n}")
        }
    }

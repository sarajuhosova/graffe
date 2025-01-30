package com.sarajuhosova.graffe.parser

import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams

fun readToCharStream(filename: String): CharStream =
    Thread.currentThread()
        .contextClassLoader
        .getResourceAsStream(filename)
        .use { input -> CharStreams.fromStream(input) }

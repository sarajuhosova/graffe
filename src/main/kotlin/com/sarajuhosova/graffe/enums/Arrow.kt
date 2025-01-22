package com.sarajuhosova.graffe.enums

import com.sarajuhosova.graffe.exception.parse.InvalidArrowException

enum class Arrow(val symbol: String) {
    LEFT("<-"),
    RIGHT("->"),
    BOTH("<->"),
    NONE("--");

    override fun toString(): String {
        return symbol
    }

    companion object {
        private val map: Map<String, Arrow> = entries.associateBy(Arrow::symbol)

        fun fromString(symbol: String): Arrow =
            map[symbol] ?: throw InvalidArrowException(symbol)
    }

}
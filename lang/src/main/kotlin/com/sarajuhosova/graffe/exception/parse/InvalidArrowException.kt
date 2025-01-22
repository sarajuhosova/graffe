package com.sarajuhosova.graffe.exception.parse

class InvalidArrowException(symbol: String): Exception() {
    override val message: String = "$symbol is not a valid arrow"
}

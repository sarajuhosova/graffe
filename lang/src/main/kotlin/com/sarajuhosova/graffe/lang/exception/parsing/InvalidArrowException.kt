package com.sarajuhosova.graffe.lang.exception.parsing

class InvalidArrowException(symbol: String): Exception() {
    override val message: String = "$symbol is not a valid arrow"
}

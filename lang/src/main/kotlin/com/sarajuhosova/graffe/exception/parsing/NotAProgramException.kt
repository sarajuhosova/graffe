package com.sarajuhosova.graffe.exception.parsing

class NotAProgramException: Exception() {
    override val message: String = "missing top-level declaration"
}

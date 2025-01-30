package com.sarajuhosova.graffe.lang.exception.parsing

class NotAProgramException: Exception() {
    override val message: String = "missing top-level declaration"
}

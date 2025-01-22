package com.sarajuhosova.graffe.exception.parse

class NotAProgramException: Exception() {
    override val message: String = "missing top-level declaration"
}
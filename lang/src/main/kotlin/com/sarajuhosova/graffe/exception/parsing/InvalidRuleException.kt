package com.sarajuhosova.graffe.exception.parsing

class InvalidRuleException(rule: String): Exception() {
    override val message: String = "$rule is not a valid restriction rule"
}

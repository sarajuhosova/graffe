package com.sarajuhosova.graffe.exception.generation

import com.sarajuhosova.graffe.restrictions.Rule

class RestrictionViolatedException(val rule: Rule): Exception() {
    override val message: String = "Restriction ${rule.name} violated"
}

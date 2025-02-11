package com.sarajuhosova.graffe.exception

class CannotEditBuiltBuilderException: Exception() {
    override val message: String = "Cannot edit a builder that has already been built"
}

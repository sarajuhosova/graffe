package com.sarajuhosova.graffe.lang.exception

class InvalidProgramArgumentsException(message: String): Exception(
    "Invalid program arguments: $message"
)

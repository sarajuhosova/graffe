package com.sarajuhosova.graffe.exception

class InvalidProgramArgumentsException(message: String): Exception(
    "Invalid program arguments: $message"
)

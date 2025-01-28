package com.sarajuhosova.graffe.exception

class InvalidProgramArgumentException(message: String): Exception(
    "Invalid program arguments: $message"
)

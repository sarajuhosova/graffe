package com.sarajuhosova.graffe.exception

import com.sarajuhosova.graffe.helper.indent

class InvalidProgramArgumentsException(message: String): Exception(
    "Invalid program arguments:\n${message.indent()}"
)

package com.sarajuhosova.graffe.exception

import com.sarajuhosova.graffe.helper.indent

class InvalidProgramArgumentsException(
    message: String, val type: Type = Type.OTHER
): Exception(
    "Invalid program arguments:\n${message.indent()}"
) {

    enum class Type {
        INVALID_OPTION,
        INCORRECT_NUMBER_OF_ARGUMENTS,
        INVALID_ARGUMENT,
        ALREADY_CONFIGURED,
        OTHER;
    }

}

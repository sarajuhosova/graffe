package com.sarajuhosova.graffe.model.ast

data class GRaffeProgram(val statements: List<GRaffeStatement>) {

        override fun toString(): String {
            return statements.joinToString("\n\n")
        }

}

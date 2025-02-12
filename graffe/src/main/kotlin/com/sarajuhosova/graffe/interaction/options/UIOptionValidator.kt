package com.sarajuhosova.graffe.interaction.options

import com.sarajuhosova.graffe.interaction.ui.Interface

object UIOptionValidator : OptionValidator {

    override fun validate(arguments: List<String>): Boolean =
        arguments[0] in Interface.Type

}

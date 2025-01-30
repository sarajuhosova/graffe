package com.sarajuhosova.graffe.lang.parser.visitor

import com.sarajuhosova.graffe.lang.GRaffeBaseVisitor
import com.sarajuhosova.graffe.lang.GRaffeParser
import com.sarajuhosova.graffe.lang.model.property.PropertyValue
import com.sarajuhosova.graffe.lang.model.property.StringProperty

object PropertyValueBuilder : GRaffeBaseVisitor<PropertyValue>() {

    override fun visitStringValue(
        ctx: GRaffeParser.StringValueContext
    ): StringProperty {
        val text = ctx.String().text
        return StringProperty(text.substring(1, text.length - 1))
    }

}

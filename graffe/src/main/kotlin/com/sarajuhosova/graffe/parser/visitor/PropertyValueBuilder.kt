package com.sarajuhosova.graffe.parser.visitor

import com.sarajuhosova.graffe.GRaffeBaseVisitor
import com.sarajuhosova.graffe.GRaffeParser
import com.sarajuhosova.graffe.model.property.PropertyValue
import com.sarajuhosova.graffe.model.property.StringProperty

object PropertyValueBuilder : com.sarajuhosova.graffe.GRaffeBaseVisitor<PropertyValue>() {

    override fun visitStringValue(
        ctx: com.sarajuhosova.graffe.GRaffeParser.StringValueContext
    ): StringProperty {
        val text = ctx.String().text
        return StringProperty(text.substring(1, text.length - 1))
    }

}

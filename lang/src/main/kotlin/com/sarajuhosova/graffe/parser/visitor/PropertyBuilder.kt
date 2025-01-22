package com.sarajuhosova.graffe.parser.visitor

import com.sarajuhosova.graffe.GRaffeBaseVisitor
import com.sarajuhosova.graffe.GRaffeParser
import com.sarajuhosova.graffe.model.ast.statement.property.GRaffeProperty
import com.sarajuhosova.graffe.model.ast.statement.property.StringProperty

class PropertyBuilder(val name: String) : GRaffeBaseVisitor<GRaffeProperty>() {

    override fun visitStringValue(ctx: GRaffeParser.StringValueContext): StringProperty {
        val text = ctx.String().text
        return StringProperty(name, text.substring(1, text.length - 1))
    }

}

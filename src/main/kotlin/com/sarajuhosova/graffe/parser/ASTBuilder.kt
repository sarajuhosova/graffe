package com.sarajuhosova.graffe.parser

import com.sarajuhosova.graffe.GRaffeBaseVisitor
import com.sarajuhosova.graffe.GRaffeParser
import com.sarajuhosova.graffe.model.ast.GRaffeElement
import com.sarajuhosova.graffe.model.ast.property.GRaffeProperty
import com.sarajuhosova.graffe.model.ast.property.StringProperty

object ASTBuilder : GRaffeBaseVisitor<GRaffeElement>() {

    override fun visitProperty(ctx: GRaffeParser.PropertyContext): GRaffeElement {
        return PropertyBuilder(ctx.Name().text).visit(ctx.value())
    }

    class PropertyBuilder(val name: String) : GRaffeBaseVisitor<GRaffeProperty>() {

        override fun visitStringValue(ctx: GRaffeParser.StringValueContext): GRaffeProperty {
            return StringProperty(name, ctx.String().text)
        }

    }

}
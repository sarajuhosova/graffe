package com.sarajuhosova.graffe.lang.parser.visitor

import com.sarajuhosova.graffe.lang.GRaffeBaseVisitor
import com.sarajuhosova.graffe.lang.GRaffeParser
import com.sarajuhosova.graffe.lang.model.ast.statement.GRaffeProperty
import com.sarajuhosova.graffe.lang.model.ast.statement.GRaffeStatement
import com.sarajuhosova.graffe.lang.model.ast.statement.declaration.GRaffeDeclaration
import com.sarajuhosova.graffe.lang.parser.ASTBuilder

object StatementBuilder : GRaffeBaseVisitor<GRaffeStatement>() {

    override fun visitDeclaration(
        ctx: GRaffeParser.DeclarationContext
    ): GRaffeDeclaration {
        return DeclarationBuilder.visit(ctx)
    }

    override fun visitProperty(
        ctx: GRaffeParser.PropertyContext
    ): GRaffeProperty {
        return ASTBuilder.visitProperty(ctx)
    }

}

package com.sarajuhosova.graffe.parser.visitor

import com.sarajuhosova.graffe.GRaffeBaseVisitor
import com.sarajuhosova.graffe.GRaffeParser
import com.sarajuhosova.graffe.model.ast.statement.GRaffeProperty
import com.sarajuhosova.graffe.model.ast.statement.GRaffeStatement
import com.sarajuhosova.graffe.model.ast.statement.declaration.GRaffeDeclaration
import com.sarajuhosova.graffe.parser.ASTBuilder

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

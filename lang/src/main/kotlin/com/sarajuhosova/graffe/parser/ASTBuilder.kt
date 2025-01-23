package com.sarajuhosova.graffe.parser

import com.sarajuhosova.graffe.GRaffeBaseVisitor
import com.sarajuhosova.graffe.GRaffeParser
import com.sarajuhosova.graffe.model.ast.GRaffeElement
import com.sarajuhosova.graffe.model.ast.GRaffeProgram
import com.sarajuhosova.graffe.model.ast.statement.GRaffeStatement
import com.sarajuhosova.graffe.model.ast.statement.declaration.GRaffeDeclaration
import com.sarajuhosova.graffe.model.ast.statement.property.GRaffeProperty
import com.sarajuhosova.graffe.parser.visitor.DeclarationBuilder
import com.sarajuhosova.graffe.parser.visitor.PropertyBuilder
import com.sarajuhosova.graffe.parser.visitor.StatementBuilder

object ASTBuilder : GRaffeBaseVisitor<GRaffeElement>() {

    /**
     * declaration* EOF
     */
    override fun visitParse(ctx: GRaffeParser.ParseContext): GRaffeProgram =
        GRaffeProgram(ctx.declaration().map { visitDeclaration(it) })

    /**
     * declaration
     *     : componentDecl
     *     | relationshipDecl
     *     | includeDecl
     *     ;
     */
    override fun visitDeclaration(ctx: GRaffeParser.DeclarationContext?): GRaffeDeclaration {
        return DeclarationBuilder.visit(ctx)
    }

    /**
     * statement
     *     : property
     *     | declaration
     *     ;
     */
    override fun visitStatement(ctx: GRaffeParser.StatementContext?): GRaffeStatement {
        return StatementBuilder.visitStatement(ctx)
    }

    /**
     * Name ':' value EOL
     */
    override fun visitProperty(ctx: GRaffeParser.PropertyContext): GRaffeProperty {
        return PropertyBuilder(ctx.Name().text).visit(ctx.value())
    }

}

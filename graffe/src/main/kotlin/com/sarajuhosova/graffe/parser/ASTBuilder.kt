package com.sarajuhosova.graffe.parser

import com.sarajuhosova.graffe.GRaffeBaseVisitor
import com.sarajuhosova.graffe.GRaffeParser
import com.sarajuhosova.graffe.model.ast.GRaffeElement
import com.sarajuhosova.graffe.model.ast.GRaffeProgram
import com.sarajuhosova.graffe.model.ast.statement.GRaffeProperty
import com.sarajuhosova.graffe.model.ast.statement.GRaffeStatement
import com.sarajuhosova.graffe.model.ast.statement.declaration.GRaffeDeclaration
import com.sarajuhosova.graffe.parser.visitor.DeclarationBuilder
import com.sarajuhosova.graffe.parser.visitor.PropertyValueBuilder
import com.sarajuhosova.graffe.parser.visitor.StatementBuilder

object ASTBuilder : GRaffeBaseVisitor<GRaffeElement>() {

    /**
     * restriction* declaration* EOF
     */
    override fun visitProgram(
        ctx: GRaffeParser.ProgramContext
    ): GRaffeProgram =
        GRaffeProgram(ctx.declaration().map { visitDeclaration(it) })

    /**
     * declaration
     *     : componentDecl
     *     | relationshipDecl
     *     | includeDecl
     *     ;
     */
    override fun visitDeclaration(
        ctx: GRaffeParser.DeclarationContext
    ): GRaffeDeclaration =
        DeclarationBuilder.visitDeclaration(ctx)

    /**
     * statement
     *     : property
     *     | declaration
     *     ;
     */
    override fun visitStatement(
        ctx: GRaffeParser.StatementContext
    ): GRaffeStatement =
        StatementBuilder.visit(ctx)

    /**
     * Name ':' value EOL
     */
    override fun visitProperty(
        ctx: GRaffeParser.PropertyContext
    ): GRaffeProperty =
        GRaffeProperty(ctx.Name().text, PropertyValueBuilder.visit(ctx.value()))

}

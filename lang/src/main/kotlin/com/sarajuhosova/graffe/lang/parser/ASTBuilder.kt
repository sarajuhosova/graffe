package com.sarajuhosova.graffe.lang.parser

import com.sarajuhosova.graffe.lang.GRaffeBaseVisitor
import com.sarajuhosova.graffe.lang.GRaffeParser
import com.sarajuhosova.graffe.lang.model.ast.GRaffeElement
import com.sarajuhosova.graffe.lang.model.ast.GRaffeProgram
import com.sarajuhosova.graffe.lang.model.ast.statement.GRaffeProperty
import com.sarajuhosova.graffe.lang.model.ast.statement.GRaffeStatement
import com.sarajuhosova.graffe.lang.model.ast.statement.declaration.GRaffeDeclaration
import com.sarajuhosova.graffe.lang.parser.visitor.DeclarationBuilder
import com.sarajuhosova.graffe.lang.parser.visitor.PropertyValueBuilder
import com.sarajuhosova.graffe.lang.parser.visitor.StatementBuilder

object ASTBuilder : GRaffeBaseVisitor<GRaffeElement>() {

    /**
     * declaration* EOF
     */
    override fun visitParse(
        ctx: GRaffeParser.ParseContext
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
        ctx: GRaffeParser.DeclarationContext?
    ): GRaffeDeclaration =
        DeclarationBuilder.visit(ctx)

    /**
     * statement
     *     : property
     *     | declaration
     *     ;
     */
    override fun visitStatement(
        ctx: GRaffeParser.StatementContext?
    ): GRaffeStatement =
        StatementBuilder.visitStatement(ctx)

    /**
     * Name ':' value EOL
     */
    override fun visitProperty(
        ctx: GRaffeParser.PropertyContext
    ): GRaffeProperty =
        GRaffeProperty(ctx.Name().text, PropertyValueBuilder.visit(ctx.value()))

}

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

object ASTBuilder : com.sarajuhosova.graffe.GRaffeBaseVisitor<GRaffeElement>() {

    /**
     * declaration* EOF
     */
    override fun visitParse(
        ctx: com.sarajuhosova.graffe.GRaffeParser.ParseContext
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
        ctx: com.sarajuhosova.graffe.GRaffeParser.DeclarationContext?
    ): GRaffeDeclaration =
        DeclarationBuilder.visit(ctx)

    /**
     * statement
     *     : property
     *     | declaration
     *     ;
     */
    override fun visitStatement(
        ctx: com.sarajuhosova.graffe.GRaffeParser.StatementContext?
    ): GRaffeStatement =
        StatementBuilder.visitStatement(ctx)

    /**
     * Name ':' value EOL
     */
    override fun visitProperty(
        ctx: com.sarajuhosova.graffe.GRaffeParser.PropertyContext
    ): GRaffeProperty =
        GRaffeProperty(ctx.Name().text, PropertyValueBuilder.visit(ctx.value()))

}

package com.sarajuhosova.graffe.lang.parser.visitor

import com.sarajuhosova.graffe.lang.GRaffeBaseVisitor
import com.sarajuhosova.graffe.lang.GRaffeParser
import com.sarajuhosova.graffe.lang.model.QName
import com.sarajuhosova.graffe.lang.model.ast.statement.declaration.ComponentDeclaration
import com.sarajuhosova.graffe.lang.model.ast.statement.declaration.GRaffeDeclaration
import com.sarajuhosova.graffe.lang.model.ast.statement.declaration.IncludeDeclaration
import com.sarajuhosova.graffe.lang.model.ast.statement.declaration.RelationshipDeclaration
import com.sarajuhosova.graffe.lang.parser.ASTBuilder

object DeclarationBuilder : GRaffeBaseVisitor<GRaffeDeclaration>() {

    /**
     * Name ('{' statement* '}' | EOL);
     */
    override fun visitComponentDecl(
        ctx: GRaffeParser.ComponentDeclContext
    ): ComponentDeclaration {
        return ComponentDeclaration(
            ctx.Name().text,
            ctx.statement().map { ASTBuilder.visitStatement(it) }
        )
    }

    /**
     * Name Arrow Name ('{' property* '}' | EOL);
     */
    override fun visitRelationshipDecl(
        ctx: GRaffeParser.RelationshipDeclContext
    ): RelationshipDeclaration {
        return RelationshipDeclaration(
            ctx.Name().first().text,
            ctx.Name().last().text,
            RelationshipDeclaration.Arrow.fromString(ctx.Arrow().text),
            ctx.property().map { ASTBuilder.visitProperty(it) }
        )
    }

    /**
     * 'include' Name+ EOL;
     */
    override fun visitIncludeDecl(
        ctx: GRaffeParser.IncludeDeclContext
    ): IncludeDeclaration {
        return IncludeDeclaration(ctx.qname()
            .map { q -> QName(q.Name().map { name -> name.text }) }
        )
    }

}

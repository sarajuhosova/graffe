package com.sarajuhosova.graffe.parser.visitor

import com.sarajuhosova.graffe.GRaffeBaseVisitor
import com.sarajuhosova.graffe.GRaffeParser
import com.sarajuhosova.graffe.model.QName
import com.sarajuhosova.graffe.model.ast.statement.declaration.ComponentDeclaration
import com.sarajuhosova.graffe.model.ast.statement.declaration.GRaffeDeclaration
import com.sarajuhosova.graffe.model.ast.statement.declaration.IncludeDeclaration
import com.sarajuhosova.graffe.model.ast.statement.declaration.RelationshipDeclaration
import com.sarajuhosova.graffe.parser.ASTBuilder

object DeclarationBuilder : com.sarajuhosova.graffe.GRaffeBaseVisitor<GRaffeDeclaration>() {

    /**
     * Name ('{' statement* '}' | EOL);
     */
    override fun visitComponentDecl(
        ctx: com.sarajuhosova.graffe.GRaffeParser.ComponentDeclContext
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
        ctx: com.sarajuhosova.graffe.GRaffeParser.RelationshipDeclContext
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
        ctx: com.sarajuhosova.graffe.GRaffeParser.IncludeDeclContext
    ): IncludeDeclaration {
        return IncludeDeclaration(ctx.qname()
            .map { q -> QName(q.Name().map { name -> name.text }) }
        )
    }

}

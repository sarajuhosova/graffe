package com.sarajuhosova.graffe.parser.visitor

import com.sarajuhosova.graffe.GRaffeBaseVisitor
import com.sarajuhosova.graffe.GRaffeParser
import com.sarajuhosova.graffe.model.QName
import com.sarajuhosova.graffe.model.ast.statement.declaration.*
import com.sarajuhosova.graffe.parser.ASTBuilder
import org.antlr.v4.runtime.tree.ParseTree

object DeclarationBuilder : GRaffeBaseVisitor<GRaffeDeclaration>() {

    override fun visit(tree: ParseTree): GRaffeDeclaration {
        return super.visit(tree)
    }

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
            ctx.Arrow().text,
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

    /**
     * '#' Restriction '!';
     */
    override fun visitRestrictionDecl(
        ctx: GRaffeParser.RestrictionDeclContext
    ): RestrictionDeclaration =
        RestrictionDeclaration(ctx.Restriction().text)

}

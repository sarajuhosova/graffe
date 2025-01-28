package com.sarajuhosova.graffe.model.ast

import com.sarajuhosova.graffe.helper.ASTid
import com.sarajuhosova.graffe.model.ast.statement.declaration.ComponentDeclaration
import com.sarajuhosova.graffe.model.ast.statement.declaration.GRaffeDeclaration
import com.sarajuhosova.graffe.model.ast.statement.declaration.RelationshipDeclaration
import com.sarajuhosova.graffe.model.graph.GRaffe

abstract class GRaffeElement {

    abstract fun generate(): GRaffe

    abstract fun type(): String

    protected fun toGRaffeParseTree(
        children: List<Pair<ComponentDeclaration, List<GRaffeDeclaration>>>,
        id: String
    ): Pair<ComponentDeclaration, List<GRaffeDeclaration>> {
        val itself = ComponentDeclaration("${type()}$id")

        val relationships = children.map { (child, _) ->
            RelationshipDeclaration(itself.name, child.name, RelationshipDeclaration.Arrow.RIGHT)
        }

        return Pair(itself, children.flatMap { it.second } + relationships)
    }

    abstract fun toGRaffeParseTree(id: String): Pair<ComponentDeclaration, List<GRaffeDeclaration>>

}

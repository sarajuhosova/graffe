package com.sarajuhosova.graffe.model.ast.statement.declaration

import com.sarajuhosova.graffe.helper.buildIndented
import com.sarajuhosova.graffe.model.ast.statement.GRaffeProperty
import com.sarajuhosova.graffe.model.ast.statement.GRaffeStatement
import com.sarajuhosova.graffe.model.graph.Graph
import com.sarajuhosova.graffe.model.graph.Node

data class ComponentDeclaration(
    val name: String,
    val statements: List<GRaffeStatement> = emptyList()
): GRaffeDeclaration() {

    override fun generate(): Node {
        val node = Node(name)

        for (property in statements.filterIsInstance<GRaffeProperty>()) {
            node.addProperty(property.generate())
        }

        val declarations = statements.filterIsInstance<GRaffeDeclaration>()
        if (declarations.isNotEmpty()) {
            node.child = Graph.fromDeclarations(declarations, node)
        }

        return node
    }

    override fun toString(): String =
        buildIndented(name, statements)

}

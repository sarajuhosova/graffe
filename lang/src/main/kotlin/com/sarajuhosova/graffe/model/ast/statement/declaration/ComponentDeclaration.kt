package com.sarajuhosova.graffe.model.ast.statement.declaration

import com.sarajuhosova.graffe.helper.buildIndented
import com.sarajuhosova.graffe.model.ast.statement.GRaffeProperty
import com.sarajuhosova.graffe.model.ast.statement.GRaffeStatement
import com.sarajuhosova.graffe.model.graph.Graph
import com.sarajuhosova.graffe.model.graph.Node
import com.sarajuhosova.graffe.model.property.StringProperty

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

    override fun type(): String = "Component"

    override fun toGRaffeParseTree(id: String): Pair<ComponentDeclaration, List<GRaffeDeclaration>> {
        val name = Pair(ComponentDeclaration("Name$id", listOf(
            GRaffeProperty("value", StringProperty(name))
        )), emptyList<GRaffeDeclaration>())
        val stmts = statements.mapIndexed { i, s -> s.toGRaffeParseTree("$id-${i + 1}") }
        return toGRaffeParseTree(listOf(name) + stmts, id)
    }

    override fun toString(): String =
        buildIndented(name, statements)

}

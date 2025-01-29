package com.sarajuhosova.graffe.playground

import com.sarajuhosova.graffe.helper.visitor.GRaffeElementVisitor
import com.sarajuhosova.graffe.model.QName
import com.sarajuhosova.graffe.model.ast.GRaffeProgram
import com.sarajuhosova.graffe.model.ast.statement.GRaffeProperty
import com.sarajuhosova.graffe.model.ast.statement.GRaffeStatement
import com.sarajuhosova.graffe.model.ast.statement.declaration.ComponentDeclaration
import com.sarajuhosova.graffe.model.ast.statement.declaration.IncludeDeclaration
import com.sarajuhosova.graffe.model.ast.statement.declaration.RelationshipDeclaration
import com.sarajuhosova.graffe.model.property.StringProperty

class ParseTreeBuilder(): GRaffeElementVisitor<ParseTree>() {

    private var id = 0

    private fun component(
        name: String, statements: List<GRaffeStatement> = emptyList()
    ) = ComponentDeclaration("$name-${id++}", statements)

    private fun toParseTree(name: String, children: List<ParseTree>): ParseTree {
        val itself = component(name)

        val relationships = children.map { (child, _) ->
            RelationshipDeclaration(itself.name, child.name, RelationshipDeclaration.Arrow.RIGHT)
        }

        return Pair(itself, children.flatMap { it.second } + relationships)
    }

    override fun visitProgram(program: GRaffeProgram): ParseTree {
        val children = program.declarations.map { d -> visitDeclaration(d) }
        return toParseTree("Program", children)
    }

    override fun visitProperty(property: GRaffeProperty): ParseTree {
        val key = component("Name", listOf(
            GRaffeProperty("value", StringProperty(property.name))
        ))
        val value = component("Value", listOf(
            GRaffeProperty("value", property.value)
        ))
        return toParseTree("Property", listOf(key, value).map { leaf(it) })
    }

    override fun visitComponent(component: ComponentDeclaration): ParseTree {
        val name = leaf(component("Name", listOf(
            GRaffeProperty("value", StringProperty(component.name))
        )))
        val stmts = component.statements.map { visitStatement(it) }
        return toParseTree("Component", listOf(name) + stmts)
    }

    override fun visitRelationship(relationship: RelationshipDeclaration): ParseTree {
        val definition = listOf(
            component("Name", listOf(
                GRaffeProperty("value", StringProperty(relationship.left))
            )),
            component("Arrow", listOf(
                GRaffeProperty("value", StringProperty(relationship.arrow.symbol))
            )),
            component("Name", listOf(
                GRaffeProperty("value", StringProperty(relationship.right))
            ))
        ).map { leaf(it) }
        val props = relationship.properties.map { visitProperty(it) }

        return toParseTree("Relationship", definition + props)
    }

    override fun visitInclude(include: IncludeDeclaration): ParseTree =
        toParseTree("Include", include.includes.map { visitQName(it) })

    private fun visitQName(qName: QName): ParseTree {
        val qname = component("QName")

        val names = qName.path.map { component("Name", listOf(
            GRaffeProperty("value", StringProperty(it))
        )) }
        val relationships = names.map { RelationshipDeclaration(
            qname.name, it.name, RelationshipDeclaration.Arrow.RIGHT
        ) }

        return qname to names + relationships
    }

}

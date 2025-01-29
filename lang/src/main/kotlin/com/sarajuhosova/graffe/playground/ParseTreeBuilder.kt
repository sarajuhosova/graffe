package com.sarajuhosova.graffe.playground

import com.sarajuhosova.graffe.helper.visitor.GRaffeElementVisitor
import com.sarajuhosova.graffe.model.QName
import com.sarajuhosova.graffe.model.ast.GRaffeProgram
import com.sarajuhosova.graffe.model.ast.statement.GRaffeProperty
import com.sarajuhosova.graffe.model.ast.statement.GRaffeStatement
import com.sarajuhosova.graffe.model.ast.statement.declaration.ComponentDeclaration
import com.sarajuhosova.graffe.model.ast.statement.declaration.IncludeDeclaration
import com.sarajuhosova.graffe.model.ast.statement.declaration.RelationshipDeclaration
import com.sarajuhosova.graffe.model.property.PropertyValue
import com.sarajuhosova.graffe.model.property.StringProperty

class ParseTreeBuilder(): GRaffeElementVisitor<ParseTree>() {

    private var id = 0

    private fun component(
        name: String, statements: List<GRaffeStatement> = emptyList()
    ): ComponentDeclaration = ComponentDeclaration("$name-${id++}", statements)

    private fun value(value: String): GRaffeProperty = GRaffeProperty("value", StringProperty(value))
    private fun value(value: PropertyValue): GRaffeProperty = GRaffeProperty("value", value)

    private fun toParseTree(name: String, children: List<ParseTree>): ParseTree {
        val itself = component(name)

        val relationships = children.map { (child, _) ->
            RelationshipDeclaration(itself.name, child.name, RelationshipDeclaration.Arrow.RIGHT)
        }

        return Pair(itself, listOf(itself) + children.flatMap { it.second } + relationships)
    }

    override fun visitProgram(program: GRaffeProgram): ParseTree {
        val children = program.declarations.map { d -> visitDeclaration(d) }
        return toParseTree("Program", children)
    }

    override fun visitProperty(property: GRaffeProperty): ParseTree {
        val key = component("Name", listOf(value(property.name)))
        val value = component("Value", listOf(value(property.value)))
        return toParseTree("Property", listOf(key, value).map { leaf(it) })
    }

    override fun visitComponent(component: ComponentDeclaration): ParseTree {
        val name = leaf(component("Name", listOf(value(component.name))))
        val stmts = component.statements.map { visitStatement(it) }
        return toParseTree("Component", listOf(name) + stmts)
    }

    override fun visitRelationship(relationship: RelationshipDeclaration): ParseTree {
        val definition = listOf(
            component("Name", listOf(value(relationship.left))),
            component("Arrow", listOf(value(relationship.arrow.symbol))),
            component("Name", listOf(value(relationship.right)))
        ).map { leaf(it) }
        val props = relationship.properties.map { visitProperty(it) }

        return toParseTree("Relationship", definition + props)
    }

    override fun visitInclude(include: IncludeDeclaration): ParseTree =
        toParseTree("Include", include.includes.map { visitQName(it) })

    private fun visitQName(qName: QName): ParseTree {
        val component = component("QName")

        val names = qName.path.map { component("Name", listOf(value(it))) }
        val relationships = names.map { RelationshipDeclaration(
            component.name, it.name, RelationshipDeclaration.Arrow.RIGHT
        ) }

        return component to listOf(component) + names + relationships
    }

}

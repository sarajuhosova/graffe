package com.sarajuhosova.graffe.helper.visitor

import com.sarajuhosova.graffe.exception.parsing.NoVisitorDefinedException
import com.sarajuhosova.graffe.model.ast.GRaffeElement
import com.sarajuhosova.graffe.model.ast.GRaffeProgram
import com.sarajuhosova.graffe.model.ast.statement.GRaffeProperty
import com.sarajuhosova.graffe.model.ast.statement.GRaffeStatement
import com.sarajuhosova.graffe.model.ast.statement.declaration.ComponentDeclaration
import com.sarajuhosova.graffe.model.ast.statement.declaration.GRaffeDeclaration
import com.sarajuhosova.graffe.model.ast.statement.declaration.IncludeDeclaration
import com.sarajuhosova.graffe.model.ast.statement.declaration.RelationshipDeclaration

abstract class GRaffeElementVisitor<T>{

    fun visit(element: GRaffeElement): T = when (element) {
        is GRaffeProgram -> visitProgram(element)
        is GRaffeStatement -> visitStatement(element)
        else -> throw NoVisitorDefinedException()
    }

    protected abstract fun visitProgram(program: GRaffeProgram): T

    // STATEMENTS

    protected fun visitStatement(statement: GRaffeStatement): T =
        when (statement) {
            is GRaffeProperty -> visitProperty(statement)
            is GRaffeDeclaration -> visitDeclaration(statement)
            else -> throw NoVisitorDefinedException()
        }

        protected abstract fun visitProperty(property: GRaffeProperty): T

        // DECLARATIONS

        protected fun visitDeclaration(declaration: GRaffeDeclaration): T = when (declaration) {
            is ComponentDeclaration -> visitComponent(declaration)
            is RelationshipDeclaration -> visitRelationship(declaration)
            is IncludeDeclaration -> visitInclude(declaration)
            else -> throw NoVisitorDefinedException()
        }

            protected abstract fun visitComponent(component: ComponentDeclaration): T

            protected abstract fun visitRelationship(relationship: RelationshipDeclaration): T

            protected abstract fun visitInclude(include: IncludeDeclaration): T

}

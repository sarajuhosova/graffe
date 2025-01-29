package com.sarajuhosova.graffe.playground

import com.sarajuhosova.graffe.model.ast.statement.declaration.ComponentDeclaration
import com.sarajuhosova.graffe.model.ast.statement.declaration.GRaffeDeclaration

typealias ParseTree = Pair<ComponentDeclaration, List<GRaffeDeclaration>>

fun leaf(component: ComponentDeclaration): ParseTree = component to emptyList()

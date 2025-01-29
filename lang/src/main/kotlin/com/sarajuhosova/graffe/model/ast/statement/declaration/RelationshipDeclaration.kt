package com.sarajuhosova.graffe.model.ast.statement.declaration

import com.sarajuhosova.graffe.exception.parsing.InvalidArrowException
import com.sarajuhosova.graffe.helper.buildIndented
import com.sarajuhosova.graffe.model.ast.statement.GRaffeProperty
import com.sarajuhosova.graffe.model.graph.Edge
import com.sarajuhosova.graffe.model.graph.structure.PropertyMap
import com.sarajuhosova.graffe.model.graph.structure.Relationship

data class RelationshipDeclaration(
    val left: String,
    val right: String,
    val arrow: Arrow,
    val properties: List<GRaffeProperty> = emptyList()
): GRaffeDeclaration() {

    enum class Arrow(
        val symbol: String,
        val mapper: (String, String) -> Relationship
    ) {
        LEFT(
            "<-",
            { left, right -> Relationship(Relationship.Arrow.DIRECTED, right to left) }
        ),
        RIGHT(
            "->",
            { left, right -> Relationship(Relationship.Arrow.DIRECTED, left to right) }
        ),
        BOTH(
            "<>",
            { left, right -> Relationship(Relationship.Arrow.REFLEXIVE, left to right) }
        ),
        NONE(
            "--",
            { left, right -> Relationship(Relationship.Arrow.UNDIRECTED, left to right) }
        );

        override fun toString(): String {
            return symbol
        }

        companion object {
            private val map: Map<String, Arrow> = entries.associateBy(Arrow::symbol)

            fun fromString(symbol: String): Arrow =
                map[symbol] ?: throw InvalidArrowException(symbol)
        }

    }

    private fun mapper(): Relationship {
        return arrow.mapper(left, right)
    }

    override fun generate(): Edge =
        Edge(mapper(), PropertyMap.fromGRaffeProperties(properties))

    override fun toString(): String = buildIndented(
        "$left $arrow $right",
        properties
    )

}

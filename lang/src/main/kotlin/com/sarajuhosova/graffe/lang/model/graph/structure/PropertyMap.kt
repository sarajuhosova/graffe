package com.sarajuhosova.graffe.lang.model.graph.structure

import com.sarajuhosova.graffe.lang.model.ast.statement.GRaffeProperty
import com.sarajuhosova.graffe.lang.model.graph.Property
import com.sarajuhosova.graffe.lang.model.property.PropertyValue

class PropertyMap(
    private val map: MutableMap<String, List<PropertyValue>> = mutableMapOf()
) {

    fun isEmpty(): Boolean = map.isEmpty()
    fun isNotEmpty(): Boolean = !isEmpty()
    fun size(): Int = map.size

    operator fun contains(key: String): Boolean = map.containsKey(key)
    operator fun get(key: String): List<PropertyValue>? = map[key]
    operator fun set(key: String, value: PropertyValue) { addProperty(key, value) }

    private fun addProperty(name: String, value: PropertyValue) {
        map[name] = map.getOrDefault(name, listOf()) + value
    }

    fun addProperty(property: Property) {
        addProperty(property.key, property.value)
    }

    private fun addProperties(name: String, values: List<PropertyValue>) {
        map[name] = map.getOrDefault(name, listOf()) + values
    }

    fun addProperties(properties: List<Property>) {
        properties.forEach { addProperty(it) }
    }

    fun addProperties(properties: PropertyMap) {
        properties.map.forEach { (name, values) ->
            addProperties(name, values)
        }
    }

    override fun toString(): String = buildString {
        for ((name, values) in map) {
            if (values.isEmpty()) continue
            val first = "$name: ${values[0]}"
            if (values.size == 1) appendLine(first)
            else {
                val spaces = " ".repeat(name.length + 2)
                val rest = values.drop(1)
                    .joinToString("\n") { spaces + it }

                appendLine("$first\n$rest")
            }
        }
    }


    companion object {

        fun fromGRaffeProperties(properties: List<GRaffeProperty>): PropertyMap {
            val map = PropertyMap()
            map.addProperties(properties.map { it.generate() })
            return map
        }

    }

}

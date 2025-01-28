package com.sarajuhosova.graffe.model.graph.structure

import com.sarajuhosova.graffe.model.ast.statement.GRaffeProperty
import com.sarajuhosova.graffe.model.graph.Property
import com.sarajuhosova.graffe.model.property.PropertyValue

class PropertyMap(
    private val map: MutableMap<String, List<PropertyValue>> = mutableMapOf()
) {

    fun isEmpty(): Boolean = map.isEmpty()

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

    companion object {

        fun fromGRaffeProperties(properties: List<GRaffeProperty>): PropertyMap {
            val map = PropertyMap()
            map.addProperties(properties.map { it.generate() })
            return map
        }

    }

}

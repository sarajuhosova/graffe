package com.sarajuhosova.graffe.model.graph

import com.sarajuhosova.graffe.model.property.PropertyValue

class PropertyMap(
    private val map: MutableMap<String, List<PropertyValue>> = mutableMapOf()
) {

    fun addProperty(name: String, value: PropertyValue) {
        map[name] = map.getOrDefault(name, listOf()) + value
    }

    private fun addProperties(name: String, values: List<PropertyValue>) {
        map[name] = map.getOrDefault(name, listOf()) + values
    }

    fun addProperties(properties: PropertyMap) {
        properties.map.forEach { (name, values) ->
            addProperties(name, values)
        }
    }

}

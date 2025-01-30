package com.sarajuhosova.graffe.lang.model.graph

import com.sarajuhosova.graffe.lang.model.property.PropertyValue

data class Property(
    val key: String,
    val value: PropertyValue
): GRaffe()

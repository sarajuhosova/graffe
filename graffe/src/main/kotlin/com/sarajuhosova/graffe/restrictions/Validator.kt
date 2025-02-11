package com.sarajuhosova.graffe.restrictions

import com.sarajuhosova.graffe.model.graph.Graph

interface Validator {

    fun validate(graph: Graph): Boolean

}

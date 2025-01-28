package com.sarajuhosova.graffe.model.ast

import com.sarajuhosova.graffe.model.graph.GRaffe

abstract class GRaffeElement {

    abstract fun generate(): GRaffe

}

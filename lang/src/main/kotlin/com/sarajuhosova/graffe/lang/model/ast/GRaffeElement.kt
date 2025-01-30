package com.sarajuhosova.graffe.lang.model.ast

import com.sarajuhosova.graffe.lang.model.graph.GRaffe

abstract class GRaffeElement {

    abstract fun generate(): GRaffe

}

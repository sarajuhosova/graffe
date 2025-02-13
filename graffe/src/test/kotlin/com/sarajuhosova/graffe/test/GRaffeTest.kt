package com.sarajuhosova.graffe.test

import com.sarajuhosova.graffe.interaction.options.Option
import kotlin.test.BeforeTest

abstract class GRaffeTest {

    @BeforeTest
    fun setupGRaffeTest() {
        Option.values().forEach { it.reset() }
    }

}

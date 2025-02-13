package com.sarajuhosova.graffe.interaction.options

import com.sarajuhosova.graffe.parser.Parser
import com.sarajuhosova.graffe.test.parseProgramAsserted
import com.sarajuhosova.graffe.test.readToCharStream
import org.assertj.core.api.Assertions.assertThatCode
import kotlin.test.BeforeTest
import kotlin.test.Test

class IgnoreRestrictionsOptionTest {

    @BeforeTest
    fun setup() {
        Option.values()
        IgnoreRestrictionsOption.reset()
    }

    @Test
    fun `invalid tree does not fail with ignore-restriction flag`() {
        Option.compileOptions("--ignore-restrictions")

        val input = readToCharStream("samples/restrictions/tree/two-parents.grf")

        val parsed = Parser.parseProgramAsserted(input)

        assertThatCode { parsed.generate() }.doesNotThrowAnyException()
    }

}

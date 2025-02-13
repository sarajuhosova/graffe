package com.sarajuhosova.graffe.restriction

import com.sarajuhosova.graffe.exception.generation.RestrictionViolatedException
import com.sarajuhosova.graffe.parser.Parser
import com.sarajuhosova.graffe.restrictions.Rule
import com.sarajuhosova.graffe.test.GRaffeTest
import com.sarajuhosova.graffe.test.parseProgramAsserted
import com.sarajuhosova.graffe.test.readToCharStream
import org.assertj.core.api.Assertions.*
import kotlin.test.Test

class TreeTest : GRaffeTest() {

    @Test
    fun `valid tree succeeds`() {
        val input = readToCharStream("samples/restrictions/tree/valid.grf")

        val parsed = Parser.parseProgramAsserted(input)

        assertThatCode { parsed.generate() }.doesNotThrowAnyException()
    }

    @Test
    fun `node with two parents fails tree restriction`() {
        val input = readToCharStream("samples/restrictions/tree/two-parents.grf")

        val parsed = Parser.parseProgramAsserted(input)

        val thrown = catchThrowable { parsed.generate() }
        assertThat(thrown)
            .isInstanceOf(RestrictionViolatedException::class.java)
        assertThat((thrown as RestrictionViolatedException).rule)
            .isEqualTo(Rule.TREE)
    }

    @Test
    fun `program with reflexive arrow fails tree restriction`() {
        val input = readToCharStream("samples/restrictions/tree/reflexive-arrow.grf")

        val parsed = Parser.parseProgramAsserted(input)

        val thrown = catchThrowable { parsed.generate() }
        assertThat(thrown)
            .isInstanceOf(RestrictionViolatedException::class.java)
        assertThat((thrown as RestrictionViolatedException).rule)
            .isEqualTo(Rule.TREE)
    }

    @Test
    fun `program with undirected arrow fails tree restriction`() {
        val input = readToCharStream("samples/restrictions/tree/undirected-arrow.grf")

        val parsed = Parser.parseProgramAsserted(input)

        val thrown = catchThrowable { parsed.generate() }
        assertThat(thrown)
            .isInstanceOf(RestrictionViolatedException::class.java)
        assertThat((thrown as RestrictionViolatedException).rule)
            .isEqualTo(Rule.TREE)
    }

}

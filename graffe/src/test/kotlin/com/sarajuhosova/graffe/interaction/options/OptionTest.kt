package com.sarajuhosova.graffe.interaction.options

import com.sarajuhosova.graffe.exception.InvalidProgramArgumentsException
import com.sarajuhosova.graffe.interaction.ui.Interface
import org.assertj.core.api.Assertions.*
import kotlin.test.BeforeTest
import kotlin.test.Test

class OptionTest {

    @BeforeTest
    fun setup() {
        Option.values()
    }

    @Test
    fun `empty options don't throw an error`() {
        assertThatCode { Option.compileOptions(emptyList()) }
            .doesNotThrowAnyException()
    }

    @Test
    fun `invalid option throws an error`() {
        val thrown = catchThrowable { Option.compileOptions("--invalid") }

        assertThat(thrown)
            .isInstanceOf(InvalidProgramArgumentsException::class.java)

        assertThat((thrown as InvalidProgramArgumentsException).type)
            .isEqualTo(InvalidProgramArgumentsException.Type.INVALID_OPTION)
    }

    @Test
    fun `configuring an option twice throws an error`() {
        assertThat(InterfaceOption.configured).isFalse()

        Option.compileOptions("--interface cli")

        assertThat(InterfaceOption.configured).isTrue()
        assertThat(InterfaceOption.choice).isEqualTo(Interface.Type.COMMAND_LINE)

        val thrown = catchThrowable { Option.compileOptions("--interface cli") }

        assertThat(thrown)
            .isInstanceOf(InvalidProgramArgumentsException::class.java)

        assertThat((thrown as InvalidProgramArgumentsException).type)
            .isEqualTo(InvalidProgramArgumentsException.Type.ALREADY_CONFIGURED)
    }

}

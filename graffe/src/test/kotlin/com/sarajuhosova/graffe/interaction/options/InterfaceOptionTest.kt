package com.sarajuhosova.graffe.interaction.options

import com.sarajuhosova.graffe.exception.InvalidProgramArgumentsException
import com.sarajuhosova.graffe.interaction.ui.Interface
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import kotlin.test.BeforeTest
import kotlin.test.Test

class InterfaceOptionTest {

    @BeforeTest
    fun setup() {
        Option.values()
        InterfaceOption.reset()
    }

    @Test
    fun `interface option is set to CLI by default`() {
        assertThat(InterfaceOption.configured).isFalse()
        assertThat(InterfaceOption.choice).isEqualTo(Interface.Type.COMMAND_LINE)
    }

    @Test
    fun `interface flag with no argument throws an exception`() {
        val thrown = catchThrowable { Option.compileOptions("--interface") }

        assertThat(thrown)
            .isInstanceOf(InvalidProgramArgumentsException::class.java)

        assertThat((thrown as InvalidProgramArgumentsException).type)
            .isEqualTo(InvalidProgramArgumentsException.Type.INCORRECT_NUMBER_OF_ARGUMENTS)
    }

    @Test
    fun `interface flag with two arguments throws an exception`() {
        val thrown = catchThrowable { Option.compileOptions(
            "--interface arg1 arg2"
        ) }

        assertThat(thrown)
            .isInstanceOf(InvalidProgramArgumentsException::class.java)

        assertThat((thrown as InvalidProgramArgumentsException).type)
            .isEqualTo(InvalidProgramArgumentsException.Type.INCORRECT_NUMBER_OF_ARGUMENTS)
    }

    @Test
    fun `interface flag with invalid interface type throws an exception`() {
        val thrown = catchThrowable { Option.compileOptions(
            "--interface nope"
        ) }

        assertThat(thrown)
            .isInstanceOf(InvalidProgramArgumentsException::class.java)

        assertThat((thrown as InvalidProgramArgumentsException).type)
            .isEqualTo(InvalidProgramArgumentsException.Type.INVALID_ARGUMENT)
    }

    @Test
    fun `interface flag with valid argument configure correctly`() {
        assertThat(InterfaceOption.configured).isFalse()

        Option.compileOptions("--interface cli")

        assertThat(InterfaceOption.configured).isTrue()
        assertThat(InterfaceOption.choice).isEqualTo(Interface.Type.COMMAND_LINE)
    }

}

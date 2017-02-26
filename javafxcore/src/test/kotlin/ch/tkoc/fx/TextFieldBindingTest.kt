package ch.tkoc.fx

import ch.tkoc.fx.component.binding.BindingView
import ch.tkoc.fx.launchDummyApplication
import javafx.scene.control.TextField
import org.junit.Assert.*
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

class TextFieldBindingTest {

    companion object {

        @JvmStatic
        @BeforeClass
        fun setUpApplication() {
            launchDummyApplication()
        }
    }

    lateinit var view: BindingView

    @Before
    fun setUp() {
        view = BindingView()
    }

    @Test
    fun setText() {
        view.email = "foo@bar.com"
        assertEquals("foo@bar.com", view.findElementById("email", TextField::class).text)
    }

    @Test
    fun initialValue() {
        assertEquals("", view.email)
    }
}
package ch.tkoc.fx

import ch.tkoc.fx.component.LoginForm
import ch.tkoc.fx.component.LoginView
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.BeforeClass
import org.junit.Test


class CustomComponentTest {

    companion object {

        @JvmStatic
        @BeforeClass
        fun setUpApplication() {
            launchDummyApplication()
        }
    }

    @Test
    fun correctStructure() {
        val view = LoginView()
        assertEquals(1, view.root.children.size)
        assertTrue(view.root.children[0] is LoginForm)
    }
}
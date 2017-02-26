package ch.tkoc.component

import ch.tkoc.context.JavaFxAwareApplicationContext
import ch.tkoc.context.scope.ViewScope
import ch.tkoc.fx.launchDummyApplication
import org.junit.Assert.*
import org.junit.BeforeClass
import org.junit.Test


class LoginTest {

    companion object {
        @BeforeClass
        @JvmStatic
        fun setUpApplication() = launchDummyApplication()
    }

    @Test
    fun correctInitialized() {
        val context = JavaFxAwareApplicationContext(LoginConfiguration::class.java)
        val loginView = context.findInitialView()
        val loginForm = loginView.fxmlLoader.namespace["loginForm"]
        val viewScope = context.getBean(ViewScope::class.java)
        assertTrue(loginView is LoginView)
        assertSame(loginView, viewScope.objects["loginView"])
        assertEquals(2, viewScope.objects.size)
    }

    @Test
    fun correctWired() {
        val context = JavaFxAwareApplicationContext(LoginConfiguration::class.java)
        val loginView = context.findInitialView()
        val loginForm = context.getBean(LoginForm::class.java)
        val potentialForm2 = loginView.root.lookup("#loginForm")
        assertSame(loginForm, potentialForm2)
    }
}
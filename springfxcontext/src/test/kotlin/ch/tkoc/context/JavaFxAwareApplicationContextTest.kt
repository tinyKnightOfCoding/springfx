package ch.tkoc.context

import ch.tkoc.context.scope.ViewScope
import ch.tkoc.fx.launchDummyApplication
import ch.tkoc.util.InitialView
import ch.tkoc.util.InitialViewAvailable
import ch.tkoc.util.NoViews
import ch.tkoc.util.OnlyNonInitialViews
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.springframework.beans.factory.config.BeanDefinitionCustomizer
import java.util.*

class JavaFxAwareApplicationContextTest {

    companion object {

        @JvmStatic
        @BeforeClass
        fun setUpApplication() {
            launchDummyApplication()
        }
    }

    @Test(expected = NoSuchElementException::class)
    fun noViews() {
        JavaFxAwareApplicationContext(NoViews::class.java).apply {
            findInitialView()
        }
    }

    @Test(expected = NoSuchElementException::class)
    fun onlyNonInitialViews() {
        JavaFxAwareApplicationContext(OnlyNonInitialViews::class.java).findInitialView()
    }

    @Test
    fun initialViewAvailable() {
        assertTrue(JavaFxAwareApplicationContext(InitialViewAvailable::class.java).findInitialView() is InitialView)
    }

    @Test
    fun onlyInitialViewInScope() {
        val context = JavaFxAwareApplicationContext(InitialView::class.java).apply {
            findInitialView()
        }
        val viewScope = context.getBean(ViewScope::class.java)
        assertEquals(1, viewScope.objects.size)
    }
}
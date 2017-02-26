package ch.tkoc.component

import ch.tkoc.context.JavaFxAwareApplicationContext
import ch.tkoc.fx.launchDummyApplication
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

    }
}
package ch.tkoc.springfx.context

import ch.tkoc.springfx.util.launchDummyApplication
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import javafx.scene.layout.FlowPane
import org.junit.Assert.*
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.springframework.context.ApplicationContext

class JavaFxBeanBuilderFactoryTest {

    companion object {

        @JvmStatic
        @BeforeClass
        fun setUpApplication() = launchDummyApplication()
    }

    lateinit var builderFactory: JavaFxBeanBuilderFactory
    lateinit var appContext: ApplicationContext

    @Before
    fun setUp() {
        builderFactory = JavaFxBeanBuilderFactory()
    }

    @Test
    fun correctBean() {
        appContext = mock {
            on { getBeanNamesForAnnotation(any()) } doReturn arrayOf("loginForm")
            on { getType(any()) } doReturn LoginForm::class.java
        }
        builderFactory.setApplicationContext(appContext)
        assertEquals(1, builderFactory.builderContext.size)
        assertEquals(JavaFxBeanBuilder::class.java, builderFactory.builderContext[LoginForm::class.java])
    }

    @Test
    fun returnsBuilderForLoginForm() {
        appContext = mock {
            on { getBeanNamesForAnnotation(any()) } doReturn arrayOf("loginForm")
            on { getType(any()) } doReturn LoginForm::class.java
            on { getBean(any<Class<*>>()) } doReturn LoginForm()
        }
        builderFactory.setApplicationContext(appContext)
        assertNotNull(builderFactory.getBuilder(LoginForm::class.java))
    }

    @Test
    fun returnsBuilderWhichReturnsLoginForm() {
        appContext = mock {
            on { getBeanNamesForAnnotation(any()) } doReturn arrayOf("loginForm")
            on { getType(any()) } doReturn LoginForm::class.java
            on { getBean(any<Class<*>>()) } doReturn LoginForm()
        }
        builderFactory.setApplicationContext(appContext)
        assertTrue(builderFactory.getBuilder(LoginForm::class.java)!!.build() is LoginForm)
    }

    @Test
    fun returnsNullForFlowPane() {
        appContext = mock {
            on { getBeanNamesForAnnotation(any()) } doReturn arrayOf("loginForm")
            on { getType(any()) } doReturn LoginForm::class.java
            on { getBean(any<Class<*>>()) } doReturn LoginForm()
        }
        builderFactory.setApplicationContext(appContext)
        assertNull(builderFactory.getBuilder(FlowPane::class.java))
    }

    @Test
    fun customBuilder() {
        appContext = mock {
            on { getBeanNamesForAnnotation(any())} doReturn arrayOf("customBuilderLoginForm")
            on { getType(any())} doReturn CustomBuilderLoginForm::class.java
            on { getBean(any<Class<*>>())} doReturn CustomBuilderLoginForm()
        }
        builderFactory.setApplicationContext(appContext)
        assertTrue(builderFactory.getBuilder(CustomBuilderLoginForm::class.java) is CustomBuilder)
    }
}
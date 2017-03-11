package ch.tkoc.springfx.context

import ch.tkoc.springfx.util.launchDummyApplication
import com.nhaarman.mockito_kotlin.*
import javafx.util.BuilderFactory
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test


class FxmlPostProcessorTest {

    lateinit var processor: JavaFxBeanPostProcessor
    lateinit var builderFactory: BuilderFactory

    companion object {
        @BeforeClass
        @JvmStatic
        fun setUpApplication() = launchDummyApplication()
    }

    @Before
    fun setUp() {
        builderFactory = mock {  }
        processor = JavaFxBeanPostProcessor(builderFactory)
    }

    @Test
    fun isRoot() {
        val root = LoginForm()
        processor.postProcessBeforeInitialization(root, "someName")
        assertEquals(3, root.children.size)
    }

    @Test
    fun customName() {
        val root = DifferentLoginForm()
        processor.postProcessBeforeInitialization(root, "someName")
        assertEquals(3, root.children.size)
    }

    @Test
    fun builderCalled() {
        whenever(builderFactory.getBuilder(any())).thenReturn(null)
        processor.postProcessBeforeInitialization(LoginForm(), "someName")
        verify(builderFactory, atLeastOnce()).getBuilder(any())
    }
}
package ch.tkoc.springfx.context

import ch.tkoc.springfx.util.launchDummyApplication
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test


class FxmlPostProcessorTest {

    lateinit var processor: FxmlPostProcessor

    companion object {
        @BeforeClass
        @JvmStatic
        fun setUpApplication() = launchDummyApplication()
    }

    @Before
    fun setUp() {
        processor = FxmlPostProcessor()
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
}
package ch.tkoc.context

import ch.tkoc.component.LoginConfiguration
import ch.tkoc.component.LoginForm
import javafx.scene.layout.FlowPane
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class ContextAwareJavaFxBuilderFactoryTest {

    lateinit var contextAwareJavaFxBuilderFactory: ContextAwareJavaFxBuilderFactory

    @Before
    fun setUp() {
        contextAwareJavaFxBuilderFactory = ContextAwareJavaFxBuilderFactory()
    }

    @Test
    fun noComponents() {
        val appContext = AnnotationConfigApplicationContext()
        appContext.refresh()
        contextAwareJavaFxBuilderFactory.setApplicationContext(appContext)
        assertTrue(contextAwareJavaFxBuilderFactory.contextBuildables.isEmpty())
    }

    @Test
    fun loginForm() {
        val appContext = AnnotationConfigApplicationContext(LoginConfiguration::class.java)
        contextAwareJavaFxBuilderFactory.setApplicationContext(appContext)
        assertEquals(1, contextAwareJavaFxBuilderFactory.contextBuildables.size)
        assertNull(contextAwareJavaFxBuilderFactory.getBuilder(FlowPane::class.java))
        assertNotNull(contextAwareJavaFxBuilderFactory.getBuilder(LoginForm::class.java))
    }
}
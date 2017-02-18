package ch.tkoc.fx.component

import ch.tkoc.fx.component.example1.LoginView
import ch.tkoc.fx.launchDummyApplication
import javafx.scene.Node
import javafx.scene.layout.FlowPane
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.BeforeClass
import org.junit.Test

class UIComponentTest {

    companion object {

        @JvmStatic
        @BeforeClass
        fun setUpApplication() {
            launchDummyApplication()
        }
    }

    @Test(expected = IllegalStateException::class)
    fun fxmlLocation_notExisting() {
        val component = UIComponent<Node>()
    }

    @Test
    fun fxmlLocation_existing() {
        val component = LoginView()
        assertTrue(component.fxmlLocation.toString().endsWith("ch/tkoc/fx/component/example1/LoginView.fxml"))
    }

    @Test
    fun fxmlLoader() {
        val component = LoginView()
        assertEquals(component, component.fxmlLoader.getController())
        assertEquals(component.fxmlLocation, component.fxmlLoader.location)
    }
}

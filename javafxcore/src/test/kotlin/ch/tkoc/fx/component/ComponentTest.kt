package ch.tkoc.fx.component

import ch.tkoc.fx.component.property.PropertyView
import ch.tkoc.fx.launchDummyApplication
import javafx.scene.Node
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.BeforeClass
import org.junit.Test

class ComponentTest {

    companion object {

        @JvmStatic
        @BeforeClass
        fun setUpApplication() {
            launchDummyApplication()
        }
    }

    @Test(expected = IllegalStateException::class)
    fun fxmlLocation_notExisting() {
        val component = Component<Node>()
    }

    @Test
    fun fxmlLocation_existing() {
        val component = PropertyView()
        assertTrue(component.fxmlLocation.toString().endsWith("ch/tkoc/fx/component/property/PropertyView.fxml"))
    }

    @Test
    fun fxmlLoader() {
        val component = PropertyView()
        assertEquals(component, component.fxmlLoader.getController())
        assertEquals(component.fxmlLocation, component.fxmlLoader.location)
    }
}

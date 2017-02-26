package ch.tkoc.fx

import ch.tkoc.fx.property.PropertyView
import javafx.scene.Parent
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.BeforeClass
import org.junit.Test

class ViewTest {

    companion object {

        @JvmStatic
        @BeforeClass
        fun setUpApplication() {
            launchDummyApplication()
        }
    }

    @Test(expected = IllegalStateException::class)
    fun fxmlLocation_notExisting() {
        val component = View<Parent>()
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

package ch.tkoc.fx.component

import ch.tkoc.fx.component.example1.LoginViewWrongId
import ch.tkoc.fx.component.example1.LoginViewWrongType
import ch.tkoc.fx.launchDummyApplication
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.BeforeClass
import org.junit.Test

class UIPropertyTest {

    companion object {

        @JvmStatic
        @BeforeClass
        fun setUpApplication() {
            launchDummyApplication()
        }
    }

    @Test
    fun wrongType() = try {
        LoginViewWrongType().email
        fail()
    } catch(ex: IllegalArgumentException) {
        assertEquals("Element email could not be assigned to class javafx.scene.control.PasswordField.", ex.message)
    }

    @Test
    fun notExisting() = try {
        LoginViewWrongId().username
        fail()
    } catch(ex: IllegalArgumentException) {
        assertEquals("Element username could not be found in namespace.", ex.message)
    }
}


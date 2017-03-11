package ch.tkoc.springfx.util

import ch.tkoc.springfx.context.DifferentLoginForm
import ch.tkoc.springfx.context.LoginForm
import org.junit.Assert.*
import org.junit.Test

class ParentKtTest {

    @Test
    fun createFxml_NullProvider() {
        assertTrue(LoginForm().createFxmlUrl({ null }).toString().endsWith("ch/tkoc/springfx/context/LoginForm.fxml"))
    }

    @Test
    fun createFxml_NoProvider() {
        assertTrue(LoginForm().createFxmlUrl().toString().endsWith("ch/tkoc/springfx/context/LoginForm.fxml"))
    }

    @Test
    fun createFxml_NoProviderAnnotated() {
        assertTrue(DifferentLoginForm().createFxmlUrl().toString().endsWith("ch/tkoc/springfx/context/LoginForm.fxml"))
    }

    @Test
    fun createFxml_NonEmptyProvider() {
        assertTrue(LoginForm().createFxmlUrl { "EnhancedHBox.fxml" }.toString().endsWith("ch/tkoc/springfx/context/EnhancedHBox.fxml"))
    }
}
package ch.tkoc.fx.component

import ch.tkoc.fx.component.binding.BindingView
import ch.tkoc.fx.launchDummyApplication
import javafx.event.ActionEvent
import javafx.scene.control.Button
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ButtonBindingTest {

    companion object {

        @JvmStatic
        @BeforeClass
        fun setUpApplication() = launchDummyApplication()
    }

    @Test
    fun initialValue() {
        BindingView().login(ActionEvent())
    }

    @Test
    fun setValue() {
        val mockedEvent = mock(ActionEvent::class.java)
        BindingView().apply {
            login = ActionEvent::consume
            login(mockedEvent)
        }
        verify(mockedEvent).consume()
    }

    @Test
    fun setValueByElementLookup() {
        val mockedEvent = mock(ActionEvent::class.java)
        BindingView().apply {
            login = ActionEvent::consume
            findElementById("login", Button::class).onAction.handle(mockedEvent)
        }
        verify(mockedEvent).consume()
    }
}
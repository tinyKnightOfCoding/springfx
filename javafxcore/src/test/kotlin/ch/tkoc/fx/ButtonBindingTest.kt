package ch.tkoc.fx

import ch.tkoc.fx.binding.BindingView
import javafx.event.ActionEvent
import javafx.scene.control.Button
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ButtonBindingTest {

    companion object {

        @JvmStatic
        @org.junit.BeforeClass
        fun setUpApplication() = launchDummyApplication()
    }

    @Test(expected = NullPointerException::class)
    fun initialValue() {
        BindingView().login(javafx.event.ActionEvent())
    }

    @Test
    fun setValue() {
        val mockedEvent = mock(javafx.event.ActionEvent::class.java)
        BindingView().apply {
            login = javafx.event.ActionEvent::consume
            login(mockedEvent)
        }
        verify(mockedEvent).consume()
    }

    @Test
    fun setValueByElementLookup() {
        val mockedEvent = mock(ActionEvent::class.java)
        BindingView().apply {
            login = ActionEvent::consume
            root.lookup("#login", Button::class.java)!!.onAction.handle(mockedEvent)
        }
        verify(mockedEvent).consume()
    }
}
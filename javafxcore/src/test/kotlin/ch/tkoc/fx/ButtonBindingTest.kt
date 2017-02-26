package ch.tkoc.fx

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
        @org.junit.BeforeClass
        fun setUpApplication() = ch.tkoc.fx.launchDummyApplication()
    }

    @org.junit.Test
    fun initialValue() {
        BindingView().login(javafx.event.ActionEvent())
    }

    @org.junit.Test
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
            findElementById("login", Button::class).onAction.handle(mockedEvent)
        }
        verify(mockedEvent).consume()
    }
}
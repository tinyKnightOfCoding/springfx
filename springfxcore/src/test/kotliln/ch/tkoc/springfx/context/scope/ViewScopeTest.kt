package ch.tkoc.springfx.context.scope

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Assert.assertSame
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.ObjectFactory

class ViewScopeTest {

    lateinit var viewScope: ViewScope

    @Before
    fun setUp() {
        viewScope = ViewScope()
    }

    @Test
    fun factoryUsed() {
        val bean = Any()
        val objectFactory = mock<ObjectFactory<*>> {
            on { `object` } doReturn bean
        }
        val result = viewScope.get("aBean", objectFactory)
        assertSame(bean, result)
    }

    @Test
    fun beanOnlyCreatedOnce() {
        val bean = Any()
        val objectFactory = mock<ObjectFactory<*>> {
            on { `object` } doReturn bean
        }
        viewScope.get("aBean", objectFactory)
        val result = viewScope.get("aBean", objectFactory)
        assertSame(bean, result)
        verify(objectFactory).`object`
    }
}
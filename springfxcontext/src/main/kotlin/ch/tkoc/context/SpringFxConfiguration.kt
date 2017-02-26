package ch.tkoc.context

import ch.tkoc.context.scope.ViewScope
import javafx.fxml.FXMLLoader
import org.springframework.beans.factory.config.CustomScopeConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

@Configuration("ch.tkoc.context.springFxConfiguration")
@ComponentScan("ch.tkoc.context")
class SpringFxConfiguration {

    companion object {

        @Bean(name = arrayOf("ch.tkoc.context.viewScopeConfigurer"))
        @JvmStatic
        fun viewScopeConfigurer(viewScope: ViewScope) = CustomScopeConfigurer().apply {
            addScope("view", viewScope)
        }

    }

    @Scope("prototype")
    @Bean(name = arrayOf("ch.tkoc.context.fxmlLoader"))
    fun fxmlLoader(awareControllerFactory: ContextAwareJavaFxControllerFactory, awareBuilderFactory: ContextAwareJavaFxBuilderFactory) = FXMLLoader().apply {
        setControllerFactory { awareControllerFactory.getBean(it) }
        builderFactory = awareBuilderFactory
    }
}
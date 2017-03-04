package ch.tkoc.springfx

import ch.tkoc.springfx.context.JavaFxBeanBuilderFactory
import ch.tkoc.springfx.context.JavaFxBeanPostProcessor
import ch.tkoc.springfx.context.scope.ViewScope
import javafx.util.BuilderFactory
import org.springframework.beans.factory.config.CustomScopeConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringFxBaseConfiguration {

    @Bean(name = arrayOf("ch.tkoc.context.javaFxBeanBuilderFactory"))
    fun javaFxBeanBuilderFactory() = JavaFxBeanBuilderFactory()

    @Bean(name = arrayOf("ch.tkoc.context.javaFxBeanPostProcessor"))
    fun javaFxBeanPostProcessor(builderFactory: BuilderFactory) = JavaFxBeanPostProcessor(builderFactory)

    @Bean(name = arrayOf("ch.tkoc.context.scope.viewScope"))
    fun viewScope() = ViewScope()

    @Bean(name = arrayOf("ch.tkoc.context.scope.scopeConfigurer"))
    fun scopeConfigurerer(viewScope: ViewScope) = CustomScopeConfigurer().apply {
        addScope("springfx.view", viewScope)
    }
}
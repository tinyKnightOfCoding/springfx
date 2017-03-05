package ch.tkoc.springfx

import ch.tkoc.springfx.context.JavaFxBeanBuilderFactory
import ch.tkoc.springfx.context.JavaFxBeanPostProcessor
import ch.tkoc.springfx.context.scope.ViewScope
import javafx.util.BuilderFactory
import org.springframework.beans.factory.config.CustomScopeConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration("ch.tkoc.springfx.springFxBaseConfiguration")
class SpringFxBaseConfiguration {

    @Bean(name = arrayOf("ch.tkoc.springfx.context.javaFxBeanBuilderFactory"))
    fun javaFxBeanBuilderFactory() = JavaFxBeanBuilderFactory()

    @Bean(name = arrayOf("ch.tkoc.springfx.context.javaFxBeanPostProcessor"))
    fun javaFxBeanPostProcessor(builderFactory: BuilderFactory) = JavaFxBeanPostProcessor(builderFactory)

    @Bean(name = arrayOf("ch.tkoc.springfx.context.scope.viewScope"))
    fun viewScope() = ViewScope()

    @Bean(name = arrayOf("ch.tkoc.springfx.context.scope.scopeConfigurer"))
    fun scopeConfigurerer(viewScope: ViewScope) = CustomScopeConfigurer().apply {
        addScope("springfx.view", viewScope)
    }
}
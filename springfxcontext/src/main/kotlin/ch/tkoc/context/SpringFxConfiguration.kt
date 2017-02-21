package ch.tkoc.context

import ch.tkoc.context.scope.ViewScope
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.CustomScopeConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration("ch.tkoc.context.springFxConfiguration")
@ComponentScan("ch.tkoc.context")
class SpringFxConfiguration {


    val customScopeConfigurer: CustomScopeConfigurer
    @Bean(name= arrayOf("ch.tkoc.context.viewScopeConfigurer"))
    get() = CustomScopeConfigurer().apply {
        addScope("view", ViewScope())
    }
}
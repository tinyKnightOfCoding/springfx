package ch.tkoc.util

import ch.tkoc.util.InitialView
import ch.tkoc.util.NonInitialView
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InitialViewAvailable {
    @Bean
    fun view1() = NonInitialView()

    @Bean
    fun view2() = NonInitialView()

    @Bean
    fun initialView() = InitialView()
}
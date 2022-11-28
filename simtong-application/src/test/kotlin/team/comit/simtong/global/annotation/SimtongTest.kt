package team.comit.simtong.global.annotation

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit.jupiter.SpringExtension
import team.comit.simtong.global.DomainPropertiesInitialization


@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@ExtendWith(SpringExtension::class)
@Import(DomainPropertiesInitialization::class)
annotation class SimtongTest

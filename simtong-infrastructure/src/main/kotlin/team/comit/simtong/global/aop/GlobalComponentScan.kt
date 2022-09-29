package team.comit.simtong.global.aop

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScan.Filter
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType
import team.comit.simtong.global.annotation.DomainService
import team.comit.simtong.global.annotation.Policy
import team.comit.simtong.global.annotation.ReadOnlyUseCase
import team.comit.simtong.global.annotation.UseCase

/**
 *
 * Annotation을 scan해 Bean으로 등록하는 GlobalComponentScan
 *
 * @author kimbeomjin
 * @date 2022/08/27
 * @version 1.0.0
 **/
@Configuration
@ComponentScan(
    basePackages = ["team.comit.simtong"],
    includeFilters = [
        Filter(
            type = FilterType.ANNOTATION,
            classes = [
                UseCase::class,
                ReadOnlyUseCase::class,
                Policy::class,
                DomainService::class
            ]
        )
    ]
)
class GlobalComponentScan {
}
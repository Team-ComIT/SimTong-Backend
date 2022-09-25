package team.comit.simtong.global.config

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.persistence.EntityManager

/**
 *
 * JPAQueryFactory를 Bean 등록하는 QuerydslConfig
 *
 * @author kimbeomjin
 * @date 2022/09/26
 * @version 1.0.0
 **/
@Configuration
class QuerydslConfig(
    private val entityManager: EntityManager
) {

    @Bean
    protected fun queryFactory(): JPAQueryFactory = JPAQueryFactory(entityManager)
}

package team.comit.simtong.global.annotation

/**
 *
 * 구현 기술에 의존하지 않고 도메인과 밀접한 관련이 있는 DomainService를 나타내는 어노테이션
 *
 * @author kimbeomjin
 * @date 2022/08/27
 * @version 1.0.0
 **/
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class DomainService()

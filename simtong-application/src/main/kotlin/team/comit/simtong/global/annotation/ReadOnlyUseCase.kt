package team.comit.simtong.global.annotation

import org.springframework.transaction.annotation.Transactional

/**
 *
 * 조회 기능을 담당하는 사용자 UseCase를 나타내는 어노테이션
 *
 * @author kimbeomjin
 * @date 2022/08/27
 * @version 1.0.0
 **/
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Transactional(readOnly = true)
annotation class ReadOnlyUseCase()

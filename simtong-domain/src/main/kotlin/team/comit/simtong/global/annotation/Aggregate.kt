package team.comit.simtong.global.annotation

/**
 *
 * 모델의 일관성을 관리하는 기준이 되는 Aggregate를 나타내는 어노테이션
 *
 * @author kimbeomjin
 * @date 2022/08/27
 * @version 1.0.0
 **/
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Aggregate()

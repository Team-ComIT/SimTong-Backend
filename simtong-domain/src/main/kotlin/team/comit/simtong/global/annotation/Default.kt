package team.comit.simtong.global.annotation

/**
 *
 * MapStruct에 사용되는 Default 생성자를 지정하는 어노테이션
 * Default 어노테이션이 붙어있으면 MapStruct가 생성자를 가장 우선순위로 사용한다.
 *
 * @author Chokyunghyeon
 * @date 2022/09/30
 * @version 1.0.0
 **/
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CONSTRUCTOR)
annotation class Default()
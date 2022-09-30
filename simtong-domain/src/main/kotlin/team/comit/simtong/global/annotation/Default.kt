package team.comit.simtong.global.annotation

/**
 *
 * MapStruct에 사용되는 Default 생성자를 지정하는 어노테이션
 *
 * @author Chokyunghyeon
 * @date 2022/09/30
 * @version 1.0.0
 **/
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CONSTRUCTOR)
annotation class Default()
package team.comit.simtong.domain.notification.dto

/**
 *
 * 알림의 종류를 구분하는 WebNotificationType
 *
 * @author kimbeomjin
 * @date 2022/12/30
 * @version 1.1.0
 **/
enum class WebNotificationType {

    /**
     * 메뉴 관련 알림
     */
    MEAL,

    /**
     * 휴무일 관련 알림
     */
    HOLIDAY,

    /**
     * 일정 관련 알림
     */
    SCHEDULE

}
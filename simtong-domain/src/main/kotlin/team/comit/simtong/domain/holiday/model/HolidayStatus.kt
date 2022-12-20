package team.comit.simtong.domain.holiday.model

/**
 *
 * 후무일 상태를 관리하는 HolidayStatus
 *
 * @author Chokyunghyeon
 * @date 2022/12/20
 * @version 1.0.0
 **/
enum class HolidayStatus {

    /**
     * 휴무표 작성 중
     */
    WRITTEN,

    /**
     * 휴무표 조율 중
     */
    TUNING,

    /**
     *휴무표 결정됨
     */
    FINAL,

    /**
     * 연차
     */
    ANNUAL

}
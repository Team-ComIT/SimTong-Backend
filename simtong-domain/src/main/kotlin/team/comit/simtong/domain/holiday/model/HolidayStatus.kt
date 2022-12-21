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
     * 휴무표 작성 완료
     */
    WRITTEN,

    /**
     *휴무표 확정 완료
     */
    COMPLETED,

    /**
     * 연차
     */
    ANNUAL

}
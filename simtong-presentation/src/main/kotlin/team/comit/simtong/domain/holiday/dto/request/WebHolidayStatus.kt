package team.comit.simtong.domain.holiday.dto.request

/**
 *
 * 휴무일 작성 상태에 대한 요청을 의미하는 WebHolidayStatus
 *
 * @author Chokyunghyeon
 * @date 2022/12/21
 * @version 1.0.0
 **/
internal enum class WebHolidayStatus {

    /**
     * 휴무일 작성 완료
     */
    WRITTEN,

    /**
     * 휴무일 확정 완료
     */
    COMPLETED

}
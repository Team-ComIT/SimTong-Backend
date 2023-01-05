package team.comit.simtong.global.value

/**
 *
 * 연차 및 휴무일을 필터링하기 위한 WebHolidayQueryType
 *
 * @author kimbeomjin
 * @date 2022/12/22
 * @version 1.2.3
 **/
enum class WebHolidayQueryType {

    /**
     * 휴무일과 연차
     */
    ALL,

    /**
     * 휴무일
     */
    HOLIDAY,

    /**
     * 연차
     */
    ANNUAL

}
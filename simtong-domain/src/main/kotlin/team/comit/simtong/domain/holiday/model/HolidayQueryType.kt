package team.comit.simtong.domain.holiday.model

/**
 *
 * 연차 및 휴무일을 필터링하기 위한 HolidayQueryType
 *
 * @author kimbeomjin
 * @date 2022/12/22
 * @version 1.0.0
 **/
enum class HolidayQueryType {

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
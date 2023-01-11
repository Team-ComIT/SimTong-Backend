package team.comit.simtong.domain.holiday.dto.response

import team.comit.simtong.domain.holiday.model.value.HolidayType
import java.time.LocalDate

/**
 *
 * 개인 휴무일 정보를 전송하는 QueryIndividualHolidayWebResponse
 *
 * @author Chokyunghyeon
 * @date 2022/12/05
 * @version 1.2.5
 **/
data class QueryIndividualHolidaysWebResponse(
    val holidays: List<IndividualHolidayResponse>
)

/**
 *
 * 개인 휴무일 정보를 전송하는 IndividualHolidayResponse
 *
 * @author Chokyunghyeon
 * @date 2022/12/05
 * @version 1.0.0
 **/
data class IndividualHolidayResponse(
    val date: LocalDate,
    val type: HolidayType
)
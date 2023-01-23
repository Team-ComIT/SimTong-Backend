package team.comit.simtong.domain.holiday.dto

import org.hibernate.validator.constraints.Range
import team.comit.simtong.domain.holiday.dto.request.AppointHolidayPeriodData
import java.time.LocalDate
import javax.validation.constraints.NotNull

/**
 *
 * 휴무표 작성 기간 설정을 요청하는 AppointHolidayPeriodRequest
 *
 * @author Chokyunghyeon
 * @date 2022/12/22
 * @version 1.2.5
 **/
data class AppointHolidayPeriodRequest(

    @field:NotNull
    val year: Int?,

    @field:NotNull
    @field:Range(
        min = 1,
        max = 12
    )
    val month: Int?,

    @field:NotNull
    val startAt: LocalDate?,

    @field:NotNull
    val endAt: LocalDate?
) {

    fun toData() = AppointHolidayPeriodData(
        year = year!!,
        month = month!!,
        startAt = startAt!!,
        endAt = endAt!!
    )
}
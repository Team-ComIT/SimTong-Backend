package team.comit.simtong.domain.holiday.dto.request

import org.hibernate.validator.constraints.Range
import java.time.LocalDate
import javax.validation.constraints.NotNull

/**
 *
 * 휴무표 작성 기간 설정을 요청하는 AppointHolidayPeriodWebRequest
 *
 * @author Chokyunghyeon
 * @date 2022/12/22
 * @version 1.0.0
 **/
data class AppointHolidayPeriodWebRequest(
    @field:NotNull
    val year: Int,

    @field:NotNull
    @field:Range(
        min = 1,
        max = 12
    )
    val month: Int,

    val startAt: LocalDate,

    val endAt: LocalDate
)
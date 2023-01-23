package team.comit.simtong.domain.holiday.dto

import team.comit.simtong.domain.holiday.dto.request.AppointAnnualData
import java.time.LocalDate
import javax.validation.constraints.NotNull

/**
 *
 * 연차 지정을 요청하는 AppointAnnualRequest
 *
 * @author Chokyunghyeon
 * @date 2022/12/19
 * @version 1.2.5
 **/
data class AppointAnnualRequest(

    @field:NotNull
    val date: LocalDate?
) {

    fun toData() = AppointAnnualData(
        date = date!!
    )
}
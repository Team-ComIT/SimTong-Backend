package team.comit.simtong.domain.holiday.dto

import team.comit.simtong.domain.holiday.dto.request.ShareHolidayData
import javax.validation.constraints.NotNull

/**
 *
 * 휴무표 공유를 요청하는 ShareHolidayRequest
 *
 * @author kimbeomjin
 * @author Chokyunghyeon
 * @date 2022/12/21
 * @version 1.2.5
 **/
data class ShareHolidayRequest(

    @field:NotNull
    val year: Int?,

    @field:NotNull
    val month: Int?
) {

    fun toData() = ShareHolidayData(
        year = year!!,
        month = month!!
    )
}

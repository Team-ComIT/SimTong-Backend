package team.comit.simtong.domain.holiday.dto.request

import javax.validation.constraints.NotNull

/**
 *
 * 휴무표 공유를 요청하는 ShareHolidayWebRequest
 *
 * @author kimbeomjin
 * @date 2022/12/21
 * @version 1.0.0
 **/
data class ShareHolidayWebRequest(

    @field:NotNull
    val year: Int,

    @field:NotNull
    val month: Int
)

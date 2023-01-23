package team.comit.simtong.domain.holiday.dto

import team.comit.simtong.domain.holiday.dto.request.CancelHolidayData
import java.time.LocalDate
import javax.validation.constraints.NotNull

/**
 *
 * 휴무일 / 연차 취소를 요청하는 CancelHolidayRequest
 *
 * @author kimbeomjin
 * @author Chokyunghyeon
 * @date 2022/12/20
 * @version 1.2.5
 **/
data class CancelHolidayRequest(

    @field:NotNull
    val date: LocalDate?
) {

    fun toData() = CancelHolidayData(
        date = date!!
    )
}
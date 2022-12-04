package team.comit.simtong.domain.holiday.dto.request

import org.jetbrains.annotations.NotNull
import java.time.LocalDate

/**
 *
 * 휴무일 또는 연차를 취소 요청하는 CancelHolidayWebRequest
 *
 * @author Chokyunghyeon
 * @date 2022/12/04
 * @version 1.0.0
 **/
data class CancelHolidayWebRequest(

    @field:NotNull
    val date: LocalDate
)
package team.comit.simtong.domain.holiday.dto.request

import org.jetbrains.annotations.NotNull
import java.time.LocalDate

/**
 *
 * 휴무일 지정을 요청하는 AppointHolidayWebRequest
 *
 * @author Chokyunghyeon
 * @date 2022/12/03
 * @version 1.0.0
 **/
data class AppointHolidayWebRequest(

    @field:NotNull
    val date: LocalDate
)
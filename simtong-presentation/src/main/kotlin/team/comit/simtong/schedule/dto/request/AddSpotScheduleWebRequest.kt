package team.comit.simtong.schedule.dto.request

import org.hibernate.validator.constraints.Length
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 *
 * 관리자가 지점 일정을 추가 요청하는 AddSpotScheduleWebRequest
 *
 * @author Chokyunghyeon
 * @date 2022/11/21
 * @version 1.0.0
 **/
data class AddSpotScheduleWebRequest(

    @field:NotBlank
    @field:Length(max = 20)
    val title: String,

    @field:NotNull
    val startAt : LocalDate,

    @field:NotNull
    val endAt: LocalDate
)
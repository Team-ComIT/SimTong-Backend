package team.comit.simtong.schedule.dto.request

import org.hibernate.validator.constraints.Length
import java.time.LocalDate
import java.time.LocalTime
import javax.validation.constraints.FutureOrPresent
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 *
 * 개인 일정 추가를 요청하는 AddScheduleRequest
 *
 * @author Chokyunghyeon
 * @date 2022/11/26
 * @version 1.0.0
 **/
data class AddScheduleWebRequest(

    @field:NotBlank
    @field:Length(max = 20)
    val title: String,

    @field:NotNull
    @field:FutureOrPresent
    val startAt: LocalDate,

    // TODO 시작일, 종료일 검증 Resolver 구현
    @field:NotNull
    val endAt: LocalDate,

    val alarm: LocalTime?
)
package team.comit.simtong.domain.schedule.dto.request

import org.hibernate.validator.constraints.Length
import java.time.LocalDate
import java.time.LocalTime
import javax.validation.constraints.FutureOrPresent
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 *
 * 개인 일정 변경을 요청하는 ChangeIndividualWebRequest
 *
 * @author Chokyunghyeon
 * @date 2022/11/27
 * @version 1.0.0
 **/
data class ChangeIndividualScheduleWebRequest(
    @field:NotBlank
    @field:Length(max = 20)
    val title: String,

    @field:NotNull
    val startAt: LocalDate,

    // TODO 시작일, 종료일 검증 Resolver 구현
    @field:NotNull
    val endAt: LocalDate,

    @field:NotNull
    val alarm: LocalTime
)
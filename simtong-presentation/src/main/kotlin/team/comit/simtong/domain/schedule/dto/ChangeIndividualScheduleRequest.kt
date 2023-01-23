package team.comit.simtong.domain.schedule.dto

import org.hibernate.validator.constraints.Length
import team.comit.simtong.domain.schedule.dto.request.ChangeIndividualScheduleData
import java.time.LocalDate
import java.time.LocalTime
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 *
 * 개인 일정 변경을 요청하는 ChangeIndividualRequest
 *
 * @author Chokyunghyeon
 * @date 2022/11/27
 * @version 1.2.5
 **/
data class ChangeIndividualScheduleRequest(

    @field:NotBlank
    @field:Length(max = 20)
    val title: String?,

    @field:NotNull
    val startAt: LocalDate?,

    // TODO 시작일, 종료일 검증 Resolver 구현
    @field:NotNull
    val endAt: LocalDate?,

    @field:NotNull
    val alarm: LocalTime?
) {

    fun toData() = ChangeIndividualScheduleData(
        title = title!!,
        startAt = startAt!!,
        endAt = endAt!!,
        alarm = alarm!!
    )
}
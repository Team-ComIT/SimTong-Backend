package team.comit.simtong.domain.schedule.dto

import org.hibernate.validator.constraints.Length
import team.comit.simtong.domain.schedule.dto.request.AddIndividualScheduleData
import java.time.LocalDate
import java.time.LocalTime
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 *
 * 개인 일정 추가를 요청하는 AddIndividualScheduleRequest
 *
 * @author Chokyunghyeon
 * @date 2022/11/26
 * @version 1.2.5
 **/
data class AddIndividualScheduleRequest(

    @field:NotBlank
    @field:Length(max = 20)
    private val title: String?,

    @field:NotNull
    private val startAt: LocalDate?,

    // TODO 시작일, 종료일 검증 Resolver 구현
    @field:NotNull
    private val endAt: LocalDate?,

    private val alarm: LocalTime?
) {

    fun toData() = AddIndividualScheduleData(
        title = title!!,
        startAt = startAt!!,
        endAt = endAt!!,
        alarm = alarm
    )
}
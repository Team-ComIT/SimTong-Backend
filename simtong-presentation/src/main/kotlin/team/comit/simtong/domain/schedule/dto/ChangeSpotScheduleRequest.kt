package team.comit.simtong.domain.schedule.dto

import org.hibernate.validator.constraints.Length
import team.comit.simtong.domain.schedule.dto.request.ChangeSpotScheduleData
import java.time.LocalDate
import java.util.UUID
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 *
 * 지점 일정을 변경 요청하는 ChangeSpotScheduleRequest
 *
 * @author Chokyunghyeon
 * @date 2022/11/22
 * @version 1.2.5
 **/
data class ChangeSpotScheduleRequest(
    @field:NotBlank
    @field:Length(max = 20)
    val title: String,

    @field:NotNull
    val startAt: LocalDate,

    // TODO 시작일, 종료일 검증 Resolver 구현
    @field:NotNull
    val endAt: LocalDate
) {

    fun toData(scheduleId: UUID) = ChangeSpotScheduleData(
        title = title,
        startAt = startAt,
        endAt = endAt,
        scheduleId = scheduleId
    )
}
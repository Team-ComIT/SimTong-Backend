package team.comit.simtong.domain.schedule.dto

import org.hibernate.validator.constraints.Length
import team.comit.simtong.domain.schedule.dto.request.AddSpotScheduleData
import java.time.LocalDate
import java.util.UUID
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 *
 * 관리자가 지점 일정을 추가 요청하는 AddSpotScheduleRequest
 *
 * @author Chokyunghyeon
 * @date 2022/11/21
 * @version 1.2.5
 **/
data class AddSpotScheduleRequest(

    @field:NotBlank
    @field:Length(max = 20)
    val title: String,

    @field:NotNull
    val startAt: LocalDate,

    // TODO 시작일, 종료일 검증 Resolver 구현
    @field:NotNull
    val endAt: LocalDate
) {

    fun toData(spotId: UUID) = AddSpotScheduleData(
        title = title,
        startAt = startAt,
        endAt = endAt,
        spotId = spotId
    )
}
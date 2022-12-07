package team.comit.simtong.domain.schedule.dto.request

import org.hibernate.validator.constraints.Length
import java.time.LocalDate
import javax.validation.constraints.FutureOrPresent
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 *
 * 지점 일정을 변경 요청하는 ChangeSpotScheduleWebRequest
 *
 * @author Chokyunghyeon
 * @date 2022/11/22
 * @version 1.0.0
 **/
data class ChangeSpotScheduleWebRequest(
    @field:NotBlank
    @field:Length(max = 20)
    val title: String,

    @field:NotNull
    val startAt: LocalDate,

    // TODO 시작일, 종료일 검증 Resolver 구현
    @field:NotNull
    val endAt: LocalDate
)
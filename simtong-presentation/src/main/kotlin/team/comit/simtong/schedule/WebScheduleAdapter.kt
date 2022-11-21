package team.comit.simtong.schedule

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import team.comit.simtong.domain.schedule.dto.AddSpotScheduleRequest
import team.comit.simtong.domain.schedule.usecase.AddSpotScheduleUseCase
import team.comit.simtong.schedule.dto.request.AddSpotScheduleWebRequest
import java.util.UUID
import javax.validation.Valid

/**
 *
 * Schedule과 관련된 요청을 받는 WebScheduleAdapter
 *
 * @author Chokyunghyeon
 * @date 2022/11/21
 * @version 1.0.0
 **/
@RestController
@RequestMapping("/schedules")
class WebScheduleAdapter(
    private val addSpotScheduleUseCase: AddSpotScheduleUseCase
) {

    @PostMapping("/spots/{spot-id}")
    @ResponseStatus(HttpStatus.CREATED)
    fun addSpotSchedule(@PathVariable("spot-id") spotId: UUID,
                        @Valid @RequestBody request: AddSpotScheduleWebRequest) {
        addSpotScheduleUseCase.execute(
            AddSpotScheduleRequest(
                spotId = spotId,
                title = request.title,
                startAt = request.startAt,
                endAt = request.endAt
            )
        )
    }
}
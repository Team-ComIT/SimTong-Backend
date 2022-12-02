package team.comit.simtong.domain.schedule

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import team.comit.simtong.domain.schedule.dto.AddIndividualScheduleRequest
import team.comit.simtong.domain.schedule.dto.AddSpotScheduleRequest
import team.comit.simtong.domain.schedule.dto.ChangeIndividualScheduleRequest
import team.comit.simtong.domain.schedule.dto.ChangeSpotScheduleRequest
import team.comit.simtong.domain.schedule.dto.QueryEntireSpotScheduleResponse
import team.comit.simtong.domain.schedule.dto.QueryIndividualSpotScheduleResponse
import team.comit.simtong.domain.schedule.dto.request.AddIndividualScheduleWebRequest
import team.comit.simtong.domain.schedule.dto.request.AddSpotScheduleWebRequest
import team.comit.simtong.domain.schedule.dto.request.ChangeIndividualScheduleWebRequest
import team.comit.simtong.domain.schedule.dto.request.ChangeSpotScheduleWebRequest
import team.comit.simtong.domain.schedule.usecase.AddIndividualScheduleUseCase
import team.comit.simtong.domain.schedule.usecase.AddSpotScheduleUseCase
import team.comit.simtong.domain.schedule.usecase.ChangeIndividualScheduleUseCase
import team.comit.simtong.domain.schedule.usecase.ChangeSpotScheduleUseCase
import team.comit.simtong.domain.schedule.usecase.QueryEntireSpotScheduleUseCase
import team.comit.simtong.domain.schedule.usecase.QueryIndividualSpotScheduleUseCase
import team.comit.simtong.domain.schedule.usecase.RemoveSpotScheduleUseCase
import java.time.LocalDate
import java.util.UUID
import javax.validation.Valid
import javax.validation.constraints.NotNull

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
    private val addIndividualScheduleUseCase: AddIndividualScheduleUseCase,
    private val changeIndividualScheduleUseCase: ChangeIndividualScheduleUseCase,
    private val queryEntireSpotScheduleUseCase: QueryEntireSpotScheduleUseCase,
    private val addSpotScheduleUseCase: AddSpotScheduleUseCase,
    private val changeSpotScheduleUseCase: ChangeSpotScheduleUseCase,
    private val removeSpotScheduleUseCase: RemoveSpotScheduleUseCase,
    private val queryIndividualSpotScheduleUseCase: QueryIndividualSpotScheduleUseCase
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addIndividualSchedule(@Valid @RequestBody request: AddIndividualScheduleWebRequest) {
        addIndividualScheduleUseCase.execute(
            AddIndividualScheduleRequest(
                title = request.title,
                startAt = request.startAt,
                endAt = request.endAt,
                alarm = request.alarm
            )
        )
    }

    @PutMapping("/{schedule-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun changeIndividualSchedule(
        @PathVariable("schedule-id") scheduleId: UUID,
        @Valid @RequestBody request: ChangeIndividualScheduleWebRequest
    ) {
        changeIndividualScheduleUseCase.execute(
            ChangeIndividualScheduleRequest(
                scheduleId = scheduleId,
                title = request.title,
                startAt = request.startAt,
                endAt = request.endAt,
                alarm = request.alarm
            )
        )
    }

    @GetMapping
    fun queryIndividualSpotSchedule(@RequestParam @NotNull date: LocalDate): QueryIndividualSpotScheduleResponse {
        return queryIndividualSpotScheduleUseCase.execute(date)
    }

    @GetMapping("/spots")
    fun entireSpotSchedule(@RequestParam @NotNull date: LocalDate): QueryEntireSpotScheduleResponse {
        return queryEntireSpotScheduleUseCase.execute(date)
    }

    @PostMapping("/spots/{spot-id}")
    @ResponseStatus(HttpStatus.CREATED)
    fun addSpotSchedule(
        @PathVariable("spot-id") spotId: UUID,
        @Valid @RequestBody request: AddSpotScheduleWebRequest
    ) {
        addSpotScheduleUseCase.execute(
            AddSpotScheduleRequest(
                spotId = spotId,
                title = request.title,
                startAt = request.startAt,
                endAt = request.endAt
            )
        )
    }

    @PutMapping("/spots/{schedule-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun changeSpotSchedule(
        @PathVariable("schedule-id") scheduleId: UUID,
        @Valid @RequestBody request: ChangeSpotScheduleWebRequest
    ) {
        changeSpotScheduleUseCase.execute(
            ChangeSpotScheduleRequest(
                scheduleId = scheduleId,
                title = request.title,
                startAt = request.startAt,
                endAt = request.endAt
            )
        )
    }

    @DeleteMapping("/spots/{schedule-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun removeSpotSchedule(@PathVariable("schedule-id") scheduleId: UUID) {
        removeSpotScheduleUseCase.execute(scheduleId)
    }
}
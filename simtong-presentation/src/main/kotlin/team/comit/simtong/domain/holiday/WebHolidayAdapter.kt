package team.comit.simtong.domain.holiday

import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import team.comit.simtong.domain.holiday.dto.AppointAnnualRequest
import team.comit.simtong.domain.holiday.dto.AppointHolidayPeriodRequest
import team.comit.simtong.domain.holiday.dto.AppointHolidayRequest
import team.comit.simtong.domain.holiday.dto.CancelHolidayRequest
import team.comit.simtong.domain.holiday.dto.ChangeEmployeeHolidayRequest
import team.comit.simtong.domain.holiday.dto.ShareHolidayRequest
import team.comit.simtong.domain.holiday.dto.response.QueryEmployeeHolidayResponse
import team.comit.simtong.domain.holiday.dto.response.QueryIndividualHolidaysResponse
import team.comit.simtong.domain.holiday.dto.response.QueryMonthHolidayPeriodResponse
import team.comit.simtong.domain.holiday.dto.response.QueryRemainAnnualResponse
import team.comit.simtong.domain.holiday.model.HolidayQueryType
import team.comit.simtong.domain.holiday.model.HolidayStatus
import team.comit.simtong.domain.holiday.usecase.AppointAnnualUseCase
import team.comit.simtong.domain.holiday.usecase.AppointHolidayPeriodUseCase
import team.comit.simtong.domain.holiday.usecase.AppointHolidayUseCase
import team.comit.simtong.domain.holiday.usecase.CancelHolidayUseCase
import team.comit.simtong.domain.holiday.usecase.ChangeEmployeeHolidayUseCase
import team.comit.simtong.domain.holiday.usecase.CheckHolidayPeriodUseCase
import team.comit.simtong.domain.holiday.usecase.QueryEmployeeHolidayUseCase
import team.comit.simtong.domain.holiday.usecase.QueryIndividualHolidayUseCase
import team.comit.simtong.domain.holiday.usecase.QueryMonthHolidayPeriodUseCase
import team.comit.simtong.domain.holiday.usecase.QueryRemainAnnualUseCase
import team.comit.simtong.domain.holiday.usecase.ShareHolidayUseCase
import java.time.LocalDate
import java.util.UUID
import javax.validation.Valid
import javax.validation.constraints.NotNull

/**
 *
 * 휴무일에 관한 요청을 받는 WebHolidayAdapter
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/12/03
 * @version 1.2.3
 **/
@Validated
@RestController
@RequestMapping("/holidays")
class WebHolidayAdapter(
    private val queryMonthHolidayPeriodUseCase: QueryMonthHolidayPeriodUseCase,
    private val appointHolidayPeriodUseCase: AppointHolidayPeriodUseCase,
    private val checkHolidayPeriodUseCase: CheckHolidayPeriodUseCase,
    private val queryRemainAnnualUseCase: QueryRemainAnnualUseCase,
    private val appointAnnualUseCase: AppointAnnualUseCase,
    private val appointHolidayUseCase: AppointHolidayUseCase,
    private val cancelHolidayUseCase: CancelHolidayUseCase,
    private val queryIndividualHolidayUseCase: QueryIndividualHolidayUseCase,
    private val shareHolidayUseCase: ShareHolidayUseCase,
    private val queryEmployeeHolidayUseCase: QueryEmployeeHolidayUseCase,
    private val changeEmployeeHolidayUseCase: ChangeEmployeeHolidayUseCase
) {

    @GetMapping("/annual/count")
    fun queryRemainAnnual(@NotNull @RequestParam year: Int?): QueryRemainAnnualResponse {
        return queryRemainAnnualUseCase.execute(year!!)
    }

    @PostMapping("/annual")
    @ResponseStatus(HttpStatus.CREATED)
    fun appointAnnual(@Valid @RequestBody request: AppointAnnualRequest) {
        appointAnnualUseCase.execute(request.toData())
    }

    @PostMapping("/dayoff")
    @ResponseStatus(HttpStatus.CREATED)
    fun appointHoliday(@Valid @RequestBody request: AppointHolidayRequest) {
        appointHolidayUseCase.execute(request.toData())
    }

    @PutMapping("/work")
    fun cancelHoliday(@Valid @RequestBody request: CancelHolidayRequest) {
        cancelHolidayUseCase.execute(request.toData())
    }

    @GetMapping("/individual")
    fun queryIndividualHolidays(
        @NotNull @RequestParam("start_at") startAt: LocalDate?,
        @NotNull @RequestParam("end_at") endAt: LocalDate?,
        @NotNull @RequestParam status: HolidayStatus?
    ): QueryIndividualHolidaysResponse {
        return queryIndividualHolidayUseCase.execute(
            startAt = startAt!!,
            endAt = endAt!!,
            status = status!!
        )
    }

    @PutMapping("/public")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun shareHoliday(@Valid @RequestBody request: ShareHolidayRequest) {
        shareHolidayUseCase.execute(request.toData())
    }

    @GetMapping("/verification-period")
    fun verifiedHolidayPeriod() {
        checkHolidayPeriodUseCase.execute()
    }

    @GetMapping("/employee")
    fun queryEmployeeHolidays(
        @NotNull @RequestParam year: Int?,
        @NotNull @RequestParam month: Int?,
        @NotNull @RequestParam type: HolidayQueryType?,
        @RequestParam("team_id", required = false) teamId: UUID?
    ): QueryEmployeeHolidayResponse {
        return queryEmployeeHolidayUseCase.execute(
            year = year!!,
            month = month!!,
            type = type!!,
            teamId = teamId
        )
    }

    @PutMapping("/employee")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun changeEmployeeHoliday(@Valid @RequestBody request: ChangeEmployeeHolidayRequest) {
        changeEmployeeHolidayUseCase.execute(request.toData())
    }

    @PutMapping("/period")
    fun appointHolidayPeriod(@Valid @RequestBody request: AppointHolidayPeriodRequest) {
        appointHolidayPeriodUseCase.execute(request.toData())
    }

    @GetMapping("/period")
    fun queryMonthHolidayPeriod(
        @NotNull @RequestParam year: Int?,
        @NotNull @RequestParam month: Int?
    ): QueryMonthHolidayPeriodResponse {
        return queryMonthHolidayPeriodUseCase.execute(
            year = year!!,
            month = month!!
        )
    }
}
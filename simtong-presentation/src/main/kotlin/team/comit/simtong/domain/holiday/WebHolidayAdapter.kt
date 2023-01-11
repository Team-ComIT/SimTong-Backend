package team.comit.simtong.domain.holiday

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import team.comit.simtong.domain.holiday.dto.request.AppointHolidayPeriodRequest
import team.comit.simtong.domain.holiday.dto.response.QueryEmployeeHolidayResponse
import team.comit.simtong.domain.holiday.dto.response.QueryIndividualHolidaysResponse
import team.comit.simtong.domain.holiday.dto.request.QueryIndividualRequest
import team.comit.simtong.domain.holiday.dto.response.QueryMonthHolidayPeriodResponse
import team.comit.simtong.domain.holiday.dto.response.QueryRemainAnnualResponse
import team.comit.simtong.domain.holiday.dto.AppointAnnualWebRequest
import team.comit.simtong.domain.holiday.dto.AppointHolidayPeriodWebRequest
import team.comit.simtong.domain.holiday.dto.AppointHolidayWebRequest
import team.comit.simtong.domain.holiday.dto.CancelHolidayWebRequest
import team.comit.simtong.domain.holiday.dto.ChangeEmployeeHolidayWebRequest
import team.comit.simtong.domain.holiday.dto.ShareHolidayWebRequest
import team.comit.simtong.domain.holiday.model.value.HolidayQueryType
import team.comit.simtong.domain.holiday.model.value.HolidayStatus
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

/**
 *
 * 휴무일에 관한 요청을 받는 WebHolidayAdapter
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/12/03
 * @version 1.2.5
 **/
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
    fun queryRemainAnnual(@RequestParam year: Int): QueryRemainAnnualResponse {
        return queryRemainAnnualUseCase.execute(year)
    }

    @PostMapping("/annual")
    @ResponseStatus(HttpStatus.CREATED)
    fun appointAnnual(@RequestBody request: AppointAnnualWebRequest) {
        appointAnnualUseCase.execute(request.date)
    }

    @PostMapping("/dayoff")
    @ResponseStatus(HttpStatus.CREATED)
    fun appointHoliday(@RequestBody request: AppointHolidayWebRequest) {
        appointHolidayUseCase.execute(request.date)
    }

    @PutMapping("/work")
    fun cancelHoliday(@RequestBody request: CancelHolidayWebRequest) {
        cancelHolidayUseCase.execute(request.date)
    }

    @GetMapping("/individual")
    fun queryIndividualHolidays(
        @RequestParam("start_at") startAt: LocalDate,
        @RequestParam("end_at") endAt: LocalDate,
        @RequestParam status: HolidayStatus
    ): QueryIndividualHolidaysResponse {
        return queryIndividualHolidayUseCase.execute(
            QueryIndividualRequest(
                startAt = startAt,
                endAt = endAt,
                status = status
            )
        )
    }

    @PutMapping("/public")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun shareHoliday(@Valid @RequestBody request: ShareHolidayWebRequest) {
        shareHolidayUseCase.execute(
            year = request.year,
            month = request.month
        )
    }

    @GetMapping("/verification-period")
    fun verifiedHolidayPeriod() {
        checkHolidayPeriodUseCase.execute()
    }

    @GetMapping("/employee")
    fun queryEmployeeHolidays(
        @RequestParam year: Int,
        @RequestParam month: Int,
        @RequestParam type: HolidayQueryType,
        @RequestParam("team_id", required = false) teamId: UUID?
    ): QueryEmployeeHolidayResponse {
        return queryEmployeeHolidayUseCase.execute(
            year = year,
            month = month,
            type = type,
            teamId = teamId
        )
    }

    @PutMapping("/employee")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun changeEmployeeHoliday(@Valid @RequestBody request: ChangeEmployeeHolidayWebRequest) {
        changeEmployeeHolidayUseCase.execute(
            beforeDate = request.beforeDate,
            userId = request.userId,
            afterDate = request.afterDate
        )
    }

    @PutMapping("/period")
    fun appointHolidayPeriod(@Valid @RequestBody request: AppointHolidayPeriodWebRequest) {
        appointHolidayPeriodUseCase.execute(
            AppointHolidayPeriodRequest(
                year = request.year,
                month = request.month,
                startAt = request.startAt,
                endAt = request.endAt
            )
        )
    }

    @GetMapping("/period")
    fun queryMonthHolidayPeriod(
        @RequestParam year: Int,
        @RequestParam month: Int
    ): QueryMonthHolidayPeriodResponse {
        return queryMonthHolidayPeriodUseCase.execute(year, month)
    }
}
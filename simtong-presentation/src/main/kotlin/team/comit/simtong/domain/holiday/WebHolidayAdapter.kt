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
import team.comit.simtong.domain.holiday.dto.QueryEmployeeHolidayResponse
import team.comit.simtong.domain.holiday.dto.QueryIndividualHolidaysResponse
import team.comit.simtong.domain.holiday.dto.QueryIndividualRequest
import team.comit.simtong.domain.holiday.dto.request.AppointAnnualWebRequest
import team.comit.simtong.domain.holiday.dto.request.AppointHolidayWebRequest
import team.comit.simtong.domain.holiday.dto.request.CancelHolidayRequest
import team.comit.simtong.domain.holiday.dto.request.ShareHolidayWebRequest
import team.comit.simtong.domain.holiday.dto.request.WebHolidayQueryType
import team.comit.simtong.domain.holiday.dto.request.WebHolidayStatus
import team.comit.simtong.domain.holiday.dto.response.QueryRemainAnnualWebResponse
import team.comit.simtong.domain.holiday.usecase.AppointAnnualUseCase
import team.comit.simtong.domain.holiday.usecase.AppointHolidayUseCase
import team.comit.simtong.domain.holiday.usecase.CancelHolidayUseCase
import team.comit.simtong.domain.holiday.usecase.CheckHolidayPeriodUseCase
import team.comit.simtong.domain.holiday.usecase.QueryEmployeeHolidayUseCase
import team.comit.simtong.domain.holiday.usecase.QueryIndividualHolidayUseCase
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
 * @date 2022/12/03
 * @version 1.0.0
 **/
@RestController
@RequestMapping("/holidays")
class WebHolidayAdapter(
    private val checkHolidayPeriodUseCase: CheckHolidayPeriodUseCase,
    private val queryRemainAnnualUseCase: QueryRemainAnnualUseCase,
    private val appointAnnualUseCase: AppointAnnualUseCase,
    private val appointHolidayUseCase: AppointHolidayUseCase,
    private val cancelHolidayUseCase: CancelHolidayUseCase,
    private val queryIndividualHolidayUseCase: QueryIndividualHolidayUseCase,
    private val shareHolidayUseCase: ShareHolidayUseCase,
    private val queryEmployeeHolidayUseCase: QueryEmployeeHolidayUseCase
) {

    @GetMapping("/annual/count")
    fun queryRemainAnnual(@RequestParam year: Int): QueryRemainAnnualWebResponse {
        return QueryRemainAnnualWebResponse(
            queryRemainAnnualUseCase.execute(year)
        )
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
    fun cancelHoliday(@RequestBody request: CancelHolidayRequest) {
        cancelHolidayUseCase.execute(request.date)
    }

    @GetMapping("/individual")
    fun queryIndividualHolidays(
        @RequestParam("start_at") startAt: LocalDate,
        @RequestParam("end_at") endAt: LocalDate,
        @RequestParam status: WebHolidayStatus
    ): QueryIndividualHolidaysResponse {
        return queryIndividualHolidayUseCase.execute(
            QueryIndividualRequest(
                startAt = startAt,
                endAt = endAt,
                status = status.name
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
        @RequestParam type: WebHolidayQueryType,
        @RequestParam("team_id", required = false) teamId: UUID?
    ): QueryEmployeeHolidayResponse {
        return queryEmployeeHolidayUseCase.execute(
            year = year,
            month = month,
            typeName = type.name,
            teamId = teamId
        )
    }
}
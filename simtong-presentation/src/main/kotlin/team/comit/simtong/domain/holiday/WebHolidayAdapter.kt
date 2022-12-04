package team.comit.simtong.domain.holiday

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import team.comit.simtong.domain.holiday.dto.request.AppointHolidayWebRequest
import team.comit.simtong.domain.holiday.dto.request.CancelHolidayWebRequest
import team.comit.simtong.domain.holiday.usecase.AppointHolidayUseCase
import team.comit.simtong.domain.holiday.usecase.CancelHolidayUseCase
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
    private val appointHolidayUseCase: AppointHolidayUseCase,
    private val cancelHolidayUseCase: CancelHolidayUseCase
) {

    @PostMapping("/dayoff")
    @ResponseStatus(HttpStatus.CREATED)
    fun appointHoliday(@Valid @RequestBody request: AppointHolidayWebRequest) {
        appointHolidayUseCase.execute(request.date)
    }

    @DeleteMapping("/work")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun cancelHoliday(@Valid @RequestBody request: CancelHolidayWebRequest) {
        cancelHolidayUseCase.execute(request.date)
    }
}
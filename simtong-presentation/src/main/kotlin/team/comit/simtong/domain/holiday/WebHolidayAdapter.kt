package team.comit.simtong.domain.holiday

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import team.comit.simtong.domain.holiday.dto.request.AppointHolidayWebRequest
import team.comit.simtong.domain.holiday.usecase.AppointHolidayUseCase
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
    private val appointHolidayUseCase: AppointHolidayUseCase
) {

    @PutMapping("/dayoff")
    @ResponseStatus(HttpStatus.CREATED)
    fun appointHoliday(@Valid @RequestBody request: AppointHolidayWebRequest) {
        appointHolidayUseCase.execute(request.date)
    }
}
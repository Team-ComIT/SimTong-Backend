package team.comit.simtong.menu

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import team.comit.simtong.domain.menu.usecase.QueryMenuByMonthUseCase
import team.comit.simtong.menu.dto.response.MenuResponse
import java.time.LocalDate

/**
 *
 * Menu에 관한 요청을 받는 WebMenuAdapter
 *
 * @author kimbeomjin
 * @date 2022/09/25
 * @version 1.0.0
 **/
@RestController
@RequestMapping("/menu")
class WebMenuAdapter(
    private val queryMenuByMonthUseCase: QueryMenuByMonthUseCase
) {

    @GetMapping
    fun getMenu(@RequestParam date: LocalDate): MenuResponse {
        val menu = queryMenuByMonthUseCase.execute(date)

        val result = menu.map { MenuResponse.MenuElement(it?.date, it?.meal) }

        return MenuResponse(result)
    }

}
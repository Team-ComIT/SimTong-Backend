package team.comit.simtong.domain.menu

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import team.comit.simtong.domain.menu.dto.MenuResponse
import team.comit.simtong.domain.menu.usecase.QueryMenuByMonthUseCase
import team.comit.simtong.domain.menu.usecase.QueryPublicMenuUseCase
import java.time.LocalDate

/**
 *
 * Menu에 관한 요청을 받는 WebMenuAdapter
 *
 * @author kimbeomjin
 * @author Chokyunghyeon
 * @date 2022/09/25
 * @version 1.0.0
 **/
@RestController
@RequestMapping("/menu")
class WebMenuAdapter(
    private val queryMenuByMonthUseCase: QueryMenuByMonthUseCase,
    private val queryPublicMenuUseCase: QueryPublicMenuUseCase
) {

    @GetMapping
    fun getMenu(
        @RequestParam date: LocalDate
    ): MenuResponse {
        return queryMenuByMonthUseCase.execute(date)
    }

    @GetMapping("/public")
    fun getPublicMenu(
        @RequestParam date: LocalDate
    ): MenuResponse {
        return queryPublicMenuUseCase.execute(date)
    }

}
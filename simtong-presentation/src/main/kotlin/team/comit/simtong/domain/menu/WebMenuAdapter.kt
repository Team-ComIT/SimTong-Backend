package team.comit.simtong.domain.menu

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import team.comit.simtong.domain.file.converter.ExcelFileConverter
import team.comit.simtong.domain.menu.dto.SaveMenuWebRequest
import team.comit.simtong.domain.menu.dto.request.SaveMenuRequest
import team.comit.simtong.domain.menu.dto.response.MenuResponse
import team.comit.simtong.domain.menu.usecase.QueryMenuByMonthUseCase
import team.comit.simtong.domain.menu.usecase.QueryPublicMenuUseCase
import team.comit.simtong.domain.menu.usecase.SaveMenuUseCase
import java.time.LocalDate
import java.util.UUID
import javax.validation.Valid

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
    private val queryPublicMenuUseCase: QueryPublicMenuUseCase,
    private val saveMenuUseCase: SaveMenuUseCase
) {

    @GetMapping
    fun getMenu(
        @RequestParam("start_at") startAt: LocalDate,
        @RequestParam("end_at") endAt: LocalDate
    ): MenuResponse {
        return queryMenuByMonthUseCase.execute(startAt, endAt)
    }

    @GetMapping("/public")
    fun getPublicMenu(
        @RequestParam("start_at") startAt: LocalDate,
        @RequestParam("end_at") endAt: LocalDate
    ): MenuResponse {
        return queryPublicMenuUseCase.execute(startAt, endAt)
    }

    @PostMapping("/{spot-id}")
    fun saveMenu(
        @PathVariable("spot-id") spotId: UUID,
        @Valid @ModelAttribute request: SaveMenuWebRequest
    ) {
        saveMenuUseCase.execute(
            SaveMenuRequest(
                file = request.file.let(ExcelFileConverter::transferTo),
                year = request.year,
                month = request.month,
                spotId = spotId
            )
        )
    }
}
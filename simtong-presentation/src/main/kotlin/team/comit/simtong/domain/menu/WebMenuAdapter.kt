package team.comit.simtong.domain.menu

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import team.comit.simtong.domain.file.converter.ExcelFileConverter
import team.comit.simtong.domain.menu.dto.response.MenuResponse
import team.comit.simtong.domain.menu.usecase.QueryMenuByMonthUseCase
import team.comit.simtong.domain.menu.usecase.QueryPublicMenuUseCase
import team.comit.simtong.domain.menu.usecase.SaveMenuUseCase
import java.time.LocalDate
import java.util.UUID
import javax.validation.constraints.NotNull

/**
 *
 * Menu에 관한 요청을 받는 WebMenuAdapter
 *
 * @author kimbeomjin
 * @author Chokyunghyeon
 * @date 2022/09/25
 * @version 1.0.0
 **/
@Validated
@RestController
@RequestMapping("/menu")
class WebMenuAdapter(
    private val queryMenuByMonthUseCase: QueryMenuByMonthUseCase,
    private val queryPublicMenuUseCase: QueryPublicMenuUseCase,
    private val saveMenuUseCase: SaveMenuUseCase
) {

    @GetMapping
    fun getMenu(
        @NotNull @RequestParam("start_at") startAt: LocalDate?,
        @NotNull @RequestParam("end_at") endAt: LocalDate?
    ): MenuResponse {
        return queryMenuByMonthUseCase.execute(
            startAt = startAt!!,
            endAt = endAt!!
        )
    }

    @GetMapping("/public")
    fun getPublicMenu(
        @NotNull @RequestParam("start_at") startAt: LocalDate?,
        @NotNull @RequestParam("end_at") endAt: LocalDate?
    ): MenuResponse {
        return queryPublicMenuUseCase.execute(
            startAt = startAt!!,
            endAt = endAt!!
        )
    }

    @PostMapping("/{spot-id}")
    fun saveMenu(
        @PathVariable("spot-id") spotId: UUID,
        @NotNull @RequestPart file: MultipartFile?,
        @NotNull @RequestParam year: Int?,
        @NotNull @RequestParam month: Int?
    ) {
        saveMenuUseCase.execute(
            spotId = spotId,
            file = file!!.let(ExcelFileConverter::transferTo),
            year = year!!,
            month = month!!
        )
    }
}
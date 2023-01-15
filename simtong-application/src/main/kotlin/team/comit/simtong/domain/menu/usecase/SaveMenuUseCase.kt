package team.comit.simtong.domain.menu.usecase

import team.comit.simtong.domain.menu.dto.request.SaveMenuData
import team.comit.simtong.domain.menu.exception.MenuExceptions
import team.comit.simtong.domain.menu.spi.CommandMenuPort
import team.comit.simtong.domain.menu.spi.ParseMenuFilePort
import team.comit.simtong.domain.menu.spi.QueryMenuPort
import team.comit.simtong.global.annotation.UseCase
import java.time.LocalDate
import java.util.UUID

/**
 *
 * 지점별로 월별 메뉴를 저장을 담당하는 SaveMenuUseCase
 *
 * @author kimbeomjin
 * @author Chokyunghyeon
 * @date 2022/12/10
 * @version 1.2.5
 **/
@UseCase
class SaveMenuUseCase(
    private val parseMenuFilePort: ParseMenuFilePort,
    private val queryMenuPort: QueryMenuPort,
    private val commandMenuPort: CommandMenuPort
) {

    fun execute(request: SaveMenuData, spotId: UUID) {
        val menu = parseMenuFilePort.importMenu(request.file, request.year, request.month, spotId)

        if (queryMenuPort.existsMenuByMonthAndSpotId(LocalDate.of(request.year, request.month, 1), spotId)) {
            throw MenuExceptions.AlreadyExistsSameMonth("${request.year}년 ${request.month}월 메뉴가 이미 존재합니다.")
        }

        commandMenuPort.saveAll(menu)
    }
}
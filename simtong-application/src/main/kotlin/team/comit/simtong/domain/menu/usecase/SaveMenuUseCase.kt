package team.comit.simtong.domain.menu.usecase

import team.comit.simtong.domain.menu.exception.MenuExceptions
import team.comit.simtong.domain.menu.spi.CommandMenuPort
import team.comit.simtong.domain.menu.spi.ParseMenuFilePort
import team.comit.simtong.domain.menu.spi.QueryMenuPort
import team.comit.simtong.global.annotation.UseCase
import java.io.File
import java.time.LocalDate
import java.util.UUID

/**
 *
 * 지점별로 월별 메뉴를 저장을 담당하는 SaveMenuUseCase
 *
 * @author kimbeomjin
 * @date 2022/12/10
 * @version 1.0.0
 **/
@UseCase
class SaveMenuUseCase(
    private val parseMenuFilePort: ParseMenuFilePort,
    private val queryMenuPort: QueryMenuPort,
    private val commandMenuPort: CommandMenuPort
) {

    fun execute(file: File, year: Int, month: Int, spotId: UUID) {
        val menu = parseMenuFilePort.importMenu(file, year, month, spotId)

        if (queryMenuPort.queryMenusByMonthAndSpotId(LocalDate.of(year, month, 1), spotId).isNotEmpty()) {
            throw MenuExceptions.AlreadyExistsSameMonth("${year}년 ${month}월 메뉴가 이미 존재합니다.")
        }

        commandMenuPort.saveAll(menu)
    }
}
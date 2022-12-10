package team.comit.simtong.domain.menu.usecase

import team.comit.simtong.domain.menu.spi.CommandMenuPort
import team.comit.simtong.domain.menu.spi.ParseMenuFilePort
import team.comit.simtong.global.annotation.UseCase
import java.io.File
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
    private val commandMenuPort: CommandMenuPort
) {

    fun execute(file: File, year: Int, month: Int, spotId: UUID) {
        val menu = parseMenuFilePort.importMenu(file, year, month, spotId)

        commandMenuPort.saveAll(menu)
    }
}
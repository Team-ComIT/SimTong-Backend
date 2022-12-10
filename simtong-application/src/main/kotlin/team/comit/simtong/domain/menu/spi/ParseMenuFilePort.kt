package team.comit.simtong.domain.menu.spi

import team.comit.simtong.domain.menu.model.Menu
import java.io.File
import java.util.UUID

/**
 *
 * Menu 파일 파싱을 요청하는 ParseMenuFilePort
 *
 * @author kimbeomjin
 * @date 2022/12/10
 * @version 1.0.0
 **/
interface ParseMenuFilePort {

    fun importMenu(file: File, year: Int, month: Int, spotId: UUID): List<Menu>

}
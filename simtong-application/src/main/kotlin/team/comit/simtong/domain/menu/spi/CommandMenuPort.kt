package team.comit.simtong.domain.menu.spi

import team.comit.simtong.domain.menu.model.Menu

/**
 *
 * Menu Domain에 관한 명령을 요청하는 CommandMenuPort
 *
 * @author kimbeomjin
 * @date 2022/12/10
 * @version 1.2.3
 **/
interface CommandMenuPort {

    fun saveAll(menus: List<Menu>)

}
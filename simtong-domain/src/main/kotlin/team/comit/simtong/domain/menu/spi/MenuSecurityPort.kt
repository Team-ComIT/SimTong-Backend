package team.comit.simtong.domain.menu.spi

import java.util.*

/**
 *
 * Menu Domain에 관한 보안 처리를 요청하는 MenuSecurityPort
 *
 * @author kimbeomjin
 * @date 2022/09/26
 * @version 1.0.0
 **/
interface MenuSecurityPort {

    fun getCurrentUserId(): UUID

}
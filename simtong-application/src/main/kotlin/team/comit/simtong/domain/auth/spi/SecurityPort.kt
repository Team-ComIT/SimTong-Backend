package team.comit.simtong.domain.auth.spi

import team.comit.simtong.domain.menu.spi.MenuSecurityPort
import team.comit.simtong.domain.user.spi.UserSecurityPort

/**
 *
 * Security에 관한 요청을 하는 SecurityPort
 *
 * @author kimbeomjin
 * @date 2022/09/18
 * @version 1.0.0
 **/
interface SecurityPort : UserSecurityPort, MenuSecurityPort
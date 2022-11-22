package team.comit.simtong.domain.menu.spi

import team.comit.simtong.domain.user.model.User
import java.util.UUID

/**
 *
 * Menu Domain에서 User Domain에 관한 Query를 요청하는 MenuQueryUserPort
 *
 * @author kimbeomjin
 * @date 2022/09/26
 * @version 1.0.0
 **/
interface MenuQueryUserPort {

    fun queryUserById(id: UUID): User?

}
package team.comit.simtong.domain.holiday.spi

import team.comit.simtong.domain.user.model.User
import java.util.UUID

/**
 *
 * Holiday Domain에서 User에 관한 Query를 요청하는 HolidayQueryUserPort
 *
 * @author Chokyunghyeon
 * @date 2022/12/03
 * @version 1.0.0
 **/
interface HolidayQueryUserPort {

    fun queryUserById(id: UUID): User?

}
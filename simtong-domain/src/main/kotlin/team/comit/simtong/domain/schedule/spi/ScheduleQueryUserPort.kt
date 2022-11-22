package team.comit.simtong.domain.schedule.spi

import team.comit.simtong.domain.user.model.User
import java.util.UUID

/**
 *
 * Schedule Domain에서 User에 관한 Query를 요청하는 ScheduleUserPort
 *
 * @author Chokyunghyeon
 * @date 2022/11/21
 * @version 1.0.0
 **/
interface ScheduleQueryUserPort {

    fun queryUserById(id: UUID): User?
}
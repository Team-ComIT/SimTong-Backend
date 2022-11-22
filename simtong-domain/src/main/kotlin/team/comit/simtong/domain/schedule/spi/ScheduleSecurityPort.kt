package team.comit.simtong.domain.schedule.spi

import java.util.UUID

/**
 *
 * Schedule Domain에서 Security에 관한 Query를 요청하는 ScheduleSecurityPort
 *
 * @author Chokyunghyeon
 * @date 2022/11/21
 * @version 1.0.0
 **/
interface ScheduleSecurityPort {

    fun getCurrentUserId(): UUID

}
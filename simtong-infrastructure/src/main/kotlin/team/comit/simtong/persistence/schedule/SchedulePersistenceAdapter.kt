package team.comit.simtong.persistence.schedule

import org.springframework.stereotype.Component
import team.comit.simtong.domain.schedule.spi.SchedulePort
import team.comit.simtong.persistence.schedule.mapper.ScheduleMapper

/**
 *
 * 일정의 영속성을 관리하는 SchedulePersistenceAdapter
 *
 * @author Chokyunghyeon
 * @date 2022/11/21
 * @version 1.0.0
 **/
@Component
class SchedulePersistenceAdapter(
    private val scheduleJpaRepository: ScheduleJpaRepository,
    private val scheduleMapper: ScheduleMapper
) : SchedulePort {


}
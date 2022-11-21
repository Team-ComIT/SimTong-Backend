package team.comit.simtong.persistence.schedule

import org.springframework.data.repository.CrudRepository
import team.comit.simtong.persistence.schedule.entity.ScheduleJpaEntity
import java.util.UUID

/**
 *
 * 일정의 Spring Repository를 담당하는 ScheduleJpaRepository
 *
 * @author Chokyunghyeon
 * @date 2022/11/21
 * @version 1.0.0
 **/
interface ScheduleJpaRepository : CrudRepository<ScheduleJpaEntity, UUID> {
}
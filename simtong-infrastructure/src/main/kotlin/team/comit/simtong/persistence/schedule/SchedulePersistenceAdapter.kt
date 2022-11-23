package team.comit.simtong.persistence.schedule

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.comit.simtong.domain.schedule.model.Schedule
import team.comit.simtong.domain.schedule.spi.SchedulePort
import team.comit.simtong.persistence.schedule.mapper.ScheduleMapper
import java.util.UUID

/**
 *
 * Schedule의 영속성을 관리하는 SchedulePersistenceAdapter
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

    override fun save(schedule: Schedule) = scheduleMapper.toDomain(
       scheduleJpaRepository.save(
           scheduleMapper.toEntity(schedule)
       )
    )!!

    override fun queryScheduleById(id: UUID): Schedule? {
        return scheduleMapper.toDomain(
            scheduleJpaRepository.findByIdOrNull(id)
        )
    }

}
package team.comit.simtong.persistence.schedule.mapper

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.comit.simtong.domain.schedule.model.Schedule
import team.comit.simtong.persistence.GenericMapper
import team.comit.simtong.persistence.schedule.entity.ScheduleJpaEntity
import team.comit.simtong.persistence.spot.SpotJpaRepository
import team.comit.simtong.persistence.user.repository.UserJpaRepository

/**
 *
 * Schedule Entity와 Schedule Aggregate를 변환하는 ScheduleMapper
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/11/21
 * @version 1.2.5
 **/
@Component
class ScheduleMapper(
    private val userJpaRepository: UserJpaRepository,
    private val spotJpaRepository: SpotJpaRepository
) : GenericMapper<ScheduleJpaEntity, Schedule> {

    override fun toEntity(model: Schedule): ScheduleJpaEntity {
        val user = userJpaRepository.findByIdOrNull(model.employeeId)!!
        val spot = spotJpaRepository.findByIdOrNull(model.spotId)!!

        return ScheduleJpaEntity(
            id = model.id,
            user = user,
            spot = spot,
            title = model.title,
            scope = model.scope,
            startAt = model.startAt,
            endAt = model.endAt,
            alarmTime = model.alarmTime
        )
    }

    override fun toDomain(entity: ScheduleJpaEntity?): Schedule? {
        return entity?.let {
            Schedule(
                id = it.id!!,
                employeeId = it.user.id!!,
                spotId = it.spot.id!!,
                title = it.title,
                scope = it.scope,
                startAt = it.startAt,
                endAt = it.endAt,
                alarmTime = it.alarmTime
            )
        }
    }

    override fun toDomainNotNull(entity: ScheduleJpaEntity): Schedule {
        return Schedule(
            id = entity.id!!,
            employeeId = entity.user.id!!,
            spotId = entity.spot.id!!,
            title = entity.title,
            scope = entity.scope,
            startAt = entity.startAt,
            endAt = entity.endAt,
            alarmTime = entity.alarmTime
        )
    }
}
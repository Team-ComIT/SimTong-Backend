package team.comit.simtong.persistence.schedule

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.comit.simtong.domain.schedule.model.Schedule
import team.comit.simtong.domain.schedule.spi.SchedulePort
import team.comit.simtong.domain.schedule.vo.SpotSchedule
import team.comit.simtong.persistence.schedule.mapper.ScheduleMapper
import team.comit.simtong.persistence.schedule.vo.QSpotScheduleVo
import java.time.LocalDate
import java.util.UUID
import team.comit.simtong.persistence.schedule.entity.QScheduleJpaEntity.scheduleJpaEntity as schedule
import team.comit.simtong.persistence.spot.entity.QSpotJpaEntity.spotJpaEntity as spot

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
    private val scheduleMapper: ScheduleMapper,
    private val queryFactory: JPAQueryFactory
) : SchedulePort {

    override fun save(schedule: Schedule) = scheduleMapper.toDomain(
       scheduleJpaRepository.save(
           scheduleMapper.toEntity(schedule)
       )
    )!!

    override fun delete(schedule: Schedule) {
        scheduleJpaRepository.delete(
            scheduleMapper.toEntity(schedule)
        )
    }

    override fun queryScheduleById(id: UUID): Schedule? {
        return scheduleMapper.toDomain(
            scheduleJpaRepository.findByIdOrNull(id)
        )
    }

    override fun querySchedulesByDateContains(date: LocalDate): List<SpotSchedule> {
        val startDate = date.withDayOfMonth(1)
        val endDate = date.withDayOfMonth(date.lengthOfMonth())

        return queryFactory
            .select(
                QSpotScheduleVo(
                    schedule.id,
                    schedule.title,
                    schedule.startAt,
                    schedule.endAt,
                    spot.id,
                    spot.name
                ))
            .from(schedule)
            .join(spot)
            .on(schedule.spot.eq(spot))
            .where(
                schedule.startAt.between(startDate, endDate)
                    .or(schedule.endAt.between(startDate, endDate))
            )
            .orderBy(schedule.startAt.asc())
            .fetch()
    }

}
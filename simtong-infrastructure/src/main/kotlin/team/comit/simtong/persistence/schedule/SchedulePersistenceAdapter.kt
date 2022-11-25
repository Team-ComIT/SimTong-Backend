package team.comit.simtong.persistence.schedule

import com.querydsl.core.types.dsl.DatePath
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.comit.simtong.domain.schedule.model.Schedule
import team.comit.simtong.domain.schedule.spi.SchedulePort
import team.comit.simtong.persistence.schedule.entity.QScheduleJpaEntity.scheduleJpaEntity
import team.comit.simtong.persistence.schedule.mapper.ScheduleMapper
import java.time.LocalDate
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
    private val scheduleMapper: ScheduleMapper,
    private val jpaQueryFactory: JPAQueryFactory
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

    override fun querySchedulesByMonth(year: Int, month: Int): List<Schedule> {
        return jpaQueryFactory
            .selectFrom(scheduleJpaEntity)
            .where(
                scheduleJpaEntity.startAt.monthEq(month)
                    .and(scheduleJpaEntity.startAt.yearEq(year))
                    .or(scheduleJpaEntity.endAt.monthEq(month)
                        .and(scheduleJpaEntity.endAt.yearEq(year)))
            )
            .orderBy(scheduleJpaEntity.startAt.asc())
            .fetch()
            .map {
                scheduleMapper.toDomain(it)!!
            }
    }

    private fun DatePath<LocalDate>.yearEq(year: Int) = year().eq(year)

    private fun DatePath<LocalDate>.monthEq(month: Int) = month().eq(month)

}
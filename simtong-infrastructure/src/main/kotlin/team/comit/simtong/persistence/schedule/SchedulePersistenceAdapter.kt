package team.comit.simtong.persistence.schedule

import team.comit.simtong.persistence.schedule.entity.QScheduleJpaEntity.scheduleJpaEntity as schedule
import team.comit.simtong.persistence.spot.entity.QSpotJpaEntity.spotJpaEntity as spot
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.comit.simtong.domain.schedule.model.Schedule
import team.comit.simtong.domain.schedule.model.Scope
import team.comit.simtong.domain.schedule.spi.SchedulePort
import team.comit.simtong.domain.schedule.vo.SpotSchedule
import team.comit.simtong.global.extension.QuerydslExtensionUtils.or
import team.comit.simtong.persistence.schedule.mapper.ScheduleMapper
import team.comit.simtong.persistence.schedule.vo.QSpotScheduleVo
import java.time.LocalDate
import java.util.UUID

/**
 *
 * Schedule의 영속성을 관리하는 SchedulePersistenceAdapter
 *
 * @author Chokyunghyeon
 * @date 2022/11/21
 * @version 1.2.3
 **/
@Component
class SchedulePersistenceAdapter(
    private val scheduleJpaRepository: ScheduleJpaRepository,
    private val scheduleMapper: ScheduleMapper,
    private val queryFactory: JPAQueryFactory
) : SchedulePort {

    override fun save(schedule: Schedule): Schedule {
        return scheduleJpaRepository.save(
            scheduleMapper.toEntity(schedule)
        ).let(scheduleMapper::toDomainNotNull)
    }

    override fun delete(schedule: Schedule) {
        scheduleJpaRepository.delete(
            scheduleMapper.toEntity(schedule)
        )
    }

    override fun queryScheduleById(id: UUID): Schedule? {
        return scheduleJpaRepository.findByIdOrNull(id)
            .let(scheduleMapper::toDomain)
    }

    override fun querySpotSchedulesByPeriodAndScope(startAt: LocalDate, endAt: LocalDate, scope: Scope): List<SpotSchedule> {
        return queryFactory
            .select(
                QSpotScheduleVo(
                    schedule.id,
                    schedule.title,
                    schedule.startAt,
                    schedule.endAt,
                    spot.id,
                    spot.name
                )
            )
            .from(schedule)
            .join(spot)
            .on(schedule.spot.eq(spot))
            .where(
                schedule.scope.eq(scope),
                inPeriodScheduleFilter(startAt, endAt)
            )
            .orderBy(schedule.startAt.asc())
            .fetch()
    }

    override fun querySchedulesByPeriodAndSpotIdAndScope(startAt: LocalDate, endAt: LocalDate, spotId: UUID, scope: Scope): List<Schedule> {
        return queryFactory
            .selectFrom(schedule)
            .where(
                schedule.scope.eq(scope),
                schedule.spot.id.eq(spotId),
                inPeriodScheduleFilter(startAt, endAt)
            )
            .orderBy(schedule.startAt.asc())
            .fetch()
            .map(scheduleMapper::toDomainNotNull)
    }

    override fun querySchedulesByPeriodAndUserIdAndScope(startAt: LocalDate, endAt: LocalDate, userId: UUID, scope: Scope): List<Schedule> {
        return queryFactory
            .selectFrom(schedule)
            .where(
                schedule.user.id.eq(userId),
                schedule.scope.eq(scope),
                inPeriodScheduleFilter(startAt, endAt)
            )
            .orderBy(schedule.startAt.asc())
            .fetch()
            .map(scheduleMapper::toDomainNotNull)
    }

    private fun inPeriodScheduleFilter(startAt: LocalDate, endAt: LocalDate) : BooleanExpression {
        return schedule.startAt.between(startAt, endAt) or
                schedule.endAt.between(startAt, endAt)
    }
}
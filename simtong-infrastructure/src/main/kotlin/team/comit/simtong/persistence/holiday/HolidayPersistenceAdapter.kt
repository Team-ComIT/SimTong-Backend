package team.comit.simtong.persistence.holiday

import team.comit.simtong.persistence.holiday.entity.QHolidayJpaEntity.holidayJpaEntity as holiday
import team.comit.simtong.persistence.spot.entity.QSpotJpaEntity.spotJpaEntity as spot
import team.comit.simtong.persistence.team.entity.QTeamJpaEntity.teamJpaEntity as team
import team.comit.simtong.persistence.user.entity.QUserJpaEntity.userJpaEntity as user
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.comit.simtong.domain.holiday.model.Holiday
import team.comit.simtong.domain.holiday.model.HolidayStatus
import team.comit.simtong.domain.holiday.model.HolidayType
import team.comit.simtong.domain.holiday.spi.HolidayPort
import team.comit.simtong.domain.holiday.spi.vo.EmployeeHoliday
import team.comit.simtong.global.extension.QuerydslExtensionUtils.or
import team.comit.simtong.global.extension.QuerydslExtensionUtils.sameWeekFilter
import team.comit.simtong.persistence.holiday.entity.HolidayJpaEntity
import team.comit.simtong.persistence.holiday.mapper.HolidayMapper
import team.comit.simtong.persistence.holiday.repository.HolidayJpaRepository
import team.comit.simtong.persistence.holiday.vo.QEmployeeHolidayVO
import java.time.LocalDate
import java.util.UUID

/**
 *
 * 휴무일의 영속성을 관리하는 HolidayPersistenceAdapter
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/12/02
 * @version 1.2.3
 **/
@Component
class HolidayPersistenceAdapter(
    private val holidayMapper: HolidayMapper,
    private val holidayJpaRepository: HolidayJpaRepository,
    private val queryFactory: JPAQueryFactory
) : HolidayPort {

    override fun countHolidayByYearAndUserIdAndType(year: Int, userId: UUID, type: HolidayType): Long {
        return queryFactory.select(holiday.count())
            .from(holiday)
            .where(
                holiday.id.userId.eq(userId),
                holidayTypeFilter(type),
                holiday.id.date.year().eq(year)
            )
            .fetchFirst()
    }

    override fun countHolidayByWeekAndUserIdAndType(date: LocalDate, userId: UUID, type: HolidayType): Long {
        return queryFactory.select(holiday.count())
            .from(holiday)
            .where(
                holiday.id.userId.eq(userId),
                holidayTypeFilter(type),
                holiday.id.date.sameWeekFilter(date)
            )
            .fetchFirst()
    }

    override fun queryHolidayByDateAndUserId(date: LocalDate, userId: UUID): Holiday? {
        return holidayJpaRepository.findByIdOrNull(
            HolidayJpaEntity.Id(
                date = date,
                userId = userId
            )
        ).let(holidayMapper::toDomain)
    }

    override fun queryHolidaysByPeriodAndUserIdAndStatus(
        startAt: LocalDate,
        endAt: LocalDate,
        userId: UUID,
        status: HolidayStatus
    ): List<Holiday> {
        return queryFactory.selectFrom(holiday)
            .where(
                holiday.id.userId.eq(userId),
                holiday.id.date.between(startAt, endAt),
                holidayAndAnnualFilter(status)
            )
            .orderBy(holiday.id.date.asc())
            .fetch()
            .map(holidayMapper::toDomainNotNull)
    }

    override fun queryHolidaysByYearAndMonthAndTeamId(
        year: Int,
        month: Int,
        type: HolidayType?,
        spotId: UUID,
        teamId: UUID?
    ): List<EmployeeHoliday> {
        return queryFactory
            .select(
                QEmployeeHolidayVO(
                    holiday.id.date,
                    holiday.type,
                    user.id,
                    user.name,
                    spot.name,
                    team.name
                )
            )
            .from(holiday)
            .join(holiday.spot, spot)
            .join(holiday.user, user)
            .join(user.team, team)
            .where(
                holiday.id.date.year().eq(year),
                holiday.id.date.month().eq(month),
                holiday.spot.id.eq(spotId),
                holiday.status.eq(HolidayStatus.WRITTEN),
                holidayTypeFilter(type),
                teamId?.let { team.id.eq(it) }
            )
            .fetch()
    }

    override fun queryHolidaysByYearAndMonthAndSpotIdAndType(
        year: Int,
        month: Int,
        spotId: UUID,
        type: HolidayType
    ): List<Holiday> {
        return queryFactory.selectFrom(holiday)
            .where(
                holiday.id.date.year().eq(year),
                holiday.id.date.month().eq(month),
                holiday.spot.id.eq(spotId),
                holidayTypeFilter(type),
                holiday.status.eq(HolidayStatus.WRITTEN)
            )
            .fetch()
            .map(holidayMapper::toDomainNotNull)
    }

    override fun existsHolidayByDateAndUserIdAndType(date: LocalDate, userId: UUID, type: HolidayType): Boolean {
        return queryFactory.selectFrom(holiday)
            .where(
                holiday.id.userId.eq(userId),
                holiday.id.date.eq(date),
                holidayTypeFilter(type)
            )
            .fetchOne() != null
    }

    override fun save(holiday: Holiday): Holiday {
        return holidayJpaRepository.save(
            holidayMapper.toEntity(holiday)
        ).let(holidayMapper::toDomainNotNull)
    }

    override fun saveAll(holidays: List<Holiday>): List<Holiday> {
        return holidayJpaRepository.saveAll(
            holidays.map(holidayMapper::toEntity)
        ).map(holidayMapper::toDomainNotNull)
    }

    override fun delete(holiday: Holiday) {
        holidayJpaRepository.delete(
            holidayMapper.toEntity(holiday)
        )
    }

    private fun holidayAndAnnualFilter(status: HolidayStatus): BooleanExpression {
        return holiday.status.eq(status) or holiday.type.eq(HolidayType.ANNUAL)
    }

    private fun holidayTypeFilter(type: HolidayType?): BooleanExpression? {
        return type?.let {
            holiday.type.eq(type)
        }
    }
}
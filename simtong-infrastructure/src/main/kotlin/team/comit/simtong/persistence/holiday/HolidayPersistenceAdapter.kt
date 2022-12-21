package team.comit.simtong.persistence.holiday

import team.comit.simtong.persistence.holiday.entity.QHolidayJpaEntity.holidayJpaEntity as holiday
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.comit.simtong.domain.holiday.model.Holiday
import team.comit.simtong.domain.holiday.model.HolidayStatus
import team.comit.simtong.domain.holiday.model.HolidayType
import team.comit.simtong.domain.holiday.spi.HolidayPort
import team.comit.simtong.persistence.QuerydslExtensionUtils.or
import team.comit.simtong.persistence.QuerydslExtensionUtils.sameWeekFilter
import team.comit.simtong.persistence.holiday.entity.HolidayJpaEntity
import team.comit.simtong.persistence.holiday.mapper.HolidayMapper
import team.comit.simtong.persistence.holiday.repository.HolidayJpaRepository
import java.time.LocalDate
import java.util.UUID

/**
 *
 * 휴무일의 영속성을 관리하는 HolidayPersistenceAdapter
 *
 * @author Chokyunghyeon
 * @date 2022/12/02
 * @version 1.0.0
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
                holiday.type.eq(type),
                holiday.id.date.year().eq(year)
            )
            .fetchFirst()
    }

    override fun countHolidayByWeekAndUserIdAndType(date: LocalDate, userId: UUID, type: HolidayType): Long {
        return queryFactory.select(holiday.count())
            .from(holiday)
            .where(
                holiday.id.userId.eq(userId),
                holiday.type.eq(type),
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
            .map { holidayMapper.toDomain(it)!! }
    }

    override fun existsHolidayByDateAndUserIdAndType(date: LocalDate, userId: UUID, type: HolidayType): Boolean {
        return queryFactory.selectFrom(holiday)
            .where(
                holiday.id.userId.eq(userId),
                holiday.id.date.eq(date),
                holiday.type.eq(type)
            )
            .fetchOne() != null
    }

    override fun save(holiday: Holiday): Holiday {
        return holidayJpaRepository.save(
            holidayMapper.toEntity(holiday)
        ).let { holidayMapper.toDomain(it)!! }
    }

    override fun delete(holiday: Holiday) {
        holidayJpaRepository.delete(
            holidayMapper.toEntity(holiday)
        )
    }

    private fun holidayAndAnnualFilter(status: HolidayStatus) : BooleanExpression {
        return holiday.status.eq(status) or holiday.type.eq(HolidayType.ANNUAL)
    }

}
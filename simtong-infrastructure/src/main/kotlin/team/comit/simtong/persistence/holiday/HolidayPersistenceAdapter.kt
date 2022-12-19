package team.comit.simtong.persistence.holiday

import team.comit.simtong.persistence.holiday.entity.QHolidayJpaEntity.holidayJpaEntity as holiday
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.comit.simtong.domain.holiday.model.Holiday
import team.comit.simtong.domain.holiday.model.HolidayType
import team.comit.simtong.domain.holiday.spi.HolidayPort
import team.comit.simtong.persistence.QuerydslExtensionUtils.sameWeekFilter
import team.comit.simtong.persistence.QuerydslExtensionUtils.sameYearFilter
import team.comit.simtong.persistence.holiday.entity.HolidayId
import team.comit.simtong.persistence.holiday.mapper.HolidayMapper
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

    override fun countHolidayByYearAndUserIdAndType(date: LocalDate, userId: UUID, type: HolidayType): Long {
        return queryFactory.select(holiday.count())
            .from(holiday)
            .where(
                holiday.id.userId.eq(userId),
                holiday.type.eq(type),
                holiday.id.date.sameYearFilter(date)
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
            HolidayId(
                date = date,
                userId = userId
            )
        ).let(holidayMapper::toDomain)
    }

    override fun queryHolidaysByPeriodAndUserId(startAt: LocalDate, endAt: LocalDate, userId: UUID): List<Holiday> {
        return queryFactory.selectFrom(holiday)
            .where(
                holiday.id.userId.eq(userId),
                holiday.id.date.between(startAt, endAt)
            )
            .orderBy(holiday.id.date.asc())
            .fetch()
            .map { holidayMapper.toDomain(it)!! }
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

}
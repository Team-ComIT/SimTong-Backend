package team.comit.simtong.persistence.holiday

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.comit.simtong.domain.holiday.model.HolidayPeriod
import team.comit.simtong.domain.holiday.spi.HolidayPeriodPort
import team.comit.simtong.persistence.holiday.entity.HolidayPeriodJpaEntity
import team.comit.simtong.persistence.holiday.mapper.HolidayPeriodMapper
import team.comit.simtong.persistence.holiday.repository.HolidayPeriodJpaRepository
import java.util.UUID

/**
 *
 * 휴무일 작성 기간과 관련된 HolidayPeriodPersistenceAdapter
 *
 * @author Chokyunghyeon
 * @date 2022/12/20
 * @version 1.0.0
 **/
@Component
class HolidayPeriodPersistenceAdapter(
    private val holidayPeriodMapper: HolidayPeriodMapper,
    private val holidayPeriodJpaRepository: HolidayPeriodJpaRepository
) : HolidayPeriodPort {

    override fun queryHolidayPeriodByYearAndMonthAndSpotId(year: Int, month: Int, spotId: UUID): HolidayPeriod? {
        return holidayPeriodJpaRepository.findByIdOrNull(
            HolidayPeriodJpaEntity.Id(
                year = year,
                month = month,
                spotId = spotId
            )
        ).let(holidayPeriodMapper::toDomain)
    }


}
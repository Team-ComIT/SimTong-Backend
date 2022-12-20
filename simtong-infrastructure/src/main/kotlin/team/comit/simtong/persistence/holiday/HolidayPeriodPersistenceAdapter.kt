package team.comit.simtong.persistence.holiday

import org.springframework.stereotype.Component
import team.comit.simtong.persistence.holiday.mapper.HolidayPeriodMapper
import team.comit.simtong.persistence.holiday.repository.HolidayPeriodJpaRepository

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
) {


}
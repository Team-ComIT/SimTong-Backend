package team.comit.simtong.persistence.holiday

import org.springframework.stereotype.Component
import team.comit.simtong.persistence.holiday.mapper.HolidayMapper

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
    private val holidayJpaRepository: HolidayJpaRepository
) {


}
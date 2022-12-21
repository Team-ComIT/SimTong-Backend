package team.comit.simtong.persistence.holiday.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.comit.simtong.persistence.holiday.entity.HolidayJpaEntity

/**
 *
 * Spring Repository의 기능을 이용하는 HolidayJpaRepository
 *
 * @author Chokyunghyeon
 * @date 2022/12/02
 * @version 1.0.0
 **/
@Repository
interface HolidayJpaRepository : CrudRepository<HolidayJpaEntity, HolidayJpaEntity.Id> {
}
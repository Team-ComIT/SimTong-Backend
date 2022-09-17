package team.comit.simtong.persistence.user.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.comit.simtong.persistence.user.entity.DeviceTokenJpaEntity
import java.util.*

/**
 *
 * Spring Repository의 기능을 이용하는 DeviceTokenJpaRepository
 *
 * @author kimbeomjin
 * @date 2022/08/21
 * @version 1.0.0
 **/
@Repository
interface DeviceTokenJpaRepository : CrudRepository<DeviceTokenJpaEntity, UUID> {
}
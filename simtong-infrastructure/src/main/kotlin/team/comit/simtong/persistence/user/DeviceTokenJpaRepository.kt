package team.comit.simtong.persistence.user

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.comit.simtong.persistence.user.entity.DeviceTokenJpaEntity
import java.util.*

@Repository
interface DeviceTokenJpaRepository : CrudRepository<DeviceTokenJpaEntity, UUID> {
}
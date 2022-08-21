package team.comit.simtong.persistence.user

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.comit.simtong.persistence.user.entity.UserJpaEntity
import java.util.*

@Repository
interface UserJpaRepository : CrudRepository<UserJpaEntity, UUID> {
}
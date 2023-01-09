package team.comit.simtong.persistence.user.mapper

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.comit.simtong.domain.user.model.DeviceToken
import team.comit.simtong.persistence.GenericMapper
import team.comit.simtong.persistence.user.entity.DeviceTokenJpaEntity
import team.comit.simtong.persistence.user.repository.UserJpaRepository

/**
 *
 * DeviceToken Entity와 DeviceToken Aggregate를 변환하는 DeviceTokenMapper
 *
 * @author kimbeomjin
 * @date 2023/01/01
 * @version 1.2.5
 **/
@Component
class DeviceTokenMapper(
    private val userJpaRepository: UserJpaRepository
) : GenericMapper<DeviceTokenJpaEntity, DeviceToken> {

    override fun toEntity(model: DeviceToken): DeviceTokenJpaEntity {
        val user = userJpaRepository.findByIdOrNull(model.userId)!!

        return DeviceTokenJpaEntity(
            userId = model.userId,
            user = user,
            token = model.token
        )
    }

    override fun toDomain(entity: DeviceTokenJpaEntity?): DeviceToken? {
        return entity?.let {
            DeviceToken(
                userId = it.user.id!!,
                token = it.token
            )
        }
    }

    override fun toDomainNotNull(entity: DeviceTokenJpaEntity): DeviceToken {
        return DeviceToken(
            userId = entity.user.id!!,
            token = entity.token
        )
    }
}
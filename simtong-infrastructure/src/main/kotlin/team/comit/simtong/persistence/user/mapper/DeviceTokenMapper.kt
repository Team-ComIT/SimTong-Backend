package team.comit.simtong.persistence.user.mapper

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.springframework.beans.factory.annotation.Autowired
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
 * @version 1.1.0
 **/
@Mapper
abstract class DeviceTokenMapper : GenericMapper<DeviceTokenJpaEntity, DeviceToken> {

    @Autowired
    protected lateinit var userRepository: UserJpaRepository

    @Mapping(target = "user", expression = "java(userRepository.findById(model.getUserId()).orElse(null))")
    abstract override fun toEntity(model: DeviceToken): DeviceTokenJpaEntity
}
package team.comit.simtong.persistence.user.mapper

import org.mapstruct.Mapper
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.persistence.GenericMapper
import team.comit.simtong.persistence.user.entity.UserJpaEntity

/**
 *
 * UserEntity와 DomainUser의 변환을 담당하는 UserMapper
 *
 * @author JoKyungHyeon
 * @date 2022/09/04
 * @version 1.0.0
 **/
@Mapper
abstract class UserMapper: GenericMapper<UserJpaEntity, User> {
}
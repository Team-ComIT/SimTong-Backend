package team.comit.simtong.persistence.user.mapper

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.persistence.GenericMapper
import team.comit.simtong.persistence.spot.SpotJpaRepository
import team.comit.simtong.persistence.team.TeamJpaRepository
import team.comit.simtong.persistence.user.entity.UserJpaEntity

/**
 *
 * UserEntity와 DomainUser의 변환을 담당하는 UserMapper
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/04
 * @version 1.2.5
 **/
@Component
class UserMapper(
    private val spotJpaRepository: SpotJpaRepository,
    private val teamJpaRepository: TeamJpaRepository
) : GenericMapper<UserJpaEntity, User> {

    override fun toEntity(model: User): UserJpaEntity {
        val spot = spotJpaRepository.findByIdOrNull(model.spotId)!!
        val team = teamJpaRepository.findByIdOrNull(model.teamId)!!

        return UserJpaEntity(
            id = model.id,
            employeeNumber = model.employeeNumber,
            email = model.email,
            authority = model.authority,
            name = model.name,
            nickname = model.nickname,
            password = model.password,
            spot = spot,
            team = team,
            profileImagePath = model.profileImagePath,
            deletedAt = model.deletedAt
        )
    }

    override fun toDomain(entity: UserJpaEntity?): User? {
        return entity?.let {
            User(
                id = it.id!!,
                nickname = it.nickname,
                name = it.name,
                email = it.email,
                password = it.password,
                employeeNumber = it.employeeNumber,
                authority = it.authority,
                spotId = it.spot.id!!,
                teamId = it.team.id!!,
                profileImagePath = it.profileImagePath,
                deletedAt = it.deletedAt
            )
        }
    }

    override fun toDomainNotNull(entity: UserJpaEntity): User {
        return User(
            id = entity.id!!,
            nickname = entity.nickname,
            name = entity.name,
            email = entity.email,
            password = entity.password,
            employeeNumber = entity.employeeNumber,
            authority = entity.authority,
            spotId = entity.spot.id!!,
            teamId = entity.team.id!!,
            profileImagePath = entity.profileImagePath,
            deletedAt = entity.deletedAt
        )
    }
}
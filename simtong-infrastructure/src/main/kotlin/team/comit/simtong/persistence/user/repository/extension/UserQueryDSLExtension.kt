package team.comit.simtong.persistence.user.repository.extension

import team.comit.simtong.persistence.user.entity.UserJpaEntity

/**
 *
 * QueryDSL을 사용해 UserJpaRepository를 확장하는 UserQueryDSLExtension
 *
 * @author Chokyunghyeon
 * @date 2022/09/11
 * @version 1.0.0
 **/
interface UserQueryDSLExtension {

    fun queryUserJpaEntityByNameAndSpotAndEmail(name: String, spot: String, email: String): UserJpaEntity?

}
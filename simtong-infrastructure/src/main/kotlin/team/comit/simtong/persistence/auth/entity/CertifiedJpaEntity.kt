package team.comit.simtong.persistence.auth.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import javax.persistence.Column
import javax.validation.constraints.NotNull

/**
  *
  * 이메일이 인증되었는지 여부를 관리하는 CertifiedJpaEntity
  *
  * @author JoKyungHyeon
  * @date 2022/08/22
  * @version 1.0.0
 **/
@RedisHash("tbl_certified")
class CertifiedJpaEntity(
    @Id
    val key: String,

    @TimeToLive
    @field:NotNull
    val expirationTime: Int
) {
    @field:NotNull
    @Column(columnDefinition = "BIT(1)")
    val isVerified: Boolean = false

}
package team.comit.simtong.persistence.auth.entity

import org.hibernate.annotations.ColumnDefault
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed
import javax.validation.constraints.NotNull

/**
  *
  * 이메일 인증 정책을 관리하는 AuthCodePolicyEntity
  *
  * @author JoKyungHyeon
  * @date 2022/08/29
  * @version 1.0.0
 **/
@RedisHash("tbl_auth_code_policy")
class AuthCodePolicyEntity(
    @Id
    @Indexed
    val key: String,

    @field:NotNull
    @TimeToLive
    val expirationTime: Int
) {
    @field:NotNull
    @ColumnDefault("1")
    val attemptCount: Short = 1

    @field:NotNull
    @ColumnDefault("false")
    val isVerified: Boolean = false
}
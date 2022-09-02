package team.comit.simtong.persistence.auth.entity

import org.hibernate.validator.constraints.Length
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed
import team.comit.simtong.domain.user.model.Authority
import javax.validation.constraints.NotNull

/**
  *
  * Access 토큰을 재발급하기 위한 RefreshTokenEntity
  *
  * @author JoKyungHyeon
  * @date 2022/09/01
  * @version 1.0.0
 **/
@RedisHash("tbl_refresh_token")
class RefreshTokenEntity(
    @Id
    val token: String,

    @field:NotNull
    val email: String,

    @field:NotNull
    @field:Length(max = 11)
    val authority: Authority,

    @field:NotNull
    @TimeToLive
    val expirationTime: Int
)
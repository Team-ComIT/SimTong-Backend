package team.comit.simtong.persistence.auth.entity

import org.hibernate.annotations.ColumnDefault
import org.hibernate.validator.constraints.Length
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.util.*
import javax.validation.constraints.NotNull

/**
  *
  * 이메일 인증시 사용자의 이메일인지 확인하는 AuthCodeJpaEntity
  *
  * @author JoKyungHyeon
  * @date 2022/08/22
  * @version 1.0.0
 **/
@RedisHash("tbl_auth_code")
class AuthCodeJpaEntity(
    @Id
    val key: String,

    @field:NotNull
    @field:Length(max = 6)
    val code: String,

    @field:NotNull
    val codeExpirationTime: Date,

    @field:NotNull
    val expirationTime: Int
) {
    @field:NotNull
    @ColumnDefault("1")
    val attemptCount: Int = 1

    // TODO 재 요청 copy method
}
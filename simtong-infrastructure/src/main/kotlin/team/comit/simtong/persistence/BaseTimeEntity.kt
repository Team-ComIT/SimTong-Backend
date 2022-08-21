package team.comit.simtong.persistence

import java.time.LocalDateTime
import javax.persistence.MappedSuperclass
import javax.validation.constraints.NotNull

/**
  *
  * createdAt 컬럼이 필요한 경우 상속받는 BaseTimeEntity
  *
  * @author kimbeomjin
  * @date 2022/08/21
 **/
@MappedSuperclass
abstract class BaseTimeEntity(
    @field:NotNull
    val createdAt: LocalDateTime = LocalDateTime.now(),
)
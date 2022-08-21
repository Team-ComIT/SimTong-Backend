package team.comit.simtong.persistence

import java.time.LocalDateTime
import javax.persistence.MappedSuperclass
import javax.validation.constraints.NotNull

@MappedSuperclass
abstract class BaseTimeEntity(
    @field:NotNull
    val createDate: LocalDateTime = LocalDateTime.now(),
)
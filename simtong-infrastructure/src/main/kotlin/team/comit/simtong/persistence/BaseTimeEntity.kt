package team.comit.simtong.persistence

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.MappedSuperclass

/**
 *
 * createdAt 컬럼이 필요한 경우 상속받는 BaseTimeEntity
 *
 * @author kimbeomjin
 * @date 2022/08/22
 * @version 1.0.0
 **/
@MappedSuperclass
abstract class BaseTimeEntity(
    @Column(nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
)
package team.comit.simtong.persistence

import java.util.*
import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.MappedSuperclass

/**
 *
 * pk의 타입이 UUID 일 경우 상속받는 BaseUUIDEntity
 *
 * @author kimbeomjin
 * @date 2022/08/21
 * @version 1.0.0
 **/
@MappedSuperclass
abstract class BaseUUIDEntity(
    @Id
    @Column(columnDefinition = "BINARY(16)")
    val id: UUID = UUID.randomUUID()
)
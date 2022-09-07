package team.comit.simtong.persistence

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass

/**
 *
 * pk의 타입이 UUID 이고 created_at 컬럼이 필요한 경우 상속받는 BaseEntity
 *
 * @author kimbeomjin
 * @date 2022/08/21
 * @version 1.0.0
 **/
@MappedSuperclass
abstract class BaseEntity(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    val id: UUID?
) : BaseTimeEntity()
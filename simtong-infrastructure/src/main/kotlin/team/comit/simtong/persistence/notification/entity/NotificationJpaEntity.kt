package team.comit.simtong.persistence.notification.entity

import team.comit.simtong.domain.notification.model.value.NotificationType
import team.comit.simtong.persistence.BaseEntity
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Table
import javax.validation.constraints.NotNull

/**
 *
 * 알림에 관해 관리하는 JPA 엔티티인 NotificationJpaEntity
 *
 * @author kimbeomjin
 * @date 2022/12/29
 * @version 1.1.0
 **/
@Entity
@Table(name = "tbl_notification")
class NotificationJpaEntity(
    override val id: UUID,

    @field:NotNull
    @Column(columnDefinition = "VARCHAR(50)")
    val title: String,

    @field:NotNull
    @Column(columnDefinition = "VARCHAR(255)")
    val content: String,

    @field:NotNull
    @Column(columnDefinition = "VARCHAR(20)")
    @Enumerated(EnumType.STRING)
    val type: NotificationType,

    @Column(columnDefinition = "BINARY(16)")
    val identify: UUID?,

    @field:NotNull
    override val createdAt: LocalDateTime

) : BaseEntity(id)
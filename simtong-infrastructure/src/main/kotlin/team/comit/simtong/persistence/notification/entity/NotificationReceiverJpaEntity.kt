package team.comit.simtong.persistence.notification.entity

import team.comit.simtong.persistence.user.entity.UserJpaEntity
import java.io.Serializable
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.MapsId
import javax.persistence.Table

/**
 *
 * 알림 수신자에 관해 관리하는 JPA 엔티티인 NotificationReceiverJpaEntity
 *
 * @author kimbeomjin
 * @date 2022/12/29
 * @version 1.1.0
 **/
@Entity
@Table(name = "tbl_notification_receiver")
class NotificationReceiverJpaEntity(
    @EmbeddedId
    val notificationReceiverId: Id,

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BINARY(16)", nullable = false)
    val user: UserJpaEntity,

    @MapsId("notificationId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_id", columnDefinition = "BINARY(16)", nullable = false)
    val notification: NotificationJpaEntity,

    val checkedAt: LocalDateTime?
) {

    /**
     *
     * 알림 엔티티의 기본키인 NotificationReceiver Id
     *
     * @author kimbeomjin
     * @date 2022/12/29
     * @version 1.1.0
     **/
    @Embeddable
    data class Id(
        @Column(columnDefinition = "BINARY(16)", nullable = false)
        val userId: UUID,

        @Column(columnDefinition = "BINARY(16)", nullable = false)
        val notificationId: UUID
    ) : Serializable
}


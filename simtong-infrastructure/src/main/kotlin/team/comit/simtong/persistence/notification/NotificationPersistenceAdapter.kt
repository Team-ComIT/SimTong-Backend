package team.comit.simtong.persistence.notification

import org.springframework.stereotype.Component
import team.comit.simtong.domain.notification.model.Notification
import team.comit.simtong.domain.notification.model.NotificationReceiver
import team.comit.simtong.domain.notification.spi.NotificationPort
import team.comit.simtong.persistence.notification.mapper.NotificationMapper
import team.comit.simtong.persistence.notification.mapper.NotificationReceiverMapper
import team.comit.simtong.persistence.notification.repository.NotificationJpaRepository
import team.comit.simtong.persistence.notification.repository.NotificationReceiverJpaRepository

/**
 *
 * Notification의 영속성을 관리하는 NotificationPersistenceAdapter
 *
 * @author kimbeomjin
 * @date 2022/12/30
 * @version 1.2.3
 **/
@Component
class NotificationPersistenceAdapter(
    private val notificationMapper: NotificationMapper,
    private val notificationRepository: NotificationJpaRepository,
    private val notificationReceiverMapper: NotificationReceiverMapper,
    private val notificationReceiverRepository: NotificationReceiverJpaRepository
) : NotificationPort {

    override fun saveNotification(notification: Notification): Notification {
        return notificationRepository.save(
            notificationMapper.toEntity(notification)
        ).let(notificationMapper::toDomainNotNull)
    }

    override fun saveNotificationReceiver(notificationReceiver: NotificationReceiver): NotificationReceiver {
        return notificationReceiverRepository.save(
            notificationReceiverMapper.toEntity(notificationReceiver)
        ).let(notificationReceiverMapper::toDomainNotNull)
    }

    override fun saveAllNotificationReceiver(notificationReceivers: List<NotificationReceiver>) {
        notificationReceiverRepository.saveAll(
            notificationReceivers.map(notificationReceiverMapper::toEntity)
        )
    }
}
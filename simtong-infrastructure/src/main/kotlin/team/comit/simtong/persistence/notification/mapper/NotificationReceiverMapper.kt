package team.comit.simtong.persistence.notification.mapper

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.comit.simtong.domain.notification.model.NotificationReceiver
import team.comit.simtong.persistence.GenericMapper
import team.comit.simtong.persistence.notification.entity.NotificationReceiverJpaEntity
import team.comit.simtong.persistence.notification.repository.NotificationJpaRepository
import team.comit.simtong.persistence.user.repository.UserJpaRepository

/**
 *
 * NotificationReceiver Entity와 NotificationReceiver Aggregate를 변환하는 NotificationReceiverMapper
 *
 * @author kimbeomjin
 * @date 2022/12/29
 * @version 1.2.5
 **/
@Component
class NotificationReceiverMapper(
    private val userJpaRepository: UserJpaRepository,
    private val notificationJpaRepository: NotificationJpaRepository
) : GenericMapper<NotificationReceiverJpaEntity, NotificationReceiver> {

    override fun toEntity(model: NotificationReceiver): NotificationReceiverJpaEntity {
        val user = userJpaRepository.findByIdOrNull(model.userId)!!
        val notification = notificationJpaRepository.findByIdOrNull(model.notificationId)!!

        return NotificationReceiverJpaEntity(
            notificationReceiverId = NotificationReceiverJpaEntity.Id(
                userId = model.userId,
                notificationId = model.notificationId
            ),
            user = user,
            notification = notification,
            checkedAt = model.checkedAt
        )
    }

    override fun toDomain(entity: NotificationReceiverJpaEntity?): NotificationReceiver? {
        return entity?.let {
            NotificationReceiver(
                userId = it.user.id!!,
                notificationId = it.notification.id,
                checkedAt = it.checkedAt
            )
        }
    }

    override fun toDomainNotNull(entity: NotificationReceiverJpaEntity): NotificationReceiver {
        return NotificationReceiver(
            userId = entity.user.id!!,
            notificationId = entity.notification.id,
            checkedAt = entity.checkedAt
        )
    }
}
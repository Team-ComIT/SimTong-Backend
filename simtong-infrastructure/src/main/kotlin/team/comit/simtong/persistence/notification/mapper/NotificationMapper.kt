package team.comit.simtong.persistence.notification.mapper

import org.springframework.stereotype.Component
import team.comit.simtong.domain.notification.model.Notification
import team.comit.simtong.persistence.GenericMapper
import team.comit.simtong.persistence.notification.entity.NotificationJpaEntity

/**
 *
 * Notification Entity와 Notification Aggregate를 변환하는 NotificationMapper
 *
 * @author kimbeomjin
 * @date 2022/12/29
 * @version 1.2.5
 **/
@Component
class NotificationMapper : GenericMapper<NotificationJpaEntity, Notification> {
    override fun toEntity(model: Notification): NotificationJpaEntity {
        return NotificationJpaEntity(
            id = model.id,
            title = model.title,
            content = model.content,
            type = model.type,
            identify = model.identify,
            createdAt = model.createdAt
        )
    }

    override fun toDomain(entity: NotificationJpaEntity?): Notification? {
        return entity?.let {
            Notification(
                id = it.id,
                title = it.title,
                content = it.content,
                type = it.type,
                identify = it.identify,
                createdAt = it.createdAt
            )
        }
    }

    override fun toDomainNotNull(entity: NotificationJpaEntity): Notification {
        return Notification(
            id = entity.id,
            title = entity.title,
            content = entity.content,
            type = entity.type,
            identify = entity.identify,
            createdAt = entity.createdAt
        )
    }
}
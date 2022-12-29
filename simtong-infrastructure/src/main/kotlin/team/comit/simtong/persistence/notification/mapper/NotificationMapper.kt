package team.comit.simtong.persistence.notification.mapper

import org.mapstruct.Mapper
import team.comit.simtong.domain.notification.model.Notification
import team.comit.simtong.persistence.GenericMapper
import team.comit.simtong.persistence.notification.entity.NotificationJpaEntity

/**
 *
 * Notification Entity와 Notification Aggregate를 변환하는 NotificationMapper
 *
 * @author kimbeomjin
 * @date 2022/12/29
 * @version 1.1.0
 **/
@Mapper
abstract class NotificationMapper : GenericMapper<NotificationJpaEntity, Notification> {
}
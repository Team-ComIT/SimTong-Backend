package team.comit.simtong.persistence.notification.mapper

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.springframework.beans.factory.annotation.Autowired
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
 * @version 1.2.3
 **/
@Mapper
abstract class NotificationReceiverMapper : GenericMapper<NotificationReceiverJpaEntity, NotificationReceiver> {

    @Autowired
    protected lateinit var notificationRepository: NotificationJpaRepository

    @Autowired
    protected lateinit var userRepository: UserJpaRepository

    @Mappings(
        Mapping(
            target = "notificationReceiverId",
            expression = "java(new NotificationReceiverJpaEntity.Id(model.getUserId(), model.getNotificationId()))"
        ),
        Mapping(
            target = "user",
            expression = "java(userRepository.findById(model.getUserId()).orElse(null))"
        ),
        Mapping(
            target = "notification",
            expression = "java(notificationRepository.findById(model.getNotificationId()).orElse(null))"
        )
    )
    abstract override fun toEntity(model: NotificationReceiver): NotificationReceiverJpaEntity

    @Mappings(
        Mapping(target = "userId", expression = "java(entity.getNotificationReceiverId().getUserId())"),
        Mapping(target = "notificationId", expression = "java(entity.getNotificationReceiverId().getNotificationId())")
    )
    abstract override fun toDomain(entity: NotificationReceiverJpaEntity?): NotificationReceiver?

    @Mappings(
        Mapping(target = "userId", expression = "java(entity.getNotificationReceiverId().getUserId())"),
        Mapping(target = "notificationId", expression = "java(entity.getNotificationReceiverId().getNotificationId())")
    )
    abstract override fun toDomainNotNull(entity: NotificationReceiverJpaEntity): NotificationReceiver

}
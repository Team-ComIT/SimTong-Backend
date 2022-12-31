package team.comit.simtong.persistence.notification.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.comit.simtong.persistence.notification.entity.NotificationReceiverJpaEntity

/**
 *
 * 알림 수신자의 Spring Repository를 담당하는 NotificationReceiverRepository
 *
 * @author kimbeomjin
 * @date 2022/12/29
 * @version 1.1.0
 **/
@Repository
interface NotificationReceiverJpaRepository : CrudRepository<NotificationReceiverJpaEntity, NotificationReceiverJpaEntity.Id> {
}
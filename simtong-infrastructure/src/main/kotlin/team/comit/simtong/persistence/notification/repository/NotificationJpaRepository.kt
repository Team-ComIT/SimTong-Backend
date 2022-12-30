package team.comit.simtong.persistence.notification.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.comit.simtong.persistence.notification.entity.NotificationJpaEntity
import java.util.UUID

/**
 *
 * 알림의 Spring Repository를 담당하는 NotificationJpaRepository
 *
 * @author kimbeomjin
 * @date 2022/12/29
 * @version 1.1.0
 **/
@Repository
interface NotificationJpaRepository : CrudRepository<NotificationJpaEntity, UUID> {
}
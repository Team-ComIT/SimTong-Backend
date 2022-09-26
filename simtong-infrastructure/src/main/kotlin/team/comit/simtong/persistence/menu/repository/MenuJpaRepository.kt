package team.comit.simtong.persistence.menu.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.comit.simtong.persistence.menu.entity.MenuJpaEntity
import java.time.LocalDate

/**
 *
 * Spring Repository의 기능을 이용하는 MenuJpaRepository
 *
 * @author kimbeomjin
 * @date 2022/09/20
 * @version 1.0.0
 **/
@Repository
interface MenuJpaRepository : CrudRepository<MenuJpaEntity, LocalDate> {
}
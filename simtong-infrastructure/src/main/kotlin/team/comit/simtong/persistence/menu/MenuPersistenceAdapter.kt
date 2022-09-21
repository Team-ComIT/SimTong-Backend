package team.comit.simtong.persistence.menu

import org.springframework.stereotype.Component
import team.comit.simtong.persistence.menu.mapper.MenuMapper

/**
 *
 * Menu의 영속성을 관리하는 MenuPersistenceAdapter
 *
 * @author kimbeomjin
 * @date 2022/09/20
 * @version 1.0.0
 **/
@Component
class MenuPersistenceAdapter(
    private val menuJpaRepository: MenuJpaRepository,
    private val menuMapper: MenuMapper
) {

    // TODO 영속성 로직

}
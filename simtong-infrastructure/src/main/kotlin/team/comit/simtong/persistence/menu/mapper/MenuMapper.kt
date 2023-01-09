package team.comit.simtong.persistence.menu.mapper

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.comit.simtong.domain.menu.model.Menu
import team.comit.simtong.persistence.GenericMapper
import team.comit.simtong.persistence.menu.entity.MenuJpaEntity
import team.comit.simtong.persistence.spot.SpotJpaRepository

/**
 *
 * MenuEntity와 Menu를 변환하는 MenuMapper
 *
 * @author kimbeomjin
 * @date 2022/09/20
 * @version 1.2.5
 **/
@Component
class MenuMapper(
    private val spotJpaRepository: SpotJpaRepository
) : GenericMapper<MenuJpaEntity, Menu> {

    override fun toEntity(model: Menu): MenuJpaEntity {
        val spot = spotJpaRepository.findByIdOrNull(model.spotId)!!

        return MenuJpaEntity(
            menuId = MenuJpaEntity.Id(
                spotId = model.spotId,
                date = model.date
            ),
            spot = spot,
            meal = model.meal
        )
    }

    override fun toDomain(entity: MenuJpaEntity?): Menu? {
        return entity?.let {
            val id = it.id
            Menu(
                date = id.date,
                meal = it.meal,
                spotId = it.spot.id!!
            )
        }
    }

    override fun toDomainNotNull(entity: MenuJpaEntity): Menu {
        return Menu(
            date = entity.id.date,
            meal = entity.meal,
            spotId = entity.spot.id!!
        )
    }
}
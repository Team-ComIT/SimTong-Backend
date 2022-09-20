package team.comit.simtong.persistence.menu.mapper

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.springframework.beans.factory.annotation.Autowired
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
 * @version 1.0.0
 **/
@Mapper
abstract class MenuMapper : GenericMapper<MenuJpaEntity, Menu> {

    @Autowired
    protected lateinit var spotJpaRepository: SpotJpaRepository

    @Mapping(target = "spot", expression = "java(spotJpaRepository.querySpotJpaEntityById(model.getSpotId()))")
    abstract override fun toEntity(model: Menu): MenuJpaEntity

    @Mapping(target = "spotId", expression = "java(entity.getSpot().getId())")
    abstract override fun toDomain(entity: MenuJpaEntity?): Menu?

}
package team.comit.simtong.persistence.menu.mapper

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
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

    @Mappings(
        Mapping(target = "id", expression = "java(new MenuId(model.getSpotId(), model.getDate()))"),
        Mapping(target = "spot", expression = "java(spotJpaRepository.findById(model.getSpotId()).orElse(null))")
    )
    abstract override fun toEntity(model: Menu): MenuJpaEntity

    @Mappings(
        Mapping(target = "spotId", expression = "java(entity.getId().getSpotId())"),
        Mapping(target = "date", expression = "java(entity.getId().getDate())")
    )
    abstract override fun toDomain(entity: MenuJpaEntity?): Menu?

}
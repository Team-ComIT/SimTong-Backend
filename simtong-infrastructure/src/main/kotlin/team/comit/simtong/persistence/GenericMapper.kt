package team.comit.simtong.persistence

/**
 *
 * Mapper의 기본 템플릿을 지원하는 GenericMapper
 *
 * @author Chokyunghyeon
 * @date 2022/09/04
 * @version 1.0.0
 **/
interface GenericMapper<E, D> {

    fun toEntity(model: D): E

    fun toDomain(entity: E?): D?
}
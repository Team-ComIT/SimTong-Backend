package team.comit.simtong.global.extension

/**
 *
 * 컬렉션의 확장함수를 관리하는 CollectionExtensionUtils
 *
 * @author Chokyunghyeon
 * @date 2023/01/04
 * @version --<releases>--
 **/
object CollectionExtensionUtils {

    inline fun <T, R : Any> Iterable<T>.mapNonNull(transform: (T) -> R?) : List<R> {
        return mapNonNullTo(mutableListOf(), transform)
    }

    inline fun <T, R : Any, C : MutableCollection<in R>> Iterable<T>.mapNonNullTo(destination: C, transform: (T) -> R?): C {
        forEach { element -> transform(element).let { destination.add(it!!) } }
        return destination
    }

}
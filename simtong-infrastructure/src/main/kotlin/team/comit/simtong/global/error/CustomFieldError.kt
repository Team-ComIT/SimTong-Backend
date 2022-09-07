package team.comit.simtong.global.error

import org.springframework.validation.BindingResult

/**
 *
 * 상세한 예외를 보여주기 위한 CustomFieldError
 *
 * @author kimbeomjin
 * @date 2022/09/07
 * @version 1.0.0
 **/
class CustomFieldError(
    val field: String,
    val value: String,
    val reason: String
) {
    companion object {
        fun of(field: String, value: String, reason: String): List<CustomFieldError> {
            val fieldErrors: MutableList<CustomFieldError> = ArrayList()
            fieldErrors.add(CustomFieldError(field, value, reason))
            return fieldErrors
        }

        fun of(bindingResult: BindingResult): List<CustomFieldError> {
            val fieldErrors = bindingResult.fieldErrors
            return fieldErrors.map { error ->
                CustomFieldError(
                    field = error.field,
                    value = error.rejectedValue.toString(),
                    reason = error.defaultMessage!!
                )
            }
        }
    }
}
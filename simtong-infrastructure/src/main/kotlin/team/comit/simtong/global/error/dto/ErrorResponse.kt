package team.comit.simtong.global.error.dto

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import team.comit.simtong.global.exception.BusinessException
import team.comit.simtong.global.exception.GlobalExceptions
import team.comit.simtong.global.exception.WebException
import javax.validation.ConstraintViolationException

/**
 *
 * 예외가 발생했을 경우 response 형태를 일관되게 유지하기 위한 ErrorResponse
 *
 * @author kimbeomjin
 * @date 2022/08/22
 * @version 1.0.0
 **/
class ErrorResponse(
    val status: Int,
    val message: String,
    val fieldErrors: List<CustomFieldError>
) {

    companion object {
        fun of(e: BusinessException) = ErrorResponse(
            status = e.status,
            message = e.message,
            fieldErrors = emptyList()
        )

        fun of(exception: WebException) = ErrorResponse(
            status = exception.status,
            message = exception.message,
            fieldErrors = emptyList()
        )

        fun of(bindingResult: BindingResult): ErrorResponse = of(
            exception = GlobalExceptions.BadRequest(),
            fieldErrors = CustomFieldError.of(bindingResult)
        )

        fun of(exception: ConstraintViolationException): ErrorResponse {
            val fieldErrors = exception.constraintViolations.flatMap { violation ->
                val path = violation.propertyPath
                val field = path.last().name
                val message = violation.message
                CustomFieldError.of(field, "", message)
            }

            return of(
                exception = GlobalExceptions.BadRequest(),
                fieldErrors = fieldErrors
            )
        }

        fun of(exception: MethodArgumentTypeMismatchException): ErrorResponse {
            val value = exception.value
            val fieldErrors = CustomFieldError.of(exception.name, value.toString(), exception.errorCode)

            return of(
                exception = GlobalExceptions.BadRequest(),
                fieldErrors = fieldErrors
            )
        }

        fun of(exception: MissingServletRequestParameterException): ErrorResponse {
            val fieldErrors = CustomFieldError.of(exception.parameterName, "", exception.message)

            return of(
                exception = GlobalExceptions.BadRequest(),
                fieldErrors = fieldErrors
            )
        }

        fun of(exception: DataIntegrityViolationException): ErrorResponse = of(
            exception = GlobalExceptions.BadRequest(),
            fieldErrors = CustomFieldError.of("", "", exception.message ?: "")
        )

        private fun of(exception: BusinessException, fieldErrors: List<CustomFieldError>) = ErrorResponse(
            status = exception.status,
            message = exception.message,
            fieldErrors = fieldErrors
        )
    }
}
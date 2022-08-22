package team.comit.simtong.global.error

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.validation.BindingResult
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import javax.validation.ConstraintViolation
import javax.validation.Path

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
    val reason: String
) {

    companion object {
        fun of(exception: ErrorProperty) = of(
            exception = exception,
            reason = ""
        )

        fun of(bindingResult: BindingResult): ErrorResponse {
            val errorMap = bindingResult.fieldErrors.associate { it.field to it.defaultMessage }

            return of(
                exception = GlobalErrorCode.BAD_REQUEST,
                reason = errorMap.toString()
            )
        }

        fun of(violations: Set<ConstraintViolation<*>>): ErrorResponse {
            val reason = violations.associate { propertyName(it.propertyPath) to it.message }

            return of(
                exception = GlobalErrorCode.BAD_REQUEST,
                reason = reason.toString()
            )
        }

        fun of(exception: MethodArgumentTypeMismatchException): ErrorResponse {
            val reason = exception.name + ":" + exception.errorCode + ":" + exception.value

            return of(
                exception = GlobalErrorCode.BAD_REQUEST,
                reason = reason
            )
        }

        fun of(exception: DataIntegrityViolationException): ErrorResponse {
            val reason = exception.cause.toString()

            return of(
                exception = GlobalErrorCode.BAD_REQUEST,
                reason = reason
            )
        }

        private fun of(exception: ErrorProperty, reason: String) = ErrorResponse(
            status = exception.status(),
            message = exception.message(),
            reason = reason
        )

        private fun propertyName(path: Path): String {
            val pathToString = path.toString()
            return pathToString.substring(pathToString.lastIndexOf('.') + 1)
        }
    }
}
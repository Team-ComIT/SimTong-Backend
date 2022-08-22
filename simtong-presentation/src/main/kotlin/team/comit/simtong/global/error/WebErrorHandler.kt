package team.comit.simtong.global.error

import org.springframework.http.HttpStatus
import org.springframework.validation.BindException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import javax.validation.ConstraintViolationException

/**
 *
 * presentation layer에서 발생하는 예외를 핸들링하는 WebErrorHandler
 *
 * @author kimbeomjin
 * @date 2022/08/22
 * @version 1.0.0
 **/
@RestControllerAdvice
class WebErrorHandler {

    /**
     * ModelAttribute 에서 binding error 발생시 BindException 발생
     */
    @ExceptionHandler(BindException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected fun handleBindException(exception: BindException): ErrorResponse? {
        return ErrorResponse.of(exception.bindingResult)
    }

    /**
     * Valid 또는 Validated 으로 binding error 발생시 발생
     * HttpMessageConverter 에서 등록한 HttpMessageConverter binding 이 실패하는 경우 발생
     * 보통 @RequestBody, @RequestPart 어노테이션에서 발생
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected fun handleMethodArgumentNotValidException(
        exception: MethodArgumentNotValidException
    ): ErrorResponse? {
        return ErrorResponse.of(exception.bindingResult)
    }

    /**
     * type 이 일치하지 않아 binding 하지 못할 경우 발생
     * 보통 @RequestParam 요청을 enum 으로 binding 못했을 경우 발생
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected fun handleMethodArgumentTypeMismatchException(
        exception: MethodArgumentTypeMismatchException
    ): ErrorResponse? {
        return ErrorResponse.of(exception)
    }

    /**
     * javax.validation 을 통과하지 못하면 에러가 발생
     * 보통 @NotBlank, @NotEmpty, @NotNull 에서 발생
     */
    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected fun handleConstraintViolationException(
        exception: ConstraintViolationException
    ): ErrorResponse? {
        return ErrorResponse.of(exception.constraintViolations)
    }

    /**
     * 적합하지 않거나 적절하지 못한 인자를 메서드에 넘기면 발생
     */
    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected fun handleIllegalArgumentException(exception: IllegalArgumentException): ErrorResponse? {
        return ErrorResponse.of(GlobalErrorCode.BAD_REQUEST)
    }

    /**
     * 지원하지 않은 HTTP method 호출할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    protected fun handleHttpRequestMethodNotSupportedException(
        exception: HttpRequestMethodNotSupportedException
    ): ErrorResponse? {
        return ErrorResponse.of(GlobalErrorCode.METHOD_NOT_ALLOWED)
    }
}
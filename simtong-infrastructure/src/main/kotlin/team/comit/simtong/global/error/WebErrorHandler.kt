package team.comit.simtong.global.error

import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import team.comit.simtong.global.error.dto.ErrorResponse
import team.comit.simtong.global.exception.GlobalExceptions
import javax.validation.ConstraintViolationException

/**
 *
 * presentation layer에서 발생하는 예외를 핸들링하는 WebErrorHandler
 *
 * @author kimbeomjin
 * @author Chokyunghyeon
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
        return ErrorResponse.of(exception)
    }

    /**
     * 적합하지 않거나 적절하지 못한 인자를 메서드에 넘기면 발생
     */
    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected fun handleIllegalArgumentException(exception: IllegalArgumentException): ErrorResponse? {
        return ErrorResponse.of(GlobalExceptions.BadRequest())
    }

    /**
     * 지원하지 않은 HTTP method 호출할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    protected fun handleHttpRequestMethodNotSupportedException(
        exception: HttpRequestMethodNotSupportedException
    ): ErrorResponse? {
        return ErrorResponse.of(GlobalExceptions.MethodNotAllowed())
    }

    /**
     * Http Message를 읽지 못하는 경우 발생
     * 주로 객체를 JSON 형태로 받지 못하는 경우 발생
     */
    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected fun handleHttpMessageNotReadableException(
        exception: HttpMessageNotReadableException
    ): ErrorResponse? {
        return ErrorResponse.of(GlobalExceptions.BadRequest())
    }

    /**
     * 허용되지 않은 Null 값이 할당될 때 발생
     */
    @ExceptionHandler(NullPointerException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected fun handleNestedServletException(exception: NullPointerException): ErrorResponse? {
        return ErrorResponse.of(GlobalExceptions.BadRequest())
    }

    /**
     * 필수 파라미터가 포함되지 않았을 때 발생
     */
    @ExceptionHandler(MissingServletRequestParameterException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected fun missingServletRequestParameterException(exception: MissingServletRequestParameterException): ErrorResponse? {
        return ErrorResponse.of(exception)
    }
}
package donggi.dev.core.api.common.handler;

import donggi.dev.core.api.common.exception.ErrorCodeAndMessage;
import donggi.dev.core.api.common.exception.GolrabaException;
import donggi.dev.core.api.common.response.ApiResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final String FIELD_ERROR_FORMAT = "%s : %s /n";

    @ExceptionHandler(GolrabaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleClientException(GolrabaException exception) {
        log.debug("[golraba-exception] code = {}, message = {}, cause = {}",
            exception.getCode(), exception.getMessage(), exception.getCause());

        return ApiResponse.error(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleClientException(MethodArgumentTypeMismatchException exception) {
        log.debug("[golraba-exception] message = {}, cause = {}",
            exception.getMessage(), exception.getCause());

        return ApiResponse.error(ErrorCodeAndMessage.QUESTION_INVALID_CATEGORY.getCode(),
            ErrorCodeAndMessage.QUESTION_INVALID_CATEGORY.getMessage(), exception.getMessage());
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleNotValidException(BindException exception) {
        log.debug("[bind-exception] message = {}, cause ={}",
            exception.getMessage(), exception.getCause());

        BindingResult bindingResult = exception.getBindingResult();
        List<String> errorMessages = bindingResult.getFieldErrors()
            .stream()
            .map(fieldError -> String.format(FIELD_ERROR_FORMAT,
                fieldError.getField(),
                fieldError.getDefaultMessage())).collect(Collectors.toList());


        return ApiResponse.error(ErrorCodeAndMessage.INVALID_REQUEST.getCode(),
            ErrorCodeAndMessage.INVALID_REQUEST.getMessage(), errorMessages);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<?> handleNotValidException(Exception exception) {
        log.debug("[not-handled-exception] message= {}, cause= {}, stackTrace= {}",
            exception.getMessage(), exception.getCause(), exception.getStackTrace());

        return ApiResponse.error(ErrorCodeAndMessage.SERVER_ERROR.getCode(),
            ErrorCodeAndMessage.SERVER_ERROR.getMessage(), null);
    }

}

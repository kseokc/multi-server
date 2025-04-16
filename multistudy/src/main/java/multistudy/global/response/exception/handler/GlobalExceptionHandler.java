package multistudy.global.response.exception.handler;

import jakarta.validation.ConstraintViolationException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import multistudy.global.response.ApiResponse;
import multistudy.global.response.exception.GeneralException;
import multistudy.global.response.status.ErrorStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<Object>> handleGeneralException(GeneralException ex) {
        log.error("Error Code: {}, Message: {}, Data: {}",
                ex.getErrorStatus().getCode(),
                ex.getErrorStatus().getMessage(),
                ex.getData() != null ? ex.getData() : "No additional data",
                ex
        );

        return ResponseEntity
                .status(ex.getErrorStatus().getHttpStatus())
                .body(ApiResponse.onFailure(
                        ex.getErrorStatus(),
                        ex.getData()
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex
    ) {
        List<String> errorMessages = getValidationErrorMessages(ex);
        log.error("Validation errors: {}", errorMessages, ex);

        return ResponseEntity
                .status(ErrorStatus.BAD_REQUEST.getHttpStatus())
                .body(ApiResponse.onFailure(
                        ErrorStatus.BAD_REQUEST,
                        errorMessages
                ));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleConstraintViolationException(
            ConstraintViolationException ex
    ) {
        List<String> errorMessages = getValidationErrorMessages(ex);
        log.error("Validation errors: {}", errorMessages, ex);

        return ResponseEntity
                .status(ErrorStatus.BAD_REQUEST.getHttpStatus())
                .body(ApiResponse.onFailure(
                        ErrorStatus.BAD_REQUEST,
                        errorMessages
                ));
    }

    private List<String> getValidationErrorMessages(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> {
                    String field = fieldError.getField();       // 실패한 필드 이름
                    String message = fieldError.getDefaultMessage(); // 검증 실패 메시지
                    return field + ": " + message;
                })
                .toList();
    }

    private List<String> getValidationErrorMessages(ConstraintViolationException ex) {
        return ex.getConstraintViolations().stream()
                .map(violation -> {
                    String field = violation.getPropertyPath().toString(); // 위반된 필드
                    String message = violation.getMessage();              // 검증 실패 메시지
                    return field + ": " + message;
                })
                .toList();
    }
}
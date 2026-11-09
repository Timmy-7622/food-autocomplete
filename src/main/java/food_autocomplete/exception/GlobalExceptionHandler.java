package food_autocomplete.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Map;
import food_autocomplete.exception.DuplicateEmailException;

// RestControllerAdvice 常用於集中處理 Controller 的例外 ，並將結果寫入回應 Body
@RestControllerAdvice
public class GlobalExceptionHandler {

        // 指定要處理哪一種類型的例外
        @ExceptionHandler(MethodArgumentNotValidException.class)
        // .class用來取得某個類別的class物件
        public ResponseEntity<Map<String, String>> handleValidationException(
                        MethodArgumentNotValidException ex) {

                System.out.println(ex);
                // 取得驗證錯誤的詳細資料，從中找出錯誤訊息。
                String message = ex.getBindingResult()
                                .getFieldErrors()
                                .get(0)
                                .getDefaultMessage();

                return ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .body(Map.of("message", message));
        }

        @ExceptionHandler(DuplicateEmailException.class)
        public ResponseEntity<Map<String, String>> handleDuplicateEmail(
                        DuplicateEmailException ex) {

                return ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .body(Map.of("message", ex.getMessage()));
        }

}

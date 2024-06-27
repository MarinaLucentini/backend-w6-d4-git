package marinalucentini.backend_w6_d4.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
public class ErrorPayload {
    private String message;
    private LocalDateTime timestamp;
}

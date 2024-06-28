package marinalucentini.backend_w6_d4.exceptions;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record ErrorDto(String message, LocalDateTime time) {
}

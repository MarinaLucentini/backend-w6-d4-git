package marinalucentini.backend_w6_d4.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleBadRequest(BadRequestException ex) {
        if (ex.getErrorsList() != null) {
            // Se c'è la lista degli errori allora nel payload metterò la lista di messaggi di errore concatenati
            String message = ex.getErrorsList().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.joining(". "));
            return new ErrorDto(message, LocalDateTime.now());

        } else {
            // Se la lista degli errori è null mandiamo un classico payload di errore con semplice messaggio
            return new ErrorDto(ex.getMessage(), LocalDateTime.now());
        }

    }

    @ExceptionHandler(NotFoundException.class)

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleNotFound(NotFoundException ex) {
        return new ErrorDto(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleGenericErrors(Exception ex) {
        ex.printStackTrace();
        return new ErrorDto("Problema lato server! Giuro che lo risolveremo presto!", LocalDateTime.now());
    }
}

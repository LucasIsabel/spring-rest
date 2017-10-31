package br.com.serverstart.server.handler;
import br.com.serverstart.server.error.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleResourceNoFoundException(NotFoundException rnfException){
        ResourceNotFoundDetails resource = ResourceNotFoundDetails.Builder
                .newBuilder()
                .timestemp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resource Not Found")
                .details(rnfException.getMessage())
                .developerMessage(rnfException.getClass().getName())
                .build();
        return new ResponseEntity<>(resource, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity <?> handlerInternalserverErrorException (InternalServerErrorException isrException){
        InternalServerErrorDetails internalServerErrorDetails = InternalServerErrorDetails.InternalServerErrorDetailsBuilder
                .newBuilder()
                .timestemp(new Date().getTime())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .title("Internal Server Error")
                .details(isrException.getMessage())
                .developerMessage(isrException.getClass().getName())
                .build();
        return new ResponseEntity<>(internalServerErrorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity <Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manvException,
                                                           HttpHeaders headers,
                                                           HttpStatus status,
                                                           WebRequest request){
        List<FieldError> erros = manvException.getBindingResult().getFieldErrors();
        String field = erros.stream().map(FieldError::getField).collect(Collectors.joining(","));
        String fieldMessage = erros.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
        ValidationErrorDetails validationErrorDetails = ValidationErrorDetails.Builder
                .newBuilder()
                .timestemp(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Argumento Not Valid")
                .details("Field Validation Error")
                .developerMessage(manvException.getClass().getName())
                .field(field)
                .fieldMessage(fieldMessage).build();
        return new ResponseEntity<>(validationErrorDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetails resource = ErrorDetails.Builder
                .newBuilder()
                .timestemp(new Date().getTime())
                .status(status.value())
                .title("Internal Exception")
                .details(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .build();

        return new ResponseEntity<>(resource, headers, status);
    }

}

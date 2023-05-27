package kz.careerguidance.applicationapi.error;


import kz.careerguidance.applicationapi.exceptions.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionControllerAdvice {

  @ExceptionHandler // when Invalid Credentials
  public ResponseEntity<ErrorMessage> handleInvalidCredentialsException(
      BadCredentialsException ex) {
    return new ResponseEntity<>(
        new ErrorMessage(ex.getMessage()), HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler
  public ResponseEntity<ErrorMessage> handleAccessDeniedException(
          AccessDeniedException ex) {
    return new ResponseEntity<>(
        new ErrorMessage(ex.getMessage()), HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler
  public ResponseEntity<ErrorMessage> handleNotFoundExceptionException(
          NotFoundException ex) {
    return new ResponseEntity<>(
        new ErrorMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler
  public ResponseEntity<ErrorMessage> handleNotUniqueUsernameException(
          NotUniqueUsernameException ex) {
    return new ResponseEntity<>(
            new ErrorMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler
  public ResponseEntity<ErrorMessage> handleHttpClientErrorException(
          HttpClientErrorException ex) {
    return new ResponseEntity<>(
            new ErrorMessage(ex.getMessage()), ex.getStatusCode());
  }

  @ExceptionHandler
  public ResponseEntity<ErrorMessage> handleSpecialityCreateException(
          SpecialityCreateException ex) {
    return new ResponseEntity<>(
            new ErrorMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler
  public ResponseEntity<ErrorMessage> handleUniversityCreateException(
          UniversityCreateException ex) {
    return new ResponseEntity<>(
            new ErrorMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler
  public ResponseEntity<ErrorMessage> handleBadRequestException(
          MethodArgumentNotValidException ex) {
    String message = ex.getFieldErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.joining(", "));
    return new ResponseEntity<>(
            new ErrorMessage(message), HttpStatus.BAD_REQUEST);
  }

  @Getter
  @Setter
  @AllArgsConstructor
  static class ErrorMessage {
    private String error;
  }
}
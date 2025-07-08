package be.julienbastin.template.controllers;

import be.julienbastin.template.exception.GlobalException;
import be.julienbastin.template.exception.GreetingMessageNotFoundException;
import be.julienbastin.template.generated.dtos.InputValidationIssueDto;
import be.julienbastin.template.generated.dtos.InputValidationProblemDto;
import be.julienbastin.template.generated.dtos.ProblemDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerControllerAdvice.class);

    @ExceptionHandler(GlobalException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ProblemDto handleGlobalException(GlobalException e) {
        LOGGER.error("A global exception occurred", e);
        return new ProblemDto()
                .detail(e.getMessage())
                .title("Internal Server Error")
                .status(500);
    }

    @ExceptionHandler(GreetingMessageNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public InputValidationProblemDto handleGreetingMessageNotFoundException(GreetingMessageNotFoundException e) {
        LOGGER.warn("A greeting MessageNotFoundException occurred : {}", e.getMessage());
        return new InputValidationProblemDto()
                .detail(e.getMessage())
                .title("Greeting Message Not Found")
                .status(400)
                .addIssuesItem(new InputValidationIssueDto()
                        .in(InputValidationIssueDto.InEnum.PATH)
                        .name("key"));
    }


}

package br.com.swar.snet.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.swar.snet.model.dto.ApiErrorResponse;
import br.com.swar.snet.model.exception.ResourceNotFoundException;


@ControllerAdvice
public class ExceptionController {
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ApiErrorResponse handleResourceNotFound(ResourceNotFoundException ex) {
        return new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Recurso nao encontrado" , ex.getMessage());
    } 
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiErrorResponse handleInternalServerError(Exception ex) {
        return new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Erro interno servidor" , ex.getMessage());
    } 
	
}

package com.degloba.common.exception;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author degloba
 * 
 * @category gestior d'excepcions i estats de resposta http dels controladors d'Spring MVC
 *
 */
@ControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @Value
    public static class RestError {
        public RestError(String message2, String rootCauseMessage) {
			// TODO Auto-generated constructor stub
        	message = message2;
        	cause = rootCauseMessage;
		}
		private final String message;
        private final String cause;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public RestError handleError(Exception ex){
        log.error("[Rest Error] Error: " + ex.getCause());
        ex.printStackTrace();
        return new RestError(ex.getMessage(), ExceptionUtils.getRootCauseMessage(ex));
    }

}

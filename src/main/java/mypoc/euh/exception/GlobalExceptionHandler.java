package mypoc.euh.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value ={ Throwable.class})
    @ResponseBody
    public ResponseEntity<Void> exception(final BaseException e, final Model model) {
    	logger.error("Exception during execution of ", e);
    	//TODO : Handle response code properly
    	return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
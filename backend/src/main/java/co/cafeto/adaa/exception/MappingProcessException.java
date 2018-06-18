package co.cafeto.adaa.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MappingProcessException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MappingProcessException() {
		super("Could not trasform input into an Adjacent List");
		
	}	
}

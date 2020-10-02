package amara.api;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import amara.api.beans.LoginBean;
import amara.api.beans.RegistrationBean;
import amara.db.DBController;

@RestController
public class Controller extends ResponseEntityExceptionHandler{
	
	
	@PostMapping("/login")
	public String login(@RequestBody LoginBean request) throws SQLException, IOException {
		final String password = DBController.getPassword(request.getEmail());
		
		String results = null;
		
		if(password == null) {
			results = "email could not be found!";
		} else if (request.getPassword().equals(password)) {
			results = "Login success";
		} else {
			results = "Wrong password";
		}
		return results;
	}
	
	@PostMapping("/register")
	public String register(@RequestBody RegistrationBean request) throws SQLException, IOException {
		final String results = DBController.registerUser(request.getFirst_name(),
				request.getLast_name(), 
				request.getEmail(),
				request.getPassword());
		return results;
	}
	
	
	@ExceptionHandler(value = {NumberFormatException.class, SQLException.class})
    protected ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}

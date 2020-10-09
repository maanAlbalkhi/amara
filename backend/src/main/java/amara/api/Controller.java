package amara.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import amara.api.beans.LoginBean;
import amara.api.beans.RegistrationBean;
import amara.db.DBController;

@RestController
public class Controller extends ResponseEntityExceptionHandler{
	

	@RequestMapping("/")
    public String index() throws IOException {
        final String index = "src/main/resources/public/index.html";
        
        return new String(Files.readAllBytes(Paths.get(index)));
    }
		
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
	public String register(@RequestBody RegistrationBean request) throws SQLException, IOException{
		final String results = DBController.registerUser(request.getFirst_name(),
				request.getLast_name(), 
				request.getEmail(),
				request.getPassword());
		return results;
	}
	
	@GetMapping("/media_list")
	public String getMediaList(){
		final String media = "grippe epidemie 1957";
		return media;
	}
	
	@GetMapping("/stream")
	public StreamingResponseBody stream() throws FileNotFoundException{
		
//		final String pathName = "src/test/resources/sarahsradio.mp3";
		
		final String pathName = "src/main/resources/public/WhatsApp Video 2020-03-26 at 15.23.50.mp4";
		
//		final String pathName = "../../../Videos/I Sell the Dead.avi";
		
		final File mediaFile = new File(pathName);
		final InputStream videoFileStream = new FileInputStream(mediaFile);
		return (os) -> {
			readAndWrite(videoFileStream, os);
		};
	}
	
	private void readAndWrite(final InputStream is, OutputStream os)
			throws IOException {
		byte[] data = new byte[2048];
		int read = 0;
		while ((read = is.read(data)) > 0) {
			os.write(data, 0, read);
		}
		os.flush();
	}
	
	
	@ExceptionHandler(value = {NumberFormatException.class, SQLException.class})
    protected ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}

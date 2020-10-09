function sendJSON(){ 
			
	let result = document.querySelector('.result'); 
	let email = document.querySelector('#email'); 
	let password = document.querySelector('#password'); 
	
	// Creating a XHR object 
	let xhr = new XMLHttpRequest(); 
	let url = "http://localhost:8080/login";

	// open a connection 
	xhr.open("POST", url, true); 

	// Set the request header i.e. which type of content you are sending 
	xhr.setRequestHeader("Content-Type", "application/json"); 

	// Create a state change callback 
	xhr.onreadystatechange = function () { 
		if (xhr.readyState === 4 && xhr.status === 200) { 

			if (this.responseText == "Login success") {
				
				window.location.replace("media_list.html");
				getMediaList();
			}

			// Print received data from server 
			result.innerHTML = this.responseText;
			
			

		} 
	}; 

	// Converting JSON data to string 
	var data = JSON.stringify({ "email": email.value, "password": password.value }); 

	// Sending data with the request 
	xhr.send(data); 
}
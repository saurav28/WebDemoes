package org.saurav.springbootdemo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class HTTPRequestTesterController {

	@RequestMapping("/")
	public String index() {
		return "Greetings from the Spring Demo application!";
	}
	
	/**
	 * Send the get request
	 * @param body
	 * @return
	 */
	@GetMapping("/get")
	public String get(@RequestBody String body) {

		return "Welcome to the get method. Printing whatever is present in the body " + body;
	}
	
	/**
	 * Sending plain raw text in the request body
	 * @param body
	 * @return
	 */
	@PostMapping("/postBodyString")
	public String postBodyAsString(@RequestBody String body) {

		return "Welcome to the get method. Printing whatever is present in the body " + body;
	}

	/**
	 * Read whatever is passed to the request to a file. To be used when a raw
	 * binary file is sent in the request body
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/postBodyBinary")
	public String postBodyAsBinary(HttpServletRequest request) {

		try {
			Files.copy(request.getInputStream(),
					Paths.get("/Users/i054564/OneDrive - SAP SE/Documents/Work/Misc Tech/HTTPTest/filename"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "Welcome to the get method. Printing whatever is present in the body " + request.toString();
	}

	/**
	 * Read the request body sent in form url encoded manner.
	 * 
	 * @param formData
	 * @return
	 */
	@PostMapping(value = "/postformurlencoded", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String postFormURLEncoded(@RequestBody MultiValueMap<String, String> formData) {

		return "Welcome to the post method with form url encoded. Printing whatever is present in the body " + formData;
	}

	/**
	 * Read the multi part data sent in the request. Multi part may mean sending
	 * data in multiple parts like one part can have text value and the other part
	 * can have file being sent.
	 * 
	 * @param formData
	 * @param file
	 * @return
	 */
	@PostMapping(value = "/postmultipartformdata", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String postFormData(@RequestParam MultiValueMap<String, String> formData,
			@RequestParam("file") MultipartFile file) {

		return "Welcome to the post method with multi part form data. Printing whatever is present in the body "
				+ formData + "file " + file;
	}
}

package com.arzds.Serice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.arzds.model.ConfirmationResponse;
import com.arzds.model.ConfirmationToken;
import com.arzds.model.RegistrationReqBody;
import com.arzds.model.RegistrationResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CoVaccineService {
	@Value("${cowin.url}")
	private String url;
	RestTemplate restTemplate = new RestTemplate();

	public RegistrationResponse registration(RegistrationReqBody body) {
		RegistrationResponse registrationResponse = null;
		String apiUrl = url + "v2/auth/public/generateOTP";
		try {
			ObjectMapper mapper = new ObjectMapper();
			String resuestData = mapper.writeValueAsString(body);
			HttpEntity entity = new HttpEntity<>(resuestData, getHeaders());

			registrationResponse = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, RegistrationResponse.class)
					.getBody();
			// System.out.println(response);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return registrationResponse;
	}

	public ConfirmationResponse confirm(ConfirmationToken body) {
		ConfirmationResponse confirmationResponse = null;
		String apiUrl = url + "v2/auth/public/confirmOTP";
		try {
			ObjectMapper mapper = new ObjectMapper();
			String resuestData = mapper.writeValueAsString(body);
			HttpEntity entity = new HttpEntity<>(resuestData, getHeaders());

			confirmationResponse = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, ConfirmationResponse.class)
					.getBody();
			// System.out.println(response);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return confirmationResponse;
	}

	private HttpHeaders getHeaders() {
		HttpHeaders headers = new org.springframework.http.HttpHeaders();
		// headers.add("Autorization", "Bearer Dheerendra");
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.add("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0");
		return headers;
	}
}

package com.arzds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arzds.Serice.CoVaccineService;
import com.arzds.model.ConfirmationResponse;
import com.arzds.model.ConfirmationToken;
import com.arzds.model.RegistrationReqBody;
import com.arzds.model.RegistrationResponse;

@RestController
@RequestMapping("/cowin")
public class CoVaccineController {
	@Autowired
	private CoVaccineService coVaccineService;

	@PostMapping
	@RequestMapping("/registration")
	public RegistrationResponse registration(@RequestBody RegistrationReqBody body) {
		return coVaccineService.registration(body);
	}

	@PostMapping
	@RequestMapping("/confirm")
	public ConfirmationResponse confirm(@RequestBody ConfirmationToken body) {
		return coVaccineService.confirm(body);
	}

}
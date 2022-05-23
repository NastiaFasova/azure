package com.epam.demo.captcha;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReCaptchaResponse {
	private boolean success;
	private String hostname;
	private String action;
	private float score;
	private String challenge_ts;

	@JsonProperty("error-codes")
	private String[] errorCodes;
}

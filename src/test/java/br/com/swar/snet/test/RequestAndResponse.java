package br.com.swar.snet.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;

@Getter
public class RequestAndResponse<Request, Response> {
	
	private String request;
	private String response;
	private Request originalRequest;
	private Response originalResponse;
	
	public RequestAndResponse(Request originalRequest, Response originalResponse) {
		this.originalRequest = originalRequest;
		this.originalResponse = originalResponse;
	}
	
	public RequestAndResponse<Request, Response> build() throws JsonProcessingException {
		this.request = toJson(originalRequest);
		this.response = toJson(originalResponse);
		
		return this;
	}
	
	private String toJson(Object o) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(o);
	}
}

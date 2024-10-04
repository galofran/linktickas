package com.proyecto.springboot.app.seguridad;


public class JWTAuthResponseDto {

	private String tokenDeAcceso;
	private String tipoDeToken = "Bearer";

	public JWTAuthResponseDto(String tokenDeAcceso) {
		super();
		this.tokenDeAcceso = tokenDeAcceso;
	}

	public JWTAuthResponseDto(String tokenDeAcceso, String tipoDeToken) {
		super();
		this.tokenDeAcceso = tokenDeAcceso;
		this.tipoDeToken = tipoDeToken;
	}

	public String getTokenDeAcceso() {
		return tokenDeAcceso;
	}

	public void setTokenDeAcceso(String tokenDeAcceso) {
		this.tokenDeAcceso = tokenDeAcceso;
	}

	public String getTipoDeToken() {
		return tipoDeToken;
	}

	public void setTipoDeToken(String tipoDeToken) {
		this.tipoDeToken = tipoDeToken;
	}

	
}

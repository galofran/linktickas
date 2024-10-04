package com.proyecto.springboot.app.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.springboot.app.dto.FormularioContacto;

@RestController
@RequestMapping("/enviarCorreo")
public class EnviarCorreo {

	@Autowired
	private JavaMailSender mail;
	
	@PostMapping()
	public ResponseEntity<?> enviarCorreo(@RequestBody FormularioContacto formulario){
		
		String nombre = formulario.getNombre();
		String apellidos = formulario.getApellidos();
		String correo = formulario.getCorreo();
		String descripcion = formulario.getDescripcion();
		
		
		enviarCorreo(nombre.toUpperCase() + " " +apellidos.toUpperCase() + " tiene una petición",descripcion+ ", mi correo es: " + correo);
		
		return ResponseEntity.ok("Se envió el mensaje");
	}
	
	
	public void enviarCorreo(String titulo, String contenido) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setFrom("linktickasusuarioadm@gmail.com");
		email.setTo("linktickasusuarioadm@gmail.com");
		email.setSubject(titulo);
		email.setText(contenido);
		
		
		mail.send(email);
	}
}

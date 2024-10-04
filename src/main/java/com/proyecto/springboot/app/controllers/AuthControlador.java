package com.proyecto.springboot.app.controllers;

import java.sql.Date;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.springboot.app.dto.EmpresaDTO;
import com.proyecto.springboot.app.dto.EmpresaRegistroDTO;
import com.proyecto.springboot.app.dto.EstudianteDTO;
import com.proyecto.springboot.app.dto.EstudianteRegistroDTO;
import com.proyecto.springboot.app.dto.GradoSuperiorDTO;
import com.proyecto.springboot.app.dto.InstitutoDTO;
import com.proyecto.springboot.app.dto.LoginDto;
import com.proyecto.springboot.app.dto.RegistroDto;
import com.proyecto.springboot.app.entity.GradoSuperior;
import com.proyecto.springboot.app.entity.Instituto;
import com.proyecto.springboot.app.entity.Rol;
import com.proyecto.springboot.app.entity.Usuario;
import com.proyecto.springboot.app.repository.RolRepositorio;
import com.proyecto.springboot.app.repository.UsuarioRepositorio;
import com.proyecto.springboot.app.seguridad.JWTAuthResponseDto;
import com.proyecto.springboot.app.seguridad.JwtTokenProvider;
import com.proyecto.springboot.app.service.EmpresaService;
import com.proyecto.springboot.app.service.EstudianteService;

@RestController
@RequestMapping("/linktickas/auth")
public class AuthControlador {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private RolRepositorio rolRepositorio;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private EstudianteService estudianteService;
	
	@Autowired
	private JavaMailSender mail;
	


	/*
	@Autowired 
	private JavaMailSender mail;
	 */

	Logger logger = LoggerFactory.getLogger(AuthControlador.class);

	@PostMapping("/iniciarSesion")

	public ResponseEntity<JWTAuthResponseDto> authenticateUser(@RequestBody LoginDto loginDTO) {

		logger.info("INICIAR SESION");

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		// obtenemos el token del jwtTokenProvider
		String token = jwtTokenProvider.generarToken(authentication);

		return ResponseEntity.ok(new JWTAuthResponseDto(token));
	}

	@PostMapping("/registro-admin")
	public ResponseEntity<?> registrarAdmin(@RequestBody RegistroDto registroDto) {

		if (usuarioRepositorio.existsByEmail(registroDto.getEmail())) {
			return new ResponseEntity<>("Ese correo ya existe", HttpStatus.BAD_REQUEST);
		}

		Usuario admin = new Usuario();

		admin.setEmail(registroDto.getEmail());
		admin.setPassword(passwordEncoder.encode(registroDto.getPassword()));

		Rol roles = rolRepositorio.findByNombre("ROLE_ADMIN").get();
		admin.setRoles(Collections.singleton(roles));

		usuarioRepositorio.save(admin);

		//enviarCorreo("Registro exitoso.","Bienvenido "+ registroDto.getEmail(),", te has dado de alta satisfactoriamente");

		return new ResponseEntity<>("Administrador registrado existosamente", HttpStatus.OK);
	}
/*
	@PostMapping("/registro-estudiante")
	public ResponseEntity<?> registrarUsuario(@RequestBody EstudianteRegistroDTO registroDto) {

		if (usuarioRepositorio.existsByEmail(registroDto.getEmail())) {
			return new ResponseEntity<>("Ese correo ya existe", HttpStatus.BAD_REQUEST);
		}

		

		EstudianteDTO estudianteDto = new EstudianteDTO();
		estudianteDto.setNombre(registroDto.getNombre());
		estudianteDto.setApellido(registroDto.getApellido());
		estudianteDto.setEdad(registroDto.getEdad());
		estudianteDto.setEmail(registroDto.getEmail());
		estudianteDto.setTelefono(registroDto.getTelefono());
		estudianteDto.setDireccion(registroDto.getDireccion());
		
		estudianteDto.setInstitutoDto(new InstitutoDTO(registroDto.getInstitutoDto()));
		estudianteDto.setGradoSuperiorDto(new GradoSuperiorDTO(registroDto.getGradoSuperiorDto()));
		
		logger.info("INFOOOOO "+ estudianteDto);
		
		estudianteService.save(estudianteDto);

		Usuario usuario = new Usuario();

		usuario.setEmail(registroDto.getEmail());
		usuario.setPassword(passwordEncoder.encode(registroDto.getPassword()));

		Rol roles = rolRepositorio.findByNombre("ROLE_ESTUDIANTE").get();
		usuario.setRoles(Collections.singleton(roles));

		usuarioRepositorio.save(usuario);

		//enviarCorreo(registroDto.getEmail());

		return new ResponseEntity<>("Estudiante registrado existosamente", HttpStatus.OK);
	}*/
	
	@PostMapping("/registro-estudiante")
	public ResponseEntity<?> registrarUsuario(@RequestBody EstudianteRegistroDTO registroDto) {

		if (usuarioRepositorio.existsByEmail(registroDto.getEmail())) {
			return new ResponseEntity<>("Ese correo ya existe", HttpStatus.BAD_REQUEST);
		}

		Instituto ins = new Instituto();
		
		ins.setId(registroDto.getInstitutoDto().getId());
		ins.setNombre(registroDto.getInstitutoDto().getNombre());
		ins.setCiudad(registroDto.getInstitutoDto().getCiudad());
		ins.setUbicacion(registroDto.getInstitutoDto().getUbicacion());
		
		GradoSuperior gs = new GradoSuperior();
		gs.setId(registroDto.getGradoSuperiorDto().getId() );
		gs.setInstituto(ins);
		gs.setNombre(registroDto.getGradoSuperiorDto().getNombre());
		gs.setSeccion(registroDto.getGradoSuperiorDto().getSeccion());
		

		EstudianteDTO estudianteDto = new EstudianteDTO();
		estudianteDto.setNombre(registroDto.getNombre());
		estudianteDto.setApellido(registroDto.getApellido());
		estudianteDto.setEdad(registroDto.getEdad());
		estudianteDto.setEmail(registroDto.getEmail());
		estudianteDto.setTelefono(registroDto.getTelefono());
		estudianteDto.setDireccion(registroDto.getDireccion());
		
		estudianteDto.setInstitutoDto(new InstitutoDTO(ins));
		estudianteDto.setGradoSuperiorDto(new GradoSuperiorDTO(gs));
				
		estudianteService.save(estudianteDto);

		Usuario usuario = new Usuario();

		usuario.setEmail(registroDto.getEmail());
		usuario.setPassword(passwordEncoder.encode(registroDto.getPassword()));

		Rol roles = rolRepositorio.findByNombre("ROLE_ESTUDIANTE").get();
		usuario.setRoles(Collections.singleton(roles));

		usuarioRepositorio.save(usuario);

		//enviarCorreo(registroDto.getEmail());

		return new ResponseEntity<>("Estudiante registrado existosamente", HttpStatus.OK);
	}

	@PostMapping("/registro-empresa")
	public ResponseEntity<?> registrarEmpresa(@RequestBody EmpresaRegistroDTO registroDto) {

		if (usuarioRepositorio.existsByEmail(registroDto.getEmail())) {
			return new ResponseEntity<>("Ese correo ya existe", HttpStatus.BAD_REQUEST);
		}

		EmpresaDTO empresa = new EmpresaDTO();
		empresa.setNombre(registroDto.getNombre());
		empresa.setCiudad(registroDto.getCiudad());
		empresa.setEmail(registroDto.getEmail());
		empresa.setUbicacion(registroDto.getUbicacion());
		empresa.setTelefono(registroDto.getTelefono());

		empresaService.save(empresa);
		
		Usuario usuario = new Usuario();

	
		usuario.setEmail(registroDto.getEmail());
		usuario.setPassword(passwordEncoder.encode(registroDto.getPassword()));
		
		Rol roles = rolRepositorio.findByNombre("ROLE_EMPRESA").get();
		usuario.setRoles(Collections.singleton(roles));

		usuarioRepositorio.save(usuario);

		enviarCorreo(registroDto.getEmail());
		
		return new ResponseEntity<>("Empresa registrada existosamente", HttpStatus.OK);
	}

	

	
	public void enviarCorreo(String correo) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setFrom("linktickasusuarioadm@gmail.com");
		email.setSubject("Registro exitoso");
		email.setTo(correo);
		email.setText("Tus datos se han registrado correctamente. Bienvenido " + correo);
		
		mail.send(email);
	}
	 
}

package tp.react.back.tpreactback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tp.react.back.tpreactback.modelo.Rol;
import tp.react.back.tpreactback.modelo.Usuario;
import tp.react.back.tpreactback.repository.IUsuarioRepository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class TpReactBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpReactBackApplication.class, args);
	}

	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Bean
	public CommandLineRunner init() {
		return args -> {
			Usuario admin = new Usuario();
			admin.setNombreUsuario("admin");
			admin.setClave(MD5Encriptador("123456"));
			admin.setRol(Rol.ADMIN);

			Usuario operador = new Usuario();
			operador.setNombreUsuario("operador");
			operador.setClave(MD5Encriptador("123456"));
			operador.setRol(Rol.OPERADOR);

			Usuario visor = new Usuario();
			visor.setNombreUsuario("visor");
			visor.setClave(MD5Encriptador("123456"));
			visor.setRol(Rol.VISOR);

			this.usuarioRepository.save(admin);
			this.usuarioRepository.save(operador);
			this.usuarioRepository.save(visor);
		};
	}

	private String MD5Encriptador(String clave) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(clave.getBytes());
			StringBuilder sb = new StringBuilder();
			for (byte b : digest) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}

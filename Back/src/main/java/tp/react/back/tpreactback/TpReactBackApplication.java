package tp.react.back.tpreactback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tp.react.back.tpreactback.modelo.Categoria;
import tp.react.back.tpreactback.modelo.Instrumento;
import tp.react.back.tpreactback.modelo.Rol;
import tp.react.back.tpreactback.modelo.Usuario;
import tp.react.back.tpreactback.repository.ICategoriaRepository;
import tp.react.back.tpreactback.repository.IInstrumentoRepository;
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

	@Autowired
	private ICategoriaRepository categoriaRepository;

	@Autowired
	private IInstrumentoRepository instrumentoRepository;

	@Bean
	public CommandLineRunner init() {
		return args -> {
			//Crear Usuarios
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

			//Crear Categorias
			Categoria cat1 = new Categoria();
			cat1.setCodigo(100);
			cat1.setDenominacion("Cuerdas");

			Categoria cat2 = new Categoria();
			cat2.setCodigo(200);
			cat2.setDenominacion("Vientos");

			Categoria cat3 = new Categoria();
			cat3.setCodigo(300);
			cat3.setDenominacion("Percusion");

			Categoria cat4 = new Categoria();
			cat4.setCodigo(400);
			cat4.setDenominacion("Teclado");

			Categoria cat5 = new Categoria();
			cat5.setCodigo(500);
			cat5.setDenominacion("Electronico");

			this.categoriaRepository.save(cat1);
			this.categoriaRepository.save(cat2);
			this.categoriaRepository.save(cat3);
			this.categoriaRepository.save(cat4);
			this.categoriaRepository.save(cat5);

			//Crear Instrumentos
			Instrumento instr1 = new Instrumento();
			instr1.setInstrumento("Mandolina Instrumento Musical Stagg Sunburst");
			instr1.setMarca("Stagg");
			instr1.setModelo("M20");
			instr1.setImagen("https://imgs.search.brave.com/ogGVBn9HHeWLw95m1aWXHPoBreXdhiHSH5KkUbJqw_g/rs:fit:860:0:0/g:ce/aHR0cHM6Ly9pbWFn/ZXMtbmEuc3NsLWlt/YWdlcy1hbWF6b24u/Y29tL2ltYWdlcy9J/LzUxc3NoWFExeWlM/LmpwZw");
			instr1.setCategoria(cat1);
			instr1.setPrecio(2450);
			instr1.setCostoEnvio("G");
			instr1.setCantidadVendida(28);
			instr1.setDescripcion("Estas viendo una excelente mandolina de la marca Stagg, con un sonido muy dulce, tapa aros y fondo de tilo, y diapasón de palisandro. Es un instrumento acústico (no se enchufa) de cuerdas dobles (4 pares) con la caja ovalada y cóncava, y el mástil corto. Su utilización abarca variados ámbitos, desde rock, folk, country y ensambles experimentales.");

			Instrumento instr2 = new Instrumento();
			instr2.setInstrumento("Pandereta Pandero Instrumento Musical");
			instr2.setMarca("DyM ventas");
			instr2.setModelo("32 sonajas");
			instr2.setImagen("https://imgs.search.brave.com/DCLOyIOqgi43-hAguNcADVcbN4zNcAzgf1QgunIUVQg/rs:fit:860:0:0/g:ce/aHR0cHM6Ly9tdXNp/Y2FzZW5jaWxsYS5j/b20vd3AtY29udGVu/dC91cGxvYWRzL3By/ZXZpZXctMTAyNHgx/MDI0LmpwZw");
			instr2.setCategoria(cat3);
			instr2.setPrecio(325);
			instr2.setCostoEnvio("150");
			instr2.setCantidadVendida(10);
			instr2.setDescripcion("1 Pandereta - 32 sonajas metálicas. Más de 8 años vendiendo con 100 % de calificaciones POSITIVAS y clientes satisfechos !!");

			Instrumento instr3 = new Instrumento();
			instr3.setInstrumento("Triangulo Musical 24 Cm Percusion");
			instr3.setMarca("LBP");
			instr3.setModelo("24");
			instr3.setImagen("https://imgs.search.brave.com/zfTYEi1SQyUJ9p6cTRzfeK5_xXCJzbeT0bl4QIGbgZk/rs:fit:860:0:0/g:ce/aHR0cHM6Ly93d3cu/aW5zdHJ1bWVudG9z/ZGVwZXJjdXNpb24u/Y29tL3dwLWNvbnRl/bnQvdXBsb2Fkcy8y/MDE5LzEwL3RyaWFu/Z3Vsby0xLmpwZw");
			instr3.setCategoria(cat3);
			instr3.setPrecio(260);
			instr3.setCostoEnvio("250");
			instr3.setCantidadVendida(3);
			instr3.setDescripcion("Triangulo Musical de 24 Centímetros De Acero. ENVIOS POR CORREO O ENCOMIENDA: Se le deberán adicionar $40 en concepto de Despacho y el Costo del envío se abonará al recibir el producto en Terminal, Sucursal OCA o Domicilio");

			Instrumento instr4 = new Instrumento();
			instr4.setInstrumento("Bar Chimes Lp Cortina Musical 72 Barras");
			instr4.setMarca("FM");
			instr4.setModelo("LATIN");
			instr4.setImagen("https://imgs.search.brave.com/nLHm37dQcWS18FPWOBonDy_PEdVMw6oJweePwG326bU/rs:fit:860:0:0/g:ce/aHR0cHM6Ly9odHRw/Mi5tbHN0YXRpYy5j/b20vRF9OUV9OUF82/NTQ5NjUtTUxBNDQ2/MTY5ODI4MTRfMDEy/MDIxLU8ud2VicA");
			instr4.setCategoria(cat3);
			instr4.setPrecio(2250);
			instr4.setCostoEnvio("G");
			instr4.setCantidadVendida(2);
			instr4.setDescripcion("BARCHIME CORTINA MUSICAL DE 25 BARRAS LATIN CUSTOM. Emitimos factura A y B");

			Instrumento instr5 = new Instrumento();
			instr5.setInstrumento("Shekeres. Instrumento. Música. Artesanía.");
			instr5.setMarca("Azalea Artesanías");
			instr5.setModelo("Cuentas de madera");
			instr5.setImagen("https://imgs.search.brave.com/_9KVxyLzOnqrXFSZPIs4ut9XSs9vNHg_POg-XPhDMd4/rs:fit:860:0:0/g:ce/aHR0cHM6Ly9pLnBp/bmltZy5jb20vb3Jp/Z2luYWxzLzRiL2Y4/L2M4LzRiZjhjODdk/MWM5NzA1N2VjOWRj/MTllZDk3NzUyZjEw/LmpwZw");
			instr5.setCategoria(cat3);
			instr5.setPrecio(850);
			instr5.setCostoEnvio("300");
			instr5.setCantidadVendida(5);
			instr5.setDescripcion("Las calabazas utilizadas para nuestras artesanías son sembradas y cosechadas por nosotros, quienes seleccionamos el mejor fruto para garantizar la calidad del producto y ofrecerle algo creativo y original.");

			Instrumento instr6 = new Instrumento();
			instr6.setInstrumento("Antiguo Piano Aleman Con Candelabros.");
			instr6.setMarca("Neumeyer");
			instr6.setModelo("Stratus");
			instr6.setImagen("https://imgs.search.brave.com/454YJsZxH95HvXTA_yzl8c6TyajPRj-ve3Ey7-OKc_I/rs:fit:860:0:0/g:ce/aHR0cHM6Ly9pbWcu/Y2xhc2YubXgvMjAy/MC8xMC8wOS9QSUFO/Ty1BTEVNTi0yMDIw/MTAwOTA3NTIyNS4x/NjMyNTkwMDE1Lmpw/Zw");
			instr6.setCategoria(cat4);
			instr6.setPrecio(17000);
			instr6.setCostoEnvio("2000");
			instr6.setCantidadVendida(2);
			instr6.setDescripcion("Buen dia! Sale a la venta este Piano Alemán Neumeyer con candelabros incluidos. Tiene una talla muy bonita en la madera. Una pieza de calidad.");

			Instrumento instr7 = new Instrumento();
			instr7.setInstrumento("Guitarra Ukelele Infantil Grande 60cm");
			instr7.setMarca("GUITARRA");
			instr7.setModelo("UKELELE");
			instr7.setImagen("https://imgs.search.brave.com/KGz5kZDyRHiZ0B_mCWzwnD_EAJizTjdxxJguH9-TXMI/rs:fit:860:0:0/g:ce/aHR0cHM6Ly9zaW5m/b25pYW9ubGluZS5l/cy9ibG9nL3dwLWNv/bnRlbnQvdXBsb2Fk/cy8yMDE4LzA4L3Vr/ZWxlbGUtY29uY2ll/cnRvLmpwZw");
			instr7.setCategoria(cat1);
			instr7.setPrecio(500);
			instr7.setCostoEnvio("G");
			instr7.setCantidadVendida(5);
			instr7.setDescripcion("Material: Plástico smil madera 4 Cuerdas longitud: 60cm, el mejor regalo para usted, su familia y amigos, adecuado para 3-18 años de edad.");

			Instrumento instr8 = new Instrumento();
			instr8.setInstrumento("Teclado Organo Electronico Musical Instrumento 54 Teclas");
			instr8.setMarca("GADNIC");
			instr8.setModelo("T01");
			instr8.setImagen("https://imgs.search.brave.com/oOY7MKuWB5hV2QcLI7cdEA1de28n8uJ3krc6RTGGEoc/rs:fit:860:0:0/g:ce/aHR0cHM6Ly9sYXRp/bm11c2ljLnBlL3dw/LWNvbnRlbnQvdXBs/b2Fkcy9rZXlib2Fy/ZC1wcm81NDMwXzEu/cG5n");
			instr8.setCategoria(cat5);
			instr8.setPrecio(2250);
			instr8.setCostoEnvio("G");
			instr8.setCantidadVendida(1375);
			instr8.setDescripcion("Organo Electrónico GADNIC T01. Display de Led. 54 Teclas. 100 Timbres / 100 Ritmos. 4 1/2 octavas. 8 Percusiones. 8 Canciones de muestra. Grabación y reproducción. Entrada para Micrófono. Salida de Audio (Auriculares / Amplificador). Vibrato. Sustain Incluye Atril Apoya partitura y Micrófono. Dimensiones: 84,5 x 32,5 x 11 cm");

			Instrumento instr9 = new Instrumento();
			instr9.setInstrumento("Instrumentos De Percusión Niños Set Musical Con Estuche");
			instr9.setMarca("KNIGHT");
			instr9.setModelo("LB17");
			instr9.setImagen("https://imgs.search.brave.com/cDMX8MFTsQK0POInmhY-D93QCS_--OXA9d0gaAVI--U/rs:fit:860:0:0/g:ce/aHR0cHM6Ly9tLm1l/ZGlhLWFtYXpvbi5j/b20vaW1hZ2VzL0kv/NzFlNEF4Ui1mb0wu/anBn");
			instr9.setCategoria(cat3);
			instr9.setPrecio(2700);
			instr9.setCostoEnvio("300");
			instr9.setCantidadVendida(15);
			instr9.setDescripcion("Estas viendo un excelente y completísimo set de percusion para niños con estuche rígido, equipado con los instrumentos mas divertidos! De gran calidad y sonoridad. Ideal para jardines, escuelas primarias, musicoterapeutas o chicos que se quieran iniciar en la música de la mejor manera. Es un muy buen producto que garantiza entretenimiento en cualquier casa o reunión, ya que esta equipado para que varias personas al mismo tiempo estén tocando un instrumento.");

			Instrumento instr10 = new Instrumento();
			instr10.setInstrumento("Batería Musical Infantil Juguete Niño 9 Piezas Palillos");
			instr10.setMarca("Bateria");
			instr10.setModelo("Infantil");
			instr10.setImagen("https://imgs.search.brave.com/fCemcdO838CyFawCgrM9k-WjHFiRqxtz8m6ZhaDIaI4/rs:fit:860:0:0/g:ce/aHR0cHM6Ly9jZG4u/d2FsbGFwb3AuY29t/L2ltYWdlcy8xMDQy/MC9ncS9vNS9fXy9j/MTA0MjBwMTAxMjI1/NTgwMi9pNDkyNjE2/NjA3OS5qcGc_cGlj/dHVyZVNpemU9VzMy/MA");
			instr10.setCategoria(cat3);
			instr10.setPrecio(850);
			instr10.setCostoEnvio("250");
			instr10.setCantidadVendida(380);
			instr10.setDescripcion("DE 1 A 3 AÑOS. EL SET INCLUYE 5 TAMBORES, PALILLOS Y EL PLATILLO TAL CUAL LAS FOTOS. SONIDOS REALISTAS Y FÁCIL DE MONTAR. MEDIDAS: 40X20X46 CM.");

			this.instrumentoRepository.save(instr1);
			this.instrumentoRepository.save(instr2);
			this.instrumentoRepository.save(instr3);
			this.instrumentoRepository.save(instr4);
			this.instrumentoRepository.save(instr5);
			this.instrumentoRepository.save(instr6);
			this.instrumentoRepository.save(instr7);
			this.instrumentoRepository.save(instr8);
			this.instrumentoRepository.save(instr9);
			this.instrumentoRepository.save(instr10);
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
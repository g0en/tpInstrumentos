package tp.react.back.tpreactback.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import tp.react.back.tpreactback.modelo.Instrumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp.react.back.tpreactback.repository.IInstrumentoRepository;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class InstrumentoService {

    @Autowired
    private IInstrumentoRepository instruRepos;

    public  List<Instrumento> getInstrumento(){
        List<Instrumento> listaInstrumento = instruRepos.findAll();
        return listaInstrumento;
    }

    public Instrumento obtenerInstrumento(long id){
        return instruRepos.findById(id);
    }

    public void cargarInstrumentosDesdeJson(String rutaArchivo) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Lee el archivo JSON y convierte sus datos en una lista de objetos Instrumento
            Instrumento[] instrumentos = objectMapper.readValue(new File(rutaArchivo), Instrumento[].class);
            // Guarda los instrumentos en la base de datos
            instruRepos.saveAll(Arrays.asList(instrumentos));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Instrumento guardarInstrumento(Instrumento instrumento){
        return instruRepos.save(instrumento);
    }

    public Instrumento modificarInstrumento(Instrumento instrumento){
    Instrumento instrumentoNuevo = instruRepos.findById(instrumento.getId());

    if(instrumento.getId() != 0 ){
        instrumentoNuevo.setId(instrumento.getId());
    }

    if(instrumento.getInstrumento() != null && !instrumento.getInstrumento().isEmpty()){
        instrumentoNuevo.setInstrumento(instrumento.getInstrumento());
    }
    if (instrumento.getMarca() != null && !instrumento.getMarca().isEmpty()){
        instrumentoNuevo.setMarca(instrumento.getMarca());
    }

    if(instrumento.getModelo() != null && !instrumento.getModelo().isEmpty()){
        instrumentoNuevo.setModelo(instrumento.getModelo());
    }

    if(instrumento.getPrecio() != 0 ){
        instrumentoNuevo.setPrecio(instrumento.getPrecio());
    }

    if(instrumento.getCostoEnvio() != null && !instrumento.getCostoEnvio().isEmpty()){
        instrumentoNuevo.setCostoEnvio(instrumento.getCostoEnvio());
    }

    if(instrumento.getCantidadVendida() != 0 ){
        instrumentoNuevo.setCantidadVendida(instrumento.getCantidadVendida());
    }

    if (instrumento.getDescripcion() != null && !instrumento.getDescripcion().isEmpty()){
        instrumentoNuevo.setDescripcion(instrumento.getDescripcion());
    }

    if (instrumento.getImagen() != null && !instrumento.getImagen().isEmpty()){
        instrumentoNuevo.setImagen(instrumento.getImagen());
    }

    if (instrumento.getCategoria() != null) {
        instrumentoNuevo.setCategoria(instrumento.getCategoria());
    }

    return instruRepos.save(instrumentoNuevo);
}

    public void eliminarInstrumento(long id){
        instruRepos.deleteById(id);
    }


}

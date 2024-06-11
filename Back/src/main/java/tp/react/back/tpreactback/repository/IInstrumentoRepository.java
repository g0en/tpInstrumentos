package tp.react.back.tpreactback.repository;


import tp.react.back.tpreactback.modelo.Instrumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInstrumentoRepository extends JpaRepository <Instrumento,Long> {

    Instrumento findById(long id);

}

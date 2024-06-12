import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import Instrumento from "../entidades/Instrumento";
import { getInstrumentoXIdFetch } from "../servicios/FuncionesApi";
import "../componentes/css/ItemInstrumento.css";
import { Button } from "@chakra-ui/react";
import Usuario from "../entidades/Usuario";
import { Roles } from "../entidades/Roles";

function DetalleInstrumento() {
  const { idInstrumento } = useParams();
  const [instrumento, setInstrumento] = useState<Instrumento | null>(null);
  const [jsonUsuario, setJSONUsuario] = useState<any>(localStorage.getItem('usuario'))
  const usuarioLogueado: Usuario = JSON.parse(jsonUsuario) as Usuario;

  const getInstrumento = async () => {
    const instrumentoSelect: Instrumento = await getInstrumentoXIdFetch(Number(idInstrumento));
    setInstrumento(instrumentoSelect);
  };

  const handleGenerarPDF = async () => {
    try {
      const response = await fetch(`http://localhost:8080/Reporte/pdf/${idInstrumento}`);
      if (response.ok) {
        const blob = await response.blob();
        const url = window.URL.createObjectURL(blob);
        window.open(url);
      } else {
        console.error('Error al generar el PDF:', response.statusText);
      }
    } catch (error) {
      console.error('Error al generar el PDF:', error);
    }
  };

  useEffect(() => {
    getInstrumento();
  }, [idInstrumento]);

  function renderCostoEnvio() {
    if (instrumento?.costoEnvio === "G") {
      return (
        <span style={{ color: "green" }}>
          <i className="fas fa-car" style={{ opacity: 0.5 }}></i>
          <img src={"/images/camion.png"} alt="Descripción de la imagen" style={{ width: '20px', height: '20px' }} />
          Envio gratis
        </span>
      );
    } else {
      return (
        <span style={{ color: "orange" }}>
          Costo de envio interior de Argentina: ${instrumento?.costoEnvio}
        </span>
      );
    }
  }

  const getImageUrl = (imagePath: string) => {
    // Asume que las imágenes locales están en la carpeta public/images
    return imagePath.startsWith("http") ? imagePath : `/images/${imagePath}`;
  };

  return (
    <>
      {instrumento && (
        <div className="card mb-3">
          <div className="row g-0">
            <div className="col-md-6 card-body">
              <img
                src={getImageUrl(instrumento.imagen)}
                className="card-img-top img-altura"
                alt={instrumento.imagen}
              />
              <h6>Descripción</h6>
              <p className="card-text">{instrumento.descripcion}</p>
            </div>
            <div
              className="col-md-6"
              style={{ borderLeft: "1px solid rgba(0,0,0,0.1)" }}
            >
              <div className="card-body">
                <p className="card-text">{instrumento.cantidadVendida} vendidos</p>
                <h6 className="card-title instrumento-title">
                  <strong>{instrumento.instrumento}</strong>
                </h6>
                <p className="card-text precio-text">
                  <strong>${instrumento.precio}</strong>
                </p>
                <div className="marca-modelo">
                  <p className="card-text">
                    <strong>Marca: {instrumento.marca}</strong>
                  </p>
                  <p className="card-text">
                    <strong>Modelo: {instrumento.modelo}</strong>
                  </p>
                </div>
                <h6 className="costo-envio">
                  <strong>Costo Envio:</strong>
                </h6>
                <p className="card-text">{renderCostoEnvio()}</p>
              </div>
              {
                (usuarioLogueado.rol === Roles.ADMIN || usuarioLogueado.rol === Roles.OPERADOR) ?
                  <Button
                    className="btn btnDanger"
                    sx={{ mt: 2, width: '100%', borderRadius: '4px'}}
                    variant="outlined"
                    onClick={handleGenerarPDF}
                  >
                    Generar PDF
                  </Button>
                  :
                  <></>
              }
              <div className="card-footer text-body-secondary">
                <a href="/menu">
                  <button type="button" className="btn btn-success">
                    Volver
                  </button>
                </a>
              </div>
            </div>
          </div>
        </div>
      )}
    </>
  );
}

export default DetalleInstrumento;
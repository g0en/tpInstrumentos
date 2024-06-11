import { useState, useEffect } from "react";
import Instrumento from "../entidades/Instrumento";
import { getInstrumentoJSONFetch } from "../servicios/FuncionesApi";
import ItemInstrumento from "./ItemInstrumento";
import MenuOpciones from "./MenuOpciones";
import "./css/SeparadorNav.css"

import { Carrito } from "./Carrito";
import { CarritoContextProvider } from "../context/CarritoContext";

function Menu() {
  const [instrumentos, setInstrumentos] = useState<Instrumento[]>([]);

  const getInstrumentos = async () => {
    const datos: Instrumento[] = await getInstrumentoJSONFetch();
    setInstrumentos(datos);
  };

  useEffect(() => {
    getInstrumentos();
  }, []);

  return (
    <>
      <div style={{ display: "flex", flexDirection: "column", marginTop:"80px" }}>
        <MenuOpciones />
        <CarritoContextProvider>
          <div style={{ display: "flex", flexDirection: "row" }}>
            <div className="container-fluid" style={{ flex: 1 }}>
              <div
                style={{
                  display: "grid",
                  gridTemplateColumns: "repeat(3, 1fr)",
                  gap: "10px",
                }}
              >
                {instrumentos.map((instrumento: Instrumento, index) => (
                  <div key={index} className="tarjeta">
                    <ItemInstrumento
                      instrumentoObject={instrumento}
                      id={instrumento.id}
                      instrumento={instrumento.instrumento}
                      marca={instrumento.marca}
                      modelo={instrumento.modelo}
                      imagen={instrumento.imagen}
                      precio={instrumento.precio}
                      descripcion={instrumento.descripcion}
                      cantidadVendida={instrumento.cantidadVendida}
                      costoEnvio={instrumento.costoEnvio}
                      initialHayStock={true}
                    />
                  </div>
                ))}
              </div>
            </div>
            <div
              className="col"
              style={{
                backgroundColor: "white", // Fondo blanco
                borderRadius: "10px", // Bordes redondeados
                padding: "10px", // Espacio interno
                marginLeft: "20px", // Espacio entre el grid y el carrito
                boxShadow: "0px 0px 10px 2px rgba(0,0,0,0.1)", // Sombra detrás del carrito
                width: "300px", // Ancho fijo para el carrito
              }}
            >
              <b>Carrito Compras</b>
              <Carrito />
            </div>
          </div>
        </CarritoContextProvider>
      </div>
    </>
  );
}

export default Menu;

import { useEffect, useState } from "react";
import Instrumento from "../entidades/Instrumento";
import {
  deleteInstrumentoXId,
  getInstrumentoJSONFetch,
} from "../servicios/FuncionesApi";
import MenuOpciones from "./MenuOpciones";
import "./css/GrillaInstrumento.css";
import Usuario from "../entidades/Usuario";
import { Roles } from "../entidades/Roles";

function GrillaInstrumento() {
  const [instrumentos, setInstrumentos] = useState<Instrumento[]>([]);
  const [jsonUsuario, setJSONUsuario] = useState<any>(localStorage.getItem('usuario'))
  const usuarioLogueado: Usuario = JSON.parse(jsonUsuario) as Usuario;

  const getInstrumentos = async () => {
    const datos: Instrumento[] = await getInstrumentoJSONFetch();
    setInstrumentos(datos);
  };

  useEffect(() => {
    getInstrumentos();
  }, []);

  const deleteInstrumento = async (id: number) => {
    await deleteInstrumentoXId(id);
    window.location.reload();
  };

  return (
    <>
      <MenuOpciones></MenuOpciones>
      <div className="container-fluid text-center">
        <br />
        <div className="d-flex justify-content-end mb-3">
          <a className="btn btn-primary" href={`/formulario/0`}>
            Nuevo
          </a>
        </div>
        <div className="row header border">
          <div className="col-1 border-end">
            <b>ID</b>
          </div>
          <div className="col-5 border-end">
            <b>Instrumento</b>
          </div>
          <div className="col-2 border-end">
            <b>Categoria</b>
          </div>
          <div className="col-1 border-end">
            <b>Cantidad Vendida</b>
          </div>
          <div className="col-1 border-end">
            <b>Precio</b>
          </div>
          <div className="col-1 border-end">
            <b>Modificar</b>
          </div>
          <div>
            {
              (usuarioLogueado.rol == Roles.ADMIN) ?
                <div className="col-1 border-end">
                  <b>Eliminar</b>
                </div>
                :
                <div className="col-1 border-end"></div>
            }
          </div>
        </div>
        {instrumentos.map((instrumento: Instrumento) => (
          <div className="row border" key={instrumento.id}>
            <div className="col-1 border-end">{instrumento.id}</div>
            <div className="col-5 border-end text-start">
              {instrumento.instrumento}
            </div>
            <div className="col-2 border-end">
              {instrumento.categoria?.denominacion}
            </div>
            <div className="col-1 border-end">
              {instrumento.cantidadVendida}
            </div>
            <div className="col-1 border-end">{instrumento.precio}</div>
            <div className="col-1 border-end">
              <a
                className="btn btn-info"
                style={{ marginBottom: 10 }}
                href={`/formulario/` + instrumento.id}
              >
                Modificar
              </a>
            </div>
            <div>
              {
                (usuarioLogueado.rol == Roles.ADMIN) ?
                  <div className="col-1 border-end">
                    <a
                      className="btn btn-danger"
                      style={{ marginBottom: 10 }}
                      onClick={() => deleteInstrumento(instrumento.id)}
                    >
                      Eliminar
                    </a>
                  </div>
                  :
                  <div className="col-1 border-end"></div>
              }
            </div>
          </div>
        ))}
      </div>
    </>
  );
}
export default GrillaInstrumento;
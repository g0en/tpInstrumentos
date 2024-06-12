import "../App.css";
import { useNavigate } from "react-router-dom";
import { Roles } from "../entidades/Roles";
import { useState } from "react";
import Usuario from "../entidades/Usuario";

function MenuOpciones() {
  const navigate = useNavigate();
  const [jsonUsuario, setJSONUsuario] = useState<any>(localStorage.getItem('usuario'))
  const usuarioLogueado: Usuario = JSON.parse(jsonUsuario) as Usuario;
  
  const cerrarSesion = async () => {
    localStorage.setItem("usuario", "");
    localStorage.removeItem("usuario");
    navigate("/login", {
      replace: true,
      state: {
        logged: false,
      },
    });
  };

  return (
    <>
      <ul className="nav nav-tabs card-header fixed-top">
        <li className="nav-item">
          <a className="nav-link" href="/home">
            Inicio
          </a>
        </li>
        <li className="nav-item">
          <a className="nav-link" href="/menu">
            Productos
          </a>
        </li>
        {
          (usuarioLogueado.rol === Roles.ADMIN || usuarioLogueado.rol === Roles.OPERADOR) ?
            <li className="nav-item">
              <a className="nav-link" href="/grilla">
                Grilla
              </a>
            </li>
            :
            <></>
        }
        {
          (usuarioLogueado.rol === Roles.ADMIN) ?
          <li className="nav-item">
            <a className="nav-link" href="/estadisticas">
              Estadisticas
            </a>
          </li>
          :
          <></>
        }
        <li className="nav-item ml-auto">
          <button
            onClick={cerrarSesion}
            className="btn btn-success my-2 my-sm-0"
            type="button"
          >
            Cerrar Sesión
          </button>
        </li>
      </ul>
    </>
  );
}
export default MenuOpciones;

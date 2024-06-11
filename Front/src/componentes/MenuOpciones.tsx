import "../App.css";
import { useNavigate } from "react-router-dom";

function MenuOpciones() {
  const navigate = useNavigate();

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
        <li className="nav-item">
          <a className="nav-link" href="/grilla">
            Grilla
          </a>
        </li>
        <li>
          <div className="nav-link"></div>
        </li>
        <li>
          <a className="nav-link">
          </a>
        </li>
        <li>
          <div className="nav-link"></div>
        </li>
        <li>
          <a className="nav-link">
          </a>
        </li>
        <li>
          <div className="nav-link"></div>
        </li>
        <li>
          <a className="nav-link">
          </a>
        </li>        <li>
          <div className="nav-link"></div>
        </li>
        <li>
          <a className="nav-link">
          </a>
        </li>        <li>
          <div className="nav-link"></div>
        </li>
        <li>
          <a className="nav-link">
          </a>
        </li>        <li>
          <div className="nav-link"></div>
        </li>
        <li>
          <a className="nav-link">
          </a>
        </li>        <li>
          <div className="nav-link"></div>
        </li>
        <li>
          <a className="nav-link">
          </a>
        </li>        <li>
          <div className="nav-link"></div>
        </li>
        <li>
          <a className="nav-link">
          </a>
        </li>        <li>
          <div className="nav-link"></div>
        </li>
        <li>
          <a className="nav-link">
          </a>
        </li>        <li>
          <div className="nav-link"></div>
        </li>
        <li>
          <a className="nav-link">
          </a>
        </li>        <li>
          <div className="nav-link"></div>
        </li>
        <li>
          <a className="nav-link">
          </a>
        </li>        <li>
          <div className="nav-link"></div>
        </li>
        <li>
          <a className="nav-link">
          </a>
        </li>        <li>
          <div className="nav-link"></div>
        </li>
        <li>
          <a className="nav-link">
          </a>
        </li>        <li>
          <div className="nav-link"></div>
        </li>
        <li>
          <a className="nav-link">
          </a>
        </li>
        <li className="nav-item">
          <button
            onClick={cerrarSesion}
            className="btn btn-success"
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

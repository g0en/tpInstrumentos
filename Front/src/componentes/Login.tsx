import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Usuario from "../entidades/Usuario";
import "./css/Login.css";
import { existeEnBase } from "../servicios/FuncionesApi";

function Login() {
    const navigate = useNavigate();
    const [usuario, setUsuario] = useState<Usuario>(new Usuario());
    const [txtValidacion, setTxtValidacion] = useState<string>("");

    const login = async () => {
        if (!usuario?.nombreUsuario) {
            setTxtValidacion("Ingrese el nombre de usuario");
            return;
        }
        if (!usuario?.clave) {
            setTxtValidacion("Ingrese la clave");
            return;
        }

        try {
            const user: Usuario = await existeEnBase(usuario.nombreUsuario, usuario.clave);
            if (user) {
                setUsuario(usuario);
                localStorage.setItem('usuario', JSON.stringify(user));
                navigate('/menu', {
                    replace: true,
                    state: {
                        logged: true,
                        usuario: user,
                    },
                });
            } else {
                setTxtValidacion("Usuario y/o clave incorrectas");
            }
        } catch (error) {
            setTxtValidacion("Error en la autenticación. Por favor, intente nuevamente.");
            console.error(error);
        }
    }

    return (
        <div className="login-wrapper">
            <div className="center">
                <form className="login-form">
                    <div className="mb-3">
                        <label htmlFor="txtUsuario" className="form-label">Usuario</label>
                        <input type="text" id='txtUsuario' className="form-control" placeholder="Ingrese el nombre" defaultValue={usuario?.nombreUsuario} onChange={e => setUsuario({ ...usuario, nombreUsuario: e.target.value })} onKeyDown={(e) => { if (e.key === "Enter") login(); }} />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="txtClave" className="form-label">Clave</label>
                        <input type="password" id='txtClave' className="form-control" placeholder="Ingrese la clave" defaultValue={usuario?.clave} onChange={e => setUsuario({ ...usuario, clave: e.target.value })} onKeyDown={(e) => { if (e.key === "Enter") login(); }} />
                    </div>
                    <div className="col">
                        <button onClick={login} className="btn btn-success" type="button">
                            Ingresar
                        </button>
                    </div>
                    <div>
                        <p style={{ color: 'red', lineHeight: 5, padding: 5 }}>{txtValidacion}</p>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default Login;

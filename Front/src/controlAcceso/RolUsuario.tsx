import { Navigate, Outlet } from 'react-router-dom';
import { Roles } from '../entidades/Roles';
import Usuario from '../entidades/Usuario';
import { useState } from 'react';

interface Props {
    roles: Roles[];
}

// Función para convertir un string al tipo Roles
const convertStringToRole = (roleString: string): Roles => {
    return roleString as Roles;
};

function RolUsuario({ roles }: Props) {

    const [jsonUsuario, setJSONUsuario] = useState<any>(localStorage.getItem('usuario'));
    const usuarioLogueado: Usuario = JSON.parse(jsonUsuario) as Usuario;
    const userRole: Roles = convertStringToRole(usuarioLogueado.rol);
    //si esta logueado y es administrador lo dejo ingresar si no
    if ((usuarioLogueado && roles.includes(userRole))) {
        return <Outlet />;
    } else if (usuarioLogueado) {
        return <Navigate replace to='/menu' />;
    } else {
        return <Navigate replace to='/login' />;
    }

}
export default RolUsuario;
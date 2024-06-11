import React, { Suspense, lazy } from "react";
import ReactDOM from "react-dom/client";

import { BrowserRouter, Route, Routes } from "react-router-dom";
import Producto from "./componentes/Menu.tsx";
import DetalleInstrumento from "./componentes/DetalleInstrumento.tsx";
import LoaderPage from "./componentes/LoaderPage.tsx";
import Home from "./Home.tsx";
import Login from "./componentes/Login.tsx";
import App from "./App.tsx";
import { RutaPrivada } from "./controlAcceso/RutaPrivada.tsx";
import RolUsuario from "./controlAcceso/RolUsuario.tsx";
import { Roles } from "./entidades/Roles.ts";


const Menu = lazy(() => import("./componentes/Menu.tsx"));
const GrillaInstrumento = lazy(() => import("./componentes/GrillaInstrumento.tsx"));
const CheckoutMP = lazy(() => import("./componentes/CheckoutMP.tsx"));
const Formulario = lazy(() => import("./componentes/Formulario.tsx")); 
const TestLoad = lazy(() => import("./componentes/TestLoad.tsx"));


ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <Suspense fallback={<LoaderPage></LoaderPage>}>
    <BrowserRouter>
      <Routes>
        //Ruta publica
        <Route index element={<Home />} />

        //Ruta publica
        <Route path="/login" element={<Login />} />

        //Ruta publica
        <Route path="/detalle">
          <Route path=":idInstrumento" element={<DetalleInstrumento />} />
        </Route>

        //Ruta publica
        <Route path="/app" element={<App/>} />

        //Ruta publica
        <Route path="/home" element={<Home/>} />

        //Ruta privada
        <Route path="/grilla" element={
                                        <RutaPrivada>
                                          <GrillaInstrumento />
                                        </RutaPrivada>
                                      } />

        //Ruta privada
        <Route path="/menu" element={
                                        <RutaPrivada>
                                          <Menu />
                                        </RutaPrivada>
                                      } />  
                                      
        // Ruta privada
        <Route path="/mercadopago" element={
                                            <RutaPrivada>
                                              <CheckoutMP />
                                            </RutaPrivada>
                                           } />

        // Ruta privada y con Rol Administrador
        <Route element={<RolUsuario rol={Roles.ADMIN} />}>
            <Route path="/formulario/:idinstrumento" element={<Formulario />} />
        </Route>

        <Route path="/loading" element={<TestLoad  />} />

        <Route path="*" element={<Producto />} />

      </Routes>
    </BrowserRouter>
    </Suspense>
  </React.StrictMode>
);

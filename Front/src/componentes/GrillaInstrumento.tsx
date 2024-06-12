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
import { Button } from "@chakra-ui/react";
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogActions from '@mui/material/DialogActions';
import TextField from '@mui/material/TextField';

function GrillaInstrumento() {
  const [instrumentos, setInstrumentos] = useState<Instrumento[]>([]);
  const [excelModalOpen, setExcelModalOpen] = useState<boolean>(false);
  const [fechaDesde, setFechaDesde] = useState<string>('');
  const [fechaHasta, setFechaHasta] = useState<string>('');
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

  const handleGenerateExcel = async () => {
    try {
      const response = await fetch(`http://localhost:8080/Reporte/excel?fechaDesde=${fechaDesde}&fechaHasta=${fechaHasta}`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
        },
      });
      const blob = await response.blob();
      const urlBlob = window.URL.createObjectURL(blob);
      const a = document.createElement('a');
      a.href = urlBlob;
      a.download = `pedidos_${fechaDesde}_to_${fechaHasta}.xls`;
      document.body.appendChild(a);
      a.click();
      a.remove();
    } catch (error) {
      console.error('Error al generar el archivo Excel:', error);
    }
  };

  return (
    <>
      <MenuOpciones></MenuOpciones>
      <div className="container-fluid text-center">
        <br />
        <div className="d-flex justify-content-between mb-3">
          {
            (usuarioLogueado.rol === Roles.ADMIN) ?
              <a className="btn btn-primary" href="/formulario/0">Nuevo</a>
              :
              <></>
          }
          <Button
            className="btnSuccess"
            sx={{ mt: 2, width: '10%', borderRadius: '4px'}}
            variant="outline"
            onClick={() => setExcelModalOpen(true)}
          >
            Generar Excel
          </Button>
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
          {
            (usuarioLogueado.rol === Roles.ADMIN) ?
              <div className="col-1 border-end">
                <b>Eliminar</b>
              </div>
              :
              <></>
          }
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
            {
              (usuarioLogueado.rol === Roles.ADMIN) ?
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
                <></>
            }
          </div>
        ))}
      </div>
      <Dialog open={excelModalOpen} onClose={() => setExcelModalOpen(false)}>
        <DialogTitle>Generar Excel de Pedidos</DialogTitle>
        <DialogContent>
          <TextField
            label="Fecha Desde"
            type="date"
            value={fechaDesde}
            onChange={(e) => setFechaDesde(e.target.value)}
            fullWidth
            InputLabelProps={{
              shrink: true,
            }}
            margin="normal"
          />
          <TextField
            label="Fecha Hasta"
            type="date"
            value={fechaHasta}
            onChange={(e) => setFechaHasta(e.target.value)}
            fullWidth
            InputLabelProps={{
              shrink: true,
            }}
            margin="normal"
          />
        </DialogContent>
        <DialogActions>
          <Button className="btnCancel" onClick={() => setExcelModalOpen(false)}>Cancelar</Button>
          <Button className="btnGenerate" onClick={handleGenerateExcel}>Generar</Button>
        </DialogActions>
      </Dialog>
    </>
  );
}
export default GrillaInstrumento;

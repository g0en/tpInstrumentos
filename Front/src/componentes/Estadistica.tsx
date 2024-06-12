import { useEffect, useState } from "react";
import MenuOpciones from "./MenuOpciones";
import { Chart } from 'react-google-charts';
import { getPedidosGroupedByFecha, getPedidosGroupedByInstrumento } from "../servicios/FuncionesApi";

function Estadistica() {
    const [pedidosPorFecha, setPedidosPorFecha] = useState([
        ['Fecha', 'Total Pedidos'],
        ['Loading...', 0],
    ]);

    const [pedidosPorInstrumento, setPedidosPorInstrumento] = useState([
        ['Instrumento', 'Cantidad de Pedidos'],
        ['Loading...', 0],
    ]);

    const getPedidos = async () => {
        try {
            const pedidosFecha = await getPedidosGroupedByFecha();
            const formattedDataFecha = [
                ['Fecha', 'Total Pedidos'],
                ...pedidosFecha.map(pedido => [pedido.fecha, pedido.totalPedidos]),
            ];
            setPedidosPorFecha(formattedDataFecha);

            const pedidosInstrumento = await getPedidosGroupedByInstrumento();
            const formattedDataInstrumento = [
                ['Instrumento', 'Cantidad de Pedidos'],
                ...pedidosInstrumento.map(pedido => [pedido.instrumento, pedido.cantidadPedidos]),
            ];
            setPedidosPorInstrumento(formattedDataInstrumento);
        } catch (error) {
            console.error("Error fetching pedidos:", error);
            setPedidosPorFecha([
                ['Fecha', 'Total Pedidos'],
                ['Error', 0],
            ]);
            setPedidosPorInstrumento([
                ['Instrumento', 'Cantidad de Pedidos'],
                ['Error', 0],
            ]);
        }
    };

    useEffect(() => {
        getPedidos();
    }, []);

    const optionsFecha = {
        title: 'Pedidos por Mes y Año',
        hAxis: { title: 'Fecha', titleTextStyle: { color: '#333' } },
        vAxis: { minValue: 0 },
        chartArea: { width: '50%', height: '70%' },
    };

    const optionsInstrumento = {
        title: 'Pedidos por Instrumento',
        chartArea: { width: '50%', height: '70%' },
    };

    return (
        <>
            <MenuOpciones />
            <div style={{ display: 'flex', justifyContent: 'space-around', padding: '20px', marginTop: '50px'}}>
                <div style={{ flex: '1' }}>
                    <h2>Pedidos por Mes y Año</h2>
                    <Chart
                        chartType="BarChart"
                        width="100%"
                        height="400px"
                        data={pedidosPorFecha}
                        options={optionsFecha}
                    />
                </div>
                <div style={{ flex: '1', marginLeft: '300px' }}>
                    <h2>Pedidos por Instrumento</h2>
                    <Chart
                        chartType="PieChart"
                        width="100%"
                        height="400px"
                        data={pedidosPorInstrumento}
                        options={optionsInstrumento}
                    />
                </div>
            </div>
        </>
    );
}

export default Estadistica;

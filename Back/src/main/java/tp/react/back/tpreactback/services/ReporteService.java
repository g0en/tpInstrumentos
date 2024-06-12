package tp.react.back.tpreactback.services;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp.react.back.tpreactback.modelo.Instrumento;
import tp.react.back.tpreactback.modelo.Pedido;
import tp.react.back.tpreactback.modelo.PedidoDetalle;
import tp.react.back.tpreactback.repository.IInstrumentoRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ReporteService {
    @Autowired
    private IInstrumentoRepository instrumentoRepository;

    @Autowired
    private PedidoService pedidoService;
    public byte[] generarPdfInstrumento(Long id) throws IOException{
        Instrumento instrumento = this.instrumentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El instrumento no se encontro"));
        try (PDDocument document = new PDDocument();
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            // Añade una página al documento
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            // Abre un flujo de contenido para escribir en la página
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {

                // Fondo para el título
                contentStream.setNonStrokingColor(200, 200, 255); // Azul claro
                contentStream.addRect(50, 750, 500, 40);
                contentStream.fill();

                // Escribe el nombre del instrumento como título del documento
                contentStream.setNonStrokingColor(0, 0, 128); // Azul oscuro
                addText(contentStream, instrumento.getInstrumento(), PDType1Font.HELVETICA_BOLD, 20, 55, 760);

                // Card para la información del instrumento
                contentStream.setNonStrokingColor(255, 255, 255); // Blanco
                contentStream.addRect(50, 500, 500, 200);
                contentStream.fill();

                contentStream.setStrokingColor(220, 220, 220); // Borde gris claro
                contentStream.setLineWidth(1);
                contentStream.addRect(50, 500, 500, 200);
                contentStream.stroke();

                // Espacio para la imagen del instrumento
                if (instrumento.getImagen() != null && !instrumento.getImagen().isEmpty()) {
                    try (InputStream imageStream = new URL(instrumento.getImagen()).openStream()) {
                        PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, imageStream.readAllBytes(), "imagen");
                        contentStream.drawImage(pdImage, 55, 505, 190, 190); // Ajusta la posición y tamaño según sea necesario
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                // Escribe los detalles del instrumento
                contentStream.setNonStrokingColor(0, 0, 0); // Negro
                addText(contentStream, "Instrumento: " + instrumento.getInstrumento(), PDType1Font.HELVETICA_BOLD, 18, 260, 680);
                addText(contentStream, "Marca: " + instrumento.getMarca(), PDType1Font.HELVETICA, 12, 260, 660);
                addText(contentStream, "Modelo: " + instrumento.getModelo(), PDType1Font.HELVETICA, 12, 260, 640);
                addText(contentStream, "Precio: $" + instrumento.getPrecio(), PDType1Font.HELVETICA_BOLD, 16, 260, 620);
                addText(contentStream, "Cantidad Vendida: " + instrumento.getCantidadVendida(), PDType1Font.HELVETICA, 12, 260, 600);
                addText(contentStream, "Costo de Envío: " + instrumento.getCostoEnvio(), PDType1Font.HELVETICA, 12, 260, 580);

                // Card para la descripción
                contentStream.setNonStrokingColor(255, 255, 255); // Blanco
                contentStream.addRect(50, 430, 500, 50);
                contentStream.fill();

                contentStream.setStrokingColor(220, 220, 220); // Borde gris claro
                contentStream.setLineWidth(1);
                contentStream.addRect(50, 430, 500, 50);
                contentStream.stroke();

                // Descripción del instrumento
                contentStream.setNonStrokingColor(0, 0, 0); // Negro
                addText(contentStream, "Descripción:", PDType1Font.HELVETICA_BOLD, 14, 55, 460);
                addText(contentStream, instrumento.getDescripcion(), PDType1Font.HELVETICA, 12, 55, 440);

                contentStream.close();
            }

            // Guarda el documento en el flujo de bytes
            document.save(baos);
            // Retorna el contenido del PDF como un array de bytes
            return baos.toByteArray();
        }
    }

    // Agregar texto en el contenido del PDF
    private void addText(PDPageContentStream contentStream, String text, PDFont font, float fontSize, float x, float y) throws IOException {
        contentStream.beginText();
        contentStream.setFont(font, fontSize);
        contentStream.newLineAtOffset(x, y);
        contentStream.showText(text);
        contentStream.endText();
    }

    public byte[] generarReporteExcel(Date fechaDesde, Date fechaHasta) throws IOException {
        List<Pedido> pedidos = pedidoService.findByFecha(fechaDesde, fechaHasta);

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Reporte de Pedidos");

        // Crear encabezado
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Fecha Pedido", "Instrumento", "Marca", "Modelo", "Cantidad", "Precio", "Subtotal"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Llenar datos
        int rowNum = 1;
        for (Pedido pedido : pedidos) {
            for (PedidoDetalle detalle : pedido.getPedidoDetalle()) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(pedido.getFecha()));
                row.createCell(1).setCellValue(detalle.getInstrumento().getInstrumento());
                row.createCell(2).setCellValue(detalle.getInstrumento().getMarca());
                row.createCell(3).setCellValue(detalle.getInstrumento().getModelo());
                row.createCell(4).setCellValue(detalle.getCantidad());
                row.createCell(5).setCellValue(detalle.getInstrumento().getPrecio());
                row.createCell(6).setCellValue(detalle.getCantidad() * detalle.getInstrumento().getPrecio());
            }
        }

        // Autosize columns
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);
        workbook.close();

        return baos.toByteArray();
    }
}
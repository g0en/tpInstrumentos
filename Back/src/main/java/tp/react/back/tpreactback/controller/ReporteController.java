package tp.react.back.tpreactback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tp.react.back.tpreactback.services.ReporteService;

import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/Reporte")
public class ReporteController {
    @Autowired
    private ReporteService reporteService;

    @GetMapping("/pdf/{id}")
    public ResponseEntity<byte[]> generarPdfInstrumento(@PathVariable Long id){
        try {
            byte[] pdfBytes = reporteService.generarPdfInstrumento(id);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "instrumento_" + id + ".pdf");
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/excel")
    public ResponseEntity<byte[]> generarReporteExcel(
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta) throws IOException, IOException {

        byte[] excelContent = reporteService.generarReporteExcel(fechaDesde, fechaHasta);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte_pedidos.xls");
        headers.setContentLength(excelContent.length);

        return ResponseEntity.ok()
                .headers(headers)
                .body(excelContent);
    }
}
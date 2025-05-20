package controlador;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import conexion.Conexion;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Edison Zambrano
 */
public class Reportes {

    private String fechaActual = "";


    /* ********************************************************************
    * metodo para crear reportes de los productos registrados en el sistema
    *********************************************************************** */
    public void ReportesProductos() {
        Document documento = new Document();

        try {
            // Cargar la fecha actual
            Date date = new Date();
            String fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(date);
            String fechaNueva = fechaActual.replace("/", "_");

            // Crear carpeta "reportes" dentro del proyecto
            String ruta = System.getProperty("user.dir") + "/reportes";
            File directorio = new File(ruta);
            if (!directorio.exists()) {
                directorio.mkdirs();
            }

            // Crear archivo PDF
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Reporte_Productos_" + fechaNueva + ".pdf"));

            // Cargar imagen desde recursos
            InputStream imagenStream = getClass().getResourceAsStream("/files/header.jpg");
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[16384];
            while ((nRead = imagenStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            Image header = Image.getInstance(buffer.toByteArray());
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);
            header.setBorder(0);

            // Tipografías personalizadas desde recursos
            BaseFont montserrat = BaseFont.createFont(
                    getClass().getResource("/fonts/Montserrat-Regular.ttf").toString(),
                    BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            BaseFont montserratBold = BaseFont.createFont(
                    getClass().getResource("/fonts/Montserrat-Bold.ttf").toString(),
                    BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            Font fuentePersonalizada = new Font(montserrat, 11, Font.NORMAL, new BaseColor(0x5f, 0x2f, 0x23));
            Font titulo = new Font(montserratBold, 18, Font.NORMAL, new BaseColor(0x5f, 0x2f, 0x23));
            Font subtitulo = new Font(montserrat, 12, Font.NORMAL, new BaseColor(0x5f, 0x2f, 0x23));
            Font encabezadoTabla = new Font(montserratBold, 11, Font.NORMAL, BaseColor.BLACK);
            Font cuerpoTabla = new Font(montserrat, 10, Font.NORMAL, BaseColor.BLACK);

            documento.open();
            documento.add(header);

            // Encabezado
            Paragraph encabezado = new Paragraph();
            encabezado.setAlignment(Element.ALIGN_CENTER);
            encabezado.setSpacingBefore(5f);
            encabezado.add(new Phrase("Reporte creado por", subtitulo));
            encabezado.add(new Chunk("\n"));
            encabezado.add(new Phrase("Cowboy Cookies\n\n", subtitulo));
            encabezado.add(new Phrase("Reporte de Productos\n\n", titulo));
            documento.add(encabezado);

            float[] columnWidths = {2, 7, 3, 5, 9, 4, 5};
            PdfPTable tabla = new PdfPTable(columnWidths);
            tabla.setWidthPercentage(100f);
            tabla.setSpacingBefore(10f);

            String[] headers = {"ID", "Nombre", "Stock", "Precio", "Descripción", "Por. ITBIS", "Categoría"};
            for (String h : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(h, encabezadoTabla));
                cell.setBackgroundColor(new BaseColor(0xa2, 0xd2, 0xff));
                cell.setPadding(5f);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla.addCell(cell);
            }

            try {
                Connection cn = Conexion.getConnection();
                PreparedStatement pst = cn.prepareStatement(
                        "SELECT p.id_producto, p.nombre, p.stock, p.precio, p.descripcion, "
                        + "p.porcentajeitbis, c.nombre_categoria AS categoria "
                        + "FROM productos AS p INNER JOIN categorias AS c ON p.id_categoria = c.id_categoria "
                        + "ORDER BY c.id_categoria ASC;");
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    for (int i = 1; i <= 7; i++) {
                        PdfPCell cell = new PdfPCell(new Phrase(rs.getString(i), cuerpoTabla));
                        cell.setPadding(4f);
                        tabla.addCell(cell);
                    }
                }

            } catch (SQLException e) {
                System.out.println("Error al consultar productos: " + e);
            }

            documento.add(tabla);
            documento.close();

            JOptionPane.showMessageDialog(null, "Reporte creado con éxito en la carpeta /reportes");

        } catch (Exception e) {
            System.out.println("Error al generar el reporte: " + e);
        }
    }


    /* ********************************************************************
    * metodo para crear reportes de los categorias registrados en el sistema
    *********************************************************************** */
    public void ReportesCategorias() {
        Document documento = new Document();
        try {
            // Cargar la fecha actual
            Date date = new Date();
            fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(date);
            String fechaNueva = fechaActual.replace("/", "_");

            // Crear carpeta "reportes" si no existe
            String ruta = System.getProperty("user.dir") + "/reportes";
            File directorio = new File(ruta);
            if (!directorio.exists()) {
                directorio.mkdirs();
            }

            // Crear el PDF dentro de la carpeta
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Reporte_Categorias_" + fechaNueva + ".pdf"));

            // Cargar imagen desde recursos
            InputStream imagenStream = getClass().getResourceAsStream("/files/header.jpg");
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] data = new byte[16384];
            int nRead;
            while ((nRead = imagenStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            Image header = Image.getInstance(buffer.toByteArray());
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);
            header.setBorder(0);

            // Cargar tipografías desde recursos
            BaseFont montserrat = BaseFont.createFont(
                    getClass().getResource("/fonts/Montserrat-Regular.ttf").toString(),
                    BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            BaseFont montserratBold = BaseFont.createFont(
                    getClass().getResource("/fonts/Montserrat-Bold.ttf").toString(),
                    BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            Font titulo = new Font(montserratBold, 18, Font.NORMAL, new BaseColor(0x5f, 0x2f, 0x23));
            Font subtitulo = new Font(montserrat, 12, Font.NORMAL, new BaseColor(0x5f, 0x2f, 0x23));
            Font encabezadoTabla = new Font(montserratBold, 12, Font.NORMAL, new BaseColor(0x5f, 0x2f, 0x23));
            Font cuerpoTabla = new Font(montserrat, 11, Font.NORMAL, BaseColor.BLACK);

            documento.open();
            documento.add(header);

            // Título del reporte
            Paragraph encabezado = new Paragraph();
            encabezado.setAlignment(Element.ALIGN_CENTER);
            encabezado.setSpacingBefore(5f);
            encabezado.add(new Phrase("Reporte creado por", subtitulo));
            encabezado.add(new Chunk("\n"));
            encabezado.add(new Phrase("Cowboy Cookies\n\n", subtitulo));
            encabezado.add(new Phrase("Reporte de Categorías\n\n", titulo));
            documento.add(encabezado);

            // Tabla
            PdfPTable tabla = new PdfPTable(3);
            tabla.setSpacingBefore(10f);
            tabla.setSpacingAfter(10f);
            tabla.setWidths(new float[]{2f, 5f, 9f});
            tabla.setWidthPercentage(100);

            BaseColor fondoEncabezado = new BaseColor(0xa2, 0xd2, 0xff);
            String[] columnas = {"Código", "Nombre", "Descripción"};
            for (String col : columnas) {
                PdfPCell celda = new PdfPCell(new Phrase(col, encabezadoTabla));
                celda.setBackgroundColor(fondoEncabezado);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setPadding(8f);
                celda.setBorderWidth(1);
                tabla.addCell(celda);
            }

            // Datos desde la BD
            try {
                Connection cn = Conexion.getConnection();
                PreparedStatement pst = cn.prepareStatement("SELECT * FROM categorias");
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    PdfPCell c1 = new PdfPCell(new Phrase(rs.getString("id_categoria"), cuerpoTabla));
                    PdfPCell c2 = new PdfPCell(new Phrase(rs.getString("nombre_categoria"), cuerpoTabla));
                    PdfPCell c3 = new PdfPCell(new Phrase(rs.getString("descripcion"), cuerpoTabla));

                    for (PdfPCell celda : new PdfPCell[]{c1, c2, c3}) {
                        celda.setPadding(6f);
                        celda.setBorderWidth(0.5f);
                    }

                    tabla.addCell(c1);
                    tabla.addCell(c2);
                    tabla.addCell(c3);
                }

            } catch (SQLException e) {
                System.out.println("Error al obtener categorías: " + e);
            }

            documento.add(tabla);
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte creado con éxito");

        } catch (DocumentException | IOException e) {
            System.out.println("Error al generar reporte: " + e);
        }
    }

    /* ********************************************************************
 * metodo para crear reportes de las ventas registrados en el sistema
 *********************************************************************** */
    public void ReportesVentas() {
        Document documento = new Document();
        try {
            // Cargar la fecha actual
            Date date = new Date();
            String fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(date);
            String fechaNueva = fechaActual.replace("/", "_");

            // Crear carpeta "reportes" dentro del proyecto
            String ruta = System.getProperty("user.dir") + "/reportes";
            File carpeta = new File(ruta);
            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }

            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Reporte_Ventas_" + fechaNueva + ".pdf"));

            // Cargar imagen desde recursos
            InputStream imagenStream = getClass().getResourceAsStream("/files/header.jpg");
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[16384];
            while ((nRead = imagenStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            Image header = Image.getInstance(buffer.toByteArray());
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);
            header.setBorder(0);

            // Tipografías personalizadas desde recursos
            BaseFont montserrat = BaseFont.createFont(
                    getClass().getResource("/fonts/Montserrat-Regular.ttf").toString(),
                    BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            BaseFont montserratBold = BaseFont.createFont(
                    getClass().getResource("/fonts/Montserrat-Bold.ttf").toString(),
                    BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            Font fuentePersonalizada = new Font(montserrat, 11, Font.NORMAL, new BaseColor(0x5f, 0x2f, 0x23));
            Font titulo = new Font(montserratBold, 18, Font.NORMAL, new BaseColor(0x5f, 0x2f, 0x23));
            Font subtitulo = new Font(montserrat, 12, Font.NORMAL, new BaseColor(0x5f, 0x2f, 0x23));
            Font fontEncabezado = new Font(montserratBold, 11, Font.NORMAL, new BaseColor(0x5f, 0x2f, 0x23));
            Font fontCuerpo = new Font(montserrat, 10, Font.NORMAL, BaseColor.BLACK);

            documento.open();
            documento.add(header);

            // Encabezado
            Paragraph encabezado = new Paragraph();
            encabezado.setAlignment(Element.ALIGN_CENTER);
            encabezado.setSpacingBefore(5f);
            encabezado.add(new Phrase("Reporte creado por", subtitulo));
            encabezado.add(new Chunk("\n"));
            encabezado.add(new Phrase("Cowboy Cookies\n\n", subtitulo));
            encabezado.add(new Phrase("Reporte de Ventas\n\n", titulo));
            documento.add(encabezado);

            // Tabla
            PdfPTable tabla = new PdfPTable(4);
            tabla.setSpacingBefore(10f);
            tabla.setSpacingAfter(10f);
            tabla.setWidths(new float[]{3f, 9f, 4f, 5f});
            tabla.setWidthPercentage(100);

            BaseColor fondoEncabezado = new BaseColor(0xa2, 0xd2, 0xff); // #a2d2ff

            String[] columnas = {"Código", "Empleado", "Total Pagar", "Fecha Venta"};
            for (String tituloCol : columnas) {
                PdfPCell celdaEncabezado = new PdfPCell(new Phrase(tituloCol, fontEncabezado));
                celdaEncabezado.setBackgroundColor(fondoEncabezado);
                celdaEncabezado.setHorizontalAlignment(Element.ALIGN_CENTER);
                celdaEncabezado.setPadding(8f);
                celdaEncabezado.setBorderWidth(1);
                tabla.addCell(celdaEncabezado);
            }

            // Datos
            try {
                Connection cn = Conexion.getConnection();
                PreparedStatement pst = cn.prepareStatement(
                        "SELECT v.id_venta AS id, CONCAT(u.nombre, ' ', u.apellido) AS Empleado, "
                        + "v.total AS total, v.fecha_hora AS fecha "
                        + "FROM ventas AS v, usuarios AS u WHERE v.id_usuario = u.id_usuario ORDER BY v.id_venta ASC;"
                );
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    PdfPCell c1 = new PdfPCell(new Phrase(rs.getString(1), fontCuerpo));
                    PdfPCell c2 = new PdfPCell(new Phrase(rs.getString(2), fontCuerpo));
                    PdfPCell c3 = new PdfPCell(new Phrase(rs.getString(3), fontCuerpo));
                    PdfPCell c4 = new PdfPCell(new Phrase(rs.getString(4), fontCuerpo));

                    for (PdfPCell celda : new PdfPCell[]{c1, c2, c3, c4}) {
                        celda.setPadding(6f);
                        celda.setBorderWidth(0.5f);
                    }

                    tabla.addCell(c1);
                    tabla.addCell(c2);
                    tabla.addCell(c3);
                    tabla.addCell(c4);
                }

                documento.add(tabla);
            } catch (SQLException e) {
                System.out.println("Error al obtener ventas: " + e);
            }

            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte creado con éxito en la carpeta /reportes");

        } catch (DocumentException | IOException e) {
            System.out.println("Error al generar reporte: " + e);
        }
    }


 /**************************************************************************
 * metodo para crear reportes de los productos mas vendidos y menos vendidos
 *********************************************************************** */
    public void ReporteProductosVendidos() {
        Document documento = new Document();
        try {
             // Cargar la fecha actual
            Date date = new Date();
            fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(date);
            String fechaNueva = fechaActual.replace("/", "_");

            // Crear carpeta "reportes" si no existe
            String ruta = System.getProperty("user.dir") + "/reportes";
            File directorio = new File(ruta);
            if (!directorio.exists()) {
                directorio.mkdirs();
            }

            // Crear el PDF dentro de la carpeta
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Reporte_Productos_Vendidos" + fechaNueva + ".pdf"));

            // Cargar imagen
            Image header = Image.getInstance("src/files/header.jpg");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);
            header.setBorder(0);

            // Tipografías personalizadas
            BaseFont montserrat = BaseFont.createFont("src/fonts/Montserrat-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            BaseFont montserratBold = BaseFont.createFont("src/fonts/Montserrat-Bold.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            Font titulo = new Font(montserratBold, 18, Font.NORMAL, new BaseColor(0x5f, 0x2f, 0x23));
            Font subtitulo = new Font(montserrat, 12, Font.NORMAL, new BaseColor(0x5f, 0x2f, 0x23));
            Font fontEncabezado = new Font(montserratBold, 11, Font.NORMAL, new BaseColor(0x5f, 0x2f, 0x23));
            Font fontCuerpo = new Font(montserrat, 10, Font.NORMAL, BaseColor.BLACK);

            documento.open();
            documento.add(header);

            // Encabezado del documento
            Paragraph encabezado = new Paragraph();
            encabezado.setAlignment(Element.ALIGN_CENTER);
            encabezado.setSpacingBefore(5f);
            encabezado.add(new Phrase("Reporte creado por", subtitulo));
            encabezado.add(new Chunk("\n"));
            encabezado.add(new Phrase("Cowboy Cookies\n\n", subtitulo));
            encabezado.add(new Phrase("Productos Más y Menos Vendidos\n\n", titulo));
            documento.add(encabezado);

            Connection cn = Conexion.getConnection();

            String[] titulos = {"Productos Más Vendidos", "Productos Menos Vendidos"};
            String[] consultas = {
                "SELECT p.id_producto, p.nombre, SUM(dv.cantidad) AS total_vendido "
                + "FROM detalleventa dv JOIN productos p ON dv.id_producto = p.id_producto "
                + "GROUP BY p.id_producto, p.nombre ORDER BY total_vendido DESC LIMIT 5;",
                "SELECT p.id_producto, p.nombre, COALESCE(SUM(dv.cantidad), 0) AS total_vendido "
                + "FROM productos p LEFT JOIN detalleventa dv ON p.id_producto = dv.id_producto "
                + "GROUP BY p.id_producto, p.nombre ORDER BY total_vendido ASC LIMIT 5;"
            };

            BaseColor fondoEncabezado = new BaseColor(0xa2, 0xd2, 0xff); // #a2d2ff
            String[] headers = {"ID", "Nombre", "Cantidad Vendida"};

            for (int i = 0; i < titulos.length; i++) {
                // Sección del título
                Paragraph seccion = new Paragraph(titulos[i] + "\n\n", subtitulo);
                seccion.setAlignment(Element.ALIGN_LEFT);
                documento.add(seccion);

                PdfPTable tabla = new PdfPTable(3);
                tabla.setWidthPercentage(100f);
                tabla.setSpacingBefore(5f);
                tabla.setSpacingAfter(10f);
                tabla.setWidths(new float[]{3f, 7f, 4f});

                // Encabezados de tabla
                for (String h : headers) {
                    PdfPCell cell = new PdfPCell(new Phrase(h, fontEncabezado));
                    cell.setBackgroundColor(fondoEncabezado);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setPadding(8f);
                    cell.setBorderWidth(1);
                    tabla.addCell(cell);
                }

                // Llenar datos
                PreparedStatement pst = cn.prepareStatement(consultas[i]);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    PdfPCell celdaId = new PdfPCell(new Phrase(rs.getString("id_producto"), fontCuerpo));
                    PdfPCell celdaNombre = new PdfPCell(new Phrase(rs.getString("nombre"), fontCuerpo));
                    PdfPCell celdaTotal = new PdfPCell(new Phrase(rs.getString("total_vendido"), fontCuerpo));

                    for (PdfPCell celda : new PdfPCell[]{celdaId, celdaNombre, celdaTotal}) {
                        celda.setPadding(6f);
                        celda.setBorderWidth(0.5f);
                    }

                    tabla.addCell(celdaId);
                    tabla.addCell(celdaNombre);
                    tabla.addCell(celdaTotal);
                }

                documento.add(tabla);
            }

            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte creado con éxito");

        } catch (Exception e) {
            System.out.println("Error al generar el reporte: " + e);
        }
    }

}

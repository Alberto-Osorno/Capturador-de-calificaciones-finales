package basultosorno;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import java.util.ArrayList;

public class ManipuladorPDF {

    //Metodo para crear un archivo PDF con las calificaciones capturadas de los estudiantes
    public static void generarArchivoPDF(ArrayList<Estudiante> estudiantes){
        try{
            PdfWriter escribir = new PdfWriter("./src/main/resources/Calificaciones_DS_2026.pdf");
            PdfDocument pdf = new PdfDocument(escribir);
            Document documento = new Document(pdf);

            documento.add(new Paragraph("Reporte de Calificaciones Finales"));
            documento.add(new Paragraph(" "));

            //Creación de la tabla
            float[] anchoDeLasColumnas = {200F, 200F, 200F}; //Arreglo de floats que simboliza cuantas columnas va a tener la tabla y de quá tamaño será cada columna
            Table tabla = new Table(anchoDeLasColumnas);

            //Encabezados
            tabla.addCell(new Cell().add(new Paragraph("Matricula")));
            tabla.addCell(new Cell().add(new Paragraph("Materia")));
            tabla.addCell(new Cell().add(new Paragraph("Calificación final")));

            //Contenido
            for(Estudiante estudiante : estudiantes){
                tabla.addCell(new Cell().add(new Paragraph(estudiante.getMatricula())));
                tabla.addCell(new Cell().add(new Paragraph("Diseño de software")));
                if(estudiante.getCalificacion() == 0){
                    tabla.addCell(new Cell().add(new Paragraph("S/C")));
                }else{
                    tabla.addCell(new Cell().add(new Paragraph(String.valueOf(estudiante.getCalificacion()))));

                }
            }

            documento.add(tabla);
            documento.close();
        } catch (Exception e){
            System.out.println("\nAlgo falló con el documento pdf");
            e.printStackTrace();
        }
    }
}
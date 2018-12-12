package codigo;

import com.sun.org.apache.xml.internal.serialize.*;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

/**
 * @author Alejandro Luna Gómez
 */
public class DOM {

    Document doc = null; // doc representa el árbol de DOM.

    public int abrir_XML_DOM(File fichero) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(fichero);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int annadirDOM(String nuevotitulo, String nuevoTituloOriginal, String nuevoDirector, String nuevoEscritor, String nuevaDuracion, String nuevaFechaEstreno, String nuevoGenero, String nuevoSubgenero, String nuevoActor1, String nuevoPersonaje1, String nuevoActor2, String nuevoPersonaje2, String nuevoActor3, String nuevoPersonaje3) {
        try {
            // Crea un nodo tipo Element con nombre 'titulo'(<titulo>).
            Node ntitulo = doc.createElement("titulo");
            // Crea un nodo de texto con el título del libro.
            Node ntitulo_text = doc.createTextNode(nuevotitulo);
            // Añade el atributo 'titulo_original' al nodo 'titulo'
            ((Element) ntitulo).setAttribute("titulo_original", nuevoTituloOriginal);
            // Añade el nodo de texto con el título como hijo del elemento titulo.
            ntitulo.appendChild(ntitulo_text);

            // Repite el proceso con el director.
            Node ndirector = doc.createElement("director");
            Node ndirector_text = doc.createTextNode(nuevoDirector);
            ndirector.appendChild(ndirector_text);
            
            // Repite el proceso con el escritor.
            Node nescritor = doc.createElement("escritor");
            Node nescritor_text = doc.createTextNode(nuevoEscritor);
            nescritor.appendChild(nescritor_text);
            
            // Crea el nodo actores
            Node nactores = doc.createElement("actores");
            
            // Crea el nodo actor1 y lo añade al nodo actores
            Node nactor1 = doc.createElement("actor");
            Node nactor1_text = doc.createTextNode(nuevoActor1);
            ((Element) nactor1).setAttribute("personaje", nuevoPersonaje1);
            nactor1.appendChild(nactor1_text);
            nactores.appendChild(nactor1);
            
            // Crea el nodo actor2 y lo añade al nodo actores
            Node nactor2 = doc.createElement("actor");
            Node nactor2_text = doc.createTextNode(nuevoActor2);
            ((Element) nactor2).setAttribute("personaje", nuevoPersonaje2);
            nactor2.appendChild(nactor2_text);
            nactores.appendChild(nactor2);
            
            // Crea el nodo actor3 y lo añade al nodo actores
            Node nactor3 = doc.createElement("actor");
            Node nactor3_text = doc.createTextNode(nuevoActor3);
            ((Element) nactor3).setAttribute("personaje", nuevoPersonaje3);
            nactor3.appendChild(nactor3_text);
            nactores.appendChild(nactor3);

            // Crea un nodo tipo Element con nombre 'pelicula'(<pelicula>).
            Node nPelicula = doc.createElement("pelicula");
            // Añade los atributos al nodo 'pelicula'.
            ((Element) nPelicula).setAttribute("duracion", nuevaDuracion);
            ((Element) nPelicula).setAttribute("fecha_estreno", nuevaFechaEstreno);
            ((Element) nPelicula).setAttribute("genero", nuevoGenero);
            ((Element) nPelicula).setAttribute("subgenero", nuevoSubgenero);

            // Añade los nodos 'titulo', 'director', 'escritor' y 'actores' al nodo 'pelicula'.
            nPelicula.appendChild(ntitulo);
            nPelicula.appendChild(ndirector);
            nPelicula.appendChild(nescritor);
            nPelicula.appendChild(nactores);

            // Obtiene el primer nodo del documento y añade como hijo el nodo 'pelicula' y todos sus hijos y atributos creados antes.
            Node raiz = doc.getChildNodes().item(0);
            raiz.appendChild(nPelicula);

            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int guardarDOMcomoFILE(File archivo_xml) {
        try {
            // Especifica el formato de salida.
            OutputFormat format = new OutputFormat(doc);

            // Especifica que la salida esté indentada.
            format.setIndenting(true);

            // Escribe el contenido en el FILE.
            XMLSerializer serializer = new XMLSerializer(new FileOutputStream(archivo_xml), format);

            serializer.serialize(doc);

            return 0;
        } catch (Exception e) {
            return -1;
        }
    }
}
package codigo;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Alejandro Luna Gómez
 */
public class SAX {

    SAXParser parser;
    ManejadorSAX sh;
    File ficheroXML;

    public int abrir_XML_SAX(File fichero) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();

            // Se crea un objeto SAXParser para implementar el documento XML.
            parser = factory.newSAXParser();

            // Se crea una instancia del manejador que será el que recorra el documento XML secuencialmente.
            sh = new ManejadorSAX();

            ficheroXML = fichero;
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String recorrerSAX() {
        try {
            sh.cadena_resultado = "";
            sh.numeroPeliculas = 0;
            parser.parse(ficheroXML, sh);
            return sh.cadena_resultado;
        } catch (SAXException e) {
            e.printStackTrace();
            return "Error al parsear con SAX";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al parsear con SAX";
        }
    }
}

class ManejadorSAX extends DefaultHandler {

    int ultimoelement;
    int numeroPeliculas;
    String cadena_resultado = "";

    public ManejadorSAX() {
        ultimoelement = 0;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        if (qName.equals("peliculas")) {
            cadena_resultado += "Peliculas";
            cadena_resultado += "\n***********";
        }
        if (qName.equals("pelicula")) {
            cadena_resultado += "\nDuración: " + atts.getValue(atts.getQName(0));
            cadena_resultado += "\nFecha de Estreno: " + atts.getValue(atts.getQName(1));
            cadena_resultado += "\nGénero: " + atts.getValue(atts.getQName(2));
            cadena_resultado += "\nSubgénero: " + atts.getValue(atts.getQName(3));
            ultimoelement = 1;
        } else if (qName.equals("titulo")) {
            cadena_resultado += "\nTítulo Original: " + atts.getValue(atts.getQName(0));
            cadena_resultado += "\nTítulo: ";
            ultimoelement = 2;
        } else if (qName.equals("director")) {
            cadena_resultado += "\nDirector: ";
            ultimoelement = 3;
        } else if (qName.equals("escritor")) {
            cadena_resultado += "\nEscritor: ";
            ultimoelement = 4;
        }else if(qName.equals("actores")){
            cadena_resultado += "\nActores:";
            ultimoelement = 5;
        } else if (qName.equals("actor")) {
            cadena_resultado += "\n     Personaje: " + atts.getValue(atts.getQName(0));
            cadena_resultado += "\n     Actor: ";
            ultimoelement = 6;
        }
    }

    // Cuando en este ejemplo se detecta el final de un elemento </pelicula> se pone una linea discontinua en la salida.
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("pelicula")) {
            cadena_resultado += "\n-----------------\n";
            numeroPeliculas ++;

        }else if(qName.equals("peliculas")){
            cadena_resultado += "\nNumero de películas: " + numeroPeliculas;
        }
    }

    // Cuando se detecta una cadena de texto posterior a uno de los elementos <titulo> entonces guarda ese texto en la variable correspondiente.
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (ultimoelement >= 1 && ultimoelement <= 6) {
            for (int i = start; i < length + start; i++) {
                cadena_resultado += ch[i];
            }
        }
    }
}

package codigo;

import java.io.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;

/**
 * @author Alejandro Luna Gómez
 */
public class Gestor_XPath {

    Document XMLDoc = null;

    public int abreXPath(File archivo) {
        try {
            //Crea un objeto DocumentBuilderFactory para el DOM (JAXP)
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            //Crear un árbol DOM (parsear) con el archivo peliculas.xml
            XMLDoc = factory.newDocumentBuilder().parse(archivo);
            return 0;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.toString());
            return -1;
        }
    }

    public String ejecutaXPath(String consulta) {
        String salida = "";
        try {
            //Crea el objeto XPath
            XPath xpath = XPathFactory.newInstance().newXPath();
            //Crea un XPathExpression con la consulta deseada
            XPathExpression exp = xpath.compile(consulta);
            //Ejecuta la consulta indicando que se ejecute sobre el DOM y que devolverá
            //el resultado como una lista de nodos.
            Object result = exp.evaluate(XMLDoc, XPathConstants.NODESET);
            NodeList nodeList = (NodeList) result;
            Element node;
            String[] datos_nodo = null;

            // Procesa los nodos hijos
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (nodeList.item(i).getNodeName().equals("pelicula")) {
                    node = (Element) nodeList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        datos_nodo = procesarPelicula(node);
                        salida = salida + "\n" + "Duración " + datos_nodo[0];
                        salida = salida + "\n" + "Fecha de Estreno: " + datos_nodo[1];
                        salida = salida + "\n" + "Género: " + datos_nodo[2];
                        salida = salida + "\n" + "Subgénero: " + datos_nodo[3];
                        salida = salida + "\n";
                        salida = salida + "\n" + "Título original: " + datos_nodo[5];
                        salida = salida + "\n" + "Título: " + datos_nodo[4];
                        salida = salida + "\n";
                        salida = salida + "\n" + "Director: " + datos_nodo[6];
                        salida = salida + "\n";
                        salida = salida + "\n" + "Escritor: " + datos_nodo[7];
                        salida = salida + "\n";
                        salida = salida + "\n" + "Actores:";
                        salida = salida + "\n";
                        salida = salida + "\n" + "      Personaje: " + datos_nodo[9];
                        salida = salida + "\n" + "      Actor: " + datos_nodo[8];
                        salida = salida + "\n";
                        salida = salida + "\n" + "      Personaje: " + datos_nodo[11];
                        salida = salida + "\n" + "      Actor: " + datos_nodo[10];
                        salida = salida + "\n";
                        salida = salida + "\n" + "      Personaje: " + datos_nodo[13];
                        salida = salida + "\n" + "      Actor: " + datos_nodo[12];
                        salida = salida + "\n";
                        salida = salida + "\n --------------------------------";
                        salida = salida + "\n";
                    }
                } else if (nodeList.item(i).getNodeName().equals("peliculas")) {
                    salida = "No se puede tomar unicamente la raíz como consulta.";
                } else {
                    salida = salida + nodeList.item(i).getNodeName() + ": " + nodeList.item(i).getChildNodes().item(0).getNodeValue() + "\n";
                }
            }
            return salida;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return "Error al ejecutar el comando.";
        }

    }

    protected String[] procesarPelicula(Node n) {
        String[] datos = new String[20];
        Node ntemp = null;
        Node ntempactor = null;
        int contador = 4;

        // Obten el valor de cada atributo del nodo (los 4 del la estructura actual).
        datos[0] = n.getAttributes().item(0).getNodeValue();
        datos[1] = n.getAttributes().item(1).getNodeValue();
        datos[2] = n.getAttributes().item(2).getNodeValue();
        datos[3] = n.getAttributes().item(3).getNodeValue();

        // Obten los hijos de la pelicula (titulo, director, escritor y actores).
        NodeList nodos = n.getChildNodes();

        for (int i = 0; i < nodos.getLength(); i++) {
            ntemp = nodos.item(i);

            if (!ntemp.getNodeName().equals("actores") && !ntemp.getNodeName().equals("titulo")) {
                if (ntemp.getNodeType() == Node.ELEMENT_NODE) {
                    // IMPORTANTE: para obtener el texto con el título, autor y escritor se accede al nodo TEXT hijo de ntemp y se saca su valor.
                    datos[contador] = ntemp.getFirstChild().getNodeValue();
                    contador++;
                }
            } else if (ntemp.getNodeName().equals("titulo")) {
                datos[contador] = ntemp.getFirstChild().getNodeValue();
                contador++;
                datos[contador] = ntemp.getAttributes().item(0).getNodeValue();
                contador++;
            } else if (ntemp.getNodeName().equals("actores")) {

                NodeList nodosActores = nodos.item(i).getChildNodes();
//                datos[contador] = nodosActores.item(0).getFirstChild().getNodeValue();
//                contador++;
//                datos[contador] = nodosActores.item(1).getFirstChild().getNodeValue();
//                contador++;
//                datos[contador] = nodosActores.item(2).getFirstChild().getNodeValue();
//                contador++;
                for (int j = 0; j < nodosActores.getLength(); j++) {
                    if (nodosActores.item(j).getNodeType() != Node.TEXT_NODE) {
                        ntempactor = nodosActores.item(j);
                        datos[contador] = ntempactor.getFirstChild().getNodeValue();
                        contador++;
                        datos[contador] = ntempactor.getAttributes().item(0).getNodeValue();
                        contador++;
                    }
                }
            }

        }

        return datos;
    }
}

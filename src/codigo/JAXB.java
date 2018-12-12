package codigo;

import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * @author Alejandro Luna Gómez
 */
public class JAXB {

    Peliculas misPeliculas = null;
    List<Peliculas.Pelicula> lPeliculas;
    Unmarshaller u;
    Marshaller m;
    File archivoXML = null;
    JAXBContext contexto;

    public int abrirXML_JAXB(File fichero) {
        try {
            archivoXML = fichero;
            // Crea una instancia JAXB.
            contexto = JAXBContext.newInstance(Peliculas.class);
            // Crea un objeto Unmarshaller.
            u = contexto.createUnmarshaller();

            // Deserializa (unmarshal) el fichero.
            misPeliculas = (Peliculas) u.unmarshal(archivoXML);
            lPeliculas = misPeliculas.getPelicula();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public int guardarJAXB(File f) {
        try {
            // Crea un objeto Marshaller.
            m = contexto.createMarshaller();

            // Codigo obtenido de stackoverflow
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // Da formato
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); // Da codificacion
            m.marshal(misPeliculas, f);
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }
    
    public int guardarJAXB() {
        try {
            guardarJAXB(archivoXML);
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

    public int modificaDatos(String titulo, String nuevotitulo, String nuevoTituloOriginal, String nuevoDirector, String nuevoEscritor, String nuevaDuracion, String nuevaFechaEstreno, String nuevoGenero, String nuevoSubgenero, String nuevoActor1, String nuevoPersonaje1, String nuevoActor2, String nuevoPersonaje2, String nuevoActor3, String nuevoPersonaje3) {
        try {
            for (int i = 0; i < lPeliculas.size(); i++) {
                if (titulo.equals(lPeliculas.get(i).getTitulo().getValue())) {
                    lPeliculas.get(i).getTitulo().setValue(nuevotitulo);
                    lPeliculas.get(i).getTitulo().setTituloOriginal(nuevoTituloOriginal);
                    lPeliculas.get(i).setDirector(nuevoDirector);
                    lPeliculas.get(i).setEscritor(nuevoEscritor);
                    lPeliculas.get(i).setDuracion(nuevaDuracion);
                    lPeliculas.get(i).setFechaEstreno(nuevaFechaEstreno);
                    lPeliculas.get(i).setGenero(nuevoGenero);
                    lPeliculas.get(i).setSubgenero(nuevoSubgenero);
                    lPeliculas.get(i).getActores().getActor().get(0).setValue(nuevoActor1);
                    lPeliculas.get(i).getActores().getActor().get(0).setPersonaje(nuevoPersonaje1);
                    lPeliculas.get(i).getActores().getActor().get(1).setValue(nuevoActor2);
                    lPeliculas.get(i).getActores().getActor().get(1).setPersonaje(nuevoPersonaje2);
                    lPeliculas.get(i).getActores().getActor().get(2).setValue(nuevoActor3);
                    lPeliculas.get(i).getActores().getActor().get(2).setPersonaje(nuevoPersonaje3);
                }
            }
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }
}

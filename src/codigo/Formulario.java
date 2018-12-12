/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Random;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;

/**
 *
 * @author Alejandro Luna Gómez
 */
public class Formulario extends javax.swing.JFrame {

    List<Peliculas.Pelicula> listaPeliculas = null;
    DOM gesDOM = new DOM();
    SAX gesSAX = new SAX();
    JAXB gesJAXB = new JAXB();
    Gestor_XPath gesXPATH = new Gestor_XPath();
    File tempFile = new File("temp.xml");
    File ficheroOriginal;
    String[] arrayPeliculas;
    int radioSeleccionado = 0;

    /**
     * Creates new form Formulario
     */
    public Formulario() {
        initComponents();

        this.setTitle("Gestor de Películas");

        comboBoxPeliculas.removeAllItems();

        grupoRadio.add(radioTodas);
        grupoRadio.add(radioTitulo);
        grupoRadio.add(radioTituloOriginal);
        grupoRadio.add(radioGenero);
        grupoRadio.add(radioDirector);
        grupoRadio.add(radioEscritor);
        grupoRadio.add(radioActor);
        grupoRadio.add(radioSubgenero);
        grupoRadio.add(radioFechaEstreno);
        grupoRadio.add(radioDuracion);
        grupoRadio.add(radioAleatoria);

        saveAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        menuArchivoGuardar.setAction(saveAction);
        saveAsAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
        menuArchivoGuardarComo.setAction(saveAsAction);
        loadAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        menuArchivoAbrir.setAction(loadAction);
        closeAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
        menuArchivoSalir.setAction(closeAction);
    }

    /**
     * Acción de guardado.
     */
    Action saveAction = new AbstractAction("Guardar") {
        @Override
        public void actionPerformed(ActionEvent e) {
            gesJAXB.guardarJAXB(tempFile);
        }
    };

    /**
     * Acción de carga.
     */
    Action loadAction = new AbstractAction("Abrir") {
        @Override
        public void actionPerformed(ActionEvent e) {
            archivoAbrir();
        }
    };

    /**
     * Acción de añadir texto.
     */
    Action saveAsAction = new AbstractAction("Guardar Como...") {
        @Override
        public void actionPerformed(ActionEvent e) {
            gesJAXB.guardarJAXB(dialogoGuardarTipoRecurso());
        }
    };

    /**
     * Acción de cierre de programa.
     */
    Action closeAction = new AbstractAction("Salir") {
        @Override
        public void actionPerformed(ActionEvent e) {
            cerrar();
        }
    };

    /**
     * Abre el fichero en DOM, SAX, JAXB y XPath.
     */
    private void archivoAbrir() {
        String mensaje = "";
        boolean errorAnterior = false;
        try {
            ficheroOriginal = dialogoSeleccionTipoRecurso();
            copiarArchivo(ficheroOriginal, tempFile);
        } catch (IOException e) {
            mensajeAccion.setText("Error al abrir el fichero.");
            System.err.println(e.getMessage());
        }

        if (gesDOM.abrir_XML_DOM(tempFile) != 0) {
            mensaje += "DOM";
            errorAnterior = true;
        }
        if (gesSAX.abrir_XML_SAX(tempFile) != 0) {
            if (errorAnterior) {
                mensaje += ", SAX";
            } else {
                mensaje += "SAX";
            }
            errorAnterior = true;
        }
        if (gesJAXB.abrirXML_JAXB(tempFile) != 0) {
            if (errorAnterior) {
                mensaje += ", JAXB";
            } else {
                mensaje += "JAXB";
            }
            errorAnterior = true;
        }
        if (gesXPATH.abreXPath(tempFile) != 0) {
            if (errorAnterior) {
                mensaje += ", XPath";
            } else {
                mensaje += "XPath";
            }
            errorAnterior = true;
        }

        if (!errorAnterior) {
            enableButtons();
            generaLista();
            texto.setText(gesSAX.recorrerSAX());
            mensajeAccion.setText("Se ha abierto correctamente el archivo.");
        } else {
            mensajeAccion.setText("Ha habido un error al abrir el fichero en: " + mensaje + ".");
        }
    }

    /**
     * Cierra la aplicación.
     */
    private void cerrar() {
        System.exit(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        grupoRadio = new javax.swing.ButtonGroup();
        mensajeAccion = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        texto = new javax.swing.JTextArea();
        tituloOriginalLabel = new javax.swing.JLabel();
        lTitulo = new javax.swing.JLabel();
        fTitulo = new javax.swing.JTextField();
        modificar = new javax.swing.JButton();
        comboBoxPeliculas = new javax.swing.JComboBox<>();
        fTituloOriginal = new javax.swing.JTextField();
        lTituloOriginal = new javax.swing.JLabel();
        fEscritor = new javax.swing.JTextField();
        lEscritor = new javax.swing.JLabel();
        lDirector = new javax.swing.JLabel();
        fDirector = new javax.swing.JTextField();
        fDuracion = new javax.swing.JTextField();
        lDuracion = new javax.swing.JLabel();
        lFechaEstreno = new javax.swing.JLabel();
        fFechaEstreno = new javax.swing.JTextField();
        fSubgenero = new javax.swing.JTextField();
        lSubgenero = new javax.swing.JLabel();
        lGenero = new javax.swing.JLabel();
        fGenero = new javax.swing.JTextField();
        fPersonaje1 = new javax.swing.JTextField();
        lPersonaje1 = new javax.swing.JLabel();
        lActor1 = new javax.swing.JLabel();
        fActor1 = new javax.swing.JTextField();
        fPersonaje2 = new javax.swing.JTextField();
        lPersonaje2 = new javax.swing.JLabel();
        lActor2 = new javax.swing.JLabel();
        fActor2 = new javax.swing.JTextField();
        fPersonaje3 = new javax.swing.JTextField();
        lPersonaje3 = new javax.swing.JLabel();
        lActor3 = new javax.swing.JLabel();
        fActor3 = new javax.swing.JTextField();
        separador = new javax.swing.JSeparator();
        annadirPelicula = new javax.swing.JButton();
        annadirToggle = new javax.swing.JToggleButton();
        consultaxpath = new javax.swing.JTextField();
        consultarxpath = new javax.swing.JButton();
        separador1 = new javax.swing.JSeparator();
        radioTituloOriginal = new javax.swing.JRadioButton();
        radioGenero = new javax.swing.JRadioButton();
        radioSubgenero = new javax.swing.JRadioButton();
        radioTitulo = new javax.swing.JRadioButton();
        radioTodas = new javax.swing.JRadioButton();
        radioFechaEstreno = new javax.swing.JRadioButton();
        radioDuracion = new javax.swing.JRadioButton();
        radioActor = new javax.swing.JRadioButton();
        radioEscritor = new javax.swing.JRadioButton();
        radioDirector = new javax.swing.JRadioButton();
        radioAleatoria = new javax.swing.JRadioButton();
        lBusqueda = new javax.swing.JLabel();
        guardarCambios = new javax.swing.JButton();
        menu = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        menuArchivoAbrir = new javax.swing.JMenuItem();
        menuArchivoGuardar = new javax.swing.JMenuItem();
        menuArchivoGuardarComo = new javax.swing.JMenuItem();
        menuArchivoSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(1311, 760));
        setMinimumSize(new java.awt.Dimension(1301, 720));
        setPreferredSize(new java.awt.Dimension(1311, 760));
        setResizable(false);
        setSize(new java.awt.Dimension(1311, 760));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(mensajeAccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 378, -1));

        texto.setColumns(20);
        texto.setRows(5);
        texto.setFocusable(false);
        jScrollPane1.setViewportView(texto);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 62, 519, 625));

        tituloOriginalLabel.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        tituloOriginalLabel.setText("Película");
        getContentPane().add(tituloOriginalLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(486, 16, -1, -1));

        lTitulo.setText("Título");
        getContentPane().add(lTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 62, -1, -1));
        getContentPane().add(fTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 83, 364, -1));

        modificar.setText("Modificar");
        modificar.setEnabled(false);
        modificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                modificarMousePressed(evt);
            }
        });
        getContentPane().add(modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1069, 440, 220, -1));

        comboBoxPeliculas.setEnabled(false);
        comboBoxPeliculas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxPeliculasActionPerformed(evt);
            }
        });
        getContentPane().add(comboBoxPeliculas, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 12, 746, -1));
        getContentPane().add(fTituloOriginal, new org.netbeans.lib.awtextra.AbsoluteConstraints(925, 83, 364, -1));

        lTituloOriginal.setText("Título Original");
        getContentPane().add(lTituloOriginal, new org.netbeans.lib.awtextra.AbsoluteConstraints(925, 62, -1, -1));
        getContentPane().add(fEscritor, new org.netbeans.lib.awtextra.AbsoluteConstraints(925, 135, 364, -1));

        lEscritor.setText("Escritor");
        getContentPane().add(lEscritor, new org.netbeans.lib.awtextra.AbsoluteConstraints(925, 114, -1, -1));

        lDirector.setText("Director");
        getContentPane().add(lDirector, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 114, -1, -1));
        getContentPane().add(fDirector, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 135, 364, -1));
        getContentPane().add(fDuracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(925, 187, 364, -1));

        lDuracion.setText("Duración");
        getContentPane().add(lDuracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(925, 166, -1, -1));

        lFechaEstreno.setText("Fecha de Estreno");
        getContentPane().add(lFechaEstreno, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 166, -1, -1));
        getContentPane().add(fFechaEstreno, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 187, 364, -1));
        getContentPane().add(fSubgenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(925, 239, 364, -1));

        lSubgenero.setText("Subgénero");
        getContentPane().add(lSubgenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(925, 218, -1, -1));

        lGenero.setText("Género");
        getContentPane().add(lGenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 218, -1, -1));
        getContentPane().add(fGenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 239, 364, -1));
        getContentPane().add(fPersonaje1, new org.netbeans.lib.awtextra.AbsoluteConstraints(925, 299, 364, -1));

        lPersonaje1.setText("Personaje");
        getContentPane().add(lPersonaje1, new org.netbeans.lib.awtextra.AbsoluteConstraints(925, 278, -1, -1));

        lActor1.setText("Actor");
        getContentPane().add(lActor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 278, -1, -1));
        getContentPane().add(fActor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 299, 364, -1));
        getContentPane().add(fPersonaje2, new org.netbeans.lib.awtextra.AbsoluteConstraints(925, 351, 364, -1));

        lPersonaje2.setText("Personaje");
        getContentPane().add(lPersonaje2, new org.netbeans.lib.awtextra.AbsoluteConstraints(925, 330, -1, -1));

        lActor2.setText("Actor");
        getContentPane().add(lActor2, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 330, -1, -1));
        getContentPane().add(fActor2, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 351, 364, -1));
        getContentPane().add(fPersonaje3, new org.netbeans.lib.awtextra.AbsoluteConstraints(925, 403, 364, -1));

        lPersonaje3.setText("Personaje");
        getContentPane().add(lPersonaje3, new org.netbeans.lib.awtextra.AbsoluteConstraints(925, 382, -1, -1));

        lActor3.setText("Actor");
        getContentPane().add(lActor3, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 382, -1, -1));
        getContentPane().add(fActor3, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 403, 364, -1));
        getContentPane().add(separador, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 270, 746, -1));

        annadirPelicula.setText("Añadir Película");
        annadirPelicula.setEnabled(false);
        annadirPelicula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                annadirPeliculaMousePressed(evt);
            }
        });
        getContentPane().add(annadirPelicula, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 440, 220, -1));

        annadirToggle.setText("Añadir");
        annadirToggle.setEnabled(false);
        annadirToggle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                annadirToggleMousePressed(evt);
            }
        });
        getContentPane().add(annadirToggle, new org.netbeans.lib.awtextra.AbsoluteConstraints(402, 12, -1, -1));

        consultaxpath.setEnabled(false);
        consultaxpath.setMaximumSize(new java.awt.Dimension(200, 23));
        consultaxpath.setMinimumSize(new java.awt.Dimension(200, 23));
        consultaxpath.setPreferredSize(new java.awt.Dimension(200, 23));
        getContentPane().add(consultaxpath, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 621, 746, 30));

        consultarxpath.setText("Realizar Búsqueda");
        consultarxpath.setEnabled(false);
        consultarxpath.setPreferredSize(new java.awt.Dimension(10, 25));
        consultarxpath.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                consultarxpathMousePressed(evt);
            }
        });
        getContentPane().add(consultarxpath, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 662, 746, -1));
        getContentPane().add(separador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 480, 746, 8));

        radioTituloOriginal.setText("Buscar por Título Original");
        radioTituloOriginal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                radioTituloOriginalMousePressed(evt);
            }
        });
        getContentPane().add(radioTituloOriginal, new org.netbeans.lib.awtextra.AbsoluteConstraints(553, 548, -1, -1));

        radioGenero.setText("Buscar por Género");
        radioGenero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                radioGeneroMousePressed(evt);
            }
        });
        getContentPane().add(radioGenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(553, 571, -1, -1));

        radioSubgenero.setText("Buscar por Subgénero");
        radioSubgenero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                radioSubgeneroMousePressed(evt);
            }
        });
        getContentPane().add(radioSubgenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(847, 571, -1, -1));

        radioTitulo.setText("Buscar por Título");
        radioTitulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                radioTituloMousePressed(evt);
            }
        });
        getContentPane().add(radioTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(553, 522, -1, -1));

        radioTodas.setSelected(true);
        radioTodas.setText("Mostrar todas las películas");
        radioTodas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                radioTodasMousePressed(evt);
            }
        });
        getContentPane().add(radioTodas, new org.netbeans.lib.awtextra.AbsoluteConstraints(553, 496, -1, -1));

        radioFechaEstreno.setText("Buscar por Año de Estreno");
        radioFechaEstreno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                radioFechaEstrenoMousePressed(evt);
            }
        });
        getContentPane().add(radioFechaEstreno, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 496, -1, -1));

        radioDuracion.setText("Buscar por Duración");
        radioDuracion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                radioDuracionMousePressed(evt);
            }
        });
        getContentPane().add(radioDuracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 522, -1, -1));

        radioActor.setText("Buscar por Actor");
        radioActor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                radioActorMousePressed(evt);
            }
        });
        getContentPane().add(radioActor, new org.netbeans.lib.awtextra.AbsoluteConstraints(847, 548, -1, -1));

        radioEscritor.setText("Buscar por Escritor");
        radioEscritor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                radioEscritorMousePressed(evt);
            }
        });
        getContentPane().add(radioEscritor, new org.netbeans.lib.awtextra.AbsoluteConstraints(847, 522, -1, -1));

        radioDirector.setText("Buscar por Director");
        radioDirector.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                radioDirectorMousePressed(evt);
            }
        });
        getContentPane().add(radioDirector, new org.netbeans.lib.awtextra.AbsoluteConstraints(847, 496, -1, -1));

        radioAleatoria.setText("Mostrar película aleatoria");
        radioAleatoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                radioAleatoriaMousePressed(evt);
            }
        });
        getContentPane().add(radioAleatoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 548, -1, -1));

        lBusqueda.setText("Busqueda:");
        getContentPane().add(lBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 601, -1, -1));

        guardarCambios.setText("Guardar Cambios");
        guardarCambios.setEnabled(false);
        guardarCambios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                guardarCambiosMousePressed(evt);
            }
        });
        getContentPane().add(guardarCambios, new org.netbeans.lib.awtextra.AbsoluteConstraints(807, 440, 220, -1));

        menuArchivo.setText("Archivo");

        menuArchivoAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        menuArchivoAbrir.setText("Abrir");
        menuArchivoAbrir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuArchivoAbrirMousePressed(evt);
            }
        });
        menuArchivo.add(menuArchivoAbrir);

        menuArchivoGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menuArchivoGuardar.setText("Guardar");
        menuArchivoGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuArchivoGuardarMousePressed(evt);
            }
        });
        menuArchivo.add(menuArchivoGuardar);

        menuArchivoGuardarComo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuArchivoGuardarComo.setText("Guardar Como...");
        menuArchivoGuardarComo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuArchivoGuardarComoMousePressed(evt);
            }
        });
        menuArchivo.add(menuArchivoGuardarComo);

        menuArchivoSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        menuArchivoSalir.setText("Salir");
        menuArchivoSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuArchivoSalirMousePressed(evt);
            }
        });
        menuArchivo.add(menuArchivoSalir);

        menu.add(menuArchivo);

        setJMenuBar(menu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Habilita los botones al conseguir cargar el archivo.
     */
    private void enableButtons() {
        this.modificar.setEnabled(true);
        this.annadirToggle.setEnabled(true);
        this.comboBoxPeliculas.setEnabled(true);
        this.consultarxpath.setEnabled(true);
        this.guardarCambios.setEnabled(true);
    }

    /**
     * Genera la lista de películas que se mostrará en el ComboBox.
     */
    private void generaLista() {
        try {
            comboBoxPeliculas.removeAllItems();
            arrayPeliculas = new String[gesJAXB.lPeliculas.size()];
            for (int i = 0; i < gesJAXB.lPeliculas.size(); i++) {
                comboBoxPeliculas.addItem(gesJAXB.lPeliculas.get(i).getTitulo().getValue());
                arrayPeliculas[i] = gesJAXB.lPeliculas.get(i).getTitulo().getValue();
            }
            cargaValores();
        } catch (Exception e) {
        }
    }

    /**
     * Genera la consulta de una película aleatoria.
     *
     * @return - String
     */
    private String generaConsultaAleatoria() {
        String nombrePeli;
        Random r = new Random();
        nombrePeli = arrayPeliculas[r.nextInt(arrayPeliculas.length)];
        return "/peliculas/pelicula[./titulo='" + nombrePeli + "']";
    }

    /**
     * Carga los valores de la Película seleccionada en el ComboBox en los
     * labels correspondiantes.
     */
    private void cargaValores() {

        Peliculas.Pelicula p = null;
        try {
            for (int i = 0; i < gesJAXB.lPeliculas.size(); i++) {
                if (comboBoxPeliculas.getSelectedItem().toString().equals(gesJAXB.lPeliculas.get(i).getTitulo().getValue())) {
                    p = gesJAXB.lPeliculas.get(i);
                }
            }
            fTitulo.setText(p.getTitulo().getValue());
            fTituloOriginal.setText(p.getTitulo().getTituloOriginal());
            fDirector.setText(p.getDirector());
            fEscritor.setText(p.getEscritor());
            fFechaEstreno.setText(p.getFechaEstreno());
            fDuracion.setText(p.getDuracion());
            fGenero.setText(p.getGenero());
            fSubgenero.setText(p.getSubgenero());
            fActor1.setText(p.getActores().getActor().get(0).getValue());
            fPersonaje1.setText(p.getActores().getActor().get(0).getPersonaje());
            fActor2.setText(p.getActores().getActor().get(1).getValue());
            fPersonaje2.setText(p.getActores().getActor().get(1).getPersonaje());
            fActor3.setText(p.getActores().getActor().get(2).getValue());
            fPersonaje3.setText(p.getActores().getActor().get(2).getPersonaje());
        } catch (Exception e) {
        }
    }

    /**
     * Limpia los valores de los labels para poder añadir una nueva película.
     */
    private void limpiaValores() {
        fTitulo.setText("");
        fTituloOriginal.setText("");
        fDirector.setText("");
        fEscritor.setText("");
        fFechaEstreno.setText("");
        fDuracion.setText("");
        fGenero.setText("");
        fSubgenero.setText("");
        fActor1.setText("");
        fPersonaje1.setText("");
        fActor2.setText("");
        fPersonaje2.setText("");
        fActor3.setText("");
        fPersonaje3.setText("");
    }

    /**
     * Copia un archivo.
     *
     * @param source - File
     * @param dest - File
     * @throws IOException - Error
     */
    private static void copiarArchivo(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

    private void menuArchivoAbrirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuArchivoAbrirMousePressed
        archivoAbrir();
    }//GEN-LAST:event_menuArchivoAbrirMousePressed

    private void modificarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificarMousePressed
        if (modificar.isEnabled()) {
            if (gesJAXB.modificaDatos(comboBoxPeliculas.getSelectedItem().toString(), fTitulo.getText(), fTituloOriginal.getText(), fDirector.getText(), fEscritor.getText(), fDuracion.getText(), fFechaEstreno.getText(), fGenero.getText(), fSubgenero.getText(), fActor1.getText(), fPersonaje1.getText(), fActor2.getText(), fPersonaje2.getText(), fActor3.getText(), fPersonaje3.getText()) == 0) {
                gesJAXB.guardarJAXB();
                gesXPATH.abreXPath(tempFile);
                texto.setText(gesSAX.recorrerSAX());
                generaLista();
                mensajeAccion.setText("Se han guardado los cambios.");
            } else {
                mensajeAccion.setText("Error al guardar los cambios.");
            }
        }
    }//GEN-LAST:event_modificarMousePressed

    private void comboBoxPeliculasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxPeliculasActionPerformed
        cargaValores();
    }//GEN-LAST:event_comboBoxPeliculasActionPerformed

    private void menuArchivoGuardarComoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuArchivoGuardarComoMousePressed
        gesJAXB.guardarJAXB(dialogoGuardarTipoRecurso());
    }//GEN-LAST:event_menuArchivoGuardarComoMousePressed

    private void annadirPeliculaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_annadirPeliculaMousePressed
        if (annadirPelicula.isEnabled()) {
            if (gesDOM.annadirDOM(fTitulo.getText(), fTituloOriginal.getText(), fDirector.getText(), fEscritor.getText(), fDuracion.getText(), fFechaEstreno.getText(), fGenero.getText(), fSubgenero.getText(), fActor1.getText(), fPersonaje1.getText(), fActor2.getText(), fPersonaje2.getText(), fActor3.getText(), fPersonaje3.getText()) == 0) {
                gesDOM.guardarDOMcomoFILE(tempFile); // Guarda el fichero temporal mediante DOM.                
                gesJAXB.abrirXML_JAXB(tempFile); // Actualiza los datos de JAXB cambiados al añadir una película.
                gesXPATH.abreXPath(tempFile); // Actualiza los datos de XPath cambiados al añadir una película.
                texto.setText(gesSAX.recorrerSAX()); // Actualiza el texto del panel de texto al añadir una película.
                mensajeAccion.setText("Seha añadido correctamente la película " + fTitulo.getText() + "."); // Informa de que se ha añadido correctamente la película.
                generaLista(); // Actualiza el ComboBox y añade la nueva película a la lista.
                limpiaValores(); // Limpia los campos de la ventana.
            } else {
                mensajeAccion.setText("Error al añadir la película."); // Informa de que ha habido un error al añadir la película.
            }
        }
    }//GEN-LAST:event_annadirPeliculaMousePressed

    private void annadirToggleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_annadirToggleMousePressed
        if (annadirToggle.isEnabled()) {
            if (!annadirToggle.isSelected()) {
                annadirPelicula.setEnabled(true);
                comboBoxPeliculas.setEnabled(false);
                modificar.setEnabled(false);
                limpiaValores();
            } else {
                annadirPelicula.setEnabled(false);
                comboBoxPeliculas.setEnabled(true);
                modificar.setEnabled(true);
                cargaValores();
            }
        }
    }//GEN-LAST:event_annadirToggleMousePressed

    private void consultarxpathMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_consultarxpathMousePressed
        if (consultarxpath.isEnabled()) {
            String consulta;
            switch (radioSeleccionado) {
                case 0:
                    consulta = "/peliculas/pelicula";
                    break;
                case 1:
                    consulta = "/peliculas/pelicula[./titulo='" + consultaxpath.getText() + "']";
                    break;
                case 2:
                    consulta = "/peliculas/pelicula[./titulo/@titulo_original='" + consultaxpath.getText() + "']";
                    break;
                case 3:
                    consulta = "/peliculas/pelicula[./@genero='" + consultaxpath.getText() + "']";
                    break;
                case 4:
                    consulta = "/peliculas/pelicula[./director='" + consultaxpath.getText() + "']";
                    break;
                case 5:
                    consulta = "/peliculas/pelicula[./escritor='" + consultaxpath.getText() + "']";
                    break;
                case 6:
                    consulta = "/peliculas/pelicula[./actores/actor='" + consultaxpath.getText() + "']";
                    break;
                case 7:
                    consulta = "/peliculas/pelicula[./@subgenero='" + consultaxpath.getText() + "']";
                    break;
                case 8:
                    consulta = "/peliculas/pelicula[./@fecha_estreno='" + consultaxpath.getText() + "']";
                    break;
                case 9:
                    consulta = "/peliculas/pelicula[./@duracion='" + consultaxpath.getText() + "']";
                    break;
                case 10:
                    consulta = generaConsultaAleatoria();
                    break;
                default:
                    consulta = "/peliculas/pelicula";
                    break;
            }
            texto.setText(gesXPATH.ejecutaXPath(consulta));
        }
    }//GEN-LAST:event_consultarxpathMousePressed

    private void radioTodasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioTodasMousePressed
        consultaxpath.setEnabled(false);
        consultaxpath.setText("");
        radioSeleccionado = 0;
    }//GEN-LAST:event_radioTodasMousePressed

    private void radioTituloMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioTituloMousePressed
        consultaxpath.setEnabled(true);
        consultaxpath.setText("");
        radioSeleccionado = 1;
    }//GEN-LAST:event_radioTituloMousePressed

    private void radioTituloOriginalMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioTituloOriginalMousePressed
        consultaxpath.setEnabled(true);
        consultaxpath.setText("");
        radioSeleccionado = 2;
    }//GEN-LAST:event_radioTituloOriginalMousePressed

    private void radioGeneroMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioGeneroMousePressed
        consultaxpath.setEnabled(true);
        consultaxpath.setText("");
        radioSeleccionado = 3;
    }//GEN-LAST:event_radioGeneroMousePressed

    private void radioDirectorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioDirectorMousePressed
        consultaxpath.setEnabled(true);
        consultaxpath.setText("");
        radioSeleccionado = 4;
    }//GEN-LAST:event_radioDirectorMousePressed

    private void radioEscritorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioEscritorMousePressed
        consultaxpath.setEnabled(true);
        consultaxpath.setText("");
        radioSeleccionado = 5;
    }//GEN-LAST:event_radioEscritorMousePressed

    private void radioActorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioActorMousePressed
        consultaxpath.setEnabled(true);
        consultaxpath.setText("");
        radioSeleccionado = 6;
    }//GEN-LAST:event_radioActorMousePressed

    private void radioSubgeneroMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioSubgeneroMousePressed
        consultaxpath.setEnabled(true);
        consultaxpath.setText("");
        radioSeleccionado = 7;
    }//GEN-LAST:event_radioSubgeneroMousePressed

    private void radioFechaEstrenoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioFechaEstrenoMousePressed
        consultaxpath.setEnabled(true);
        consultaxpath.setText("");
        radioSeleccionado = 8;
    }//GEN-LAST:event_radioFechaEstrenoMousePressed

    private void radioDuracionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioDuracionMousePressed
        consultaxpath.setEnabled(true);
        consultaxpath.setText("");
        radioSeleccionado = 9;
    }//GEN-LAST:event_radioDuracionMousePressed

    private void radioAleatoriaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioAleatoriaMousePressed
        consultaxpath.setEnabled(false);
        consultaxpath.setText("");
        radioSeleccionado = 10;
    }//GEN-LAST:event_radioAleatoriaMousePressed

    private void guardarCambiosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guardarCambiosMousePressed
        gesJAXB.guardarJAXB(ficheroOriginal);
    }//GEN-LAST:event_guardarCambiosMousePressed

    private void menuArchivoGuardarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuArchivoGuardarMousePressed
        gesJAXB.guardarJAXB(ficheroOriginal);
    }//GEN-LAST:event_menuArchivoGuardarMousePressed

    private void menuArchivoSalirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuArchivoSalirMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuArchivoSalirMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Formulario().setVisible(true);
            }
        });
    }

    private File dialogoSeleccionTipoRecurso() {
        File fichero = null;
        int rv;
        try {
            JFileChooser fc = new JFileChooser();
            fc.setMultiSelectionEnabled(false);
            fc.setDialogType(JFileChooser.OPEN_DIALOG);
            rv = fc.showOpenDialog(this);
            if (rv == JFileChooser.APPROVE_OPTION) {
                fichero = fc.getSelectedFile();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return fichero;
    }

    private File dialogoGuardarTipoRecurso() {
        File fichero = null;
        int rv;
        try {
            JFileChooser fc = new JFileChooser();
            fc.setMultiSelectionEnabled(false);
            fc.setDialogType(JFileChooser.SAVE_DIALOG);
            rv = fc.showSaveDialog(this);
            if (rv == JFileChooser.APPROVE_OPTION) {
                fichero = fc.getSelectedFile();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return fichero;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton annadirPelicula;
    private javax.swing.JToggleButton annadirToggle;
    private javax.swing.JComboBox<String> comboBoxPeliculas;
    private javax.swing.JButton consultarxpath;
    private javax.swing.JTextField consultaxpath;
    private javax.swing.JTextField fActor1;
    private javax.swing.JTextField fActor2;
    private javax.swing.JTextField fActor3;
    private javax.swing.JTextField fDirector;
    private javax.swing.JTextField fDuracion;
    private javax.swing.JTextField fEscritor;
    private javax.swing.JTextField fFechaEstreno;
    private javax.swing.JTextField fGenero;
    private javax.swing.JTextField fPersonaje1;
    private javax.swing.JTextField fPersonaje2;
    private javax.swing.JTextField fPersonaje3;
    private javax.swing.JTextField fSubgenero;
    private javax.swing.JTextField fTitulo;
    private javax.swing.JTextField fTituloOriginal;
    private javax.swing.ButtonGroup grupoRadio;
    private javax.swing.JButton guardarCambios;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lActor1;
    private javax.swing.JLabel lActor2;
    private javax.swing.JLabel lActor3;
    private javax.swing.JLabel lBusqueda;
    private javax.swing.JLabel lDirector;
    private javax.swing.JLabel lDuracion;
    private javax.swing.JLabel lEscritor;
    private javax.swing.JLabel lFechaEstreno;
    private javax.swing.JLabel lGenero;
    private javax.swing.JLabel lPersonaje1;
    private javax.swing.JLabel lPersonaje2;
    private javax.swing.JLabel lPersonaje3;
    private javax.swing.JLabel lSubgenero;
    private javax.swing.JLabel lTitulo;
    private javax.swing.JLabel lTituloOriginal;
    private javax.swing.JLabel mensajeAccion;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenuItem menuArchivoAbrir;
    private javax.swing.JMenuItem menuArchivoGuardar;
    private javax.swing.JMenuItem menuArchivoGuardarComo;
    private javax.swing.JMenuItem menuArchivoSalir;
    private javax.swing.JButton modificar;
    private javax.swing.JRadioButton radioActor;
    private javax.swing.JRadioButton radioAleatoria;
    private javax.swing.JRadioButton radioDirector;
    private javax.swing.JRadioButton radioDuracion;
    private javax.swing.JRadioButton radioEscritor;
    private javax.swing.JRadioButton radioFechaEstreno;
    private javax.swing.JRadioButton radioGenero;
    private javax.swing.JRadioButton radioSubgenero;
    private javax.swing.JRadioButton radioTitulo;
    private javax.swing.JRadioButton radioTituloOriginal;
    private javax.swing.JRadioButton radioTodas;
    private javax.swing.JSeparator separador;
    private javax.swing.JSeparator separador1;
    private javax.swing.JTextArea texto;
    private javax.swing.JLabel tituloOriginalLabel;
    // End of variables declaration//GEN-END:variables
}

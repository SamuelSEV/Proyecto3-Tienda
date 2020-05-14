import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;

import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.DoubleSpinnerValueFactory;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField ;
import javafx.scene.text.Text;

import javafx.stage.Stage;

import javafx.util.Duration;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Pos;

import java.io.FileInputStream;

import java.util.ArrayList;
import java.io.Console;
import java.io.FileReader;
import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.Double;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class javafx extends Application {
    //Reset
      public static final String ANSI_RESET = "\u001B[0m";
      //Colores de letra
      public static final String ANSI_RED = "\u001B[31m";
      public static final String ANSI_GREEN = "\u001B[32m";
      public static final String ANSI_CYAN = "\u001B[36m";
      //Colores de fondo
      public static final String ANSI_BLACK_BACKGROUND = "\u001B[30m";
    @Override
    public void start(Stage primaryStage) throws Exception {
        Compra c = new Compra();
        Person p = new Person();
        DAOCompra daocompra = new JDBCCompra();
	System.out.println("Dime que base de datos vas a usar (sqlite o mysql)");
                Scanner sc = new Scanner(System.in);
                String respuesta1 = sc.nextLine();
                if (respuesta1.equalsIgnoreCase("sqlite")) {
                        daocompra.crearSqlite();
                } else if (respuesta1.equalsIgnoreCase("mysql")) {
                        daocompra.crearMysql();
                } else {
                        System.out.println("No seleccionaste ninguna base de datos");
                        System.out.println("Esperamos que la próxima selecciones una disponible");
                        return;
                }
        ArrayList<Compra> lista1 = new ArrayList<Compra>();
        Double precio = 0.0;
        //Cartel de bienvenido
        Label bienvenido = new Label("BIENVENIDO");
        bienvenido.setStyle("-fx-font-size: 70px;" +
                            "-fx-text-fill: white;");
        bienvenido.setLayoutX(35);
        bienvenido.setLayoutY(175);
        //Nombre de la tienda, que aparece en la esquina inferior derecha
        Label nombretienda = new Label ("Tienda de fruta e informatica");
        nombretienda.setLayoutX(300);
        nombretienda.setLayoutY(480);
        
        //boton que cubre el color de fondo, que se ira agrandando
        Button btn = new Button();
        btn.setStyle("-fx-background-radius: 5em; " +
                     "-fx-min-width: 3px; " +
                     "-fx-min-height: 3px; " +
                     "-fx-max-width: 1000px; " +
                     "-fx-max-height: 1000px;" +
                     "-fx-background-color: black;");
        Button fruteria=new Button("fruteria");
        fruteria.setStyle("-fx-background-radius: 5em; " +
                         "-fx-width: 200px; " +
                         "-fx-height: 200px;" +
                         "-fx-background-color: green;"+
                         "-fx-font-family: Impact;"+
                         "-fx-font-size: 20px;"+
                         "-fx-border-color:black;"+
                         "-fx-border-radius:5em;");
        fruteria.setLayoutX(100);
        fruteria.setLayoutY(-250); 

        Button informatica=new Button("informatica");
        informatica.setStyle("-fx-background-radius: 5em; " +
                                           "-fx-width: 200px; " +
                                           "-fx-height: 200px;" +
                                           "-fx-background-color: red;"+
                                           "-fx-font-family: Impact;"+
                                           "-fx-font-size: 20px;"+
                                           "-fx-border-color:black;"+
                                           "-fx-border-radius:5em;");
        informatica.setLayoutX(700);
        informatica.setLayoutY(200);

        Group group = new Group(btn,bienvenido,nombretienda,fruteria,informatica);
        //escenas
        Scene scene = new Scene(group, 500, 500);

        
        //TRANSICCIONES
        //transiccion del boton de fondo
        Duration duration = Duration.millis(3000);
        ScaleTransition scaleTransition = new ScaleTransition(duration, btn);
        scaleTransition.setByX(100);
        scaleTransition.setByY(100);
        scaleTransition.play();
        //transiccion del cartel de bienvenido
        Duration duration2 = Duration.millis(3000);
        TranslateTransition transition = new TranslateTransition(duration2, bienvenido);
        transition.setByY(-175);
        transition.play();
        //transiccion de botones
        Duration duration3 = Duration.millis(4000);
        
        TranslateTransition transitioninformatica = new TranslateTransition(duration3, informatica);
        transitioninformatica.setByX(-450);
        transitioninformatica.play();
        primaryStage.setScene(scene);
        TranslateTransition transitionfruteria = new TranslateTransition(duration3, fruteria);
        transitionfruteria.setByY(450);
        transitionfruteria.play();
        primaryStage.show();
        fruteria.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                try {
                            //boton que cubre el color de fondo, que se ira agrandando
                            Button btn = new Button();
                            btn.setStyle("-fx-background-radius: 5em; " +
                                         "-fx-min-width: 3px; " +
                                         "-fx-min-height: 3px; " +
                                         "-fx-max-width: 1000px; " +
                                         "-fx-max-height: 1000px;" +
                                         "-fx-background-color: black;");
                            //boton para comprar
                            Button comprar=new Button("Comprar");
                            comprar.setStyle("-fx-background-radius: 5em; " +
                                             "-fx-width: 200px; " +
                                             "-fx-height: 200px;" +
                                             "-fx-background-color: green;"+
                                             "-fx-font-family: Impact;"+
                                             "-fx-font-size: 20px;"+
                                             "-fx-border-color:black;"+
                                             "-fx-border-radius:5em;"); 

                            comprar.setLayoutX(-400);
                            comprar.setLayoutY(200);
                            //boton para consultar
                            Button consultar=new Button("Consultar");
                            consultar.setStyle("-fx-background-radius: 5em; " +
                                               "-fx-width: 200px; " +
                                               "-fx-height: 200px;" +
                                               "-fx-background-color: white;"+
                                               "-fx-font-family: Impact;"+
                                               "-fx-font-size: 20px;"+
                                               "-fx-border-color:black;"+
                                               "-fx-border-radius:5em;");
                            consultar.setLayoutX(200);
                            consultar.setLayoutY(800);
                            //boton para salir
                            Button salir = new Button ("Salir");
                            salir.setStyle("-fx-background-radius: 5em; " +
                                           "-fx-width: 200px; " +
                                           "-fx-height: 200px;" +
                                           "-fx-background-color: red;"+
                                           "-fx-font-family: Impact;"+
                                           "-fx-font-size: 20px;"+
                                           "-fx-border-color:black;"+
                                           "-fx-border-radius:5em;");
                            salir.setLayoutX(800);
                            salir.setLayoutY(200);
                            //grupos donde se guardan todas las variables
                            Group group1 = new Group(btn,bienvenido,nombretienda,comprar,consultar,salir);
                            //escenas
                            Scene scene1 = new Scene(group1, 500, 500);
                            
                            //transiccion de botones
                            //transiccion del boton de fondo
                            Duration duration5 = Duration.millis(3000);
                            ScaleTransition scaleTransition = new ScaleTransition(duration5, btn);
                            scaleTransition.setByX(100);
                            scaleTransition.setByY(100);
                            scaleTransition.play();
                            Duration duration4 = Duration.millis(4000);
                            TranslateTransition transitionconsultar = new TranslateTransition(duration4, consultar);
                            transitionconsultar.setByY(-450);
                            transitionconsultar.play();
                            TranslateTransition transitionsalir = new TranslateTransition(duration4, salir);
                            transitionsalir.setByX(-450);
                            transitionsalir.play();
                            TranslateTransition transitioncomprar = new TranslateTransition(duration4, comprar);
                            transitioncomprar.setByX(450);
                            transitioncomprar.play();
                            //set de la escena, y mostrar
                            primaryStage.setScene(scene1);
                            primaryStage.show();
                            comprar.setOnAction(new EventHandler<ActionEvent>(){
                                @Override public void handle(ActionEvent e){
                                    try {
                                        //para introducir nombre
                                        Label nombre = new Label("Nombre:");
                                        nombre.setStyle("-fx-font-size: 20px;" +
                                                                "-fx-text-fill: white;");
                                        TextField introducirn = new TextField ();
                                        introducirn.setPrefWidth(120);
                                        HBox hb = new HBox();
                                        hb.getChildren().addAll(nombre, introducirn);
                                        hb.setSpacing(10);
                                        hb.setLayoutX(35);
                                        hb.setLayoutY(10);
                                        //para introducir ID de compra
                                        Label id = new Label("ID:");
                                        id.setStyle("-fx-font-size: 20px;" +
                                                                "-fx-text-fill: white;");
                                        TextField introduciri = new TextField ();
                                        introduciri.setPrefWidth(120);
                                        HBox hb2 = new HBox();
                                        hb2.getChildren().addAll(id, introduciri);
                                        hb2.setSpacing(10);
                                        hb2.setLayoutX(300);
                                        hb2.setLayoutY(10);
                                        //PRODUCTO1
                                        //imagen
                                        Image producto1 = new Image(new FileInputStream("src/main/resources/imagenes/peras.jpg"));
                                        ImageView peras = new ImageView(producto1); 
                                        peras.setLayoutX(35);
                                        peras.setLayoutY(60);
                                        peras.setFitHeight(75);
                                        peras.setFitWidth(75);
                                        //texto
                                        Label nombrePeras = new Label("PERAS");
                                        nombrePeras.setStyle("-fx-font-size: 25px;"+
                                                             "-fx-font-family: Impact;" +
                                                             "-fx-text-fill: white;");
                                        nombrePeras.setLayoutX(120);
                                        nombrePeras.setLayoutY(55);
                                        //spinner
                                        Spinner<Double> perasSpinner = new Spinner<Double>(0.0, 1000.0, 0,0.1);
                                        SpinnerValueFactory.DoubleSpinnerValueFactory perasFactory = (SpinnerValueFactory.DoubleSpinnerValueFactory) perasSpinner.getValueFactory();
                                        Double imin1 = perasFactory.getMin(); // 0
                                        Double imax1 = perasFactory.getMax(); // 10
                                        Double istep1 = perasFactory.getAmountToStepBy(); // 1
                                        perasSpinner.setLayoutX(120);
                                        perasSpinner.setLayoutY(100); 
                                        perasSpinner.setPrefWidth(70);
                                        //PRODUCTO2
                                        //imagen
                                        Image producto2 = new Image(new FileInputStream("src/main/resources/imagenes/sandias.jpg"));
                                        ImageView sandias = new ImageView(producto2); 
                                        sandias.setLayoutX(290);
                                        sandias.setLayoutY(60);
                                        sandias.setFitHeight(75);
                                        sandias.setFitWidth(75);
                                        //texto
                                        Label nombreSandias = new Label("SANDIAS");
                                        nombreSandias.setStyle("-fx-font-size: 25px;"+
                                                               "-fx-font-family: Impact;"+
                                                             "-fx-text-fill: white;");
                                        nombreSandias.setLayoutX(375);
                                        nombreSandias.setLayoutY(55);
                                        //spinner
                                        Spinner<Double> sandiasSpinner = new Spinner<Double>(0.0, 1000.0, 0, 0.1);
                                        SpinnerValueFactory.DoubleSpinnerValueFactory sandiasFactory = (SpinnerValueFactory.DoubleSpinnerValueFactory) sandiasSpinner.getValueFactory();
                                        Double imin2 = sandiasFactory.getMin(); // 0
                                        Double imax2 = sandiasFactory.getMax(); // 1000
                                        Double istep2 = sandiasFactory.getAmountToStepBy(); // 1
                                        sandiasSpinner.setLayoutX(375);
                                        sandiasSpinner.setLayoutY(100); 
                                        sandiasSpinner.setPrefWidth(70);
                                        //grupo de producto 1 y 2
                                        Group group2a= new Group(peras,nombrePeras,perasSpinner,sandias,nombreSandias,sandiasSpinner);
                                        //PRODUCTO3
                                        //imagen
                                        Image producto3 = new Image(new FileInputStream("src/main/resources/imagenes/mangos.jpg"));
                                        ImageView mangos = new ImageView(producto3); 
                                        mangos.setLayoutX(290);
                                        mangos.setLayoutY(180);
                                        mangos.setFitHeight(75);
                                        mangos.setFitWidth(75);
                                        //texto
                                        Label nombreMangos = new Label("MANG0S");
                                        nombreMangos.setStyle("-fx-font-size: 25px;"+
                                                              "-fx-font-family: Impact;"+
                                                             "-fx-text-fill: white;");
                                        nombreMangos.setLayoutX(375);
                                        nombreMangos.setLayoutY(175);
                                        //spinner
                                        Spinner<Double> mangosSpinner = new Spinner<Double>(0.0, 1000.0, 0, 0.1);
                                        SpinnerValueFactory.DoubleSpinnerValueFactory mangosFactory = (SpinnerValueFactory.DoubleSpinnerValueFactory) mangosSpinner.getValueFactory();
                                        Double imin3 = mangosFactory.getMin();
                                        Double imax3 = mangosFactory.getMax();
                                        Double istep3 = mangosFactory.getAmountToStepBy();
                                        mangosSpinner.setLayoutX(375);
                                        mangosSpinner.setLayoutY(220); 
                                        mangosSpinner.setPrefWidth(70);
                                        //PRODUCTO4
                                        //imagen
                                        Image producto4 = new Image(new FileInputStream("src/main/resources/imagenes/manzanas.jpg"));
                                        ImageView manzanas = new ImageView(producto4); 
                                        manzanas.setLayoutX(35);
                                        manzanas.setLayoutY(180);
                                        manzanas.setFitHeight(75);
                                        manzanas.setFitWidth(75);
                                        //texto
                                        Label nombreManzanas = new Label("MANZANAS");
                                        nombreManzanas.setStyle("-fx-font-size: 25px;"+
                                                                "-fx-font-family: Impact;"+
                                                             "-fx-text-fill: white;");
                                        nombreManzanas.setLayoutX(120);
                                        nombreManzanas.setLayoutY(175);
                                        //spinner
                                        Spinner<Double> manzanasSpinner = new Spinner<Double>(0.0, 1000.0, 0,0.1);
                                        SpinnerValueFactory.DoubleSpinnerValueFactory manzanasFactory = (SpinnerValueFactory.DoubleSpinnerValueFactory) manzanasSpinner.getValueFactory();
                                        Double imin4 = manzanasFactory.getMin();
                                        Double imax4 = manzanasFactory.getMax();
                                        Double istep4 = manzanasFactory.getAmountToStepBy();
                                        manzanasSpinner.setLayoutX(120);
                                        manzanasSpinner.setLayoutY(220); 
                                        manzanasSpinner.setPrefWidth(70);
                                        //grupo de producto 3 y 4
                                        Group group2b= new Group(mangos,nombreMangos,mangosSpinner,manzanas,nombreManzanas,manzanasSpinner);
                                        //PRODUCTO5
                                        //imagen
                                        Image producto5 = new Image(new FileInputStream("src/main/resources/imagenes/fresas.jpg"));
                                        ImageView fresas = new ImageView(producto5); 
                                        fresas.setLayoutX(35);
                                        fresas.setLayoutY(300);
                                        fresas.setFitHeight(75);
                                        fresas.setFitWidth(75);
                                        //texto
                                        Label nombreFresas = new Label("FRESAS");
                                        nombreFresas.setStyle("-fx-font-size: 25px;"+
                                                              "-fx-font-family: Impact;"+
                                                             "-fx-text-fill: white;");
                                        nombreFresas.setLayoutX(120);
                                        nombreFresas.setLayoutY(290);
                                        //spinner
                                        Spinner<Double> fresasSpinner = new Spinner<Double>(0.0, 1000.0, 0, 0.1);
                                        SpinnerValueFactory.DoubleSpinnerValueFactory fresasFactory = (SpinnerValueFactory.DoubleSpinnerValueFactory) fresasSpinner.getValueFactory();
                                        Double imin5 = fresasFactory.getMin();
                                        Double imax5 = fresasFactory.getMax();
                                        Double istep5 = fresasFactory.getAmountToStepBy();
                                        fresasSpinner.setLayoutX(120);
                                        fresasSpinner.setLayoutY(330); 
                                        fresasSpinner.setPrefWidth(70);
                                        //PRODUCTO6
                                        //imagen
                                        Image producto6 = new Image(new FileInputStream("src/main/resources/imagenes/naranjas.jpg"));
                                        ImageView naranjas = new ImageView(producto6); 
                                        naranjas.setLayoutX(290);
                                        naranjas.setLayoutY(300);
                                        naranjas.setFitHeight(75);
                                        naranjas.setFitWidth(75);
                                        //texto
                                        Label nombreNaranjas = new Label("NARANJAS");
                                        nombreNaranjas.setStyle("-fx-font-size: 25px;"+
                                                                "-fx-font-family: Impact;"+
                                                             "-fx-text-fill: white;");
                                        nombreNaranjas.setLayoutX(375);
                                        nombreNaranjas.setLayoutY(290);
                                        //spinner
                                        Spinner<Double> naranjasSpinner = new Spinner<Double>(0.0, 1000.0, 0, 0.1);
                                        SpinnerValueFactory.DoubleSpinnerValueFactory naranjasFactory = (SpinnerValueFactory.DoubleSpinnerValueFactory) naranjasSpinner.getValueFactory();
                                        Double imin6 = naranjasFactory.getMin();
                                        Double imax6 = naranjasFactory.getMax();
                                        Double istep6 = naranjasFactory.getAmountToStepBy();
                                        naranjasSpinner.setLayoutX(375);
                                        naranjasSpinner.setLayoutY(330); 
                                        naranjasSpinner.setPrefWidth(70);
                                        //grupo de producto 5 y 6
                                        Group group2c= new Group(fresas,nombreFresas,fresasSpinner,naranjas,nombreNaranjas,naranjasSpinner);
                                        //boton acceptar
                                        Button acceptar = new Button("Aceptar");
                                        acceptar.setLayoutX(150);
                                        acceptar.setLayoutY(430);
                                        //boton cancelar
                                        Button cancelar = new Button("Cancelar");
                                        cancelar.setLayoutX(300);
                                        cancelar.setLayoutY(430);
                                        //grupo final y escena + cambio de escena
                                        Group group2= new Group(hb,hb2,group2a,group2b,group2c,acceptar,cancelar);
                                        Scene scene2 = new Scene(group2,500,500,Color.BLACK);
                                        primaryStage.setScene(scene2);
                                        primaryStage.show();
                                        acceptar.setOnAction(new EventHandler<ActionEvent>(){
                                            @Override public void handle(ActionEvent e){
                                                if (!introducirn.getText().equals("") || !introduciri.getText().equals("")){
                                                    Double preciototal=0.0;
                                                    if (naranjasSpinner.getValue() != 0 || manzanasSpinner.getValue() != 0 || mangosSpinner.getValue() != 0 || sandiasSpinner.getValue() != 0 || fresasSpinner.getValue() != 0 || perasSpinner.getValue() != 0){

                                                        p.setName(introducirn.getText());
                                                        int idint=Integer.parseInt(introduciri.getText());
                                                        c.setId(idint);
                                                        Date fechaFactura = new Date();
                                                        c.setFecha(fechaFactura);
                                                        c.getPer().setName(p.getName());
                                                        System.out.println("");                         
                                                        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + " ***Ticket de la compra***" + ANSI_RESET);
                                                        System.out.println("");
                                                        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + "Nombre: "+p.getName() + "    ID compra: " + c.getId() + "  Fecha:  " +c.getFecha() +ANSI_RESET);
                                                        System.out.println("");
                                                        System.out.println("PRODUCTO    CANTIDAD    PRECIO");
                                                        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + "-------------------------------------" + ANSI_RESET);
                                                        
                                                    }
                                                    if (naranjasSpinner.getValue()>0){
                                                        c.setCant(naranjasSpinner.getValue());
                                                        Double precio1 = 1.3;
                                                        c.getArt().setPrecio(precio1 * naranjasSpinner.getValue());
                                                        c.getArt().setNombre("Naranjas");
                                                        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + c.getArt().getNombre()+"    " + c.getCant() + "    kg. "   + c.getArt().getPrecio() + ANSI_RESET);
                                                        naranjasSpinner.getValueFactory().setValue(0.0);
                                                        preciototal=preciototal + c.getArt().getPrecio();
                                                        lista1.add(c);
                                                        daocompra.grabar(c);

                                                    }
                                                    if (manzanasSpinner.getValue()>0){
                                                        c.setCant(manzanasSpinner.getValue());
                                                        Double precio2 = 3.7;
                                                        c.getArt().setPrecio(precio2 * manzanasSpinner.getValue());
                                                        c.getArt().setNombre("Manzanas");
                                                        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + c.getArt().getNombre()+"    " + c.getCant() + "    kg. "   + c.getArt().getPrecio() + ANSI_RESET);
                                                        manzanasSpinner.getValueFactory().setValue(0.0);
                                                        preciototal=preciototal + c.getArt().getPrecio();
                                                        lista1.add(c);
                                                        daocompra.grabar(c);
                                                    }
                                                    if (mangosSpinner.getValue()>0){
                                                        c.setCant(mangosSpinner.getValue());
                                                        Double precio3 = 7.2;
                                                        c.getArt().setPrecio(precio3 * mangosSpinner.getValue());
                                                        c.getArt().setNombre("Mangos");
                                                        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN +c.getArt().getNombre()+"    " + c.getCant() + "    kg. "   + c.getArt().getPrecio() + ANSI_RESET);
                                                        mangosSpinner.getValueFactory().setValue(0.0);
                                                        preciototal=preciototal + c.getArt().getPrecio();
                                                        lista1.add(c);
                                                        daocompra.grabar(c);
                                                    }
                                                    if (sandiasSpinner.getValue()>0){
                                                        c.setCant(sandiasSpinner.getValue());
                                                        Double precio4 = 11.2;
                                                        c.getArt().setPrecio(precio4 * sandiasSpinner.getValue());
                                                        c.getArt().setNombre("Sandias");
														System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + c.getArt().getNombre()+"    " + c.getCant() + "    kg. "   + c.getArt().getPrecio() + ANSI_RESET);                                                        sandiasSpinner.getValueFactory().setValue(0.0);
                                                        preciototal=preciototal + c.getArt().getPrecio();
                                                        lista1.add(c);
                                                        daocompra.grabar(c);
                                                    }
                                                    if (fresasSpinner.getValue()>0){
                                                        c.setCant(fresasSpinner.getValue());
                                                        Double precio5 = 1.5;
                                                        c.getArt().setPrecio(precio5 * fresasSpinner.getValue());
                                                        c.getArt().setNombre("Fresas");
                                                        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + c.getArt().getNombre()+"    " + c.getCant() + "    kg. "   + c.getArt().getPrecio() + ANSI_RESET);
                                                        fresasSpinner.getValueFactory().setValue(0.0);
                                                        preciototal=preciototal + c.getArt().getPrecio();
                                                        lista1.add(c);
                                                        daocompra.grabar(c);
                                                    }
                                                    if (perasSpinner.getValue()>0){
                                                        c.setCant(perasSpinner.getValue());
                                                        Double precio6 = 1.7;
                                                        c.getArt().setPrecio(precio6 * perasSpinner.getValue());
                                                        c.getArt().setNombre("Peras");
                                                        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + c.getArt().getNombre()+"    " + c.getCant() + "    kg. "   + c.getArt().getPrecio() + ANSI_RESET);
                                                        perasSpinner.getValueFactory().setValue(0.0);
                                                        preciototal=preciototal + c.getArt().getPrecio();
                                                        lista1.add(c);
                                                        daocompra.grabar(c);
                                                    }
                                                        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN +"----------------------------------------"+ ANSI_RESET);
                                                        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN +"Total:               " +  preciototal + ANSI_RESET);
                                                        //for (Compra abc : lista1){
                                                            //System.out.println(abc.getPer().getName());                           
                                                        //}

                                                    
                                            }
                                                primaryStage.setScene(scene);
                                                primaryStage.show();
                                            }
                                        });
                                        cancelar.setOnAction(new EventHandler<ActionEvent>(){
                                            @Override public void handle(ActionEvent e){
                                                primaryStage.setScene(scene1);
                                                primaryStage.show();
                                            }
                                        });
                                    }
                                    catch(Exception ex) {
                                         System.out.println(ANSI_BLACK_BACKGROUND + ANSI_RED +"Error, no se encuentra la imagen." + ANSI_RESET);
                                    }
                                }
                            });
                            
                            consultar.setOnAction(new EventHandler<ActionEvent>(){
                                            @Override public void handle(ActionEvent e){
                                                Button consultarid = new Button("ID");
                            consultarid.setStyle("-fx-background-radius: 5em; " +
                                             "-fx-width: 200px; " +
                                             "-fx-height: 200px;" +
                                             "-fx-background-color: green;"+
                                             "-fx-font-family: Impact;"+
                                             "-fx-font-size: 20px;"+
                                             "-fx-border-color:black;"+
                                             "-fx-border-radius:5em;"); 

                            consultarid.setLayoutX(50);
                            consultarid.setLayoutY(40);
                            Button consultarn = new Button ("Nombre");
                            consultarn.setStyle("-fx-background-radius: 5em; " +
                                           "-fx-max-width: 200px; " +
                                           "-fx-max-height: 200px;" +
                                           "-fx-background-color: red;"+
                                           "-fx-font-family: Impact;"+
                                           "-fx-font-size: 20px;"+
                                           "-fx-border-color:black;"+
                                           "-fx-border-radius:5em;");
                            consultarn.setLayoutX(350);
                            consultarn.setLayoutY(40);

                            Button atrasBurbuja = new Button ("Atras");
                            atrasBurbuja.setStyle("-fx-background-radius: 5em; " +
                                           "-fx-max-width: 200px; " +
                                           "-fx-max-height: 200px;" +
                                           "-fx-background-color: white;"+
                                           "-fx-font-family: Impact;"+
                                           "-fx-font-size: 20px;"+
                                           "-fx-border-color:black;"+
                                           "-fx-border-radius:5em;");
                            atrasBurbuja.setLayoutX(200);
                            atrasBurbuja.setLayoutY(40);


                            Group group3= new Group(consultarid,consultarn,atrasBurbuja);
                            Scene scene3 = new Scene(group3,500,500,Color.BLACK);
                            primaryStage.setScene(scene3);
                            primaryStage.show();



                            atrasBurbuja.setOnAction(new EventHandler<ActionEvent>(){
                                @Override public void handle(ActionEvent e){
                                    primaryStage.setScene(scene);
                                    primaryStage.show();
                                }
                            });

                            //
                            //
                            //
                            //
                            //
                            //
                            //
                            consultarid.setOnAction(new EventHandler<ActionEvent>(){
                                            @Override public void handle(ActionEvent e){
                                                Label introid = new Label("ID");
                                                introid.setStyle("-fx-font-size: 20px;" +
                                                                "-fx-text-fill: white;");
                                                TextField introid2= new TextField();
                                                introid.setPrefWidth(120);
                                                HBox hb = new HBox();
                                                hb.getChildren().addAll(introid,introid2);
                                                hb.setSpacing(10);
                                                hb.setLayoutX(35);
                                                hb.setLayoutY(10);

                                                //Boton aceptar y atras
                                                Button aceptarConsulta = new Button("Aceptar");
                                                Button atrasID = new Button("Atras");
                                                HBox hbxac = new HBox(10);

                                                //Informacion cuando pongo raton encima
                                                aceptarConsulta.setTooltip(new Tooltip("Aceptar y consultar"));
                                                atrasID.setTooltip(new Tooltip("Volver atras"));

                                                //Posicion en este caso abajo_izquierda
                                                hbxac.setAlignment(Pos.BOTTOM_LEFT);
                                                hbxac.getChildren().addAll(aceptarConsulta, atrasID);

                                                //aqui escribes lo que quieres que muestre
                                                Group group4= new Group(hb,aceptarConsulta, atrasID); //aqui metes lo que quieras que muestre
                                                Scene scene4 = new Scene(group4,350,100,Color.BLACK);
                                                primaryStage.setScene(scene4);
                                                primaryStage.show();

                                                //posicion de los botones
                                                aceptarConsulta.setTranslateX(180);
                                                aceptarConsulta.setTranslateY(45);

                                                atrasID.setTranslateX(255);
                                                atrasID.setTranslateY(45);


                                                aceptarConsulta.setOnAction(new EventHandler<ActionEvent>() {
                                                    @Override public void handle(ActionEvent e){
                                                        //Aqui a traves de una interface se comprueba lo escrito en el textfield
                                                        //con la base de datos e imprime la informacion en la terminal
                                                        if(introid2.getText().isEmpty()){
                                                            System.out.println(ANSI_BLACK_BACKGROUND + ANSI_RED + "No ha escrito ningun ID" + ANSI_RESET + "\n");
                                                        }else{
                                                            daocompra.consultari(Integer.parseInt(introid2.getText()));

                                                        }
                                                    }

                                                });

                                                atrasID.setOnAction(new EventHandler<ActionEvent>(){
                                                    @Override public void handle(ActionEvent e){
                                                        primaryStage.setScene(scene3);
                                                        primaryStage.show();
                                                    }
                                                });
                                            }
                                        });


                             consultarn.setOnAction(new EventHandler<ActionEvent>(){
                                            @Override public void handle(ActionEvent e){
                                                Label intron = new Label("Nombre");
                                                intron.setStyle("-fx-font-size: 20px;" +
                                                                "-fx-text-fill: white;");
                                                TextField intron2 = new TextField();
                                                intron.setPrefWidth(120);
                                                HBox hb = new HBox();
                                                hb.getChildren().addAll(intron,intron2);
                                                hb.setSpacing(10);
                                                hb.setLayoutX(35);
                                                hb.setLayoutY(10);

                                                //Boton aceptar y atras
                                                Button aceptarNom = new Button("Aceptar");
                                                Button atrasNom = new Button("Atras");
                                                HBox hbxac = new HBox(10);

                                                //Informacion cuando pongo raton encima
                                                aceptarNom.setTooltip(new Tooltip("Aceptar y consultar"));
                                                atrasNom.setTooltip(new Tooltip("Volver atras"));

                                                //Posicion en este caso abajo_izquierda
                                                hbxac.setAlignment(Pos.BOTTOM_LEFT);
                                                hbxac.getChildren().addAll(aceptarNom, atrasNom);

                                                //aqui escribes lo que quieres que muestre
                                                Group group5= new Group(hb,aceptarNom,atrasNom);//aqui metes lo que quieres que muestre
                                                Scene scene5 = new Scene(group5,350,100,Color.BLACK);
                                                primaryStage.setScene(scene5);
                                                primaryStage.show();

                                                //posicion de los botones
                                                aceptarNom.setTranslateX(180);
                                                aceptarNom.setTranslateY(45);

                                                atrasNom.setTranslateX(255);
                                                atrasNom.setTranslateY(45);

                                                //Accion boton aceptar
                                                aceptarNom.setOnAction(new EventHandler<ActionEvent>() {
                                                    @Override public void handle(ActionEvent e){
                                                        //Aqui a traves de una interface se comprueba lo escrito en el textfield
                                                        //con la base de datos e imprime la informacion en la terminal
                                                        if(intron2.getText().isEmpty()){
                                                            System.out.println(ANSI_BLACK_BACKGROUND + ANSI_RED +"No ha escrito ningun nombre"+ ANSI_RESET + "\n");
                                                        }else{
                                                            daocompra.consultarn(intron2.getText());
                                                        }
                                                    }

                                                });

                                                atrasNom.setOnAction(new EventHandler<ActionEvent>(){
                                                    @Override public void handle(ActionEvent e){
                                                        primaryStage.setScene(scene3);
                                                        primaryStage.show();
                                                    }
                                                });
                                            }
                                        });
                                            }
                                        });
                                        //
                                        //
                                        //
                                        //
                                        //
                                        //
                                        //
                                       salir.setOnAction(new EventHandler<ActionEvent>(){
                                                        @Override public void handle(ActionEvent e){
                                                            System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + "Vuelva pronto." + ANSI_RESET);
                                                            primaryStage.close();
                                                        }
                                                    });
                }
                catch(Exception ex) {

                }

            }
        });
        informatica.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                try {
                            //boton que cubre el color de fondo, que se ira agrandando
                            Button btn = new Button();
                            btn.setStyle("-fx-background-radius: 5em; " +
                                         "-fx-min-width: 3px; " +
                                         "-fx-min-height: 3px; " +
                                         "-fx-max-width: 1000px; " +
                                         "-fx-max-height: 1000px;" +
                                         "-fx-background-color: black;");
                            //boton para comprar
                            Button comprar=new Button("Comprar");
                            comprar.setStyle("-fx-background-radius: 5em; " +
                                             "-fx-width: 200px; " +
                                             "-fx-height: 200px;" +
                                             "-fx-background-color: green;"+
                                             "-fx-font-family: Impact;"+
                                             "-fx-font-size: 20px;"+
                                             "-fx-border-color:black;"+
                                             "-fx-border-radius:5em;"); 

                            comprar.setLayoutX(-400);
                            comprar.setLayoutY(200);
                            //boton para consultar
                            Button consultar=new Button("Consultar");
                            consultar.setStyle("-fx-background-radius: 5em; " +
                                               "-fx-width: 200px; " +
                                               "-fx-height: 200px;" +
                                               "-fx-background-color: white;"+
                                               "-fx-font-family: Impact;"+
                                               "-fx-font-size: 20px;"+
                                               "-fx-border-color:black;"+
                                               "-fx-border-radius:5em;");
                            consultar.setLayoutX(200);
                            consultar.setLayoutY(800);
                            //boton para salir
                            Button salir = new Button ("Salir");
                            salir.setStyle("-fx-background-radius: 5em; " +
                                           "-fx-width: 200px; " +
                                           "-fx-height: 200px;" +
                                           "-fx-background-color: red;"+
                                           "-fx-font-family: Impact;"+
                                           "-fx-font-size: 20px;"+
                                           "-fx-border-color:black;"+
                                           "-fx-border-radius:5em;");
                            salir.setLayoutX(800);
                            salir.setLayoutY(200);
                            //grupos donde se guardan todas las variables
                            Group group1 = new Group(btn,bienvenido,nombretienda,comprar,consultar,salir);
                            //escenas
                            Scene scene1 = new Scene(group1, 500, 500);
                            
                            //transiccion de botones
                            //transiccion del boton de fondo
                            Duration duration5 = Duration.millis(3000);
                            ScaleTransition scaleTransition = new ScaleTransition(duration5, btn);
                            scaleTransition.setByX(100);
                            scaleTransition.setByY(100);
                            scaleTransition.play();
                            Duration duration4 = Duration.millis(4000);
                            TranslateTransition transitionconsultar = new TranslateTransition(duration4, consultar);
                            transitionconsultar.setByY(-450);
                            transitionconsultar.play();
                            TranslateTransition transitionsalir = new TranslateTransition(duration4, salir);
                            transitionsalir.setByX(-450);
                            transitionsalir.play();
                            TranslateTransition transitioncomprar = new TranslateTransition(duration4, comprar);
                            transitioncomprar.setByX(450);
                            transitioncomprar.play();
                            //set de la escena, y mostrar
                            primaryStage.setScene(scene1);
                            primaryStage.show();
                            comprar.setOnAction(new EventHandler<ActionEvent>(){
                                @Override public void handle(ActionEvent e){
                                    try {
                                        //para introducir nombre
                                        Label nombre = new Label("Nombre:");
                                        nombre.setStyle("-fx-font-size: 20px;" +
                                                                "-fx-text-fill: white;");
                                        TextField introducirn = new TextField ();
                                        introducirn.setPrefWidth(120);
                                        HBox hb = new HBox();
                                        hb.getChildren().addAll(nombre, introducirn);
                                        hb.setSpacing(10);
                                        hb.setLayoutX(35);
                                        hb.setLayoutY(10);
                                        //para introducir ID de compra
                                        Label id = new Label("ID:");
                                        id.setStyle("-fx-font-size: 20px;" +
                                                                "-fx-text-fill: white;");
                                        TextField introduciri = new TextField ();
                                        introduciri.setPrefWidth(120);
                                        HBox hb2 = new HBox();
                                        hb2.getChildren().addAll(id, introduciri);
                                        hb2.setSpacing(10);
                                        hb2.setLayoutX(300);
                                        hb2.setLayoutY(10);
                                        //PRODUCTO1
                                        //imagen
                                        Image producto1 = new Image(new FileInputStream("src/main/resources/imagenes/ram.jpg"));
                                        ImageView ram = new ImageView(producto1); 
                                        ram.setLayoutX(35);
                                        ram.setLayoutY(60);
                                        ram.setFitHeight(75);
                                        ram.setFitWidth(75);
                                        //texto
                                        Label nombreRam = new Label("RAM");
                                        nombreRam.setStyle("-fx-font-size: 25px;"+
                                                             "-fx-font-family: Impact;" +
                                                             "-fx-text-fill: white;");
                                        nombreRam.setLayoutX(120);
                                        nombreRam.setLayoutY(55);
                                        //spinner
                                        Spinner<Double> ramSpinner = new Spinner<Double>(0.0, 1000.0, 0,0.1);
                                        SpinnerValueFactory.DoubleSpinnerValueFactory ramFactory = (SpinnerValueFactory.DoubleSpinnerValueFactory) ramSpinner.getValueFactory();
                                        Double imin1 = ramFactory.getMin(); // 0
                                        Double imax1 = ramFactory.getMax(); // 10
                                        Double istep1 = ramFactory.getAmountToStepBy(); // 1
                                        ramSpinner.setLayoutX(120);
                                        ramSpinner.setLayoutY(100); 
                                        ramSpinner.setPrefWidth(70);
                                        //PRODUCTO2
                                        //imagen
                                        Image producto2 = new Image(new FileInputStream("src/main/resources/imagenes/ssd.jpg"));
                                        ImageView ssd = new ImageView(producto2); 
                                        ssd.setLayoutX(290);
                                        ssd.setLayoutY(60);
                                        ssd.setFitHeight(75);
                                        ssd.setFitWidth(75);
                                        //texto
                                        Label nombressd = new Label("SSD");
                                        nombressd.setStyle("-fx-font-size: 25px;"+
                                                               "-fx-font-family: Impact;"+
                                                             "-fx-text-fill: white;");
                                        nombressd.setLayoutX(375);
                                        nombressd.setLayoutY(55);
                                        //spinner
                                        Spinner<Double> ssdSpinner = new Spinner<Double>(0.0, 1000.0, 0, 0.1);
                                        SpinnerValueFactory.DoubleSpinnerValueFactory ssdFactory = (SpinnerValueFactory.DoubleSpinnerValueFactory) ssdSpinner.getValueFactory();
                                        Double imin2 = ssdFactory.getMin(); // 0
                                        Double imax2 = ssdFactory.getMax(); // 1000
                                        Double istep2 = ssdFactory.getAmountToStepBy(); // 1
                                        ssdSpinner.setLayoutX(375);
                                        ssdSpinner.setLayoutY(100); 
                                        ssdSpinner.setPrefWidth(70);
                                        //grupo de producto 1 y 2
                                        Group group2a= new Group(ram,nombreRam,ramSpinner,ssd,nombressd,ssdSpinner);
                                        //PRODUCTO3
                                        //imagen
                                        Image producto3 = new Image(new FileInputStream("src/main/resources/imagenes/placabase.jpg"));
                                        ImageView placabase = new ImageView(producto3); 
                                        placabase.setLayoutX(290);
                                        placabase.setLayoutY(180);
                                        placabase.setFitHeight(75);
                                        placabase.setFitWidth(75);
                                        //texto
                                        Label nombrePlacaBase = new Label("PLACA BASE");
                                        nombrePlacaBase.setStyle("-fx-font-size: 25px;"+
                                                              "-fx-font-family: Impact;"+
                                                             "-fx-text-fill: white;");
                                        nombrePlacaBase.setLayoutX(375);
                                        nombrePlacaBase.setLayoutY(175);
                                        //spinner
                                        Spinner<Double> placabaseSpinner = new Spinner<Double>(0.0, 1000.0, 0, 0.1);
                                        SpinnerValueFactory.DoubleSpinnerValueFactory placabaseFactory = (SpinnerValueFactory.DoubleSpinnerValueFactory) placabaseSpinner.getValueFactory();
                                        Double imin3 = placabaseFactory.getMin();
                                        Double imax3 = placabaseFactory.getMax();
                                        Double istep3 = placabaseFactory.getAmountToStepBy();
                                        placabaseSpinner.setLayoutX(375);
                                        placabaseSpinner.setLayoutY(220); 
                                        placabaseSpinner.setPrefWidth(70);
                                        //PRODUCTO4
                                        //imagen
                                        Image producto4 = new Image(new FileInputStream("src/main/resources/imagenes/grafica.jpg"));
                                        ImageView grafica = new ImageView(producto4); 
                                        grafica.setLayoutX(35);
                                        grafica.setLayoutY(180);
                                        grafica.setFitHeight(75);
                                        grafica.setFitWidth(75);
                                        //texto
                                        Label nombreGrafica = new Label("GRAFICA");
                                        nombreGrafica.setStyle("-fx-font-size: 25px;"+
                                                                "-fx-font-family: Impact;"+
                                                             "-fx-text-fill: white;");
                                        nombreGrafica.setLayoutX(120);
                                        nombreGrafica.setLayoutY(175);
                                        //spinner
                                        Spinner<Double> graficaSpinner = new Spinner<Double>(0.0, 1000.0, 0,0.1);
                                        SpinnerValueFactory.DoubleSpinnerValueFactory graficaFactory = (SpinnerValueFactory.DoubleSpinnerValueFactory) graficaSpinner.getValueFactory();
                                        Double imin4 = graficaFactory.getMin();
                                        Double imax4 = graficaFactory.getMax();
                                        Double istep4 = graficaFactory.getAmountToStepBy();
                                        graficaSpinner.setLayoutX(120);
                                        graficaSpinner.setLayoutY(220); 
                                        graficaSpinner.setPrefWidth(70);
                                        //grupo de producto 3 y 4
                                        Group group2b= new Group(placabase,nombrePlacaBase,placabaseSpinner,grafica,nombreGrafica,graficaSpinner);
                                        //PRODUCTO5
                                        //imagen
                                        Image producto5 = new Image(new FileInputStream("src/main/resources/imagenes/refrigeracion.jpg"));
                                        ImageView refrigeracion = new ImageView(producto5); 
                                        refrigeracion.setLayoutX(35);
                                        refrigeracion.setLayoutY(300);
                                        refrigeracion.setFitHeight(75);
                                        refrigeracion.setFitWidth(75);
                                        //texto
                                        Label nombreRefrigeracion = new Label("REFRI");
                                        nombreRefrigeracion.setStyle("-fx-font-size: 25px;"+
                                                              "-fx-font-family: Impact;"+
                                                             "-fx-text-fill: white;");
                                        nombreRefrigeracion.setLayoutX(120);
                                        nombreRefrigeracion.setLayoutY(290);
                                        //spinner
                                        Spinner<Double> refrigeracionSpinner = new Spinner<Double>(0.0, 1000.0, 0, 0.1);
                                        SpinnerValueFactory.DoubleSpinnerValueFactory refrigeracionFactory = (SpinnerValueFactory.DoubleSpinnerValueFactory) refrigeracionSpinner.getValueFactory();
                                        Double imin5 = refrigeracionFactory.getMin();
                                        Double imax5 = refrigeracionFactory.getMax();
                                        Double istep5 = refrigeracionFactory.getAmountToStepBy();
                                        refrigeracionSpinner.setLayoutX(120);
                                        refrigeracionSpinner.setLayoutY(330); 
                                        refrigeracionSpinner.setPrefWidth(70);
                                        //PRODUCTO6
                                        //imagen
                                        Image producto6 = new Image(new FileInputStream("src/main/resources/imagenes/fuente.jpg"));
                                        ImageView fuente = new ImageView(producto6); 
                                        fuente.setLayoutX(290);
                                        fuente.setLayoutY(300);
                                        fuente.setFitHeight(75);
                                        fuente.setFitWidth(75);
                                        //texto
                                        Label nombreFuente = new Label("FUENTE");
                                        nombreFuente.setStyle("-fx-font-size: 25px;"+
                                                                "-fx-font-family: Impact;"+
                                                             "-fx-text-fill: white;");
                                        nombreFuente.setLayoutX(375);
                                        nombreFuente.setLayoutY(290);
                                        //spinner
                                        Spinner<Double> fuenteSpinner = new Spinner<Double>(0.0, 1000.0, 0, 0.1);
                                        SpinnerValueFactory.DoubleSpinnerValueFactory fuenteFactory = (SpinnerValueFactory.DoubleSpinnerValueFactory) fuenteSpinner.getValueFactory();
                                        Double imin6 = fuenteFactory.getMin();
                                        Double imax6 = fuenteFactory.getMax();
                                        Double istep6 = fuenteFactory.getAmountToStepBy();
                                        fuenteSpinner.setLayoutX(375);
                                        fuenteSpinner.setLayoutY(330); 
                                        fuenteSpinner.setPrefWidth(70);
                                        //grupo de producto 5 y 6
                                        Group group2c= new Group(refrigeracion,nombreRefrigeracion,refrigeracionSpinner,fuente,nombreFuente,fuenteSpinner);
                                        //boton acceptar
                                        Button acceptar = new Button("Aceptar");
                                        acceptar.setLayoutX(150);
                                        acceptar.setLayoutY(430);
                                        //boton cancelar
                                        Button cancelar = new Button("Cancelar");
                                        cancelar.setLayoutX(300);
                                        cancelar.setLayoutY(430);
                                        //grupo final y escena + cambio de escena
                                        Group group2= new Group(hb,hb2,group2a,group2b,group2c,acceptar,cancelar);
                                        Scene scene2 = new Scene(group2,500,500,Color.BLACK);
                                        primaryStage.setScene(scene2);
                                        primaryStage.show();
                                        acceptar.setOnAction(new EventHandler<ActionEvent>(){
                                            @Override public void handle(ActionEvent e){
                                                if (!introducirn.getText().equals("") || !introduciri.getText().equals("")){
                                                    Double preciototal=0.0;
                                                    if (refrigeracionSpinner.getValue() != 0 || fuenteSpinner.getValue() != 0 || placabaseSpinner.getValue() != 0 || ssdSpinner.getValue() != 0 || graficaSpinner.getValue() != 0 || ramSpinner.getValue() != 0){

                                                        p.setName(introducirn.getText());
                                                        int idint=Integer.parseInt(introduciri.getText());
                                                        c.setId(idint);
                                                        Date fechaFactura = new Date();
                                                        c.setFecha(fechaFactura);
                                                        c.getPer().setName(p.getName());
                                                        System.out.println("");                         
                                                        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + " ***Ticket de la compra***" + ANSI_RESET);
                                                        System.out.println("");
                                                        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + "Nombre: "+p.getName() + "    ID compra: " + c.getId() + "  Fecha:  " +c.getFecha() + ANSI_RESET);
                                                        System.out.println("");
                                                        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + "PRODUCTO    CANTIDAD    PRECIO" + ANSI_RESET);
                                                        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + "-------------------------------------" + ANSI_RESET);
                                                        
                                                    }
                                                    if (fuenteSpinner.getValue()>0){
                                                        c.setCant(fuenteSpinner.getValue());
                                                        Double precio1 = 70.0;
                                                        c.getArt().setPrecio(precio1 * fuenteSpinner.getValue());
                                                        c.getArt().setNombre("Fuente");
                                                        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + c.getArt().getNombre()+"    " + c.getCant() + "    " + c.getArt().getPrecio() + ANSI_RESET);
                                                        fuenteSpinner.getValueFactory().setValue(0.0);
                                                        preciototal=preciototal + c.getArt().getPrecio();
                                                        lista1.add(c);
                                                        daocompra.grabar(c);

                                                    }
                                                    if (ssdSpinner.getValue()>0){
                                                        c.setCant(ssdSpinner.getValue());
                                                        Double precio2 = 50.0;
                                                        c.getArt().setPrecio(precio2 * ssdSpinner.getValue());
                                                        c.getArt().setNombre("SSD");
                                                        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + c.getArt().getNombre()+"    " + c.getCant() + "    " + c.getArt().getPrecio() + ANSI_RESET);
                                                        ssdSpinner.getValueFactory().setValue(0.0);
                                                        preciototal=preciototal + c.getArt().getPrecio();
                                                        lista1.add(c);
                                                        daocompra.grabar(c);
                                                    }
                                                    if (graficaSpinner.getValue()>0){
                                                        c.setCant(graficaSpinner.getValue());
                                                        Double precio3 = 120.0;
                                                        c.getArt().setPrecio(precio3 * graficaSpinner.getValue());
                                                        c.getArt().setNombre("Grafica");
                                                        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + c.getArt().getNombre()+"    " + c.getCant() + "    " + c.getArt().getPrecio() + ANSI_RESET);
                                                        graficaSpinner.getValueFactory().setValue(0.0);
                                                        preciototal=preciototal + c.getArt().getPrecio();
                                                        lista1.add(c);
                                                        daocompra.grabar(c);
                                                    }
                                                    if (refrigeracionSpinner.getValue()>0){
                                                        c.setCant(refrigeracionSpinner.getValue());
                                                        Double precio4 = 100.0;
                                                        c.getArt().setPrecio(precio4 * refrigeracionSpinner.getValue());
                                                        c.getArt().setNombre("Refrigeracion");
                                                        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + c.getArt().getNombre()+"    " + c.getCant() + "    " + c.getArt().getPrecio() + ANSI_RESET);
                                                        refrigeracionSpinner.getValueFactory().setValue(0.0);
                                                        preciototal=preciototal + c.getArt().getPrecio();
                                                        lista1.add(c);
                                                        daocompra.grabar(c);
                                                    }
                                                    if (placabaseSpinner.getValue()>0){
                                                        c.setCant(placabaseSpinner.getValue());
                                                        Double precio5 = 200.0;
                                                        c.getArt().setPrecio(precio5 * placabaseSpinner.getValue());
                                                        c.getArt().setNombre("Placa base");
                                                        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + c.getArt().getNombre()+"    " + c.getCant() + "    " + c.getArt().getPrecio() + ANSI_RESET);
                                                        placabaseSpinner.getValueFactory().setValue(0.0);
                                                        preciototal=preciototal + c.getArt().getPrecio();
                                                        lista1.add(c);
                                                        daocompra.grabar(c);
                                                    }
                                                    if (ramSpinner.getValue()>0){
                                                        c.setCant(ramSpinner.getValue());
                                                        Double precio6 = 80.0;
                                                        c.getArt().setPrecio(precio6 * ramSpinner.getValue());
                                                        c.getArt().setNombre("Ram");
                                                        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + c.getArt().getNombre()+"    " + c.getCant() + "    " + c.getArt().getPrecio() + ANSI_RESET);
                                                        ramSpinner.getValueFactory().setValue(0.0);
                                                        preciototal=preciototal + c.getArt().getPrecio();
                                                        lista1.add(c);
                                                        daocompra.grabar(c);
                                                    }
                                                        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN +"----------------------------------------" + ANSI_RESET);
                                                        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + "Total:               " +  preciototal+ ANSI_RESET);
                                                        //for (Compra abc : lista1){
                                                            //System.out.println(abc.getPer().getName());                           
                                                        //}

                                                    
                                            }
                                                primaryStage.setScene(scene);
                                                primaryStage.show();
                                            }
                                        });
                                        cancelar.setOnAction(new EventHandler<ActionEvent>(){
                                            @Override public void handle(ActionEvent e){
                                                primaryStage.setScene(scene1);
                                                primaryStage.show();
                                            }
                                        });
                                    }
                                    catch(Exception ex) {
                                         System.out.println(ANSI_BLACK_BACKGROUND + ANSI_RED + "Error, no se encuentra la imagen." + ANSI_RESET);
                                    }
                                }
                            });
                            
                            consultar.setOnAction(new EventHandler<ActionEvent>(){
                                            @Override public void handle(ActionEvent e){
                                                Button consultarid = new Button("ID");
                            consultarid.setStyle("-fx-background-radius: 5em; " +
                                             "-fx-width: 200px; " +
                                             "-fx-height: 200px;" +
                                             "-fx-background-color: green;"+
                                             "-fx-font-family: Impact;"+
                                             "-fx-font-size: 20px;"+
                                             "-fx-border-color:black;"+
                                             "-fx-border-radius:5em;"); 

                            consultarid.setLayoutX(50);
                            consultarid.setLayoutY(40);
                            Button consultarn = new Button ("Nombre");
                            consultarn.setStyle("-fx-background-radius: 5em; " +
                                           "-fx-max-width: 200px; " +
                                           "-fx-max-height: 200px;" +
                                           "-fx-background-color: red;"+
                                           "-fx-font-family: Impact;"+
                                           "-fx-font-size: 20px;"+
                                           "-fx-border-color:black;"+
                                           "-fx-border-radius:5em;");
                            consultarn.setLayoutX(350);
                            consultarn.setLayoutY(40);

                            Button atrasBurbuja = new Button ("Atras");
                            atrasBurbuja.setStyle("-fx-background-radius: 5em; " +
                                           "-fx-max-width: 200px; " +
                                           "-fx-max-height: 200px;" +
                                           "-fx-background-color: white;"+
                                           "-fx-font-family: Impact;"+
                                           "-fx-font-size: 20px;"+
                                           "-fx-border-color:black;"+
                                           "-fx-border-radius:5em;");
                            atrasBurbuja.setLayoutX(200);
                            atrasBurbuja.setLayoutY(40);


                            Group group3= new Group(consultarid,consultarn,atrasBurbuja);
                            Scene scene3 = new Scene(group3,500,100,Color.BLACK);
                            primaryStage.setScene(scene3);
                            primaryStage.show();



                            atrasBurbuja.setOnAction(new EventHandler<ActionEvent>(){
                                @Override public void handle(ActionEvent e){
                                    primaryStage.setScene(scene);
                                    primaryStage.show();
                                }
                            });

                            //
                            //
                            //
                            //
                            //
                            //
                            //
                            consultarid.setOnAction(new EventHandler<ActionEvent>(){
                                            @Override public void handle(ActionEvent e){
                                                Label introid = new Label("ID");
                                                introid.setStyle("-fx-font-size: 20px;" +
                                                                "-fx-text-fill: white;");
                                                TextField introid2= new TextField();
                                                introid.setPrefWidth(120);
                                                HBox hb = new HBox();
                                                hb.getChildren().addAll(introid,introid2);
                                                hb.setSpacing(10);
                                                hb.setLayoutX(35);
                                                hb.setLayoutY(10);

                                                //Boton aceptar y atras
                                                Button aceptarConsulta = new Button("Aceptar");
                                                Button atrasID = new Button("Atras");
                                                HBox hbxac = new HBox(10);

                                                //Informacion cuando pongo raton encima
                                                aceptarConsulta.setTooltip(new Tooltip("Aceptar y consultar"));
                                                atrasID.setTooltip(new Tooltip("Volver atras"));

                                                //Posicion en este caso abajo_izquierda
                                                hbxac.setAlignment(Pos.BOTTOM_LEFT);
                                                hbxac.getChildren().addAll(aceptarConsulta, atrasID);

                                                //aqui escribes lo que quieres que muestre
                                                Group group4= new Group(hb,aceptarConsulta, atrasID); //aqui metes lo que quieras que muestre
                                                Scene scene4 = new Scene(group4,350,100,Color.BLACK);
                                                primaryStage.setScene(scene4);
                                                primaryStage.show();

                                                //posicion de los botones
                                                aceptarConsulta.setTranslateX(180);
                                                aceptarConsulta.setTranslateY(45);

                                                atrasID.setTranslateX(255);
                                                atrasID.setTranslateY(45);


                                                aceptarConsulta.setOnAction(new EventHandler<ActionEvent>() {
                                                    @Override public void handle(ActionEvent e){
                                                        //Aqui a traves de una interface se comprueba lo escrito en el textfield
                                                        //con la base de datos e imprime la informacion en la terminal
                                                        if(introid2.getText().isEmpty()){
                                                            System.out.println(ANSI_BLACK_BACKGROUND + ANSI_RED + "No ha escrito ningun ID" + ANSI_RESET + "\n");
                                                        }else{
                                                            daocompra.consultari(Integer.parseInt(introid2.getText()));

                                                        }
                                                    }

                                                });

                                                atrasID.setOnAction(new EventHandler<ActionEvent>(){
                                                    @Override public void handle(ActionEvent e){
                                                        primaryStage.setScene(scene3);
                                                        primaryStage.show();
                                                    }
                                                });
                                            }
                                        });


                             consultarn.setOnAction(new EventHandler<ActionEvent>(){
                                            @Override public void handle(ActionEvent e){
                                                Label intron = new Label("Nombre");
                                                intron.setStyle("-fx-font-size: 20px;" +
                                                                "-fx-text-fill: white;");
                                                TextField intron2 = new TextField();
                                                intron.setPrefWidth(120);
                                                HBox hb = new HBox();
                                                hb.getChildren().addAll(intron,intron2);
                                                hb.setSpacing(10);
                                                hb.setLayoutX(35);
                                                hb.setLayoutY(10);

                                                //Boton aceptar y atras
                                                Button aceptarNom = new Button("Aceptar");
                                                Button atrasNom = new Button("Atras");
                                                HBox hbxac = new HBox(10);

                                                //Informacion cuando pongo raton encima
                                                aceptarNom.setTooltip(new Tooltip("Aceptar y consultar"));
                                                atrasNom.setTooltip(new Tooltip("Volver atras"));

                                                //Posicion en este caso abajo_izquierda
                                                hbxac.setAlignment(Pos.BOTTOM_LEFT);
                                                hbxac.getChildren().addAll(aceptarNom, atrasNom);

                                                //aqui escribes lo que quieres que muestre
                                                Group group5= new Group(hb,aceptarNom,atrasNom);//aqui metes lo que quieres que muestre
                                                Scene scene5 = new Scene(group5,350,100,Color.BLACK);
                                                primaryStage.setScene(scene5);
                                                primaryStage.show();

                                                //posicion de los botones
                                                aceptarNom.setTranslateX(180);
                                                aceptarNom.setTranslateY(45);

                                                atrasNom.setTranslateX(255);
                                                atrasNom.setTranslateY(45);

                                                //Accion boton aceptar
                                                aceptarNom.setOnAction(new EventHandler<ActionEvent>() {
                                                    @Override public void handle(ActionEvent e){
                                                        //Aqui a traves de una interface se comprueba lo escrito en el textfield
                                                        //con la base de datos e imprime la informacion en la terminal
                                                        if(intron2.getText().isEmpty()){
                                                            System.out.println(ANSI_BLACK_BACKGROUND + ANSI_RED + "No ha escrito ningun nombre" + ANSI_RESET + "\n");
                                                        }else{
                                                            daocompra.consultarn(intron2.getText());
                                                        }
                                                    }

                                                });

                                                atrasNom.setOnAction(new EventHandler<ActionEvent>(){
                                                    @Override public void handle(ActionEvent e){
                                                        primaryStage.setScene(scene3);
                                                        primaryStage.show();
                                                    }
                                                });
                                            }
                                        });
                                            }
                                        });
                                        //
                                        //
                                        //
                                        //
                                        //
                                        //
                                        //
                                       salir.setOnAction(new EventHandler<ActionEvent>(){
                                                        @Override public void handle(ActionEvent e){
                                                            System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN +"Vuelva pronto." + ANSI_RESET);
                                                            primaryStage.close();
                                                        }
                                                    });
                }
                catch(Exception ex) {

                }

            }
        });
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}

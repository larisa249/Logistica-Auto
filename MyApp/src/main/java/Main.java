
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {
	static String url = "jdbc:mysql://localhost:3306/logistica";
	static String username = "root";
	static String password = "thisisMYSQL23!";
	

	private void showPopUp(Stage primaryStage, String message) {
		Stage popUpStage = new Stage();
	    popUpStage.initModality(Modality.APPLICATION_MODAL);
	    popUpStage.initOwner(primaryStage);
	    popUpStage.getIcons().add(new Image("file:///C:/Users/laris/OneDrive/Desktop/icon.jpg")); 
	    String title = null;
	    popUpStage.setTitle(title != null ? title : "Notificare");

	    Label label = new Label(message);
	    label.getStyleClass().add("label-message");

	    StackPane layout = new StackPane();
	    layout.getChildren().add(label);
	    layout.getStyleClass().add("popup-stage");

	    Scene scene = new Scene(layout, 300, 100);
	    scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

	    popUpStage.setScene(scene);
	    popUpStage.showAndWait();
	}


	public static Background imagineFundal(String imagePath) {
        Image backgroundImage = new Image( imagePath);
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
        );
        return new Background(background);
    }

		

    private Button creeazaButon(String text, String styleClass) {
        Button buton = new Button(text);
        buton.getStyleClass().add(styleClass);
        return buton;
    }
    
   
    private TextField creeazaTextField(String promptText, int maxWidth) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setMaxWidth(maxWidth);
        return textField;
    }

    

    public void metodaDistribuitor(Stage primaryStage, String Username, String parola) {
    	Background backgroundObject = imagineFundal("file:///C:/Users/laris/OneDrive/Desktop/fundal2.jpeg");
        StackPane stackPane = new StackPane();
        stackPane.setBackground(backgroundObject);
        
        Button inapoi = creeazaButon("Înapoi", "buton1");
        inapoi.setOnAction(event -> metodaCreareCont(primaryStage));
        
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(inapoi);
        BorderPane.setAlignment(inapoi, Pos.TOP_LEFT);
        BorderPane.setMargin(inapoi, new Insets(10));
        
        TextField CUI =creeazaTextField("C.U.I",250); 
        
        TextField numeDistribuitor =creeazaTextField("Nume",250);
        
        TextField adresaSediu =creeazaTextField("Adresa sediului",250);
        
        TextField nrTelefon =creeazaTextField("Numărul de telefon",250);
        
        TextField tipMarfaDistribuita =creeazaTextField("Tipul mărfii distribuite",250);
        
        Button creare =creeazaButon("Creează", "buton1");
        creare.setOnAction(event -> {
        	 int utilizator_id;
    	try {
			Connection connection = DriverManager.getConnection(url, username, password);
			if (connection !=null) {
				String CUII=CUI.getText();
				String numeDistribuitorr=numeDistribuitor.getText();
				String adresaSediuu=adresaSediu.getText();
				String nrTelefonn=nrTelefon.getText();
				String tipMarfaDistribuitaa=tipMarfaDistribuita.getText();
				if(CUII !="" && numeDistribuitorr !="" && adresaSediuu != "" && nrTelefonn != "" && tipMarfaDistribuitaa != "") {
					utilizator_id = utilizatori.adaugaUtilizator(connection, Username, parola);
					distribuitori.adaugaDistribuitor(connection,CUII,numeDistribuitorr,adresaSediuu,nrTelefonn,tipMarfaDistribuitaa,utilizator_id);
					showPopUp(primaryStage, "A fost creat contul de coordonator!");
		    		start(primaryStage);
				}
				else {
					showPopUp(primaryStage, "Toate câmpurile sunt obligatorii!");
				}
			}
		} catch(SQLException e) {
			System.out.println("Conectareeee esuata la baza de date");
			e.printStackTrace();
		}
        });
        
        Text textt = new Text("Finalizează contul de distribuitor");
        textt.getStyleClass().add("textt");
        
        VBox content = new VBox(10);
        content.getChildren().addAll(textt, CUI, numeDistribuitor, adresaSediu, nrTelefon, tipMarfaDistribuita, creare);
        content.setAlignment(Pos.CENTER);
        borderPane.setCenter(content);
        stackPane.getChildren().addAll(borderPane);
        
        Scene scene = new Scene(stackPane, 1000, 763);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
   	 	primaryStage.show();
    }
    
    
    
   
    public void metodaSofer(Stage primaryStage, String Username, String parola) {
    	Background backgroundObject = imagineFundal("file:///C:/Users/laris/OneDrive/Desktop/fundal2.jpeg");
        StackPane stackPane = new StackPane();
        stackPane.setBackground(backgroundObject);
        
        Button inapoi = creeazaButon("Înapoi", "buton1");
        inapoi.setOnAction(event -> metodaCreareCont(primaryStage));
        
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(inapoi);
        BorderPane.setAlignment(inapoi, Pos.TOP_LEFT);
        BorderPane.setMargin(inapoi, new Insets(10));
        
        TextField CNP =creeazaTextField("C.N.P",250);
        
        TextField numeSofer =creeazaTextField("Nume",250);
        
        TextField domiciliu =creeazaTextField("Domiciliul",250);
        
        TextField nrTelefon =creeazaTextField("Numărul de telefon",250); 
        
        TextField categoriePermis =creeazaTextField("Categorie permis",250); 
        
        TextField disponibilitate =creeazaTextField("Disponibilitate",250); 
        
        TextField atestat =creeazaTextField("Atestat",250);
        
        Text textt = new Text("Finalizează contul de șofer independent");
        textt.getStyleClass().add("textt");
        
        Button creare =creeazaButon("Creează", "buton1");
        creare.setOnAction(event -> {
        	 int utilizator_id;
    	try {
			Connection connection = DriverManager.getConnection(url, username, password);
			if (connection !=null) {
				String CNPP=CNP.getText();
				String numeSoferr=numeSofer.getText();
				String domiciliuu=domiciliu.getText();
				String nrTelefonn=nrTelefon.getText();
				String categoriePermiss=categoriePermis.getText();
				String disponibilitatee=disponibilitate.getText();
				String atestatt=atestat.getText();
				if(CNPP !="" && numeSoferr !="" && domiciliuu !="" && nrTelefonn != "" && categoriePermiss != "" && disponibilitatee != "" && atestatt!= "") {
					utilizator_id = utilizatori.adaugaUtilizator(connection, Username, parola);
					soferiIndependenti.adaugaSofer(connection,CNPP,numeSoferr,domiciliuu,nrTelefonn,categoriePermiss,disponibilitatee, atestatt, utilizator_id);
					showPopUp(primaryStage, "A fost creat contul de șofer independent!");
					start(primaryStage);
				}
				else {
					showPopUp(primaryStage, "Toate câmpurile sunt obligatorii!");
				}
			}
		} catch(SQLException e) {
			System.out.println("Conectareeee esuata la baza de date");
			e.printStackTrace();
		}
    	
        });
        
        
        VBox content = new VBox(10);
        content.getChildren().addAll(textt, CNP, numeSofer, domiciliu, nrTelefon, categoriePermis, disponibilitate, atestat,creare);
        content.setAlignment(Pos.CENTER);
        borderPane.setCenter(content);
        stackPane.getChildren().addAll(borderPane);
        
        Scene scene = new Scene(stackPane, 1000, 763);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
   	 	primaryStage.show();
    }
    
    
   
    public void metodaFirma(Stage primaryStage, String Username, String parola) {
    	Background backgroundObject = imagineFundal("file:///C:/Users/laris/OneDrive/Desktop/fundal2.jpeg");
        StackPane stackPane = new StackPane();
        stackPane.setBackground(backgroundObject);
        
        Button inapoi = creeazaButon("Înapoi", "buton1");
        inapoi.setOnAction(event -> metodaCreareCont(primaryStage));
        
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(inapoi);
        BorderPane.setAlignment(inapoi, Pos.TOP_LEFT);
        BorderPane.setMargin(inapoi, new Insets(10));
        
        TextField CUI =creeazaTextField("C.U.I",250);
        
        TextField denumireCompanie =creeazaTextField("Denumirea companiei",250);
        
        TextField adresaSediu =creeazaTextField("Adresa sediului",250);
        
        TextField nrTelefon =creeazaTextField("Numărul de telefon",250); 
        
        TextField tipMarfaPosibila =creeazaTextField("Tipul de marfă transportată",250);
        
        TextField actDoveditor =creeazaTextField("Exista act doveditor?",250);
        
        TextField tipCamioane =creeazaTextField("Camioanele deținute",250); 
        
        Text textt = new Text("Finalizează contul firmei de transport");
        textt.getStyleClass().add("textt");
        
        Button creare =creeazaButon("Creează", "buton1");
        creare.setOnAction(event -> {
        	 int utilizator_id;
    	try {
			Connection connection = DriverManager.getConnection(url, username, password);
			if (connection !=null) {
				String CUII=CUI.getText();
				String denumireCompaniee=denumireCompanie.getText();
				String adresaSediuu=adresaSediu.getText();
				String nrTelefonn=nrTelefon.getText();
				String tipMarfaPosibilaa=tipMarfaPosibila.getText();
				String actDoveditorr=actDoveditor.getText();
				String tipCamioanee=tipCamioane.getText();
				
				if(CUII != "" && denumireCompaniee != "" && adresaSediuu != "" && nrTelefonn != "" && tipMarfaPosibilaa!= "" && actDoveditorr!= "" && tipCamioanee != "") {
					utilizator_id = utilizatori.adaugaUtilizator(connection, Username, parola);
					firmeTransport.adaugaFirma(connection,CUII,denumireCompaniee,adresaSediuu,nrTelefonn, tipCamioanee, tipMarfaPosibilaa, actDoveditorr, utilizator_id);
					showPopUp(primaryStage, "A fost creat contul pentru reprezentantul firmei de transport!");
					start(primaryStage);
				}
				else {
					showPopUp(primaryStage, "Toate câmpurile sunt obligatorii!");
				}
			}
		} catch(SQLException e) {
			System.out.println("Conectareeee esuata la baza de date");
			e.printStackTrace();
		}
    	
        });
        
        VBox content = new VBox(10);
        content.getChildren().addAll(textt, CUI, denumireCompanie, adresaSediu, nrTelefon, tipMarfaPosibila, actDoveditor, tipCamioane, creare);
        content.setAlignment(Pos.CENTER);
        borderPane.setCenter(content);
        stackPane.getChildren().addAll(borderPane);
        
        Scene scene = new Scene(stackPane, 1000, 763);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
   	 	primaryStage.show();
    }
    
    
  
    public void metodaCreareCont(Stage primaryStage) {
    	Background backgroundObject = imagineFundal("file:///C:/Users/laris/OneDrive/Desktop/fundal2.jpeg");

        Text textt = new Text("Creează cont");
        textt.getStyleClass().add("textt");
        
        TextField Username =creeazaTextField("Nume utilizator",250);
        
        PasswordField Parola = new PasswordField();
        Parola.setPromptText("Parolă");
        Parola.setMaxWidth(250);
        

        Button distribuitor = creeazaButon("Coordonator","buton1");
        Button sofer = creeazaButon("Șofer independent","buton1");
        Button firma = creeazaButon("Firmă de transport","buton1");
        
        distribuitor.setOnAction(event -> {
        	String Usernamee=Username.getText();
            String Parolaa=Parola.getText();
            if( Usernamee != "" && Parolaa != "") {
            	metodaDistribuitor(primaryStage,Usernamee,Parolaa);
            }
            else {
            	showPopUp(primaryStage, "Toate câmpurile sunt obligatorii!");
            }
            
        });
        
        sofer.setOnAction(event -> {
        	String Usernamee=Username.getText();
            String Parolaa=Parola.getText();
            if( Usernamee != "" && Parolaa != "") {
            	metodaSofer(primaryStage,Usernamee,Parolaa);
            }
            else {
            	showPopUp(primaryStage, "Toate câmpurile sunt obligatorii!");
            }
        });
        
        firma.setOnAction(event -> {
        	String Usernamee=Username.getText();
            String Parolaa=Parola.getText();
            if( Usernamee != "" && Parolaa != "") {
            	metodaFirma(primaryStage,Usernamee,Parolaa);
            }
            else {
            	showPopUp(primaryStage, "Toate câmpurile sunt obligatorii!");
            }
        });
           
        HBox butoane = new HBox(10);
        butoane.getChildren().addAll(distribuitor, sofer, firma);
        butoane.setAlignment(Pos.CENTER);

        Button inapoi = creeazaButon("Înapoi", "buton1");
        inapoi.setOnAction(event -> start(primaryStage));
        
        BorderPane.setAlignment(inapoi, Pos.TOP_LEFT);
        BorderPane.setMargin(inapoi, new Insets(10));

        VBox content = new VBox(10);
        content.getChildren().addAll(textt, Username, Parola, butoane);
        content.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(inapoi);
        borderPane.setCenter(content);

        StackPane stackPane = new StackPane();
        stackPane.setBackground(backgroundObject);
        stackPane.getChildren().addAll(borderPane);

        Scene scene2 = new Scene(stackPane, 1000, 763);
        scene2.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene2);
        primaryStage.show();
    }
 
    public void stergeCont(Stage primaryStage, int id_Utilizator, String tipUtilizator) {
    	if(tipUtilizator.equals("sofer")) {
    		try {
                Connection connection = DriverManager.getConnection(url, username, password);
                if (connection != null) {
                    id_Utilizator = metodaConectare(primaryStage);
                    soferiIndependenti.stergeSofer(connection, id_Utilizator);
                    showPopUp(primaryStage, "Șoferul a fost șters!");
                    metodaConectare(primaryStage);
                }
            } catch (SQLException e) {
                System.out.println("Conectare esuata la baza de date");
                e.printStackTrace();
            }
    	}
    	
    	else if (tipUtilizator.equals("distribuitor")) {
    		try {
     			Connection connection = DriverManager.getConnection(url, username, password);
     			if (connection !=null) {
     				    id_Utilizator=metodaConectare(primaryStage);
     					distribuitori.stergeDistribuitor(connection,id_Utilizator);
     					showPopUp(primaryStage, "Coordonatorul a fost șters!");
     					metodaConectare(primaryStage);
     			}}
               catch(SQLException e) {
     			System.out.println("Conectareeee esuata la baza de date");
     			e.printStackTrace();
               }
         	}
    	else {
    		try {
    			Connection connection = DriverManager.getConnection(url, username, password);
    			if (connection !=null) {
    				    
    					firmeTransport.stergeFirma(connection,id_Utilizator);
    					showPopUp(primaryStage, "Firma a fost ștearsă!");
    					metodaConectare(primaryStage);
    			}}
              catch(SQLException e) {
    			System.out.println("Conectareeee esuata la baza de date");
    			e.printStackTrace();
              }
        	}
    	}
    	
    public void confirmareStergere(Stage primaryStage, int id_Utilizator, String tipUtilizator) {
    	Background backgroundObject = imagineFundal("file:///C:/Users/laris/OneDrive/Desktop/fundal2.jpeg");

        StackPane stackPane = new StackPane();
        stackPane.setBackground(backgroundObject);
    	VBox meniuLateral;
    	if(tipUtilizator.equals("sofer")) {
    		 meniuLateral = meniuSofer(primaryStage);
    	}
    	else if(tipUtilizator.equals("firma")) {
    		 meniuLateral = meniuFirma(primaryStage);
    	}
    	else {
    		meniuLateral = meniuDistribuitor(primaryStage);
    	}
    	
    	Text textt = new Text("Ștergi permanent contul?");
        textt.getStyleClass().add("sterge");
        Text texttt = new Text("Nu îl vei mai putea recupera.");
        texttt.getStyleClass().add("sterge");
        
        
        Button da = creeazaButon("Da", "buton1");
        da.setOnAction(event -> {
            stergeCont(primaryStage, id_Utilizator, tipUtilizator);
        });
    	
        Button nu = creeazaButon("Nu", "buton1");
        nu.setOnAction(event -> metodaConectare(primaryStage));
        

        HBox butoane = new HBox(10);
        butoane.getChildren().addAll(da,nu);
        butoane.setAlignment(Pos.CENTER);
        
        VBox content = new VBox(10);
        content.getChildren().addAll(textt, texttt, butoane);
        content.setAlignment(Pos.CENTER);
        
    	BorderPane borderPane = new BorderPane();
        borderPane.setLeft(meniuLateral);
        borderPane.setCenter(content);
        stackPane.getChildren().addAll(borderPane);

        Scene scene = new Scene(stackPane, 1000, 763);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    
    
    public VBox soferi(Stage primaryStage) {
        VBox soferi = new VBox();
        soferi.setSpacing(10);
        //soferi.setAlignment(Pos.CENTER);
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM SOFERI");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String numeSofer = resultSet.getString("numeSofer");
                String cnp = resultSet.getString("cnp");
                String domiciliu = resultSet.getString("domiciliu");
                String nrTelefon = resultSet.getString("nrTelefon");
                String categoriePermis = resultSet.getString("categoriePermis");
                String disponibilitate = resultSet.getString("disponibilitate");
                String atestat = resultSet.getString("atestat");

                
                BorderPane dreptunghiSofer = new BorderPane();
                dreptunghiSofer.getStyleClass().add("dreptunghi");

                Label sofer = new Label("Șoferul " + id + ":" + "\n CNP: " + cnp + " | Nume: " + numeSofer +
                        " | Domiciliu: " + domiciliu + " | Telefon: " + nrTelefon + "| Categorie permis: " +
                        categoriePermis + "\n| Disponibilitate: " + disponibilitate + " | Atestat: " + atestat);
             

                VBox detaliiSofer = new VBox(sofer);
                dreptunghiSofer.setCenter(detaliiSofer);

                soferi.getChildren().add(dreptunghiSofer);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Conectare esuata la baza de date");
            e.printStackTrace();
        }
        return soferi;
    }
    
    
    public void afisareSoferi(Stage primaryStage) {
    	Background backgroundObject = imagineFundal("file:///C:/Users/laris/OneDrive/Desktop/fundal2.jpeg");

        StackPane stackPane = new StackPane();
        stackPane.setBackground(backgroundObject);

        VBox meniuLateral = meniuAdmin(primaryStage);

        VBox soferi = soferi(primaryStage);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(soferi);
        borderPane.setLeft(meniuLateral);
        stackPane.getChildren().addAll(borderPane);

        Scene scene = new Scene(stackPane, 1000, 763);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    public VBox firme(Stage primaryStage) {
    	VBox firme = new VBox(10);
        firme.setSpacing(10);
        //firme.setAlignment(Pos.CENTER);
    	
    	try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM FIRMETRANSPORT");

            while (resultSet.next()) {
            	int id = resultSet.getInt("id");
                String cui = resultSet.getString("cui");
                String denumireCompanie = resultSet.getString("denumireCompanie");
                String adresaSediu = resultSet.getString("adresaSediu");
                String nrTelefon = resultSet.getString("nrTelefon");
                String tipCamioane = resultSet.getString("tipCamioane");
                String tipMarfaPosibila = resultSet.getString("tipMarfaPosibila");
                String actDoveditor = resultSet.getString("actDoveditor");

                BorderPane dreptunghiFirma = new BorderPane();
                dreptunghiFirma.getStyleClass().add("dreptunghi");
                
                Label firma = new Label("Firma " + id + ":" + "\n CUI: " + cui + " | Denumire: " + denumireCompanie +
                        " | Adresă: " + adresaSediu + " | Telefon: " + nrTelefon + "\n| Tip marfa disponibilă: " +
                        tipCamioane + " | Tip camioane: " + tipMarfaPosibila + " | Act doveditor: " + actDoveditor);
               
                VBox detaliiFirma = new VBox(firma);
                dreptunghiFirma.setCenter(detaliiFirma);
                
                firme.getChildren().add(dreptunghiFirma);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Conectare esuata la baza de date");
            e.printStackTrace();
        }
		return firme;
    }
    
    
    public void afisareFirme(Stage primaryStage) {
        Background backgroundObject = imagineFundal("file:///C:/Users/laris/OneDrive/Desktop/fundal2.jpeg");
         
        StackPane stackPane = new StackPane();
        stackPane.setBackground(backgroundObject);
        
        VBox meniuLateral = meniuAdmin(primaryStage);

        VBox firme = firme(primaryStage);
        

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(meniuLateral);
        borderPane.setCenter(firme); 

        stackPane.getChildren().add(borderPane);

        Scene scene = new Scene(stackPane, 1000, 763);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    public VBox curse(Stage primaryStage) {
    	VBox curse = new VBox();
        curse.setSpacing(10);
        //curse.setAlignment(Pos.CENTER);

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CURSEDISPONIBILE");

            while (resultSet.next()) {
                String dataPreluare = resultSet.getString("dataPreluare");
                String dataPredare = resultSet.getString("dataPredare");
                String locPreluare = resultSet.getString("locPreluare");
                String locPredare = resultSet.getString("locPredare");
                String tipIncarcatura = resultSet.getString("tipIncarcatura");
                int utilizator_id = resultSet.getInt("utilizator_id");
                
                
                BorderPane dreptunghiCursa = new BorderPane();
                dreptunghiCursa.getStyleClass().add("dreptunghi");
         		
         		Label cursa = new Label("Cursa  "+ utilizator_id + ":" + "\n Data preluare: " + dataPreluare + " | Data predare: " + dataPredare +
                        " | Loc preluare: " + locPreluare + " | Loc predare: " + locPredare + " \n| Tip încărcătură: " +
                        tipIncarcatura);
         		VBox detaliiCursa = new VBox(cursa);
                dreptunghiCursa.setCenter(detaliiCursa);

                curse.getChildren().add(dreptunghiCursa);
         		}
        

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Conectare esuata la baza de date");
            e.printStackTrace();
        }
        return curse;
    }
    
    
    public void afisareCursa(Stage primaryStage) {
    	Background backgroundObject = imagineFundal("file:///C:/Users/laris/OneDrive/Desktop/fundal2.jpeg");

        StackPane stackPane = new StackPane();
        stackPane.setBackground(backgroundObject);

        VBox meniuLateral = meniuAdmin(primaryStage);
        VBox curse = curse(primaryStage);
        
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(curse);
        borderPane.setLeft(meniuLateral);
        
        stackPane.getChildren().addAll(borderPane);

        Scene scene = new Scene(stackPane, 1000, 763);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    public void afisareUtilizatori(Stage primaryStage) {
    	Background backgroundObject = imagineFundal("file:///C:/Users/laris/OneDrive/Desktop/fundal2.jpeg");
    	 
    	 StackPane stackPane = new StackPane();
         stackPane.setBackground(backgroundObject);
         
         VBox meniuLateral = meniuAdmin(primaryStage);
         
         VBox utilizatori = new VBox();
         utilizatori.setSpacing(10);
         //utilizatori.setAlignment(Pos.CENTER);

         try {
             Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM UTILIZATORI");

             while (resultSet.next()) {
            	 int id = resultSet.getInt("id");
                 String Username = resultSet.getString("username");
                 String Parola = resultSet.getString("parola");
            
                 Button dreptunghiUtilizator = new Button();
                 dreptunghiUtilizator.getStyleClass().add("dreptunghi2");
                 
                 Label utilizator = new Label("\n Utilizator " + id + ":" + "\n Username: " + Username + " | Parola: " + Parola  );
                 
                 
                 VBox detaliiUtilizator = new VBox(utilizator);
                 dreptunghiUtilizator.setGraphic(detaliiUtilizator);
                 dreptunghiUtilizator.setOnAction(event -> stergereUtilizator(primaryStage,Username));
                 utilizatori.getChildren().add(dreptunghiUtilizator);
             }

             resultSet.close();
             statement.close();
             connection.close();
         } catch (SQLException e) {
             System.out.println("Conectare esuata la baza de date");
             e.printStackTrace();
         }
         
         BorderPane borderPane = new BorderPane();
         borderPane.setCenter(utilizatori);
         borderPane.setLeft(meniuLateral);
         stackPane.getChildren().addAll(borderPane);
         
         Scene scene = new Scene(stackPane, 1000, 763);
         scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
         primaryStage.setScene(scene);
    	 primaryStage.show();
    }
   
    public void afisareDistribuitori(Stage primaryStage) {
    	Background backgroundObject = imagineFundal("file:///C:/Users/laris/OneDrive/Desktop/fundal2.jpeg");
    	 
    	 StackPane stackPane = new StackPane();
         stackPane.setBackground(backgroundObject);
         
         VBox meniuLateral = meniuAdmin(primaryStage);
         
         VBox distribuitori = new VBox();
         distribuitori.setSpacing(10);
         //distribuitori.setAlignment(Pos.CENTER);

         try {
             Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM distribuitori");

             while (resultSet.next()) {
            	 int id = resultSet.getInt("id");
                 String cui = resultSet.getString("cui");
                 String numeDistribuitorr = resultSet.getString("numeDistribuitor");
                 String adresaSediuu = resultSet.getString("adresaSediu");
                 String nrTelefon = resultSet.getString("nrTelefon");
                 String tipMarfaDistribuita = resultSet.getString("tipMarfaDistribuita");
                
                 BorderPane dreptunghiDistribuitor = new BorderPane();
                 dreptunghiDistribuitor.getStyleClass().add("dreptunghi");
                 
                 Label distribuitor = new Label("Distribuitorul " + id + ":" + "\n CUI: " + cui + " | Nume: " + numeDistribuitorr +
                         " | Adresă: " + adresaSediuu + "\n| Telefon: " + nrTelefon + " | Tip marfa disponibilă: "
                          + tipMarfaDistribuita  );
                 VBox detaliiDistribuitor = new VBox(distribuitor);
                 dreptunghiDistribuitor.setCenter(detaliiDistribuitor);

                 distribuitori.getChildren().add(dreptunghiDistribuitor);
             }

             resultSet.close();
             statement.close();
             connection.close();
         } catch (SQLException e) {
             System.out.println("Conectare esuata la baza de date");
             e.printStackTrace();
         }
         
         BorderPane borderPane = new BorderPane();
 
         borderPane.setCenter(distribuitori);
         stackPane.getChildren().addAll(borderPane);
         borderPane.setLeft(meniuLateral);
         Scene scene = new Scene(stackPane, 1000, 763);
         scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
         primaryStage.setScene(scene);
    	 primaryStage.show();
    }

    
    public void afisareParteneriate(Stage primaryStage) {
    	Background backgroundObject = imagineFundal("file:///C:/Users/laris/OneDrive/Desktop/fundal2.jpeg");
    	 
    	 StackPane stackPane = new StackPane();
         stackPane.setBackground(backgroundObject);
         
         VBox meniuLateral = meniuAdmin(primaryStage); 
         
         VBox parteneriate = new VBox();
         parteneriate.setSpacing(10);
         //parteneriate.setAlignment(Pos.CENTER);

         try {
             Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM parteneriate");

             while (resultSet.next()) {
                 String coordonator = resultSet.getString("coordonator");
                 String transportator = resultSet.getString("transportator");
                 String dataPreluare = resultSet.getString("dataPreluare");
                 String dataPredare = resultSet.getString("dataPredare");
                 String locPreluare = resultSet.getString("locPreluare");
                 String locPredare = resultSet.getString("locPredare");
                 String tipIncarcatura = resultSet.getString("tipIncarcatura");
                
                 Button dreptunghiParteneriat = new Button();
                 dreptunghiParteneriat.getStyleClass().add("dreptunghi2");
                 
                 Label parteneriat = new Label("Parteneriatul dintre coordonatorul " + coordonator + " și transportatorul " + transportator +
                         "\n| Data de preluare: " + dataPreluare + " | Data de predare: " + dataPredare + " | Locul de preluare: "
                          + locPreluare +  " | Locul de predare: " + locPredare + "\n| Tipul de încărcătură: " + tipIncarcatura);
                 VBox detaliiParteneriate = new VBox(parteneriat);
                 dreptunghiParteneriat.setGraphic(detaliiParteneriate);
                 dreptunghiParteneriat.setOnAction(e -> validare(primaryStage,coordonator, transportator, dataPreluare, dataPredare, locPreluare, locPredare, tipIncarcatura));
                 
                 parteneriate.getChildren().add(dreptunghiParteneriat);
             }

             resultSet.close();
             statement.close();
             connection.close();
         } catch (SQLException e) {
             System.out.println("Conectare esuata la baza de date");
             e.printStackTrace();
         }
         
         BorderPane borderPane = new BorderPane();
 
         borderPane.setCenter(parteneriate);
         stackPane.getChildren().addAll(borderPane);
         borderPane.setLeft(meniuLateral);
         Scene scene = new Scene(stackPane, 1000, 763);
         scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
         primaryStage.setScene(scene);
    	 primaryStage.show();
    }
    
   
    public void stergereUtilizator(Stage primaryStage, String Username) {
         try {
  			Connection connection = DriverManager.getConnection(url, username, password);
  			if (connection !=null) {
  				
  				utilizatori.stergeUtilizator(connection,Username);
  				showPopUp(primaryStage, "Utilizatorul a fost șters!");
  				userAdmin(primaryStage);
  			}}
            catch(SQLException e) {
  			System.out.println("Conectareeee esuata la baza de date");
  			e.printStackTrace();
            }
     	
        
    }
    
    public void validare(Stage primaryStage, String coordonator,String transportator,String dataPreluare,String dataPredare,String locPreluare,String locPredare,String tipIncarcatura) {
    	
    	boolean lungime = coordonator.length() > 10 && transportator.length() > 5;
    	boolean egal = !coordonator.equals(transportator);
    	
    	boolean automobil = false;
    	
    	try {
    		Connection connection = DriverManager.getConnection(url, username, password);
    		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM soferi WHERE numeSofer = ?");
    		preparedStatement.setString(1, transportator);
    		ResultSet resultSet = preparedStatement.executeQuery();
    		
    		if(resultSet.next()) {
    			int id = resultSet.getInt("id");
    			String disponibilitatee = resultSet.getString("disponibilitate");
    			String domiciliuu = resultSet.getString("domiciliu");
    			String atestat = resultSet.getString("atestat");
    			PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT * FROM automobil WHERE id = ?");
        		preparedStatement2.setInt(1, id);
        		ResultSet resultSet2 = preparedStatement2.executeQuery();
        		
        		if(resultSet2.next() ) {
        			String verificareITP = resultSet2.getString("verificareITP");
        			String asigurare = resultSet2.getString("asigurare");
        			
        			if(disponibilitatee.equals(dataPreluare) && domiciliuu.equals(locPreluare) && "da".equals(verificareITP.toLowerCase()) && "da".equals(asigurare.toLowerCase()) && "da".equals(atestat.toLowerCase())) {
        				automobil = true;
        				System.out.println("what do you want");
        			}
        			
        		}
        		resultSet2.close();
                preparedStatement2.close();
    			
    		}else {
    			PreparedStatement preparedStatement3 = connection.prepareStatement("SELECT * FROM firmetransport WHERE denumireCompanie = ?");
    			preparedStatement3.setString(1, transportator);
                ResultSet resultSet3 = preparedStatement3.executeQuery();
                if (resultSet3.next()) {
                	String marfa = resultSet3.getString("tipMarfaPosibila");
                	String act = resultSet3.getString("actDoveditor");
        			if(marfa.equals(tipIncarcatura) && act.equals("da") ) {
        				automobil =true;
        				
        			}
        			
                    System.out.println("Transportatorul este o companie din tabelul firmetransport.");
                } else {
                    
                    System.out.println("Transportatorul nu este nici șofer nici companie.");
                }
                resultSet3.close();
                preparedStatement3.close();
            }

            resultSet.close();
            preparedStatement.close();	
    	} catch (SQLException e) {
            System.out.println("Eroare la interogare: " + e.getMessage());
            
        }
    	
    	
    	if(lungime && egal && automobil) {
    		showPopUp(primaryStage, "valorile sunt corecte");
    		String caleSalvare = "C:/Users/laris/OneDrive/Desktop/";
    	    crearePDF(coordonator, transportator, dataPreluare, dataPredare, locPreluare, locPredare, tipIncarcatura, caleSalvare, "document");
    	}
    	else {
    		showPopUp(primaryStage, "valorile nu sunt corecte");
    	}
    	
    }
    
    
    public static void crearePDF(
            String coordonator, String transportator,
            String dataPreluare, String dataPredare,
            String locPreluare, String locPredare,
            String tipIncarcatura, String caleSalvare, String numeFisier) {
        try {
            // Configurați cache-ul fonturilor
            System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
            System.setProperty("pdfbox.fontcache", "/tmp/pdfbox-fonts");

            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            
            contentStream.setFont(PDType1Font.HELVETICA, 12);

            
            float x = 100;
            float y = 700;

            contentStream.beginText();

            
            contentStream.newLineAtOffset(x, y);
            contentStream.showText("Coordonator: " + coordonator);
            contentStream.newLine(); 
            
            contentStream.newLineAtOffset(0, -20); 
            contentStream.showText("Transportator: " + transportator);
            contentStream.newLine();

            contentStream.newLineAtOffset(0, -20); 
            contentStream.showText("Data Preluare: " + dataPreluare);
            contentStream.newLine();

            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Data Predare: " + dataPredare);
            contentStream.newLine();

            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Loc Preluare: " + locPreluare);
            contentStream.newLine();

            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Loc Predare: " + locPredare);
            contentStream.newLine();

            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Tip Incarcatura: " + tipIncarcatura);

            contentStream.endText();
            contentStream.close();

            document.save(caleSalvare + numeFisier + ".pdf");
            document.close();
            System.out.println("Fișierul PDF a fost creat cu succes: " + caleSalvare + numeFisier + ".pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private boolean esteTabelGol(String numeTabel) {
        boolean esteGol = true;
        String query = "SELECT COUNT(*) FROM " + numeTabel;

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                int numarRanduri = resultSet.getInt(1);
                if (numarRanduri > 0) {
                    esteGol = false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return esteGol;
    }

    
    private VBox meniuAdmin(Stage primaryStage) {
        VBox meniuLateral = new VBox(10);
        meniuLateral.setId("meniu-lateral");

        Label titluMeniu = new Label("Meniu");
        titluMeniu.setId("titlu-meniu");
        meniuLateral.getChildren().add(titluMeniu);

        Button inapoi = creeazaButon("Înapoi", "buton-meniu");
        inapoi.setOnAction(event -> metodaConectare(primaryStage));
        meniuLateral.getChildren().add(inapoi);

        String[] numeButoane = { "Vizualizare utilizatori", 
                                "Vizualizare firme", "Vizualizare soferi", "Vizualizare distribuitori", 
                                "Vizualizare curse",  "Vizualizare parteneriate"};

        for (String numeButon : numeButoane) {
            Button buton = creeazaButon(numeButon, "buton-meniu");
            switch (numeButon) {
            case "Vizualizare utilizatori":
            	if (!esteTabelGol("utilizatori")) {
                    buton.setOnAction(event -> {
                        afisareUtilizatori(primaryStage);
                    });
                } else {
                    buton.setOnAction(event -> showPopUp(primaryStage, "Nu există utilizatori!")); 
                }
                break;
            case "Vizualizare firme":
            	if (!esteTabelGol("firmetransport")) {
                    buton.setOnAction(event -> {
                        afisareFirme(primaryStage);
                    });
                } else {
                    buton.setOnAction(event -> showPopUp(primaryStage, "Nu există firme disponibile!")); 
                }
                break;
            case "Vizualizare soferi":
            	if (!esteTabelGol("soferi")) {
                    buton.setOnAction(event -> {
                        afisareSoferi(primaryStage);
                    });
                } else {
                    buton.setOnAction(event -> showPopUp(primaryStage, "Nu există șoferi disponibili!")); 
                }
                break;
            case "Vizualizare distribuitori":
            	if (!esteTabelGol("distribuitori")) {
                    buton.setOnAction(event -> {
                        afisareDistribuitori(primaryStage);
                    });
                } else {
                    buton.setOnAction(event -> showPopUp(primaryStage, "Nu există distribuitori disponibili!")); 
                }
                break;
            case "Vizualizare curse":
                if (!esteTabelGol("curseDisponibile")) {
                    buton.setOnAction(event -> {
                        afisareCursa(primaryStage);
                    });
                } else {
                    buton.setOnAction(event -> showPopUp(primaryStage, "Nu există curse disponibile!")); 
                }
                break;
            case "Vizualizare parteneriate":
            	if (!esteTabelGol("parteneriate")) {
                    buton.setOnAction(event -> {
                        afisareParteneriate(primaryStage);
                    });
                } else {
                    buton.setOnAction(event -> showPopUp(primaryStage, "Nu există parteneriate disponibile!")); 
                }
                break;
           
        }
            meniuLateral.getChildren().add(buton);
        }

        return meniuLateral;
    }

    
    
    public void userAdmin(Stage primaryStage) {
        Background backgroundObject = imagineFundal("file:///C:/Users/laris/OneDrive/Desktop/fundal2.jpeg");
         
        StackPane stackPane = new StackPane();
        stackPane.setBackground(backgroundObject);
        
        VBox meniuLateral = meniuAdmin(primaryStage);

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(meniuLateral);
        stackPane.getChildren().add(borderPane);
        
        Scene scene = new Scene(stackPane, 1000, 763);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void metodaAdaugaCurse(Stage primaryStage) {
    	Background backgroundObject = imagineFundal("file:///C:/Users/laris/OneDrive/Desktop/fundal2.jpeg");
    	 
    	 StackPane stackPane = new StackPane();
         stackPane.setBackground(backgroundObject);
         
         VBox meniuLateral = meniuDistribuitor(primaryStage);
         
         Text textt = new Text("Adaugă cursă");
         textt.getStyleClass().add("textt");
         
         TextField dataPreluare =creeazaTextField("ZZ/LL/AAAA",250);
         
         TextField dataPredare =creeazaTextField("ZZ/LL/AAAA",250);
         
         TextField locPreluare =creeazaTextField("Locul de preluare",250); 
        		 
         TextField locPredare =creeazaTextField("Locul de predare",250);
         
         TextField tipIncarcatura =creeazaTextField("Tipul de încărcătură",250);
         
         Button adauga =creeazaButon("Adaugă cursă","buton2");
         adauga.setOnAction(event -> {
             try {
                 Connection connection = DriverManager.getConnection(url, username, password);
                 if (connection != null) {
                     id_Utilizator = metodaConectare(primaryStage);
                     String dataPreluaree = dataPreluare.getText();
                     String dataPredaree = dataPredare.getText();
                     String locPreluaree = locPreluare.getText();
                     String locPredaree = locPredare.getText();
                     String tipIncarcaturaa = tipIncarcatura.getText();
                     
                     if (dataPreluaree.equals("") || dataPredaree.equals("") || locPreluaree.equals("") || locPredaree.equals("") || tipIncarcaturaa.equals("")) {                        
                         metodaAdaugaCurse(primaryStage);
                         showPopUp(primaryStage, "Toate câmpurile sunt obligatorii!");
                     } else {
                    	 userDistribuitor(primaryStage);
                    	 curseDisponibile.adaugaCursa(connection, id_Utilizator, dataPreluaree, dataPredaree, locPreluaree, locPredaree, tipIncarcaturaa);
                         showPopUp(primaryStage, "Cursa a fost adăugată");
                         
                    	 
                     }
                 }
             } catch (SQLException e) {
                 System.out.println("Conectare esuată la baza de date");
                 e.printStackTrace();
             }
         });

         
         VBox content = new VBox(10);
         content.getChildren().addAll(textt, dataPreluare, dataPredare, locPreluare, locPredare, tipIncarcatura, adauga);
         content.setAlignment(Pos.CENTER);
         BorderPane borderPane = new BorderPane();
         borderPane.setLeft(meniuLateral);
         borderPane.setCenter(content);
         stackPane.getChildren().addAll(borderPane);
         
         Scene scene = new Scene(stackPane, 1000, 763);
         scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
         primaryStage.setScene(scene);
    	 primaryStage.show();
    }
    
    
    
    public void query(Connection connection, int destinatar_id, int expeditor_id) throws SQLException {
    	String queryy = "SELECT * from distribuitori where id =?";
          PreparedStatement statement2 = connection.prepareStatement(queryy);
          statement2.setInt(1,expeditor_id);
          ResultSet rezultat = statement2.executeQuery();
   		if (rezultat.next()) {
   			String numee=rezultat.getString("numeDistribuitor");
   			String continut = "Ai fost contactat de catre distribuitorul " + numee + " !";
   			String query2 = "Insert into mesaje(expeditor_id, destinatar_id, continut) VALUES (?, ?, ?)";
   			PreparedStatement st = connection.prepareStatement(query2);
   			st.setInt(1, expeditor_id);
   			st.setInt(2, destinatar_id);
   			st.setString(3, continut);
   			st.executeUpdate();
   		}
    }
    
    
    public void contactare(Stage primaryStage) {
    	Background backgroundObject = imagineFundal("file:///C:/Users/laris/OneDrive/Desktop/fundal2.jpeg");
    	 
    	 StackPane stackPane = new StackPane();
         stackPane.setBackground(backgroundObject);
         
         VBox meniuLateral = meniuDistribuitor(primaryStage);
         
         Text textt = new Text("Contactează șofer/firmă");
         textt.getStyleClass().add("textt");
         
         TextField utilizator =creeazaTextField("Sofer/firma",250);
         
         TextField nume =creeazaTextField("Numele șoferului/Denumirea firmei",250); 
         
         Button contactare = creeazaButon("Contactează","buton2"); 
         contactare.setOnAction(event -> {
        	 try {
      			Connection connection = DriverManager.getConnection(url, username, password);
      			if (connection !=null) {
      				    String Utilizator = utilizator.getText();
      				    String Nume = nume.getText();
      				 
      				    if(Utilizator.equalsIgnoreCase("sofer")) {
      				    	showPopUp(primaryStage, "Șoferul a fost contactat!");
      				    	
      				    	String query = "SELECT * FROM soferi WHERE numeSofer = ?";
      				    	PreparedStatement statement = connection.prepareStatement(query);
      				    	statement.setString(1, Nume);
      				    	ResultSet rs= statement.executeQuery();
      				    	while (rs.next()) {
      				    		//id-ul utilizatorului pentru care se trimite mesajul
      				    		int destinatar_id = rs.getInt("utilizator_id");
      				    		int expeditor_id = metodaConectare(primaryStage);
      				    		
      				    		query(connection,destinatar_id, expeditor_id);
      				    		userDistribuitor(primaryStage);
      				    	}
      				    	
      				    	rs.close();
      				    	statement.close();
      				    	connection.close();
      				    	
      				    }
      				    else  {
      				    	showPopUp(primaryStage, "Firma de transport a fost contactată!");
      				    	String query = "SELECT * FROM firmeTransport WHERE denumireCompanie = ?";
      				    	PreparedStatement statement = connection.prepareStatement(query);
      				    	statement.setString(1, Nume);
      				    	ResultSet rs= statement.executeQuery();
      				    	while (rs.next()) {
      				    		int destinatar_id = rs.getInt("utilizator_id");
      				    		int expeditor_id = metodaConectare(primaryStage);
      				    		
      				    		query(connection,destinatar_id, expeditor_id);
      				    		userDistribuitor(primaryStage);
      				    		 
      				    	}
      				    	
      				    	rs.close();
      				    	statement.close();
      				    	connection.close();
      				    }
      				 
      					
      			}}
                catch(SQLException e) {
      			System.out.println("Conectareeee esuata la baza de date");
      			e.printStackTrace();
                }
          	}
              );     
         VBox content = new VBox(10);
         content.getChildren().addAll(textt,utilizator, nume, contactare);
         content.setAlignment(Pos.CENTER);
         
         BorderPane borderPane = new BorderPane();
         borderPane.setLeft(meniuLateral);
         borderPane.setCenter(content);
         
         stackPane.getChildren().addAll(borderPane);
         
         Scene scene = new Scene(stackPane, 1000, 763);
         scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
         primaryStage.setScene(scene);
    	 primaryStage.show();
    }
    
    public void afisareSoferiPentruDistribuitor(Stage primaryStage) {
    	Background backgroundObject = imagineFundal("file:///C:/Users/laris/OneDrive/Desktop/fundal2.jpeg");

        StackPane stackPane = new StackPane();
        stackPane.setBackground(backgroundObject);

        VBox meniuLateral = meniuDistribuitor(primaryStage);

        VBox soferi = soferi(primaryStage);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(soferi);
        borderPane.setLeft(meniuLateral);
        stackPane.getChildren().addAll(borderPane);

        Scene scene = new Scene(stackPane, 1000, 763);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    public void afisareFirmePentruDistribuitor(Stage primaryStage) {
        Background backgroundObject = imagineFundal("file:///C:/Users/laris/OneDrive/Desktop/fundal2.jpeg");
         
        StackPane stackPane = new StackPane();
        stackPane.setBackground(backgroundObject);
        
        VBox meniuLateral = meniuDistribuitor(primaryStage);

        VBox firme = firme(primaryStage);
        

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(meniuLateral);
        borderPane.setCenter(firme); 

        stackPane.getChildren().add(borderPane);

        Scene scene = new Scene(stackPane, 1000, 763);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private VBox meniuDistribuitor(Stage primaryStage) {
        VBox meniuLateral = new VBox(10);
        meniuLateral.setId("meniu-lateral");

        Label titluMeniu = new Label("Meniu");
        titluMeniu.setId("titlu-meniu");
        meniuLateral.getChildren().add(titluMeniu);

        Button inapoi = creeazaButon("Înapoi", "buton-meniu");
        inapoi.setOnAction(event -> metodaConectare(primaryStage));
        meniuLateral.getChildren().add(inapoi);

        String[] numeButoane = {"Vizualizează șoferii disponibili", "Vizualizează firmele de transport", "Contactează șoferul/firma de transport", 
                                "Adaugă curse", "Șterge contul"};

        for (String numeButon : numeButoane) {
            Button buton = creeazaButon(numeButon, "buton-meniu");
            switch (numeButon) {
            case "Vizualizează șoferii disponibili":
            	if (!esteTabelGol("utilizatori")) {
                    buton.setOnAction(event -> {
                        afisareSoferiPentruDistribuitor(primaryStage);
                    });
                } else {
                    buton.setOnAction(event -> showPopUp(primaryStage, "Nu există șoferi!")); 
                }
                break;
            case "Vizualizează firmele de transport":
            	if (!esteTabelGol("utilizatori")) {
                    buton.setOnAction(event -> {
                        afisareFirmePentruDistribuitor(primaryStage);
                    });
                } else {
                    buton.setOnAction(event -> showPopUp(primaryStage, "Nu există firme!")); 
                }
                break;
            case "Contactează șoferul/firma de transport":
            	
                    buton.setOnAction(event -> 
                    contactare(primaryStage)
                    );
                
                break;
            case "Adaugă curse":
            
                    buton.setOnAction(event -> 
                    metodaAdaugaCurse(primaryStage));
                    
                break;
            case "Șterge contul":
     
                    buton.setOnAction(event -> {
                    	id_Utilizator = metodaConectare(primaryStage);
                    	confirmareStergere(primaryStage, id_Utilizator, "distribuitor");
                    	
                    });
                
                break;
            
           
        }
            meniuLateral.getChildren().add(buton);
        }

        return meniuLateral;
    }
    
    public void userDistribuitor(Stage primaryStage) {
        Background backgroundObject = imagineFundal("file:///C:/Users/laris/OneDrive/Desktop/fundal2.jpeg");
         
        StackPane stackPane = new StackPane();
        stackPane.setBackground(backgroundObject);
        
        VBox meniuLateral = meniuDistribuitor(primaryStage);

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(meniuLateral);
        stackPane.getChildren().add(borderPane);
        
        Scene scene = new Scene(stackPane, 1000, 763);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    
    
    
    
    
    
    
    public void metodaAdaugaAutomobil(Stage primaryStage) {
    	Background backgroundObject = imagineFundal("file:///C:/Users/laris/OneDrive/Desktop/fundal2.jpeg");

        StackPane stackPane = new StackPane();
        stackPane.setBackground(backgroundObject);

        VBox meniuLateral = meniuSofer(primaryStage);

        Text textt = new Text("Adaugă automobil");
        textt.getStyleClass().add("textt");

        TextField tipAutomobil =creeazaTextField("Tip automobil",250); 

        TextField inaltimeMaxima =creeazaTextField("Înălțimea maximă",250);

        TextField cantitateMaxima = creeazaTextField("Cantitatea maximă",250);

        TextField verificareITP = creeazaTextField("Verificare ITP",250);

        TextField asigurare = creeazaTextField("Asigurare",250);

        Button adaugaa =creeazaButon("Adaugă automobil","buton2");
        adaugaa.setOnAction(event -> {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                if (connection != null) {
                    int idUtilizator = metodaConectare(primaryStage);

                    String tipAutomobill = tipAutomobil.getText();
                    String inaltimeMaximaa = inaltimeMaxima.getText();
                    String cantitateMaximaa = cantitateMaxima.getText();
                    String verificareITPP = verificareITP.getText();
                    String asiguraree = asigurare.getText();

                    if (tipAutomobill.equals("") || inaltimeMaximaa.equals("") || cantitateMaximaa.equals("") || verificareITPP.equals("") || asiguraree.equals("")) {
                        metodaAdaugaAutomobil(primaryStage);
                    	showPopUp(primaryStage, "Toate câmpurile sunt obligatorii!");
                    } else {
                        automobil.adaugaAutomobil(connection, tipAutomobill, inaltimeMaximaa, cantitateMaximaa, verificareITPP, asiguraree, idUtilizator);
                        userSofer(primaryStage);
                        showPopUp(primaryStage, "Automobilul a fost adăugat!");
                        
                    }
                }
            } catch (SQLException e) {
                System.out.println("Conectare esuata la baza de date");
                e.printStackTrace();
            }

        });

        VBox content = new VBox(10);
        content.getChildren().addAll(textt, tipAutomobil, inaltimeMaxima, cantitateMaxima, verificareITP, asigurare, adaugaa);
        content.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(meniuLateral);
        borderPane.setCenter(content);

        stackPane.getChildren().addAll(borderPane);

        Scene scene = new Scene(stackPane, 1000, 763);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    public void afisareCursaPentruSofer(Stage primaryStage) {
    	Background backgroundObject = imagineFundal("file:///C:/Users/laris/OneDrive/Desktop/fundal2.jpeg");

        StackPane stackPane = new StackPane();
        stackPane.setBackground(backgroundObject);

        VBox meniuLateral = meniuSofer(primaryStage);
        VBox curse = curse(primaryStage);
        
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(curse);
        borderPane.setLeft(meniuLateral);
        
        stackPane.getChildren().addAll(borderPane);

        Scene scene = new Scene(stackPane, 1000, 763);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    private VBox meniuSofer(Stage primaryStage) {
        VBox meniuLateral = new VBox(10);
        meniuLateral.setId("meniu-lateral");

        Label titluMeniu = new Label("Meniu");
        titluMeniu.setId("titlu-meniu");
        meniuLateral.getChildren().add(titluMeniu);

        Button inapoi = creeazaButon("Înapoi", "buton-meniu");
        inapoi.setOnAction(event -> metodaConectare(primaryStage));
        meniuLateral.getChildren().add(inapoi);

        String[] numeButoane = {"Adaugă automobil", "Șterge automobil", "Șterge contul",
                "Vizualizează cursele disponibile"};

        for (String numeButon : numeButoane) {
            Button buton = creeazaButon(numeButon, "buton-meniu");
            meniuLateral.getChildren().add(buton); 

            switch (numeButon) {
                case "Adaugă automobil":
                    buton.setOnAction(event -> metodaAdaugaAutomobil(primaryStage));
                    break;

                case "Șterge automobil":
                    buton.setOnAction(event -> {
                        try {
                            Connection connection = DriverManager.getConnection(url, username, password);
                            if (connection != null) {
                                id_Utilizator = metodaConectare(primaryStage);
                                automobil.stergeAutomobil(connection, id_Utilizator);
                                userSofer(primaryStage);
                                showPopUp(primaryStage, "Automobilul a fost șters!");
                            }
                        } catch (SQLException e) {
                            System.out.println("Conectare esuata la baza de date");
                            e.printStackTrace();
                        }
                    });
                    break;

                case "Vizualizează cursele disponibile":
                    if (!esteTabelGol("curseDisponibile")) {
                        buton.setOnAction(event -> afisareCursaPentruSofer(primaryStage));
                    } else {
                        buton.setOnAction(event -> showPopUp(primaryStage, "Nu există curse disponibile!"));
                    }
                    break;

                case "Șterge contul":
                    buton.setOnAction(event ->{
                    	id_Utilizator = metodaConectare(primaryStage);
                    	confirmareStergere(primaryStage, id_Utilizator, "sofer");
                    	
                    });
                    
                    break;
            }
        }

        return meniuLateral;
    }

    
    public void userSofer(Stage primaryStage) {
        Background backgroundObject = imagineFundal("file:///C:/Users/laris/OneDrive/Desktop/fundal2.jpeg");
         
        StackPane stackPane = new StackPane();
        stackPane.setBackground(backgroundObject);
        
        VBox meniuLateral = meniuSofer(primaryStage);

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(meniuLateral);
        stackPane.getChildren().add(borderPane);
        
        Scene scene = new Scene(stackPane, 1000, 763);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    
    
    
    
    
    

    
    public void afisareCursePentruFirma(Stage primaryStage) {
    	Background backgroundObject = imagineFundal("file:///C:/Users/laris/OneDrive/Desktop/fundal2.jpeg");

        StackPane stackPane = new StackPane();
        stackPane.setBackground(backgroundObject);

        VBox meniuLateral = meniuFirma(primaryStage);
        VBox curse = curse(primaryStage);
        
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(curse);
        borderPane.setLeft(meniuLateral);
        
        stackPane.getChildren().addAll(borderPane);

        Scene scene = new Scene(stackPane, 1000, 763);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
       
    
    private VBox meniuFirma(Stage primaryStage) {
        VBox meniuLateral = new VBox(10);
        meniuLateral.setId("meniu-lateral");

        Label titluMeniu = new Label("Meniu");
        titluMeniu.setId("titlu-meniu");
        meniuLateral.getChildren().add(titluMeniu);

        Button inapoi = creeazaButon("Înapoi", "buton-meniu");
        inapoi.setOnAction(event -> metodaConectare(primaryStage));
        meniuLateral.getChildren().add(inapoi);

        String[] numeButoane = {"Vizualizează cursele disponibile", "Șterge contul"};

        for (String numeButon : numeButoane) {
            Button buton = creeazaButon(numeButon, "buton-meniu");
            switch (numeButon) {
            case "Vizualizează cursele disponibile":
            	if (!esteTabelGol("curseDisponibile")) {
                    buton.setOnAction(event -> {
                        afisareCursePentruFirma(primaryStage);
                    });
                } else {
                    buton.setOnAction(event -> showPopUp(primaryStage, "Nu există curse disponibile!")); 
                }
                break;
            case "Șterge contul":
            
                    buton.setOnAction(event -> {
                   	 	id_Utilizator=metodaConectare(primaryStage);
                   	 	confirmareStergere(primaryStage, id_Utilizator, "firma");  	
                    });
                   
                break;
            
           
        }
            meniuLateral.getChildren().add(buton);
        }

        return meniuLateral;
    }
    

    public void userFirma(Stage primaryStage) {
        Background backgroundObject = imagineFundal("file:///C:/Users/laris/OneDrive/Desktop/fundal2.jpeg");
         
        StackPane stackPane = new StackPane();
        stackPane.setBackground(backgroundObject);
        
        VBox meniuLateral = meniuFirma(primaryStage);

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(meniuLateral);
        stackPane.getChildren().add(borderPane);
        
        Scene scene = new Scene(stackPane, 1000, 763);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void vizualizareaOfertei(Stage primaryStage, int destinatar, int expeditor, String tipUtilizator) {
        Background backgroundObject = imagineFundal("file:///C:/Users/laris/OneDrive/Desktop/fundal2.jpeg");

        StackPane stackPane = new StackPane();
        stackPane.setBackground(backgroundObject);

        VBox meniuLateral;
        if(tipUtilizator.equals("sofer")) {
        	meniuLateral = meniuSofer(primaryStage);
        }
        else {
        	meniuLateral = meniuFirma(primaryStage);
        }
        
        VBox curse = new VBox();
        curse.setSpacing(10);
        curse.setAlignment(Pos.CENTER);

        Curse(curse, expeditor);

        Button accepta = creeazaButon("Acceptă oferta", "buton1");
        Button refuza = creeazaButon("Refuză oferta", "buton1");
        accepta.setOnAction(event -> acceptaOferta(primaryStage,destinatar, expeditor, tipUtilizator));
        refuza.setOnAction(event -> refuzaOferta(primaryStage,destinatar, expeditor));
        curse.getChildren().addAll(accepta,refuza);
        BorderPane borderPane = new BorderPane();
        
        borderPane.setCenter(curse);
        borderPane.setLeft(meniuLateral);
        stackPane.getChildren().add(borderPane);

        Scene scene = new Scene(stackPane, 1000, 763);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void refuzaOferta(Stage primaryStage, int destinatar, int expeditor) {
    	Connection connection;
		try {
			connection = DriverManager.getConnection(url, username, password);
			if (connection != null) {
	             try {
	            	showPopUp(primaryStage, "Oferta a fost refuzată!");
		 
	             	 String deleteQuery = "DELETE FROM mesaje WHERE destinatar_id = ?";
	                 PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
	                 preparedStatement.setInt(1, destinatar);
	                 int rowsDeleted = preparedStatement.executeUpdate();
	                 if (rowsDeleted > 0) {
	                     System.out.println("Mesajul a fost șters din tabelul mesaje!");
	                 } else {
	                     System.out.println("Nu s-a găsit mesajul cu ID-ul specificat.");
	                 }
	             } catch (SQLException e) {
	                 System.err.println("Eroare la ștergerea mesajului: " + e.getMessage());
	             } finally {
	                 try {
	                     connection.close();
	                 } catch (SQLException e) {
	                     e.printStackTrace();
	                 }
	             }
	         }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
    }
    
   
    
    private void Curse(VBox curse, int expeditor) {
        curse.setSpacing(10);
    	try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM CURSEDISPONIBILE WHERE utilizator_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, expeditor);
            ResultSet resultSet = statement.executeQuery();
            
            
            while (resultSet.next()) {
                String dataPreluare = resultSet.getString("dataPreluare");
                String dataPredare = resultSet.getString("dataPredare");
                String locPreluare = resultSet.getString("locPreluare");
                String locPredare = resultSet.getString("locPredare");
                String tipIncarcatura = resultSet.getString("tipIncarcatura");
                int utilizator_id = resultSet.getInt("utilizator_id");
                
                
                String queryy = "SELECT * from distribuitori where id =?";
                PreparedStatement statement2 = connection.prepareStatement(queryy);
                statement2.setInt(1,utilizator_id);
                ResultSet rezultat = statement2.executeQuery();
         		if (rezultat.next()) {
         			String nume=rezultat.getString("numeDistribuitor");
         			BorderPane dreptunghiCursa = new BorderPane();
                    dreptunghiCursa.getStyleClass().add("dreptunghi");
         			Label cursa = new Label("Cursa distribuitorului "+ nume + ":" + "\n Data preluare: " + dataPreluare + "| Data predare: " + dataPredare +
                        "| Loc preluare: " + locPreluare + "| Loc predare: " + locPredare + "| Tip încărcătură: " +
                        tipIncarcatura);
         			VBox detaliiCursa = new VBox(cursa);
                    dreptunghiCursa.setCenter(detaliiCursa);

                    curse.getChildren().add(dreptunghiCursa);
         		}
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Conectare esuata la baza de date");
            e.printStackTrace();
        }
    }
    
    
    private void acceptaOferta(Stage primaryStage, int destinatar, int expeditor, String tipUtilizator) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            showPopUp(primaryStage, "Oferta a fost acceptată!");

           
            String deleteQuery = "DELETE FROM mesaje WHERE destinatar_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setInt(1, destinatar);
                int rowsDeleted = preparedStatement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Mesajul a fost șters din tabelul mesaje!");
                } else {
                    System.out.println("Nu s-a găsit mesajul cu ID-ul specificat.");
                }
            }

           
            String queryCurse = "SELECT * FROM curseDisponibile WHERE utilizator_id=?";
            try (PreparedStatement statementCurse = connection.prepareStatement(queryCurse)) {
                statementCurse.setInt(1, expeditor);
                try (ResultSet resultSet = statementCurse.executeQuery()) {
                    if (resultSet.next()) {
                        
                        String dataPreluare = resultSet.getString("dataPreluare");
                        String dataPredare = resultSet.getString("dataPredare");
                        String locPreluare = resultSet.getString("locPreluare");
                        String locPredare = resultSet.getString("locPredare");
                        String tipIncarcatura = resultSet.getString("tipIncarcatura");

                       
                        adaugaParteneriatSiStergeCursa(connection, expeditor, destinatar, dataPreluare, dataPredare, locPreluare, locPredare, tipIncarcatura, tipUtilizator);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Eroare la procesarea ofertei: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void adaugaParteneriatSiStergeCursa(Connection connection, int expeditor, int destinatar, String dataPreluare, String dataPredare, String locPreluare, String locPredare, String tipIncarcatura, String tipUtilizator) throws SQLException {
        
        String coordonator = determinaCoordonator(connection, expeditor);
        String transportator = determinaTransportator(connection, destinatar, tipUtilizator);

       
        parteneriate.adaugaParteneriat(connection, coordonator, transportator, dataPreluare, dataPredare, locPreluare, locPredare, tipIncarcatura);

        
        curseDisponibile.stergeCursa(connection, expeditor);
    }

    private String determinaCoordonator(Connection connection, int expeditor) {
    	String coordonator = null;
        String query = "SELECT numeDistribuitor FROM distribuitori WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, expeditor);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    coordonator = resultSet.getString("numeDistribuitor");
                }
            }
        } catch (SQLException e) {
            System.err.println("Eroare la determinarea coordonatorului: " + e.getMessage());
            e.printStackTrace();
        }
        return coordonator;
    }

    private String determinaTransportator(Connection connection, int destinatar, String tipUtilizator) {
    	String transportator = null ;
    	if(tipUtilizator.equals("sofer")) {
    		String query = "SELECT numeSofer FROM soferi WHERE utilizator_id = ?";
    		try (PreparedStatement statement = connection.prepareStatement(query)) {
    			statement.setInt(1, destinatar);
    			try (ResultSet resultSet = statement.executeQuery()) {
    				if (resultSet.next()) {
    					transportator = resultSet.getString("numeSofer");
    				}
    			}
    		} catch (SQLException e) {
    			System.err.println("Eroare la determinarea transportatorului: " + e.getMessage());
    			e.printStackTrace();
    		}
    		}
    	else {
    		String query = "SELECT denumireCompanie FROM firmetransport WHERE utilizator_id = ?";
    		try (PreparedStatement statement = connection.prepareStatement(query)) {
    			statement.setInt(1, destinatar);
    			try (ResultSet resultSet = statement.executeQuery()) {
    				if (resultSet.next()) {
    					transportator = resultSet.getString("denumireCompanie");
    				}
    			}
    		} catch (SQLException e) {
    			System.err.println("Eroare la determinarea transportatorului: " + e.getMessage());
    			e.printStackTrace();
    		}
    	}
        
        return transportator;
    }



    public int id_Utilizator;
    public int metodaConectare(Stage primaryStage) {
    	 Background backgroundObject = imagineFundal("file:///C:/Users/laris/OneDrive/Desktop/fundal2.jpeg");
    	 StackPane stackPane = new StackPane();
         stackPane.setBackground(backgroundObject);
         
         Text textt = new Text("Conectare");
         textt.getStyleClass().add("textt");
         
         TextField Username =creeazaTextField("Nume utilizator",250);
         
         PasswordField Parola = new PasswordField();
         Parola.setPromptText("Parolă");
         Parola.setMaxWidth(250);
        
         Button inapoi = creeazaButon("Înapoi", "buton1");
         inapoi.setOnAction(event -> start(primaryStage));
         
         Button conectare = creeazaButon("Conectare", "buton2");
         conectare.setOnAction( event -> {
         	 try {
     			Connection connection = DriverManager.getConnection(url, username, password);
     			if (connection !=null) {
     				String Usernamee = Username.getText();
     				String Parolaa = Parola.getText();
     				String query = "SELECT * FROM utilizatori WHERE Username=? AND Parola=?";
     		        PreparedStatement statement = connection.prepareStatement(query);
     		        statement.setString(1, Usernamee);
     		        statement.setString(2, Parolaa);
     		        ResultSet resultSet = statement.executeQuery();

     		        if (resultSet.next()) {
     		            System.out.println("Autentificare reușită!");
     		            int idUtilizator = resultSet.getInt("id");
     		          
     		            String queryDistribuitori = "SELECT * FROM distribuitori WHERE utilizator_id=?";
     		            PreparedStatement statementDistribuitori = connection.prepareStatement(queryDistribuitori);
     		            statementDistribuitori.setInt(1, idUtilizator);
     		            ResultSet resultSetDistribuitori = statementDistribuitori.executeQuery();
     		          
     		            String querySoferi = "SELECT * FROM soferi WHERE utilizator_id=?";
     		            PreparedStatement statementSoferi = connection.prepareStatement(querySoferi);
     		            statementSoferi.setInt(1, idUtilizator);
     		            ResultSet resultSetSoferi = statementSoferi.executeQuery();
    		          
     		            String queryFirma = "SELECT * FROM firmetransport WHERE utilizator_id=?";
     		            PreparedStatement statementFirma = connection.prepareStatement(queryFirma);
     		            statementFirma.setInt(1, idUtilizator);
     		            ResultSet resultSetFirma = statementFirma.executeQuery();

     		            if (resultSetDistribuitori.next()) {
     		            	System.out.println("Utilizatorul este și distribuitor.");
     		            	int id_Utilizatorr=resultSetDistribuitori.getInt("utilizator_id");
     		            	id_Utilizator = resultSetDistribuitori.getInt("id");
     		            	String queryMesaje = "SELECT continut FROM mesaje WHERE destinatar_id = ?";
     		            	PreparedStatement statementMesaje = connection.prepareStatement(queryMesaje);
     		            	statementMesaje.setInt(1, id_Utilizatorr);
     		            	ResultSet resultSetMesaje = statementMesaje.executeQuery();

     		            	StringBuilder mesaje = new StringBuilder();
     		            	while (resultSetMesaje.next()) {
     		            		mesaje.append(resultSetMesaje.getString("continut")).append("\n");
     		            	}
     		            	if (!mesaje.isEmpty()) {
     		            		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
     		            		alert.setHeaderText(null);
     		            		alert.setContentText(mesaje.toString());

     		            		ButtonType buton = new ButtonType("");
     		            		alert.getButtonTypes().setAll(buton);

     		            		Optional<ButtonType> result = alert.showAndWait();
     		            		if (result.isPresent() && result.get() == buton) {
     		            			//functiaMea();
     		            		}
     		            	}

     		            	resultSetMesaje.close();
     		            	statementMesaje.close();
     		            	resultSetDistribuitori.close();
     		            	statementDistribuitori.close();
     		            	userDistribuitor(primaryStage);
     		              
     		              
     		          } else if (resultSetSoferi.next()) {
     		        	  	System.out.println("Utilizatorul este șofer.");
     		        	  	int destinatar = resultSetSoferi.getInt("utilizator_id");
     		        	  	id_Utilizator = resultSetSoferi.getInt("id");
     		            
     		        	  	String queryMesaje = "SELECT continut FROM mesaje WHERE destinatar_id = ?";
     		        	  	PreparedStatement statementMesaje = connection.prepareStatement(queryMesaje);
     		        	  	statementMesaje.setInt(1, destinatar);
     		        	  	ResultSet resultSetMesaje = statementMesaje.executeQuery();

     		        	  	StringBuilder mesaje = new StringBuilder();
     		        	  	while (resultSetMesaje.next()) {
     		        	  		mesaje.append(resultSetMesaje.getString("continut")).append("\n");
     		        	  	}

     		         
     		        	  	if (!mesaje.isEmpty()) {
     		        	  		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
     		        	  		alert.setHeaderText(null);
     		        	  		alert.setContentText(mesaje.toString());

     		        	  		ButtonType buton = new ButtonType("Vizualizează oferta distribuitorului");

     		        	  		alert.getButtonTypes().setAll(buton);

     		        	  		Optional<ButtonType> result = alert.showAndWait();
     		        	  		if (result.isPresent() && result.get() == buton) {
     		        	  			String queryy = "SELECT * FROM mesaje WHERE destinatar_id = ?";
     		        	  			PreparedStatement mesajee = connection.prepareStatement(queryy);
     		        	  			mesajee.setInt(1, destinatar);
     		        	  			ResultSet rezultat = mesajee.executeQuery();
     		        	  			if (rezultat.next()) {
     		        	  				int expeditor=rezultat.getInt("expeditor_id");
     		        	  				vizualizareaOfertei(primaryStage,destinatar, expeditor, "sofer");
     		            		   
     		        	  			}
     		            		 
     		        	  			rezultat.close();
     		        	  			mesajee.close();
     		        	  		}
     		        	  	}
     		        	  	else {
     		        	  		userSofer(primaryStage);
     		        	  	}
     		             
     		           
     		        	  	resultSetMesaje.close();
     		        	  	statementMesaje.close();
     		        	  	resultSetSoferi.close();
     		        	  	statementSoferi.close();
     		        	  	resultSet.close();
     		        	  	statement.close();
     		          
     		              
     		          } else if(resultSetFirma.next()){
     		        	  	System.out.println("Utilizatorul este reprezentantul unei firme de transport.");
     		        	  	int destinatar = resultSetFirma.getInt("utilizator_id");
     		        	  	id_Utilizator = resultSetFirma.getInt("id");
     		        	  	String queryMesaje = "SELECT continut FROM mesaje WHERE destinatar_id = ?";
     		        	  	PreparedStatement statementMesaje = connection.prepareStatement(queryMesaje);
     		        	  	statementMesaje.setInt(1, destinatar);
     		        	  	ResultSet resultSetMesaje = statementMesaje.executeQuery();

     		        	  	StringBuilder mesaje = new StringBuilder();
     		        	  	while (resultSetMesaje.next()) {
     		        	  		mesaje.append(resultSetMesaje.getString("continut")).append("\n");
     		        	  	}

     		         
     		        	  	if (!mesaje.isEmpty()) {
     		        	  		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
     		        	  		alert.setHeaderText(null);
     		        	  		alert.setContentText(mesaje.toString());

     		        	  		ButtonType buton = new ButtonType("Vizualizează oferta distribuitorului");
     		        	  		alert.getButtonTypes().setAll(buton);

     		        	  		Optional<ButtonType> result = alert.showAndWait();
     		        	  		if (result.isPresent() && result.get() == buton) {
     		        	  			String queryy = "Select expeditor_id from mesaje where destinatar_id = ?";
     		        	  			PreparedStatement mesajee = connection.prepareStatement(queryy);
     		        	  			mesajee.setInt(1, destinatar);
     		        	  			ResultSet rezultat = mesajee.executeQuery();
     		        	  			if (rezultat.next()) {
     		        	  				int expeditor=rezultat.getInt("expeditor_id");
     		        	  				vizualizareaOfertei(primaryStage,destinatar, expeditor, "firma");
     		        	  			}
     		            		
     		        	  			rezultat.close();
     		        	  			mesajee.close();
     		        	  		}
     		        	  	}
     		           
     		        	  	else {
     		        	  		userFirma(primaryStage);
     		        	  	}

     		        	  	resultSetMesaje.close();
     		        	  	statementMesaje.close();
     		        	  	resultSetFirma.close();
     		        	  	statementFirma.close();
     		        	  	resultSet.close();
     		        	  	statement.close();
     		        	
     		          }
     		          
     		         
     		        } else if ("admin".equalsIgnoreCase(Usernamee) && "admin".equalsIgnoreCase(Parolaa)) {
     		           System.out.println("Utilizatorul este admin.");
     		           userAdmin(primaryStage);
     		        	
     		        } else if ("".equalsIgnoreCase(Usernamee) || "".equalsIgnoreCase(Parolaa)) {
      		           showPopUp(primaryStage, "Toate câmpurile sunt obligatorii!");
    		        	
      		        } else {
     		            System.out.println("Autentificare eșuată. Verifică username-ul și parola.");
     		            showPopUp(primaryStage, "Autentificare eșuată. Verifică username-ul și parola.");
     		        }
     		        
     		        connection.close();
     		    }
     			
         	 }
     		 catch(SQLException e) {
     			System.out.println("Conectareeee esuata la baza de date");
     			e.printStackTrace();
     		}
         	 
         	
         });
         
         BorderPane borderPane = new BorderPane();
         borderPane.setTop(inapoi);
         BorderPane.setAlignment(inapoi, Pos.TOP_LEFT);
         BorderPane.setMargin(inapoi, new Insets(10)); 
         
         VBox content = new VBox(10);
         content.getChildren().addAll(textt,Username, Parola, conectare);
         content.setAlignment(Pos.CENTER);
         
         borderPane.setCenter(content);
         stackPane.getChildren().addAll(borderPane);
         
         Scene scene = new Scene(stackPane, 1000, 763);
         scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
         primaryStage.setScene(scene);
    	 primaryStage.show();
    	 return id_Utilizator;
    }
    
    
    
   
    public void start(Stage primaryStage) {
    	primaryStage.setTitle("Logistică Auto");    
    	
    	 Background backgroundObject = imagineFundal("file:///C:/Users/laris/OneDrive/Desktop/fundal.jpg");

    	   
    	 Button creareCont =creeazaButon("Creează un cont nou","buton1");
    	 Button conectare = creeazaButon("Conectează-te la un cont existent","buton2");
    	 
    	 VBox vbox = new VBox(10); 
    	 vbox.getChildren().addAll(creareCont, conectare);
    	 vbox.setAlignment(Pos.CENTER); 

    	 StackPane stackPane = new StackPane();
         stackPane.setBackground(backgroundObject);
         stackPane.getChildren().add(vbox);
         Scene scene = new Scene(stackPane, 1000, 763);
         scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
         
         creareCont.setOnAction(event -> metodaCreareCont(primaryStage));
         conectare.setOnAction(event -> metodaConectare(primaryStage));
         
         primaryStage.setScene(scene);
    	 primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
 }

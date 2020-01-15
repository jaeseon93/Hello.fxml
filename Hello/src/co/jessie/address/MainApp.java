package co.jessie.address;

import java.io.IOException;

import co.jessie.address.model.Person;
import co.jessie.address.view.PersonOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	private Stage primaryStage; // 최상위 루트 스테이지
	private BorderPane rootLayout;
	private ObservableList<Person> personData = FXCollections.observableArrayList();// fx가가지고있는컬렉션리스트

	public MainApp() {
		personData.add(new Person("Hans", "Muster"));
		personData.add(new Person("Ruth", "Mueller"));
		personData.add(new Person("Heinz", "Kurz"));
		personData.add(new Person("Cornelia", "Meier"));
		personData.add(new Person("Werner", "Meyer"));
		personData.add(new Person("Lydia", "Kunz"));
		personData.add(new Person("Anna", "Best"));
		personData.add(new Person("Stefan", "Meier"));
		personData.add(new Person("Martin", "Mueller"));
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("AddressApp");

		initRootLayout();

		showPersonOverview();
	}

	public void showPersonOverview() {
		try {
			// 연락처 요약을 가져온다.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load(); // AnchorPane타입

			// 연락처 요약을 상위 레이아웃 가운데로 설정한다.
			rootLayout.setCenter(personOverview);// AnchorPane을 가지고있음. , setCenter:가운데
			//컨트롤러 연결해서 호출하는부분
			PersonOverviewController controller = loader.getController(); 
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 상위 레이아웃을 초기화한다.
	 */
	private void initRootLayout() {
		try {
			// fxml 파일에서 상위 레이아웃을 가져온다.
			FXMLLoader loader = new FXMLLoader(); // fxml파일을 로드하기위해 선언.
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// 상위 레이아웃을 포함하는 scene을 보여준다. (boderpane만보는것)
			Scene scene = new Scene(rootLayout); // rootLayout - BorderPane을가지고있음.
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args); // 실행시키면 1.start 매소드로간다. ( stage를 만들어놈) 2."AddressApp"가지고있다.
	}

	public ObservableList<Person> getPersonData() {
		return personData;
	}

}

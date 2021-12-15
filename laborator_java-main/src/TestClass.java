import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;
import java.io.File;

enum LOAD_TYPE {
	HARDCODAT, KEYBOARD, FILE
}

enum DISPLAY_TYPE  {
	CONSOLA, FISIER, GUI
}

public class TestClass {
	public static void main(String[] args) {
		// Cerinta 1: incarcam setarile in memorie
		Settings.loadSettings();
		IDisplayManager displayManager = Settings.displayHashMap.get(Settings.displayType);
		IDataLoader dataManager = Settings.dataLoaderHashMap.get(Settings.loadType);
		displayManager.displayStudents(dataManager.createStudentsData());
	}
}

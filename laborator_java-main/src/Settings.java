import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Settings {
    static String STUDENTS_PATH = "studenti.csv";
    static String TEACHERS_PATH = "profesori.csv";
    static String COURSES_PATH= "cursuri.csv";
    static String SETTINGS_PATH= "settings.txt"; // Cale fisier setari
    static LOAD_TYPE loadType = LOAD_TYPE.HARDCODAT;
    static DISPLAY_TYPE displayType = DISPLAY_TYPE.GUI;
    static  HashMap<LOAD_TYPE, IDataLoader> dataLoaderHashMap = new HashMap<>() {{ put(LOAD_TYPE.HARDCODAT, new HardcodatDataManager()); put(LOAD_TYPE.FILE, new FileDataManager()); put(LOAD_TYPE.KEYBOARD, new KeyboardDataManager()); }  };
    static HashMap<DISPLAY_TYPE, IDisplayManager> displayHashMap = new HashMap<>() {{ put(DISPLAY_TYPE.CONSOLA, new ConsoleDisplay()); put(DISPLAY_TYPE.FISIER, new FileDisplay()); put(DISPLAY_TYPE.GUI, new GraphicUserInterfaceDisplay()); }  };

    // Functie de load
    static void loadSettings() {
        try {
            //Folosim reader pentru a citi fisierul
            BufferedReader br = new BufferedReader(new FileReader(SETTINGS_PATH));
            String line = null;
            while ((line = br.readLine()) != null) {
                Scanner sc = new Scanner(line);
                // Delimitare nume cu "="
                sc.useDelimiter("=");
                String name = sc.next();
                String value = sc.next().trim(); // eliminam spatiul de dupa egal (in fisier au un spatiu in plus, trebuie eliminat)
                value = value.substring(1, value.length() - 1); // eliminam ghilimelele
                // Se actualizeaza setarile si datele clasei in functie de ce e scris in fisier
                if (name.equals("STUDENTS_PATH")) {
                    STUDENTS_PATH = value;
                } else if (name.equals("TEACHERS_PATH")) {
                    TEACHERS_PATH = value;
                } else if (name.equals("COURSES_PATH")) {
                    COURSES_PATH = value;
                } else if (name.equals("LOAD_TYPE")) {
                    loadType = LOAD_TYPE.valueOf(value);
                } else if (name.equals("DISPLAY_TYPE")) {
                    displayType = DISPLAY_TYPE.valueOf(value);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Afisam setarile curente citite din fisier
        System.out.println("Setari curente: loadType=" + loadType +  ", displayType=" + displayType);
    }
}

import java.io.File;

public class testRuta {
    public static void main(String[] args) {
        File file = new File("C:/Users/mvarg/Documents/programming/ADSO/4 trimestre/JAVA/Ejercicios/AdministradorDePersonas-JTable/src/properties/archivo.properties");
        System.out.println("¿Existe el archivo?: " + file.exists());
    }
}
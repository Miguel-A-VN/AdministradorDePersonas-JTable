import java.io.File;

public class testRuta {
    public static void main(String[] args) {
        File file = new File("C:/Users/josey/OneDrive/Documentos/EjemploMVCPersona/src/properties/archivo.properties");
        System.out.println("¿Existe el archivo?: " + file.exists());
    }
}
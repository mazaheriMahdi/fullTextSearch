import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File folder = new File(scanner.nextLine());
        List<File> files = List.of(folder.listFiles());
        for (File file : files){
            System.out.println(file.getName());
        }
    }
}
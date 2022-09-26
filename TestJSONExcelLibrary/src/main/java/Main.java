
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<User> userList = ListUtils.getUserList();
        String nameFileXML = "Users.xml";
        String nameFileJSON = "Users.json";
        FileUtils.writeToJSON(userList, nameFileJSON);
        FileUtils.writeToXML(userList, nameFileXML);

        List<User> listReadJSON = FileUtils.readJSON("Users.json");
        List<User> listReadXML = FileUtils.readXML("Users.xml");

        System.out.println("Результат считывания из файла JSON");
        listReadJSON.forEach(System.out::println);
        System.out.println("Результат считывания из файла XML");
        listReadXML.forEach(System.out::println);
    }
}

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
    public static List<User> getUserList() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("Ирина", "Несговорова", 41, "Бухгалтер"));
        userList.add(new User("Игорь", "Андреев", 45, "Веб-разработчик"));
        userList.add(new User("Вячеслав", "Тягунов", 45, "Директор"));
        userList.add(new User("Елена", "Коробейникова", 42, "Актриса"));
        userList.add(new User("Анна", "Большова", 40, "Актриса"));
        return userList;
    }

}

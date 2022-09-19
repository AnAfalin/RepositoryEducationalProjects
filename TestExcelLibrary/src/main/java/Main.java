import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<User> userList = ListUtils.getUserList();
        ExcelUtils.writeExcelFile(userList, new File("UsersList.xls"));




    }
}

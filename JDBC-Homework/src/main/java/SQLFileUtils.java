
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SQLFileUtils {

    public static String readSqlQuery(String file)  {
        try {
            List<String> list = Files.readAllLines(Path.of(file));
            return String.join("", list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

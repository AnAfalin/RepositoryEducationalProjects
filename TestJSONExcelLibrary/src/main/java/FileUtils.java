import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class FileUtils {
    public static void writeToJSON(List<User> list, String file) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File(file), list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeToXML(List<User> list, String file) {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.writeValue(new File(file), list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static List<User> readJSON(String file)  {
        ObjectMapper mapper = new ObjectMapper();
        List<User> resultList = new LinkedList<>();
        Field[] declaredFields = User.class.getDeclaredFields();
        List list = null;
        try {
            list = mapper.readValue(new File(file), List.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Object elementList : list) {
            LinkedHashMap element = (LinkedHashMap) elementList;
            User user = new User();
            for (Field field :declaredFields){
                field.setAccessible(true);
                try {
                    field.set(user, element.get(field.getName()));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            resultList.add(user);
        }
        return resultList;
    }

    public static List<User> readXML(String file)  {
        XmlMapper xmlMapper = new XmlMapper();
        List<User> resultList = new LinkedList<>();
        Field[] declaredFields = User.class.getDeclaredFields();
        List list = null;
        try {
            list = xmlMapper.readValue(new File(file), List.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Object elementList : list) {
            LinkedHashMap element = (LinkedHashMap) elementList;
            User user = new User();
            for (Field field :declaredFields){
                field.setAccessible(true);
                try {
                    Class<?> classField = field.getType();
                    String valueField = element.get(field.getName()).toString();
                    if(classField == int.class || classField == Integer.class){
                        field.set(user, Integer.parseInt(valueField));
                    }else {
                        field.set(user, valueField);
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            resultList.add(user);
        }
        return resultList;
    }
}

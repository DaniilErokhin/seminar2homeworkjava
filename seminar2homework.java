//1) Дана строка sql-запроса "select * from students WHERE ". Сформируйте часть WHERE этого запроса, используя StringBuilder. Данные для фильтрации приведены ниже в виде json-строки.
//Если значение null, то параметр не должен попадать в запрос.
//String input_str = "{"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}"
//Ввод данных: {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}
//вывод: select * from students WHERE name=Ivanov AND country=Russia AND city=Moscow
import java.util.HashMap;
import java.util.Map;
public class seminar2homework {
    public static void main(String[] args) {
        Map<String, String> params1 = new HashMap<String,String>();
        params1.put("name","Ivanov");
        params1.put("country","Russia");
        params1.put("city","Moscow");
        params1.put("age",null);
        System.out.println(getQuery(params1));
    }
    public static String getQuery(Map<String, String> params)
    {
        StringBuilder s = new StringBuilder();
        for (Map.Entry<String,String> pair : params.entrySet())
        {
            if (pair.getValue() != null)
            {
                s.append(pair.getKey() +" = '" + pair.getValue()+"' and ");
            }
        }
        s.delete(s.toString().length()-5,s.toString().length());
        return s.toString();
    }

}

//2) Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите в лог-файл.
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;

public class seminar2homework {
    public static void bubbleSort(int arr[], String pathFile) {
        int n = arr.length;
        var logger = WriteLog(pathFile);
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    logger.log(Level.INFO, String.format("%s больше %s. Элементы поменяны местами.",
                            String.valueOf(arr[j + 1]), String.valueOf(arr[j])));
                }
    }

    public static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static Logger WriteLog(String pathFile) {
        try {

            Logger logger = Logger.getLogger(task1.class.getName());
            FileHandler fh = new FileHandler(pathFile, true);
            fh.setEncoding("UTF-8");
            logger.addHandler(fh);
            SimpleFormatter sf = new SimpleFormatter();
            fh.setFormatter(sf);
            return logger;
        } catch (Exception e) {
            System.out.println("Что-то пошло не так");
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
                int arr[] = { 64, 34, 25, 90, 12, 22, 11 };
        String pathProject = System.getProperty("user.dir");
        System.out.println(pathProject);
        String pathFile = pathProject.concat("seminar2homework.log");
        printArray(arr);
        bubbleSort(arr, pathFile);
        printArray(arr);
    }
}

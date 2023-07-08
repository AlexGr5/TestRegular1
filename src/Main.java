import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {


        /*
        // Ввод пути и открытие файла
        try
        {
            System.out.println("Input full file path:");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));

            String inputPath = reader.readLine();

            List<String> listRows = ReadRowsFromTXT.GetRowsFromFile(inputPath);

            for(String str: listRows)
            {
                System.out.println(str);
            }

        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
         */


        /*
        // Ввод паттерна и его анализ
        try
        {
            System.out.print("Input Patterns: ");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));

            String inputPattern = reader.readLine();

            LogsPattern logsPattern = new LogsPattern();
            List<OnePattern> listMyPatterns = new ArrayList<OnePattern>();

            listMyPatterns = logsPattern.SplitStringForPatterns(inputPattern);

            for (OnePattern onePattern: listMyPatterns)
            {
                System.out.println(onePattern.GetNamePattern());
                System.out.println(onePattern.GetListRegular());
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

         */



        /*
        // Тест регулярных выражений
        String input = "2006-10-20 14:06:49,812 skdfsdfhlsdfh flsfhshfusf";
        Pattern pattern = Pattern.compile("[0-9]{4}[-]{1}[0-9]{2}[-]{1}[0-9]{2} [0-9]{2}[:]{1}[0-9]{2}[:]{1}[0-9]{2}[,]{1}[0-9]{0,8}");
        Matcher matcher = pattern.matcher(input);
        while(matcher.find()) {
            System.out.println(matcher.group());
            System.out.println(matcher.end());
        }
         */

        /*
        // Тест работы класса SplitLogs
        String str = "2023-06-20 12:29:50,221 INFO  [stdout] (containersThreadPoolTaskScheduler-1) \\tacks = 1";
        String pattern = "%data%level%logger%thread%message";
        List<String> listLogs = new ArrayList<String>();
        listLogs.add(str);

        List<List<String>> result = SplitLogs.SplitLogsForPattern(listLogs, pattern);
        System.out.println(result);
         */





        // Пример ввода данных:
        // D:\altstu\3kurs\LetPract\test1.txt   или   D:\altstu\3kurs\LetPract\log-example.txt
        // %data%level%logger%thread%message
        // Enter или %level

        // ПРОГРАММА
        try
        {
            // Вводим путь к файлу
            System.out.print("Input full file path: ");

            // Создаем буффер для ввода строк
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));

            // Читаем путь файла из буффера
            String inputPath = reader.readLine();

            // Открываем файл и читаем из него все строки
            List<String> listRows = ReadRowsFromTXT.GetRowsFromFile(inputPath);

            // Вводим паттерн
            System.out.print("Input Patterns: ");
            String inputPattern = reader.readLine();

            // Распознаем логи из файла по паттерну
            List<List<String>> result = SplitLogs.SplitLogsForPattern(listRows, inputPattern);


            System.out.print("Input ONE SORTED Pattern or Enter: ");
            // Для ввода паттерна сортировки
            String sortedPattern = reader.readLine();

            if (sortedPattern.length() > 0)
            {
                result = SplitLogs.SortRecognLogsForOnePattern(result, inputPattern, sortedPattern);
                DisplayLogs.DisplayColorLogsForPattern(result, inputPattern, sortedPattern);
            }
            else
            {
                // Выводим на экран
                //System.out.println(result);
                DisplayLogs.DisplayLogsForPattern(result, inputPattern);
            }

        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

    }
}
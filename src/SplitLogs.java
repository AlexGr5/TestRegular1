import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SplitLogs
{

    // Разбить логи по паттерну (Основной метод)
    static public List<List<String>> SplitLogsForPattern(List<String> logs, String inputPattern)
    {
        //Выходной список строк, состояхих из кусков(списка) строк
        List<List<String>> result = new ArrayList<>();

        // Список текущего паттерна
        List<OnePattern> listMyPatterns;

        // Распознаем входной паттерн
        listMyPatterns = RecognizePatternFromInputString(inputPattern);

        // Основной перебор всех строк
        MainEnumerationOfLogs(logs, result, listMyPatterns);

        return result;
    }

    // Вынесенный основной перебор строк и поиск частей логов
    private static void MainEnumerationOfLogs(List<String> logs, List<List<String>> result, List<OnePattern> listMyPatterns) {
        // Цикл по строкам логов
        for (String currentLog: logs)
        {
            // Текущая строка, разбитая на части, которая будет добавляться в выходной список
            List<String> currentListRow = new ArrayList<String>();

            // Копия строки лога
            String copyCurRow = currentLog;

            //
            int currentStart = 0;

            //-----------------------------
            //System.out.println("\n\n\n");
            //System.out.println(currentLog);
            //-----------------------------

            // Цикл по введенным паттернам
            for(OnePattern currentPattern: listMyPatterns)
            {
                // Получаем список всех регулярных выражений для текущего паттерна
                List<String> currentRegulars = currentPattern.GetListRegular();


                //-----------------------------
                //System.out.println(currentPattern.GetNamePattern());
                //-----------------------------
                // Цикл по всем регулярным выражения текущего паттерна
                for (String currentRegular: currentRegulars)
                {

                    Pattern pattern = Pattern.compile(currentRegular);
                    Matcher matcher = pattern.matcher(copyCurRow);


                    //-----------------------------
                    //System.out.println(currentRegular);
                    //-----------------------------

                    // Если нашли совпадение с шаблоном
                    if(matcher.find()) {
                        // Добавляем результат поиска в выходной список
                        currentListRow.add(matcher.group());

                        // Присваиваем конец найденной подстроки переменной
                        currentStart = matcher.end();

                        //-----------------------------
                        //System.out.println("V");
                        //-----------------------------

                        // Обрезаем строку, до последнего найденного символа
                        copyCurRow = copyCurRow.substring(currentStart);
                        break;
                    }
                }
            }
            // Добавляем разбитую строку лога в выходной список
            result.add(currentListRow);
        }
    }

    // Распознать список текущего паттерна из входной строки
    static public List<OnePattern> RecognizePatternFromInputString(String inputPattern)
    {
        // Создаем объект заранее заготовленных шаблонов паттерна
        LogsPattern logsPattern = new LogsPattern();

        // Список текущего паттерна
        List<OnePattern> listMyPatterns = new ArrayList<OnePattern>();

        // Определяем введенный паттерн
        listMyPatterns = logsPattern.SplitStringForPatterns(inputPattern);

        return listMyPatterns;
    }
}

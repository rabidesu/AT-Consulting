import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Nem on 25.06.2015.
 */
public class Test {


    public static void main(String[] args) {

        for (String param : args){

            if (!param.startsWith("-T")) continue;
            String param_name = param.substring(param.indexOf("T")+1, param.indexOf("="));
            switch (param_name){
                case "week": printWeek(param); break;
                case "fact": printFact(param); break;
                default: printAnother();
            }
        }

    }

    private static void printWeek(String param){

        // Проверка на длину значения
        String param_value = (param.substring(param.indexOf("=") + 1));
        if (param_value.length() != 8) {
            System.out.println("error: " + param+" - Invalid data");
            return;
        }

        // Проверка на соответствие числу
        try {
            Integer.parseInt(param_value);
        } catch (NumberFormatException e) {
            System.out.println("error: " + param+" - Invalid data");
            return;
        }

        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
        format.setLenient(false);
        Date date;
        try {
            date = format.parse(param_value);
        } catch (ParseException e){
            System.out.println("error: " + param+" - Invalid date");
            return;
        }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int weekday = calendar.get(Calendar.DAY_OF_WEEK);

            // Определение первого дня недели
            calendar.add(Calendar.DAY_OF_WEEK, -(weekday - 2));
            System.out.print("Start week: " + calendar.getTime() + " | ");

            // Проверка на наличие пятницы 13-го
            calendar.add(Calendar.DATE, 4);
            if (calendar.get(Calendar.DAY_OF_MONTH) == 13){
                System.out.print("Hurray! | ");
            }

            // Определение последнего дня недели
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_WEEK, 8-weekday);
            System.out.println("End week: " + calendar.getTime());
    }

    private static void printFact(String param){

        String param_value = param.substring(param.indexOf("=")+1);
        long startTime = System.currentTimeMillis();
        BigInteger factorial;

        // Проверка на соотвествие числу
        try {
            factorial = factorial(Integer.parseInt(param_value));
        } catch (NumberFormatException e){
            System.out.println("error: " + param + " - Invalid number");
            return;
        }

            long timeSpent = System.currentTimeMillis() - startTime;
            System.out.println(factorial + " " + timeSpent + "ms");
    }

    private static BigInteger factorial(int num){
        BigInteger res;
        if (num == 0) return BigInteger.ONE;
        res = BigInteger.valueOf(num).multiply(factorial(num-1));
        return res;
    }

    private static void printAnother(){
        System.out.println("42!");
    }
}


package restaurant;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateSQL {

    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_PATTERN);
    private static final String DATE_PATTERN_TIME = "dd.MM.yyyy hh:mm:ss";
    private static final SimpleDateFormat DATE_FORMATTER_TIME = new SimpleDateFormat(DATE_PATTERN_TIME);

    /**
     * Возвращает полученную дату в виде хорошо отформатированной строки.
     * Используется определённый выше {@link DateSQL#DATE_PATTERN}.
     *
     * @param date - дата, которая будет возвращена в виде строки
     * @return отформатированную строку
     */
    public static String format(Date date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    public static String formatAndTime(Date date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER_TIME.format(date);
    }
    /**
     * Преобразует строку, которая отформатирована по правилам
     * шаблона {@link DateSQL#DATE_PATTERN} в объект {@link Date}.
     *
     * Возвращает null, если строка не может быть преобразована.
     *
     * @param dateString - дата в виде String
     * @return объект даты или null, если строка не может быть преобразована
     */
    public static java.sql.Date parse(String dateString) {
        try {
            return new Date(DATE_FORMATTER.parse(dateString).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
     /**
     * Проверяет, является ли строка корректной датой.
     *
     * @param dateString
     * @return true, если строка является корректной датой
     */
    public static boolean validDate(String dateString) {
        // Пытаемся разобрать строку.
        return DateSQL.parse(dateString) != null;
    }
}

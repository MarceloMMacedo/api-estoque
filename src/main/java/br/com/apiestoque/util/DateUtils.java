package br.com.apiestoque.util;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 *
 * @author Tomaz Lavieri
 */
public class DateUtils {
    /**
     * Formatação "dd/mm/yyyy".
     */
    public static DateFormat BRAZIL_FORMAT = new SimpleDateFormat("dd/mm/yyyy ");
    /**
     * Formatação "dd/mm/yy".
     */
    public static DateFormat BRAZIL_SHORT_FORMAT =
            new SimpleDateFormat("dd/mm/yy");
    /**
     * Não é possivel instanciar DateUtils.
     */
    public DateUtils(){}
    /**
     * Retorna o valor do horário minimo para a data de referencia passada.
     * <BR>
     * <BR> Por exemplo se a data for "30/01/2009 as 17h:33m:12s e 299ms" a data
     * retornada por este metodo será "30/01/2009 as 00h:00m:00s e 000ms".
     * @param date de referencia.
     * @return {@link Date} que representa o horário minimo para dia informado.
     */
    public static Date lowDateTime(Date date) {
        Calendar aux = Calendar.getInstance();
        aux.setTime(date);
        toOnlyDate(aux); //zera os parametros de hour,min,sec,milisec
        return aux.getTime();
    }
    /**
     * Retorna o valor do horário maximo para a data de referencia passada.
     * <BR>
     * <BR> Por exemplo se a data for &quot;30/01/2009 as 17h:33m:12s e 299ms&quot; a data
     * retornada por este metodo será &quot;30/01/2009 as 23h:59m:59s e 999ms&quot;.
     * @param date de referencia.
     * @return {@link Date} que representa o horário maximo para dia informado.
     */
    public static Date highDateTime(Date date) {
        Calendar aux = Calendar.getInstance();
        aux.setTime(date);
        toOnlyDate(aux); //zera os parametros de hour,min,sec,milisec
        aux.roll(Calendar.DATE, true); //vai para o dia seguinte
        aux.roll(Calendar.MILLISECOND, false); //reduz 1 milisegundo
        return aux.getTime();
    }
    /**
     * Zera todas as referencias de hora, minuto, segundo e milesegundo do
     * {@link Calendar}.
     * @param date a ser modificado.
     */
    public static void toOnlyDate(Calendar date) {
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
    }
    /**
     * Converte uma {@link String} no formato &quot;dd/mm/yyyy&quot; ou &quot;dd/mm/yy&quot; em um
     * {@link Date}.
     * @param date a {@link String} contendo a data.
     * @return o objeto {@link Date} para a string passada.
     * @throws  IllegalArgumentException caso a string não seja em dos dois
     *          formatos permitidos.
     */
    public static Date toDate(String date)  {
        if (date == null)
                return null;
         
            int gt;
			try {
				return   BRAZIL_SHORT_FORMAT.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
        
    }
    /**
     * Formata a data passada em uma {@link String} com o formato &quot;dd/mm/yyyy&quot;.
     * @param date a data a ser formatada.
     * @return {@link String} com a data formatada.
     */
    public static String toString(Date date) {
        if (date == null)
                return null;
        return BRAZIL_FORMAT.format(date);
    }
    /**
     * Formata a data passada em uma {@link String} com &lt;tt&gt;shortFormat&lt;/tt&gt;
     * "dd/mm/yy", ou com o formato longo "dd/mm/yyyy".
     * @param date a data a ser formatada.
     * @param shortFormat &lt;tt&gt;true&lt;/tt&gt; se o formato é o curto, ou
     *          &lt;tt&gt;false&lt;/tt&gt; caso o formato seja o longo.
     * @return {@link String} com a data formatada.
     */
    public static String toString(Date date, boolean shortFormat) {
        if (date == null)
                return null;
        return shortFormat ?
            BRAZIL_SHORT_FORMAT.format(date) : BRAZIL_FORMAT.format(date);
    }
}
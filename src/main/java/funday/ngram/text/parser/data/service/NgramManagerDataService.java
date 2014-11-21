package funday.ngram.text.parser.data.service;

/**
 * @author efazlic
 * @since 11/21/14 10:00 AM
 */
public interface NgramManagerDataService {
    public String[][] getNgram(String text, int n);
}

package funday.ngram.text.parser.data.service;

import funday.ngram.text.parser.data.model.Trigram;

import java.util.List;

/**
 * @author efazlic
 * @since 11/21/14 10:00 AM
 */
public interface NgramManagerDataService {
    public String[][] getNgram(String text, int n);

    void saveOrUpdate(String firstKey, String secondKey, String value);

    List<Trigram> findAll();

    void removeAll();
}

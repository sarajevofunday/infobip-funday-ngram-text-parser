package funday.ngram.text.parser.data.service.internal;

import funday.ngram.text.parser.data.service.NgramManagerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author efazlic
 * @since 11/21/14 9:54 AM
 */
public class NgramManagerDataServiceImpl implements NgramManagerDataService {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public NgramManagerDataServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    public String[][] getNgram(String text, int n) {
        String[] wordsList = text.replaceAll("\\n", " ").replaceAll("  ", " ").split(" "); //leave only one white space and remove new lines

        if(wordsList.length < n) {
            return null;
        }

        String [][] ngramContent = new String[wordsList.length][];
        for(int i = 0; i < wordsList.length - (n - 1); i++) {
            ngramContent[i] = new String[n];
            System.arraycopy(wordsList, i, ngramContent[i], 0, n);
        }

        return ngramContent;
    }
}

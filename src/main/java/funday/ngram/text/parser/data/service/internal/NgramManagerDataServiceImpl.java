package funday.ngram.text.parser.data.service.internal;

import funday.ngram.text.parser.data.model.Trigram;
import funday.ngram.text.parser.data.service.NgramManagerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

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

    @Override
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


    @Override
    public void saveOrUpdate(String firstKey, String secondKey, String value) {
        Trigram trigram = mongoTemplate.findOne(query(where("firstKey").is(firstKey).and("secondKey").is(secondKey)), Trigram.class);

        if(trigram == null) {
            trigram = new Trigram();
            trigram.setFirstKey(firstKey);
            trigram.setSecondKey(secondKey);
            trigram.setValues(Arrays.asList(value));
        }
        else {
            trigram.getValues().add(value);
            trigram.setValues(trigram.getValues());
        }
        mongoTemplate.save(trigram);
    }

    @Override
    public List<Trigram> findAll() {
        return mongoTemplate.findAll(Trigram.class);
    }

    @Override
    public void removeAll() {
        mongoTemplate.dropCollection(Trigram.class);
    }
}

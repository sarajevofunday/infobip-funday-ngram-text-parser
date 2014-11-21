package funday.ngram.text.parser;

import funday.ngram.text.parser.configuration.ContextConfiguration;
import funday.ngram.text.parser.data.service.FilesUtil;
import funday.ngram.text.parser.data.service.NgramManagerDataService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

/**
 * @author efazlic
 * @since 11/21/14 12:24 PM
 */
public class App {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);
        NgramManagerDataService ngramManagerDataService = context.getBean(NgramManagerDataService.class);
        FilesUtil filesUtil = context.getBean(FilesUtil.class);

        ngramManagerDataService.removeAll();

        int n = 3;
        int k = 0;

        String folderName = System.getProperty("user.home") + "/funday";
        for(String fileName : filesUtil.getAllFilesInFolder(folderName)) {
            String[][] ngram = ngramManagerDataService.getNgram(filesUtil.getTextFromFile(fileName), n);

            for(int i = 0; i < ngram.length - (n - 1); i++) {
                /*for(int j = 0; j < n; j++) {
                    System.out.print("'" + ngram[i][j] + "'");
                }
                System.out.println("");*/

                ngramManagerDataService.saveOrUpdate(ngram[i][0], ngram[i][1], ngram[i][2]);
                if(k % 1000 == 0)
                    System.out.println(k + " " + new Date(System.currentTimeMillis()));
                k++;
            }
        }

        System.out.println(ngramManagerDataService.findAll().size());
    }
}

package funday.ngram.text.parser;

import funday.ngram.text.parser.configuration.ContextConfiguration;
import funday.ngram.text.parser.data.service.NgramManagerDataService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @author efazlic
 * @since 11/21/14 12:24 PM
 */
public class App {

    public static void main(String[] args) throws Exception {

        try {
            String inputFileName = System.getProperty("user.home") + "/funday/output/secondBookColumbus/GREAT EXPECTATIONS1.txt"; //todo dynamic
            BufferedReader br = new BufferedReader(new FileReader(inputFileName));

            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }


            int n = 100;
            ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);
            NgramManagerDataService ngramManagerDataService = context.getBean(NgramManagerDataService.class);

            String[][] ngram = ngramManagerDataService.getNgram(sb.toString(), n); //todo implementacija i imena varijabli
            for(int i = 0; i < ngram.length - (n - 1); i++) {
                for(int j = 0; j < n; j++) {
                    System.out.print("'" + ngram[i][j] + "'");
                }
                System.out.println("");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

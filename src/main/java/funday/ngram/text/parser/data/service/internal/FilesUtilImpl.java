package funday.ngram.text.parser.data.service.internal;

import funday.ngram.text.parser.data.service.FilesUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author efazlic
 * @since 11/21/14 1:03 PM
 */
public class FilesUtilImpl implements FilesUtil {

    @Override
    public String getTextFromFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    @Override
    public List<String> getAllFilesInFolder(String folderName) {
        List<String> folders = new ArrayList<>();
        try {
            Files.walk(Paths.get(folderName)).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    folders.add(filePath.toString());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return folders;
    }
}

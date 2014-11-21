package funday.ngram.text.parser.data.service;

import java.util.List;

/**
 * @author efazlic
 * @since 11/21/14 1:03 PM
 */
public interface FilesUtil {

    public String getTextFromFile(String fileName);

    public List<String> getAllFilesInFolder(String folderName);
}

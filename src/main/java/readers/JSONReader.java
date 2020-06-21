package readers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSONReader {
    public static Object[][] JsonReader() {
        String filePath = "src/main/resources/testdata/testdata.json";

        File srcFile = new File(filePath);

        JSONParser parser = new JSONParser();
        JSONArray jarray = null;
        try {
            jarray = (JSONArray) parser.parse(new FileReader(srcFile));
        } catch (org.json.simple.parser.ParseException e) {
            exceptions.ExceptionsMessages.JSONParseMsg(e);
        } catch (FileNotFoundException e) {
            exceptions.ExceptionsMessages.FileNotFoundMsg(e);
        } catch (IOException e) {
            exceptions.ExceptionsMessages.IOMsg(e);
        }
        String[][] arrayJSONData = new String[jarray.size()][3];
        Integer i = 0;
        for (Object jsonObj : jarray) {
            JSONObject searchData = (JSONObject) jsonObj;
            arrayJSONData[i][0] = (String) searchData.get("DataToSearch");
            arrayJSONData[i][1] = (String) searchData.get("TheFirstPageNumber");
            arrayJSONData[i][2] = (String) searchData.get("TheSecondPageNumber");
            i++;
        }
        return arrayJSONData;
    }
}

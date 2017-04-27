package utils.datamaker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Karol Pokomeda on 2017-04-23.
 */
public class TestDataGenerator {
    public static boolean generateData(DataType dataType, int dataQuantity, boolean isAscending){
        boolean result = true;
        String filePath =
                dataType.getName() + "_" +
                dataQuantity + "_";
        if (isAscending) filePath += "ASCENDING";
        else filePath += "DESCENDING";
        filePath += ".csv";
        File myFile = new File(filePath);
        int[] dataToSave = dataType.generate(dataQuantity, isAscending);
        try (
                FileWriter fileOut = new FileWriter(myFile);
                PrintWriter dataOut = new PrintWriter(fileOut);
        ){
            myFile.createNewFile();

            for (int i = 0; i < dataToSave.length; i++) dataOut.print(dataToSave[i] + ",");

            if (fileOut != null) fileOut.close();
            if (dataOut != null) dataOut.close();
        } catch (IOException e){
            result = false;
        }
        return result;
    }
}
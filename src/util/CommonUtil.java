package util;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class CommonUtil {
	public static final String JSON_WITH_UTF8 = "application/json; charset=UTF-8";
	public static final Gson GSON = new Gson();
	private static final String DATA_FILE_PATH = "D:/test/data.csv";

	public static boolean writeFile(String line) {
		try (
			FileWriter fw = new FileWriter(DATA_FILE_PATH, true);
			PrintWriter pw = new PrintWriter(fw)
		) {
			pw.println(line);
			pw.flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static List<String> readFile() {
		List<String> resultList = new ArrayList<>();
		try (BufferedReader br = Files.newBufferedReader(Paths.get(DATA_FILE_PATH))) {
			String line;
			while ((line = br.readLine()) != null) {
				resultList.add(line);
			}
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

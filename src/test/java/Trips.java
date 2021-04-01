import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Trips {

    public static void main(String[] args) {
        final String filePath = Trips.class.getClassLoader().getResource("trip.txt").getPath();
        List<String> starts = new ArrayList<>();
        Set<String> ends = new HashSet<>();
        HashMap<String, String> tripMap= new HashMap<String, String>();
        List<String> results = new ArrayList<>();

        String startOfOurJorney = "";
        try {
            List<String> allLines = Files.readAllLines(Paths.get(filePath));
            for (String line : allLines) {
                String[] str = line.split(" -> ");
                starts.add(str[0]);
                ends.add(str[1]);
                tripMap.put(str[0], str[1]);
            }
            for (String start: starts) {
                if (!ends.contains(start)) {
                    startOfOurJorney = start;
                    break;
                }
            }

            for(int i=0; i<allLines.size(); i++) {
                String end = tripMap.get(startOfOurJorney);
                String tmp = startOfOurJorney + " -> " + end;
                results.add(tmp);
                startOfOurJorney = end;
            }


            FileWriter writer = new FileWriter("output.txt");
            for(String str: results) {
                System.out.println(str);
                writer.write(str + System.lineSeparator());
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }





}

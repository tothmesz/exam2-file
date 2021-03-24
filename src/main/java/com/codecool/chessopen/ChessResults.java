package com.codecool.chessopen;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class ChessResults {

    public List<String> getCompetitorsNamesFromFile(String fileName) {
        List<String> result = new ArrayList<>();
        Map competitors = new HashMap();
        File file = new File(fileName);
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine = null;
            while ((currentLine = reader.readLine()) != null) {
                List<String> myList = new ArrayList<String>(Arrays.asList(currentLine.split(",")));
                int countPoints = 0;
                for (int i = 1; i < myList.size(); i++) {
                    countPoints += Integer.parseInt(myList.get(i));
                }
                competitors.put(myList.get(0), countPoints);
            }
            Stream set = competitors.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()));
            Iterator itr = set.iterator();
            while(itr.hasNext()) {
                Map.Entry entry = (Map.Entry) itr.next();
                String entryToAdd = (String) entry.getKey();
                result.add(entryToAdd);
            }


        } catch (IOException e) {
            System.out.println("File not found!");
        }
        return result;
    }

}

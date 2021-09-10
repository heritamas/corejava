package ch09;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MapOps {

    public static void main(String[] args) throws URISyntaxException, IOException {
        TextReader tr = new TextReader("Hamlet.txt");
        Map<String, List<String>> acts = new LinkedHashMap<>();
        Pattern role = Pattern.compile("\\*(.*)\\*");

        String currentRole = "UNKNOWN";
        String currentText = "";
        for (String text : tr.getContent()) {
            Matcher matcher = role.matcher(text);
            if (matcher.matches()){
                acts.putIfAbsent(currentRole, new ArrayList<>());
                acts.merge(currentRole, List.of(currentText), (oldList, newElement) -> 
                {
                    oldList.addAll(newElement);
                    return oldList;
                });
                currentRole = matcher.group(1);
                currentText = "";
            }
            else {
                currentText += text;
            }
        }

        // How many times did Hamlet tell something?
        System.out.format("%d%n", acts.get("HAMLET").size());

        // And Fortinbras?
        System.out.format("%d%n", acts.get("PRINCE FORTINBRAS").size());

        // Which thought is the "to be or not to be"
        System.out.format("%d%n", acts.get("HAMLET").indexOf(new Object() {

            @Override
            public boolean equals(Object obj) {
                return ((String) obj).matches(".*To be, or not to be.*");
                //return ((String) obj).matches(".*");
            }
        }));


    }
}

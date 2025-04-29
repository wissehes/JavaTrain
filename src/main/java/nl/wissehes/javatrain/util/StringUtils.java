package nl.wissehes.javatrain.util;

import java.util.List;

public class StringUtils {

    public static String naturallyJoinStrings(List<String> list) {
        if(list.size() <= 2) {
            return String.join(" en ", list);
        }

        int lastIndex = list.size() - 1;

        return String.join(", ", list.subList(0, lastIndex)) +
                " en " +
                list.get(lastIndex);
    }

}

package nl.wissehes.javatrain.util;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class StringUtilities {

    public static String naturallyJoinStrings(List<String> list) {
        if(list.size() <= 2) {
            return String.join(" en ", list);
        }

        int lastIndex = list.size() - 1;

        return String.join(", ", list.subList(0, lastIndex)) +
                " en " +
                list.get(lastIndex);
    }

    /**
     * Map the materieel nummer to a proper string, for example:
     * <p>
     * - 000000-02652-0 -> 26520
     * - 000000-16476-0 -> 164760
     * @param materieelNummer
     * @return
     */
    public static String formatMaterialNumber(String materieelNummer) {
        if (materieelNummer == null || materieelNummer.isEmpty()) {
            return null;
        }

        var strippedZeros = StringUtils.strip(materieelNummer, "0");
        var strippedDashes = StringUtils.strip(strippedZeros, "-");

        return StringUtils.stripStart(strippedDashes, "0");
    }

}

package ns.tcphack;

import java.util.ArrayList;
import java.util.List;

/**
 * BaseHeader for base functions
 * @author Mark Banierink
 */

public abstract class Header {

    protected List<Integer> stringToHexList(String hexString) {
        List<Integer> arrayList = new ArrayList<>();
        for (int j = 0; j < hexString.length() / 2; j++) {
            int start = j * 2;
            String substring = hexString.substring(start, start + 2);
            arrayList.add(j, Integer.parseInt(substring, 16));
        }
        return arrayList;
    }

    protected int getHeaderValue(HeaderSetting headerSetting, List<Integer> packetList) {
        return Integer.parseInt(getPacketValueString(headerSetting, packetList), 16);
    }

    protected String getPacketValueString(HeaderSetting headerSetting, List<Integer> packetList) {
        int start = headerSetting.getStartingBit() / 4;
        int end = start + headerSetting.getBits() / 4;
        return listToString(packetList).substring(start, end);
    }

    protected String listToString(List<Integer> packetList) {
        StringBuilder string = new StringBuilder();
        for (int byteValue : packetList) {
            string.append(Integer.toHexString(byteValue));
        }
        return string.toString();
    }

    protected String getSettingString(HeaderSetting headerSetting, int value) {
        int paddingSize = headerSetting.getBits() / 4 - Integer.toHexString(value).length();
        return String.format("%0" + paddingSize + "d", 0) + Integer.toHexString(value);
    }
}

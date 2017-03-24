package ns.tcphack;

import java.util.List;

import static ns.tcphack.IPv6HeaderSetting.*;

/**
 * IPv6 header of a packet
 * @author Mark Banierink
 */
public class IPv6Header extends Header {

    private int version;
    private int trafficClass;
    private int flowLabel;
    private int payloadLength;
    private int nextHeader;
    private int hopLimit;
    private String sourceAddress;
    private String destinationAddress;

    public IPv6Header() {
        version = 0x6;
        trafficClass = 0x00;
        flowLabel = 0x00000;
        payloadLength = 0x0014;
        nextHeader = 0xfd;
        hopLimit = 0xff;
        sourceAddress = "2001067c2564a130d577e7f770d51a9d";
        destinationAddress = "2001067c2564a170020423fffede4b2c";
    }

    public IPv6Header(List<Integer> packetList) {
        version = getHeaderValue(VERSION, packetList);
        trafficClass = getHeaderValue(TRAFFIC_CLASS, packetList);
        flowLabel = getHeaderValue(FLOW_LABEL, packetList);
        payloadLength = getHeaderValue(PAYLOAD_LENGTH, packetList);
        nextHeader = getHeaderValue(NEXT_HEADER, packetList);
        hopLimit = getHeaderValue(HOP_LIMIT, packetList);
        sourceAddress = getPacketValueString(SOURCE_ADDRESS, packetList);
        destinationAddress = getPacketValueString(DESTINATION_ADDRESS, packetList);
    }

    private int getHeaderValue(HeaderSetting headerSetting, List<Integer> packetList) {
        return Integer.parseInt(getPacketValueString(headerSetting, packetList), 16);
    }

    private String getIPv6HeaderString() {
        return getSettingString(VERSION, version) + getSettingString(TRAFFIC_CLASS, trafficClass) + getSettingString(FLOW_LABEL, flowLabel)
                + getSettingString(PAYLOAD_LENGTH, payloadLength) + getSettingString(NEXT_HEADER, nextHeader) + getSettingString(HOP_LIMIT, hopLimit) + sourceAddress
                + destinationAddress;
    }

    public List<Integer> getIPv6HeaderList() {
        return stringToHexList(getIPv6HeaderString());
    }

    public void setPayloadLength(int payloadLength) {
        this.payloadLength = payloadLength;
    }

    public int getPayloadLength() {
        return payloadLength;
    }
}

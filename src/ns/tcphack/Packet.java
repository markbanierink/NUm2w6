package ns.tcphack;

import java.util.ArrayList;
import java.util.List;

/**
 * Packet containing an IPv6 header and TCP header
 * @author Mark Banierink
 */
public class Packet {

    private static final int IPv6HEADER_SIZE = 20;

    private IPv6Header ipV6Header;
    private TCPHeader tcpHeader;

    public Packet() {
        ipV6Header = new IPv6Header();
        tcpHeader = new TCPHeader();
    }

    public Packet(int[] packetArray) {
        List<Integer> packetList = arrayToList(packetArray);
        ipV6Header = new IPv6Header(packetList.subList(0, IPv6HEADER_SIZE));
        tcpHeader = new TCPHeader(packetList.subList(IPv6HEADER_SIZE + 1, packetList.size()));
    }

    public IPv6Header getIPv6Header() {
        return ipV6Header;
    }

    public TCPHeader getTCPHeader() {
        return tcpHeader;
    }

    private List<Integer> getPacketList() {
        List<Integer> packetList = new ArrayList<>();
        packetList.addAll(getIPv6HeaderList());
        packetList.addAll(getTCPHeaderList());
        return packetList;
    }

    public int[] getPacketArray() {
        return listToArray(getPacketList());
    }

    private List<Integer> arrayToList(int[] array) {
        List<Integer> list = new ArrayList<>();
        for (int element : array) {
            list.add(element);
        }
        return list;
    }

    private int[] listToArray(List<Integer> list) {
        int[] array = new int[list.size()];
        int i = 0;
        for (Integer number : list) {
            array[i] = number;
            i++;
        }
        return array;
    }

    private List<Integer> getIPv6HeaderList() {
        return ipV6Header.getIPv6HeaderList();
    }

    private List<Integer> getTCPHeaderList() {
        return tcpHeader.getTCPHeaderList();
    }
}

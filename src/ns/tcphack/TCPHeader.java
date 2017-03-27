package ns.tcphack;

import java.util.List;

import static ns.tcphack.TCPHeaderSetting.*;

/**
 * TCP header of a packet
 * @author Mark Banierink
 */
public class TCPHeader extends Header {

    private static final int FIN = Integer.parseInt("00000001", 2);
    private static final int SYN = Integer.parseInt("00000010", 2);
    private static final int ACK = Integer.parseInt("00010000", 2);

    private int sourcePort;
    private int destinationPort;
    private int sequenceNumber;
    private int ackNumber;
    private int dataOffset;
    private int reserved;
    private int controlFlags;
    private int windowSize;
    private int checksum;
    private int urgentPointer;

    public TCPHeader() {
        sourcePort = 0x281e;
        destinationPort = 0x1e1e;
        sequenceNumber = 0x00000001;
        ackNumber = 0x00000000;
        dataOffset = 0x5;
        reserved = 0x00;
        controlFlags = 0x2;
        windowSize = 0x0800;
        checksum = 0x0000;
        urgentPointer = 0x0000;
    }

    public TCPHeader(List<Integer> packetList) {
        sourcePort = getHeaderValue(SOURCE_PORT, packetList);
        destinationPort = getHeaderValue(DESTINATION_PORT, packetList);
        sequenceNumber = getHeaderValue(SEQUENCE_NUMBER, packetList);
        ackNumber = getHeaderValue(ACKNOWLEDGMENT_NUMBER, packetList);
        dataOffset = getHeaderValue(DATA_OFFSET, packetList);
        reserved = getHeaderValue(RESERVED, packetList);
        controlFlags = getHeaderValue(CONTROL_FLAGS, packetList);
        windowSize = getHeaderValue(WINDOW_SIZE, packetList);
        checksum = getHeaderValue(CHECKSUM, packetList);
        urgentPointer = getHeaderValue(URGENT_POINTER, packetList);
    }

    public String getTCPHeaderString() {
        return getSettingString(SOURCE_PORT, sourcePort) + getSettingString(DESTINATION_PORT, destinationPort) + getSettingString(SEQUENCE_NUMBER, sequenceNumber)
                + getSettingString(ACKNOWLEDGMENT_NUMBER, ackNumber) + getSettingString(DATA_OFFSET, dataOffset) + getSettingString(RESERVED, reserved)
                + getSettingString(CONTROL_FLAGS, controlFlags) + getSettingString(WINDOW_SIZE, windowSize) + getSettingString(CHECKSUM, checksum)
                + getSettingString(URGENT_POINTER, urgentPointer);
    }

    public List<Integer> getTCPHeaderList() {
        return stringToHexList(getTCPHeaderString());
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public void setAckNumber(int ackNumber) {
        this.ackNumber = ackNumber;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public int getAckNumber() {
        return ackNumber;
    }

    public void setFin(boolean fin) {
        if (fin && !isFin()) {
            controlFlags = controlFlags + FIN;
        }
        else if (!fin && isFin()) {
            controlFlags = controlFlags - FIN;
        }
    }

    public void setSyn(boolean syn) {
        if (syn && !isSyn()) {
            controlFlags = controlFlags + SYN;
        }
        else if (!syn && isSyn()) {
            controlFlags = controlFlags - SYN;
        }
    }

    public void setAck(boolean ack) {
        if (ack && !isAck()) {
            controlFlags = controlFlags + ACK;
        }
        else if (!ack && isAck()) {
            controlFlags = controlFlags - ACK;
        }
    }

    public boolean isFin() {
        int isFin = controlFlags & FIN;
        return isFin > 0;
    }

    public boolean isSyn() {
        int isSyn = controlFlags & SYN;
        return isSyn > 0;
    }

    public boolean isAck() {
        int isAck = controlFlags & ACK;
        return isAck > 0;
    }
}

package ns.tcphack;

/**
 * TCP header enum
 * @author Mark Banierink
 */
public enum TCPHeaderSetting implements HeaderSetting {

    SOURCE_PORT(16),
    DESTINATION_PORT(16),
    SEQUENCE_NUMBER(32),
    ACKNOWLEDGMENT_NUMBER(32),
    DATA_OFFSET(4),
    RESERVED(4),
    CONTROL_FLAGS(8),
    WINDOW_SIZE(16),
    CHECKSUM(16),
    URGENT_POINTER(16);

    private final int bits;

    TCPHeaderSetting(int bits) {
        this.bits = bits;
    }

    public int getStartingBit() {
        int start = 0;
        for (TCPHeaderSetting headerSetting : TCPHeaderSetting.values()) {
            if (headerSetting == this) {
                break;
            }
            start = start + headerSetting.getBits();
        }
        return start;
    }

    public int getBits() {
        return bits;
    }
}

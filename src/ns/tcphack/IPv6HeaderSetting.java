package ns.tcphack;

/**
 * IPv6 header enum
 * @author Mark Banierink
 */
public enum IPv6HeaderSetting implements HeaderSetting {

    VERSION(4),
    TRAFFIC_CLASS(8),
    FLOW_LABEL(20),
    PAYLOAD_LENGTH(16),
    NEXT_HEADER(8),
    HOP_LIMIT(8),
    SOURCE_ADDRESS(128),
    DESTINATION_ADDRESS(128);

    private int bits;

    IPv6HeaderSetting(int bits) {
        this.bits = bits;
    }

    public int getStartingBit() {
        int start = 0;
        for (IPv6HeaderSetting headerSetting : IPv6HeaderSetting.values()) {
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

    public int getSize() {
        int size = 0;
        for (IPv6HeaderSetting headerSetting : IPv6HeaderSetting.values()) {
            size += headerSetting.getSize();
        }
        return size;
    }

}

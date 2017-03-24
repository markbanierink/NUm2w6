package ns.tcphack;

class MyTcpHandler extends TcpHandler {

    private boolean done = false;

    public static void main(String[] args) {
        new MyTcpHandler();
    }

    private MyTcpHandler() {
        super();

        // send first packet
        Packet firstPacket = new Packet();
        this.sendData(firstPacket.getPacketArray());

        while (!done) {
            // check for reception of a packet, but wait at most 500 ms:
            int[] rxpkt = this.receiveData(500);
            if (rxpkt.length == 0) {
                // nothing has been received yet
                System.out.println("Nothing...");
                continue;
            }

            // something has been received
            int len = rxpkt.length;

            // print the received bytes:
            System.out.print("Received " + len + " bytes: ");
            for (int aRxpkt : rxpkt) {
                System.out.print(aRxpkt + " ");
            }
            System.out.println("");

            // process incoming packet
            processIncomingPacketArray(rxpkt);
        }
    }

    private void processIncomingPacketArray(int[] rxpkt) {
        Packet packet = new Packet(rxpkt);
        Packet responsePacket = new Packet();
        if (packet.getTCPHeader().isAck() && packet.getTCPHeader().isSyn()) {
            responsePacket.getTCPHeader().setSequenceNumber(0);
        }
        else if (packet.getTCPHeader().isAck()) {
            int ackNumber = packet.getTCPHeader().getAckNumber();
            responsePacket.getTCPHeader().setSequenceNumber(ackNumber);
        }
        else if (packet.getTCPHeader().isFin()) {
            done = true;
        }
    }
}

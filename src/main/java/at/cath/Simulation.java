package at.cath;

public class Simulation {

    private final Kingdom simKingdom = new Kingdom("Imperial", null);

    public static void main(String[] args) {

        byte b = 0b00000000;

        System.out.println(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
        b = (byte) (b | (1 << 7));
        System.out.println(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));

    }
}
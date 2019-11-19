package at.cath;

public class Simulation {

    private final Kingdom simKingdom = new Kingdom("Imperial");

    public static void main(String[] args) {

        int coordinate = 0xFFFF0000;

        System.out.println("Binary: "+Integer.toBinaryString(coordinate));

        System.out.println("x: " + Integer.toBinaryString(IndexUtility.getCoordinatesFromIndex(coordinate)[0]));
        System.out.println("z: " + String.format("%32s", Integer.toBinaryString(IndexUtility.getCoordinatesFromIndex(coordinate)[1])).replace(' ', '0'));

        System.out.println();
        System.out.println("Restructure: ");

        int coordinate2 = IndexUtility.getIndexFromCoordinates(Short.MAX_VALUE, Short.MIN_VALUE);
        System.out.println(Integer.toBinaryString(coordinate2));

    }

}

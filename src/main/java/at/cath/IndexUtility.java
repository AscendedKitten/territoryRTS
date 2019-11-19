package at.cath;

public class IndexUtility {

    public static short[] getCoordinatesFromIndex(int index) {
        return new short[]{(short) (index >>> 16), (short) index};
    }

    public static int getIndexFromCoordinates(short x, short z) {
        return (x << 16) | (z & 0xFFFF);
    }

}

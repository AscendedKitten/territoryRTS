package at.cath;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class TerritoryCoordinate {

    @Getter
    private int x;
    @Getter
    private int z;

    @Override
    public int hashCode() {
        return x ^ (z << 16);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof TerritoryCoordinate)) {
            return false;
        } else {
            TerritoryCoordinate other = (TerritoryCoordinate) o;
            return this.getX() == other.getX() && this.getZ() == other.getZ();
        }
    }

    public int getXMin() {
        return x * 512;
    }

    public int getXMax() {
        return (x + 1) * 512;
    }

    public int getZMin() {
        return z * 512;
    }

    public int getZMax() {
        return (z + 1) * 512;
    }

    public int getYMin() {
        return 0;
    }

    public int getYMax() {
        return 255;
    }

    /*private static short[] getCoordinatesFromIndex(int index) {
        return new short[]{(short) (index >>> 16), (short) index};
    }

    private static int getIndexFromCoordinates(int startChunkX, int startChunkZ) {
        return (startChunkX << 16) | (startChunkZ & 0xFFFF);
    }*/

}
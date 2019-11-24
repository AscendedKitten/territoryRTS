package at.cath;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class TerritoryCoordinate {

    @Getter
    private int startChunkX;
    @Getter
    private int startChunkZ;

    @Override
    public int hashCode() {
        return startChunkX ^ (startChunkZ << 16);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof TerritoryCoordinate)) {
            return false;
        } else {
            TerritoryCoordinate other = (TerritoryCoordinate) o;
            return this.getStartChunkX() == other.getStartChunkX() && this.getStartChunkZ() == other.getStartChunkZ();
        }
    }

    public int getXMin() {
        return startChunkX * 512;
    }

    public int getXMax() {
        return (startChunkX + 1) * 512;
    }

    public int getZMin() {
        return startChunkZ * 512;
    }

    public int getZMax() {
        return (startChunkZ + 1) * 512;
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
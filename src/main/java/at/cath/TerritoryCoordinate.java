package at.cath;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class TerritoryCoordinate {

    @Getter
    private int chunkX;
    @Getter
    private int chunkZ;

    @Override
    public int hashCode() {
        return chunkX ^ (chunkZ << 16);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof TerritoryCoordinate)) {
            return false;
        } else {
            TerritoryCoordinate other = (TerritoryCoordinate) o;
            return this.getChunkX() == other.getChunkX() && this.getChunkZ() == other.getChunkZ();
        }
    }

    /*private static short[] getCoordinatesFromIndex(int index) {
        return new short[]{(short) (index >>> 16), (short) index};
    }

    private static int getIndexFromCoordinates(int chunkX, int chunkZ) {
        return (chunkX << 16) | (chunkZ & 0xFFFF);
    }*/

}
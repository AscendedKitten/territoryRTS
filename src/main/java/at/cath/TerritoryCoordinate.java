package at.cath;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TerritoryCoordinate {

    @Getter
    @EqualsAndHashCode.Include
    private int coordinate;

    public static short[] getCoordinatesFromIndex(int index) {
        return new short[]{(short) (index >>> 16), (short) index};
    }

    public static int getIndexFromCoordinates(short x, short z) {
        return (x << 16) | (z & 0xFFFF);
    }

}

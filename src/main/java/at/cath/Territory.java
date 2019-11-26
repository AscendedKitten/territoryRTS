package at.cath;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class Territory {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private byte allegiance;
    @NonNull
    private Kingdom kingdom;
    @NonNull
    private TerritoryCoordinate territoryCoordinate;

    public void updateAllegiance(int bit, boolean friendly, boolean mirrored) {
        if (!mirrored)
            allegiance = friendly ? (byte) (allegiance | (1 << bit)) : (byte) (allegiance & ~(1 << (bit)));
        else
            allegiance = friendly ? (byte) (allegiance | (1 << (bit + 4) % 8)) : (byte) (allegiance & ~(1 << ((bit + 4) % 8)));
    }

    public boolean getAllegianceBit(int bit) {
        return (allegiance & (1 << (bit + 4) % 8)) != 0;
    }


}
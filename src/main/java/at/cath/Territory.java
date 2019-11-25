package at.cath;

import at.cath.utility.TerritoryManager;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Territory {

    //@Setter(AccessLevel.NONE)
    private byte allegiance;
    @NonNull
    private Kingdom kingdom;
    @NonNull
    private TerritoryCoordinate territoryCoordinate;

    public int distanceTo(Territory territory) {
        return TerritoryManager.getInstance().distanceInTerritories(this, territory);
    }


}
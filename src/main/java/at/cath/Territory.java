package at.cath;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Territory {

    private Kingdom kingdom;
    private TerritoryCoordinate territoryCoordinate;

    public int distanceTo(Territory territory) {
        return TerritoryManager.getInstance().distanceInTerritories(this, territory);
    }
}
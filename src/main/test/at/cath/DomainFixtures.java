package at.cath;

import java.util.List;

public class DomainFixtures {

    private final static Kingdom DEFAULT_KINGDOM = new Kingdom("Imperial");

    public static List<Territory> getDirectionalTerritories() {

        TerritoryCoordinate coordOrigin = new TerritoryCoordinate(0, 0);
        TerritoryCoordinate coordNorth = new TerritoryCoordinate(0, -1);
        TerritoryCoordinate coordSouth = new TerritoryCoordinate(0, 1);
        TerritoryCoordinate coordEast = new TerritoryCoordinate(1, 0);
        TerritoryCoordinate coordWest = new TerritoryCoordinate(-1, 0);

        Territory terrOrigin = new Territory(DEFAULT_KINGDOM, coordOrigin);
        Territory terrNorth = new Territory(DEFAULT_KINGDOM, coordNorth);
        Territory terrSouth = new Territory(DEFAULT_KINGDOM, coordSouth);
        Territory terrEast = new Territory(DEFAULT_KINGDOM, coordEast);
        Territory terrWest = new Territory(DEFAULT_KINGDOM, coordWest);

        return List.of(terrOrigin, terrNorth, terrSouth, terrEast, terrWest);
    }

}

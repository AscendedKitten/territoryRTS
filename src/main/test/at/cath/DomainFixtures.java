package at.cath;

import java.util.List;

public class DomainFixtures {

    public static List<Territory> getDirectionalTerritories() {

        TerritoryCoordinate coordOrigin = new TerritoryCoordinate(0, 0);
        TerritoryCoordinate coordNorth = new TerritoryCoordinate(0, -32);
        TerritoryCoordinate coordNorth2 = new TerritoryCoordinate(0, -64);
        TerritoryCoordinate coordNorth3 = new TerritoryCoordinate(0, -96);
        TerritoryCoordinate coordSouth = new TerritoryCoordinate(0, 32);

        TerritoryCoordinate coordEast = new TerritoryCoordinate(32, 0);
        TerritoryCoordinate coordWest = new TerritoryCoordinate(-32, 0);

        Territory terrOrigin = new Territory(new Kingdom("origin"), coordOrigin);
        Territory terrNorth = new Territory(new Kingdom("north"), coordNorth);
        Territory terrSouth = new Territory(new Kingdom("south"), coordSouth);
        Territory terrEast = new Territory(new Kingdom("east"), coordEast);
        Territory terrWest = new Territory(new Kingdom("west"), coordWest);

        Territory terrNorth2 = new Territory(new Kingdom("further north"), coordNorth2);
        Territory terrNorth3 = new Territory(new Kingdom("very much north"), coordNorth3);

        return List.of(terrOrigin, terrNorth, terrSouth, terrEast, terrWest, terrNorth2, terrNorth3);
    }

}
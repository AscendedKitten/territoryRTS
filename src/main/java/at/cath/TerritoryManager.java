package at.cath;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public enum TerritoryManager {

    INSTANCE;

    @Getter
    private final Map<Integer, Territory> territories = new HashMap<>();

    public TerritoryManager getInstance() {
        return INSTANCE;
    }

}

package at.cath;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Kingdom {

    @Getter
    private String name;

    @Getter
    private Alliance alliance;


    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }

    public static Kingdom defaultKingdom() {
        return new Kingdom("Wilderness", null);
    }
}

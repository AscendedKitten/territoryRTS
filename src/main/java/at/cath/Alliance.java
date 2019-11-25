package at.cath;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Alliance {

    @Getter
    private static List<Kingdom> members = new ArrayList<>();

    public void addMember(Kingdom kingdom) {
        members.add(kingdom);
    }

    public void removeMember(Kingdom kingdom) {
        members.remove(kingdom);
    }

}

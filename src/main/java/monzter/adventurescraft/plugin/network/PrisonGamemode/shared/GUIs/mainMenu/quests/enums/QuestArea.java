package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.quests.enums;

public enum QuestArea {
    TOWN("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmUyY2M0MjAxNWU2Njc4ZjhmZDQ5Y2NjMDFmYmY3ODdmMWJhMmMzMmJjZjU1OWEwMTUzMzJmYzVkYjUwIn19fQ=="),
    ;

    private final String head;


    QuestArea(String head) {
        this.head = head;
    }

    public String getHead() {
        return head;
    }
}

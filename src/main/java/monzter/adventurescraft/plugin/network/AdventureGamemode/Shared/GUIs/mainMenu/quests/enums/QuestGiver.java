package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.quests.enums;

public enum QuestGiver {
    FLINT(QuestArea.TOWN,  "eyJ0aW1lc3RhbXAiOjE1NDY3MTk2MDU0OTUsInByb2ZpbGVJZCI6ImE5MGI4MmIwNzE4NTQ0ZjU5YmE1MTZkMGY2Nzk2NDkwIiwicHJvZmlsZU5hbWUiOiJJbUZhdFRCSCIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjA4Y2JiNWVjMzc3M2M4NjRjMjNiZmVmMWUzNDJmZTM2MTNkMjZmMTVhYTA0MjFlYTQ3YTg0NTczMDllNGEzMyJ9fX0="),
    WIZARD(QuestArea.TOWN,  "eyJ0aW1lc3RhbXAiOjE1NTQyMjkyMzIzMDksInByb2ZpbGVJZCI6IjkxZmUxOTY4N2M5MDQ2NTZhYTFmYzA1OTg2ZGQzZmU3IiwicHJvZmlsZU5hbWUiOiJoaGphYnJpcyIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzZiNzk1MzZkZjA4ZTI4ODQxMjI0OGIxNTNkZDVlNDMwYmE1YTc4ZTE0YWNkZTc5MDU1NDMzNjNkYzg4MGRhOCJ9fX0="),
    WARLOCK(QuestArea.TOWN,  "ewogICJ0aW1lc3RhbXAiIDogMTYwNDYyOTkwMjY4MSwKICAicHJvZmlsZUlkIiA6ICI5ZDEzZjcyMTcxM2E0N2U0OTAwZTMyZGVkNjBjNDY3MyIsCiAgInByb2ZpbGVOYW1lIiA6ICJUYWxvZGFvIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2UzNWM5ZWE0M2IxYTBhMDNlMmNiOWFkYTBjY2RiMTQ1MzBmMDc3MTE2Y2ZmZjhjNzYxZmIxYzY4YTk2OGU5ZjAiCiAgICB9CiAgfQp9"),
    GEM_SMITH(QuestArea.TOWN, "ewogICJ0aW1lc3RhbXAiIDogMTU5NTYyODg5OTUwMiwKICAicHJvZmlsZUlkIiA6ICJkODAwZDI4MDlmNTE0ZjkxODk4YTU4MWYzODE0Yzc5OSIsCiAgInByb2ZpbGVOYW1lIiA6ICJ0aGVCTFJ4eCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS85ZDQyODI4ZWVmMDU2YTM0MWRlMGNhNGNlYjg2ZTNmZWFiN2VlN2UzZmM1ODk1YmNjYTY3ZmE0ZjQ0NGI4ZGIyIgogICAgfQogIH0KfQ=="),

    CIRL(QuestArea.GRAVEYARD, "eyJ0aW1lc3RhbXAiOjE1ODQ4OTg1ODgzMDAsInByb2ZpbGVJZCI6Ijc3MjdkMzU2NjlmOTQxNTE4MDIzZDYyYzY4MTc1OTE4IiwicHJvZmlsZU5hbWUiOiJsaWJyYXJ5ZnJlYWsiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzYzODIyZDBiNGFlMjBhOGRjMzA3NWRiYTg5YjI2YjU3NDU1ZDlmYWYwYTEwNWNlMzRmYjViYmQzZGJiZmY3M2YifX19"),
    NAVID(QuestArea.COURTYARD, "eyJ0aW1lc3RhbXAiOjE1ODY0Nzc0MjQ4MTAsInByb2ZpbGVJZCI6IjRkNzA0ODZmNTA5MjRkMzM4NmJiZmM5YzEyYmFiNGFlIiwicHJvZmlsZU5hbWUiOiJzaXJGYWJpb3pzY2hlIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS85ZGQzMWM5ZjFmMWYxZGE5ZGE3ZjA4Y2JmMGE5MDNhMWJmMGE0NzhkNWM0ZmM0NGQ0MjNhMWZiZGJlNDkxZGU5In19fQ=="),
    KLAUS(QuestArea.CASTLE, "ewogICJ0aW1lc3RhbXAiIDogMTU4OTg2MDA0NTg5MSwKICAicHJvZmlsZUlkIiA6ICI0NDAzZGM1NDc1YmM0YjE1YTU0OGNmZGE2YjBlYjdkOSIsCiAgInByb2ZpbGVOYW1lIiA6ICJGbGF3Q3JhQm90MDEiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGQ4ZDI4ZTgxNmYyMDM0MjA5MjU4MTMzYWI2ZTBhZmUwZTU0YjQ3MTlmMzhmZjMyZWZkYTJhMzRmZDM2MWM1MSIKICAgIH0KICB9Cn0="),

    ANJU(QuestArea.FARM, "ewogICJ0aW1lc3RhbXAiIDogMTYxMjkzOTE1OTc4NiwKICAicHJvZmlsZUlkIiA6ICI0ZWQ4MjMzNzFhMmU0YmI3YTVlYWJmY2ZmZGE4NDk1NyIsCiAgInByb2ZpbGVOYW1lIiA6ICJGaXJlYnlyZDg4IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2M4NTY4NzZiYjI1Nzc5NjEzZTkxNzJlNjQxZTdiYmM1MDE3YzgxZjZlNWQ1YTg3NWE1YTgyZDYwYmUwNDk2IiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0="),
    INGO(QuestArea.FARM, "ewogICJ0aW1lc3RhbXAiIDogMTU4OTU4NzA0NDU0NiwKICAicHJvZmlsZUlkIiA6ICJkNjBmMzQ3MzZhMTI0N2EyOWI4MmNjNzE1YjAwNDhkYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJCSl9EYW5pZWwiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGI1NjZlMjViZDgzOTVmMWU4OTk3OWZkYTI4MzljYzgyOTk3ZDdmYjU2NzhmYzQ5ZTNjNDY5YWU5NWYxYjRiYiIKICAgIH0KICB9Cn0="),
    MANDY(QuestArea.FARM, "eyJ0aW1lc3RhbXAiOjE1MjU2NTc3MzkyODQsInByb2ZpbGVJZCI6IjQzYTgzNzNkNjQyOTQ1MTBhOWFhYjMwZjViM2NlYmIzIiwicHJvZmlsZU5hbWUiOiJTa3VsbENsaWVudFNraW42Iiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82ZGY1MzgzNzdkNmY3ZGY4MjkyMzhiZDVkMDEzNzY5ODExNjA2ZTE0NDVlODNjYzYyMTgyZjYyOTY0MTExYjU2IiwibWV0YWRhdGEiOnsibW9kZWwiOiJzbGltIn19fX0="),
    BILLY(QuestArea.FARM, "eyJ0aW1lc3RhbXAiOjE1NTk3NDI4NzgzNzcsInByb2ZpbGVJZCI6IjNmYzdmZGY5Mzk2MzRjNDE5MTE5OWJhM2Y3Y2MzZmVkIiwicHJvZmlsZU5hbWUiOiJZZWxlaGEiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2Y1ZTMzNDVmZDFlZGQ0NDc3NGRjMjg2NDY5ZGRmZWY3N2ZlZTQ2MmJlN2M4NTAzOWFiMjM2NmY3ZTExNDkyMTQifX19"),

    MAEL(QuestArea.VALLEY, "eyJ0aW1lc3RhbXAiOjE1MzQwNjgxMTIzNzMsInByb2ZpbGVJZCI6IjU2Njc1YjIyMzJmMDRlZTA4OTE3OWU5YzkyMDZjZmU4IiwicHJvZmlsZU5hbWUiOiJUaGVJbmRyYSIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2RlZmI3MWUzZjk2ZWNjMGIyNGQzNzM5MTg4NWIzYWFmZWIzOTdiYTY2NWM2NzBhMzhmNzNjYmUzN2E3ODg3NCJ9fX0="),

    BOWYER(QuestArea.THE_ESTATE, "eyJ0aW1lc3RhbXAiOjE1NzgwNjU0MDg0MzYsInByb2ZpbGVJZCI6ImIwZDczMmZlMDBmNzQwN2U5ZTdmNzQ2MzAxY2Q5OGNhIiwicHJvZmlsZU5hbWUiOiJPUHBscyIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGI1OTRlZDBlNmE0Nzc3M2NjNDVmZTQ2Y2QzY2IwNTU3ZWNmNmY2M2ViYTY2NGY5ZDgyMGNiNDk5MmM0MTFiMCJ9fX0="),

    ZRAS(QuestArea.GOBLIN_TOWN, "ewogICJ0aW1lc3RhbXAiIDogMTU4OTE5OTg4OTQxMSwKICAicHJvZmlsZUlkIiA6ICIxOTI1MjFiNGVmZGI0MjVjODkzMWYwMmE4NDk2ZTExYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJTZXJpYWxpemFibGUiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzFkODcwYmFkNjIzZWRhYTc5MWZkZTgxYTMxYTYxY2U0N2UxZTUwNjI5NDg5Mjg4MDU4MDg4YTg4OTZjN2IiCiAgICB9CiAgfQp9"),

    HAZEL(QuestArea.SPIRIT_GROUNDS, "eyJ0aW1lc3RhbXAiOjE1NzY5OTQ1NTE3MjUsInByb2ZpbGVJZCI6Ijc1MTQ0NDgxOTFlNjQ1NDY4Yzk3MzlhNmUzOTU3YmViIiwicHJvZmlsZU5hbWUiOiJUaGFua3NNb2phbmciLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzEwODUwNmI1NTdjODIwYTNhYTVjMTYyY2VjNzFmYTU4NmE3ZWVjZjExZDAyOTBhNTllNDFjNTQwYWVmOTMyZDcifX19"),

    DAMON(QuestArea.HELL, "eyJ0aW1lc3RhbXAiOjE1MzIzNjYwMzUxMzYsInByb2ZpbGVJZCI6ImMxZWQ5N2Q0ZDE2NzQyYzI5OGI1ODFiZmRiODhhMjFmIiwicHJvZmlsZU5hbWUiOiJ5b2xvX21hdGlzIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9iMDYyMTU4ODM0NTUwMWNjMzY5MWJmOTI2MGFjYzc2ZWQ5ZTcxZTIxN2Q5M2IxY2NhYTdjYzg0Y2FlMGNiYWYwIn19fQ=="),
    DIABLO(QuestArea.HELL, "eyJ0aW1lc3RhbXAiOjE1Njg2OTkzMzEzMzQsInByb2ZpbGVJZCI6IjNhYTM2M2QwNjY0ZjRlYTBhNjdhZTk0MGYxMzgxMzFlIiwicHJvZmlsZU5hbWUiOiJFbmRlcl9hc3Nhc3NpbjYiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2MzZDQwOGI3YzRhZWRiYzY4MjQ0NTI1NTdiYmRiMjMxY2RhMzE4YTI0OTFjODc2NzA4YTE5MmYwYWY1MjhlNTcifX19"),

    KHAZIX(QuestArea.VOID, "ewogICJ0aW1lc3RhbXAiIDogMTYwNjM4ODc2OTUyMiwKICAicHJvZmlsZUlkIiA6ICI0ZWQ4MjMzNzFhMmU0YmI3YTVlYWJmY2ZmZGE4NDk1NyIsCiAgInByb2ZpbGVOYW1lIiA6ICJGaXJlYnlyZDg4IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2NkYmQ2MzI5YWFkMGU3NWFkODQwOWNlMGY0MTkwOGI5NDY4NjJiMzk4MTQ4ZWQ4N2M3YzgxYzgwZjE0ZDRkMWUiCiAgICB9CiAgfQp9"),
    KASSADIN(QuestArea.VOID, "ewogICJ0aW1lc3RhbXAiIDogMTYwNjM4ODc2OTUyMiwKICAicHJvZmlsZUlkIiA6ICI0ZWQ4MjMzNzFhMmU0YmI3YTVlYWJmY2ZmZGE4NDk1NyIsCiAgInByb2ZpbGVOYW1lIiA6ICJGaXJlYnlyZDg4IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2NkYmQ2MzI5YWFkMGU3NWFkODQwOWNlMGY0MTkwOGI5NDY4NjJiMzk4MTQ4ZWQ4N2M3YzgxYzgwZjE0ZDRkMWUiCiAgICB9CiAgfQp9"),
    KOGMAW(QuestArea.VOID, "ewogICJ0aW1lc3RhbXAiIDogMTYwNjM4ODc2OTUyMiwKICAicHJvZmlsZUlkIiA6ICI0ZWQ4MjMzNzFhMmU0YmI3YTVlYWJmY2ZmZGE4NDk1NyIsCiAgInByb2ZpbGVOYW1lIiA6ICJGaXJlYnlyZDg4IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2NkYmQ2MzI5YWFkMGU3NWFkODQwOWNlMGY0MTkwOGI5NDY4NjJiMzk4MTQ4ZWQ4N2M3YzgxYzgwZjE0ZDRkMWUiCiAgICB9CiAgfQp9"),
    ;

    private QuestArea area;
    private final String head;


    QuestGiver(QuestArea area, String head) {
        this.area = area;
        this.head = head;
    }

    public QuestArea getArea() {
        return area;
    }

    public String getHead() {
        return head;
    }

}

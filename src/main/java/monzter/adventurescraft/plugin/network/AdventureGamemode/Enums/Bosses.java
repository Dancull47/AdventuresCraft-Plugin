package monzter.adventurescraft.plugin.network.AdventureGamemode.Enums;

import dev.dbassett.skullcreator.SkullCreator;
import monzter.adventurescraft.plugin.utilities.enums.CrateList;
import org.bukkit.inventory.ItemStack;

public enum Bosses {
    REAPER(CrateList.REAPER, CrateList.REAPER.getName(), true, SkullCreator.itemFromBase64("eyJ0aW1lc3RhbXAiOjE1NzAzNzcwMTQ5OTcsInByb2ZpbGVJZCI6ImIwZDczMmZlMDBmNzQwN2U5ZTdmNzQ2MzAxY2Q5OGNhIiwicHJvZmlsZU5hbWUiOiJPUHBscyIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODk1NjI5ODZjOWY0NzEyYzZlZWYzYjY4MGFmNTkzNTFkOTgxZDk4ZWI0YTBiMGEzY2ZhZWM2NjQyZjRiMjY3In19fQ==")),
    MORDEN(CrateList.MORDEN, CrateList.MORDEN.getName(), false, SkullCreator.itemFromBase64("eyJ0aW1lc3RhbXAiOjE1ODYxMTkyMzk1MzAsInByb2ZpbGVJZCI6Ijc1MTQ0NDgxOTFlNjQ1NDY4Yzk3MzlhNmUzOTU3YmViIiwicHJvZmlsZU5hbWUiOiJUaGFua3NNb2phbmciLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzVmYzExMTFmYzBlMGYyYzAwNDBjOTVlZDBhNjkwZWM5YWIwZDMyZTNjMDZhNTRiMTAxYzI0Mjc2MGYxMjY3ZWUifX19")),
    DRYAD(CrateList.DRYAD, CrateList.DRYAD.getName(), false, SkullCreator.itemFromBase64("eyJ0aW1lc3RhbXAiOjE1NzE3MDkwMDQwMjMsInByb2ZpbGVJZCI6ImRlNTcxYTEwMmNiODQ4ODA4ZmU3YzlmNDQ5NmVjZGFkIiwicHJvZmlsZU5hbWUiOiJNSEZfTWluZXNraW4iLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzM5ZmU1OTdkNDg3MTYxMTRhYjk0ODVlOTA3OWFjNjE1M2UzZjA1ZTljNTFiN2RjMmIxNGYzNjlhNjU0NTk1OTUifX19")),
    GOBLIN_CHIEF(CrateList.GOBLIN_CHIEF, CrateList.GOBLIN_CHIEF.getName(), true, SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjZiOTcyZTMyZDc2MWIxOTI2MjZlNWQ2ZDAxZWRjMDk0OTQwOTEwMTAzY2VhNWUyZTJkMWYyMzFhZGI3NTVkNSJ9fX0=")),
    BULBLIN(CrateList.BULBLIN,  CrateList.BULBLIN.getName(), true, SkullCreator.itemFromBase64("ewogICJ0aW1lc3RhbXAiIDogMTU5NzUxMzg1MzAwNiwKICAicHJvZmlsZUlkIiA6ICIwNmE4NjAyZDAwODk0YWQxOTcyMGQ3NGE1OGU1MDZjZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJfMW5kcmFfIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzY4ZjBhZjRkZmZiMWZhYzQxNjQ3YjcxMDMwMGUwN2EyNGFiNWY3MDM1NmU0ZmU3OTUxYjBmMzY5NmQxZTJlZTgiCiAgICB9CiAgfQp9")),
    BULLBO(CrateList.BULLBO,  CrateList.BULLBO.getName(), true, SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2QyMGJmNTJlYzM5MGEwNzk5Mjk5MTg0ZmM2NzhiZjg0Y2Y3MzJiYjFiZDc4ZmQxYzRiNDQxODU4ZjAyMzVhOCJ9fX0=")),
    ENCHANTRESS(CrateList.ENCHANTRESS, CrateList.ENCHANTRESS.getName(), false, SkullCreator.itemFromBase64("ewogICJ0aW1lc3RhbXAiIDogMTYwMTg2NzYyNjIzNiwKICAicHJvZmlsZUlkIiA6ICJhNzdkNmQ2YmFjOWE0NzY3YTFhNzU1NjYxOTllYmY5MiIsCiAgInByb2ZpbGVOYW1lIiA6ICIwOEJFRDUiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGY5NjE0M2E1MDlkZmU2ZmM5YzMyMDA1ZGQ1N2Y2OGFjNWU0MmVjMGI3MTY4YTU4ZmUzMTc3YzJjY2Y3NWRlMCIKICAgIH0KICB9Cn0=")),

    ;
    private CrateList crateList;
    private String name;
    private boolean respawn;
    private ItemStack itemStack;


    Bosses(CrateList crateList, String name, boolean respawn, ItemStack itemStack) {
        this.crateList = crateList;
        this.name = name;
        this.respawn = respawn;
        this.itemStack = itemStack;
    }

    public String getName() {
        return name;
    }

    public boolean isRespawn() {
        return respawn;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public CrateList getCrateList() {
        return crateList;
    }
}

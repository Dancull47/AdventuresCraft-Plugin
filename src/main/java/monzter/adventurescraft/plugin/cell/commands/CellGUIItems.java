package monzter.adventurescraft.plugin.cell.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import world.bentobox.bentobox.api.flags.Flag;
import world.bentobox.bentobox.lists.Flags;

public enum CellGUIItems {
    Anvil(Material.ANVIL, Flags.ANVIL, ChatColor.GREEN + "Anvil Usage", "Allow others to interact with Anvils."),
    Boats(Material.OAK_BOAT, Flags.BOAT, ChatColor.GREEN + "Boats", "Place, Break, or Ride Boats.")
    ;

    private final Material material;
    private final Flag flag;
    private final String name;
    private final String info;

    CellGUIItems(Material material, Flag flag, String name, String info) {
        this.material = material;
        this.flag = flag;
        this.name = name;
        this.info = info;
    }

    public Material getMaterial() {
        return material;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public Flag getFlag() {
        return flag;
    }
}

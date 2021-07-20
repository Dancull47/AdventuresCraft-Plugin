package monzter.adventurescraft.plugin.utilities.GUI;

import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.quests.QuestGiver;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface GUIHelper {
    ItemStack background();

    ItemStack background(Material material);

    ItemStack backButton();

    ItemStack nextPageButton();

    ItemStack previousPageButton();

    ItemStack firstPageButton();

    ItemStack lastPageButton();

    String guiName(String name);

    ItemStack itemCreator(Material material, String name, String[] lore);

    ItemStack itemCreator(String skullTexture, String name, String[] lore);

    ItemStack itemCreator(ItemStack itemStack, String name, String[] lore);

    ItemStack questInactive(String questName, TextComponent[] questDescription, TextComponent[] questRewards, String questGiver, String questGiverArea);

    ItemStack questInactive(String questName, String[] questDescription, String[] questRewards, String questGiver, String questGiverArea);

    ItemStack questActive(String questName, TextComponent[] questDescription, TextComponent[] questRewards, String questGiver, String questGiverArea);

    ItemStack questActive(String questName, String[] questDescription, String[] questRewards, String questGiver, String questGiverArea);

    ItemStack questComplete(String questName, TextComponent[] questDescription, TextComponent[] questRewards, String questGiver, String questGiverArea);

    ItemStack questComplete(String questName, String[] questDescription, String[] questRewards, String questGiver, String questGiverArea);

    ItemStack questUnclaimed(String questName, String[] questDescription, String[] questRewards, String questGiver, String questGiverArea);

    ItemStack jobActive(String questName, String questDescription, ItemStack[] questRewards, int[] rewardsAmount, String[] currencyRewards, int[] currencyRewardsAmount, String questGiver, String questGiverArea);

    ItemStack jobActive(String questName, String questDescription, ItemStack[] questRewards, int[] rewardsAmount, String[] currencyRewards, int[] currencyRewardsAmount, String resetTime);

    void questMenuGenerator(Player player, QuestGiver questGiver, Material backgroundColor);

    void jobMenuGenerator(Player player, QuestGiver questGiver, Material backgroundColor);

    void miningPassMenuGenerator(Player player, QuestGiver questGiver, Material backgroundColor);
}

package ru.gikexe.itemranks;

import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.CraftingRecipe;
import org.bukkit.inventory.ItemStack;

import static net.kyori.adventure.text.format.NamedTextColor.*;
import static org.bukkit.Material.AIR;

public class CraftListener implements Listener {
	private final ItemRanks plugin;

	public CraftListener(ItemRanks plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void on(CraftItemEvent event) {
		ItemStack cursorItem = event.getCursor();
		if (!cursorItem.getType().equals(AIR)) return;

		ItemStack item = event.getCurrentItem();
		if (item == null) return; // по идее тут не должно срабатывать*
		plugin.setRank(item, plugin.getRandomRank());
		event.setCurrentItem(item);

		Player player = (Player) event.getWhoClicked();
		CraftingRecipe recipe = (CraftingRecipe) event.getRecipe();
		NamespacedKey key = recipe.getKey();
		// было бы не плохо отличать какие крафты нужно обрабатывать...
		// key = minecraft:diamond_pickaxe
		// ванильные крафты инструментов через yaml\json
		player.sendMessage(Component.text(key.asString(), RED));
	}

	@EventHandler
	public void on(InventoryClickEvent event) {}
}

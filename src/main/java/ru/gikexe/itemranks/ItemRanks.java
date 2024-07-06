package ru.gikexe.itemranks;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static net.kyori.adventure.text.format.NamedTextColor.*;
import static net.kyori.adventure.text.format.TextDecoration.*;

public final class ItemRanks extends JavaPlugin {
	String ranks = "XSABCDEF";
	Random random = new Random();

	@Override
	public void onEnable() {
		Server server = getServer();
		PluginManager pm = server.getPluginManager();

		pm.registerEvents(new CraftListener(this), this);
	}

	public void setRank(ItemStack item, Character rank) {
		if (rank.equals('F')) _setRank(item, "404040", "Ужасно");
		else if (rank.equals('E')) _setRank(item, "808080", "Плохо");
		else if (rank.equals('D')) _setRank(item, "FFD800", "Нормально");
		else if (rank.equals('C')) _setRank(item, "B6FF00", "Хорошо");
		else if (rank.equals('B')) _setRank(item, "00FFFF", "Отлично");
		else if (rank.equals('A')) _setRank(item, "FF006E", "Шедеврально");
		else if (rank.equals('S')) _setRank(item, "B200FF", "Легендарно");
		else if (rank.equals('X')) _setRank(item, "57007F", "Божественно");
	}

	public Character getRandomRank() {
		return ranks.charAt(random.nextInt(ranks.length()));
	}

	private void _setRank(ItemStack item, String color, String name) {
		ItemMeta meta = item.getItemMeta();
		meta.displayName(Component.translatable(item.translationKey(), TextColor.fromHexString("#"+color)).decoration(ITALIC, false));
		List<Component> lore = meta.lore();
		if (lore == null) lore = new ArrayList<>();
		lore.add(0, Component.text("качество: ", WHITE).decoration(ITALIC, false)
			.append(Component.text(name, TextColor.fromHexString("#"+color))));
		meta.lore(lore);
		item.setItemMeta(meta);
	}



	@Override
	public void onDisable() {
	}
}

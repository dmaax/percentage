package dmax.percentage;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemManager {

	public ArrayList<ItemStack> itens = new ArrayList<>();

	@SuppressWarnings("deprecation")
	public void setupItemExclusivo(Player p, Integer id) {
		setup();
		for (String items : Files.caixasConfig.getConfigurationSection("Items").getKeys(false)) {
			String name = ChatColor.translateAlternateColorCodes('&',Files.caixasConfig.getString("Items." + items + ".Display"));
			ItemStack item = new ItemStack(
					Material.valueOf(Files.caixasConfig.getString("Items." + items + ".Material")));
			ItemMeta itemmeta = item.getItemMeta();
			List<String> lore = Files.caixasConfig.getStringList("Items." + items + ".Lore");
			lore.add("§7Item dropado por: " + p.getName());
			lore.replaceAll(s -> Colors.color(s));
			itemmeta.setDisplayName(name);
			itemmeta.setLore(lore);
			boolean isEnchant = Files.caixasConfig.getBoolean("Items." + items + ".enable_enchantment");
			boolean isFlag = Files.caixasConfig.getBoolean("Items." + items + ".enable_itemflag");
			if (isFlag == true) {
				for (String itemflag : Files.caixasConfig.getStringList("Items." + items + ".ItemFlag")) {
					itemmeta.addItemFlags(ItemFlag.valueOf(itemflag));
				}
			}
			if (isEnchant == true) {
				for (String enchant : Files.caixasConfig.getStringList("Items." + items + ".Enchantment")) {
					String enchantname = enchant.split(":")[0];
					Integer enchantlvl = Integer.valueOf(enchant.split(":")[1]);
					itemmeta.addEnchant(Enchantment.getByName(enchantname), enchantlvl, false);
				}
			}
			
			item.setItemMeta(itemmeta);
			itens.add(item);
			return;
		}
	}
	
	public void give(Player p, Integer id) {
		p.getInventory().addItem(itens.get(id));
		p.sendMessage("§aVocê recebeu um item exclusivo do Evento de §6§lHalloween!");
	}

	public void setup() {
		itens.clear();
	}
		
	public ItemStack criar(String nome, Material m, Integer i) {
		ItemStack item = new ItemStack(m);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}
	
	public void teste() {
	}

}

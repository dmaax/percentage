package dmax.percentage;

import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

public class SpecialItems {
	
	public static ItemStack giveSpawnIronGolem() {
		ItemStack spawner = new ItemStack(Material.SPAWNER, 1);
		BlockStateMeta meta2 = (BlockStateMeta) spawner.getItemMeta();
		CreatureSpawner cs = (CreatureSpawner) meta2.getBlockState();
		cs.setSpawnedType(EntityType.IRON_GOLEM);
		meta2.setBlockState(cs);
		spawner.setItemMeta(meta2);
		return spawner;
	}
	
	
	
	public static ItemStack giveSpawnZombiePigman() {
		ItemStack spawner = new ItemStack(Material.SPAWNER, 1);
		BlockStateMeta meta2 = (BlockStateMeta) spawner.getItemMeta();
		CreatureSpawner cs = (CreatureSpawner) meta2.getBlockState();
		cs.setSpawnedType(EntityType.ZOMBIFIED_PIGLIN);
		meta2.setBlockState(cs);
		spawner.setItemMeta(meta2);
		return spawner;
	}
	
	public static ItemStack giveSharpness() {
		ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
		EnchantmentStorageMeta metab = (EnchantmentStorageMeta) book.getItemMeta();
		metab.addStoredEnchant(Enchantment.DAMAGE_ALL, 5, true);
		book.setItemMeta(metab);
		return book;
	}
	
	public static ItemStack giveProtection() {
		ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
		EnchantmentStorageMeta metab = (EnchantmentStorageMeta) book.getItemMeta();
		metab.addStoredEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		book.setItemMeta(metab);
		return book;
	}
	
}

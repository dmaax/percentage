package dmax.percentage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.server.v1_16_R3.CommandException;

public class Commands implements CommandExecutor {

	ItemManager im = new ItemManager();
	ArrayList<ItemStack> itensExclusivo = new ArrayList<>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("caixa")) {
			if (sender instanceof Player) {
				sender.sendMessage("§cComando interno!");
				return true;
			}
			if (args.length != 2) {
				sender.sendMessage("§cUso correto: /caixa <numero> <jogador>");
				return true;
			}

			if (args[0] == null || args[1] == null) {
				sender.sendMessage("§cUso correto: /caixa <numero> <jogador>");
				return true;
			}

			int argint = Integer.parseInt(args[0]);
			Player p = (Player) Bukkit.getPlayer(args[1]);
			pegarItens(argint, p);
			return true;
		}
		
		if(label.equalsIgnoreCase("presents")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				if(!player.hasPermission("percentage.presents")) {
					player.sendMessage("§cSem permissão.");
					return true;
				}
				if(args.length != 2) {
					player.sendMessage("§7[§5Percentage§7] §5Esse comando seta os presentes automaticamente, exceção dos itens exclusivos.");
					player.sendMessage("§7[§5Percentage§7] §7Exemplo para setar do numero 10 até o numero 32:");
					player.sendMessage("§7[§5Percentage§7] §c/presents 10 32");
					return true;
				}
				Integer numero = Integer.parseInt(args[0]);
				Integer numero2 = Integer.parseInt(args[1]);
				while(numero <= numero2) {
					Random rand = new Random();
					int aleatorio = rand.nextInt(4) + 1;
					player.performCommand("lps reward setcustom " + numero + " caixa " + aleatorio + " %player%");
					numero++;
				}
				player.sendMessage("§6Todos os presentes foram setados!");
				return true;
			}
			sender.sendMessage("§cComando ingame apenas.");
			return true;
		}
		
		if(label.equalsIgnoreCase("setpresents")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				if(!player.hasPermission("percentage.presents")) {
					player.sendMessage("§cSem permissão.");
					return true;
				}
				if(args.length != 2) {
					player.sendMessage("§7[§5Percentage§7] §5Esse comando seta os presentes automaticamente, exceção dos itens exclusivos.");
					player.sendMessage("§7[§5Percentage§7] §7Exemplo para setar do numero 10 até o numero 32:");
					player.sendMessage("§7[§5Percentage§7] §c/setpresents 10 32");
					return true;
				}
				Integer numero = Integer.parseInt(args[0]);
				Integer numero2 = Integer.parseInt(args[1]);
				while(numero <= numero2) {
					Random rand = new Random();
					int aleatorio = rand.nextInt(4) + 1;
					player.performCommand("lps reward setcustom " + numero + " caixa " + aleatorio + " %player%");
					numero++;
				}
				player.sendMessage("§6Todos os presentes foram setados!");
				return true;
			}
			sender.sendMessage("§cComando ingame apenas.");
			return true;
		}
		
		if(label.equalsIgnoreCase("removepresents")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				if(!player.hasPermission("percentage.presents")) {
					player.sendMessage("§cSem permissão.");
					return true;
				}
				if(args.length != 2) {
					player.sendMessage("§7[§5Percentage§7] §5Esse comando remove os presentes automaticamente.");
					player.sendMessage("§7[§5Percentage§7] §7Exemplo para remove do numero 1 até o numero 50:");
					player.sendMessage("§7[§5Percentage§7] §c/removepresents 1 50");
					return true;
				}
				Integer numero = Integer.parseInt(args[0]);
				Integer numero2 = Integer.parseInt(args[1]);
				while(numero <= numero2) {
					player.performCommand("lps remove " + numero);
					numero++;
				}
				player.sendMessage("§6Todos os presentes foram removidos!");
				return true;
			}
			sender.sendMessage("§cComando ingame apenas.");
			return true;
		}

		if (label.equalsIgnoreCase("giveitemexclusivo")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				p.sendMessage("§cComando interno apenas!");
				return true;
			}
			if (args.length != 2) {
				sender.sendMessage("§cUso correto: /giveitemexclusivo <player> <id>");
				return true;
			}

			int id = Integer.parseInt(args[1]);
			Player p = (Player) Bukkit.getPlayer(args[0]);
			getDeprecatedItemExclusivo(p, id);
			return true;

		}
		return false;

	}

	// A partir disso voce ganha um item exclusivo!
	@SuppressWarnings("deprecation")
	public void getDeprecatedItemExclusivo(Player p, Integer id) {
		sendEffects(p, id);
		switch (id) {
		case 1:
			ItemStack abrobora = new ItemStack(Material.valueOf(Files.caixasConfig.getString("itens.i1.type")));
			ItemMeta meta = abrobora.getItemMeta();
			meta.setDisplayName(Files.caixasConfig.getString("itens.i1.name").replace('&', '§'));
			if (Files.caixasConfig.getString("itens.i1.enchant") != "") {
				String[] enchant = Files.caixasConfig.getString("itens.i1.enchant").split(":");
				meta.addEnchant(Enchantment.getByName(enchant[0]), Integer.parseInt(enchant[1]), true);
			}
			List<String> lore1 = Files.caixasConfig.getStringList("itens.i1.lore");
			lore1.replaceAll(s -> Colors.color(s));
			lore1.replaceAll(s -> Colors.name(s, p));
			meta.setLore(lore1);
			abrobora.setItemMeta(meta);
			p.getInventory().addItem(abrobora);
			p.sendMessage(Files.caixasConfig.getString("itens.i1.message").replace('&', '§'));
			break;
		case 2:
				
			ItemStack maca = new ItemStack(Material.valueOf(Files.caixasConfig.getString("itens.i2.type")));
			ItemMeta meta2 = maca.getItemMeta();
			meta2.setDisplayName(Files.caixasConfig.getString("itens.i2.name").replace('&', '§'));
			if (Files.caixasConfig.getString("itens.i2.enchant") != "") {
				String[] enchant = Files.caixasConfig.getString("itens.i2.enchant").split(":");
				meta2.addEnchant(Enchantment.getByName(enchant[0]), Integer.parseInt(enchant[1]), true);
			}
			List<String> lore2 = Files.caixasConfig.getStringList("itens.i2.lore");
			lore2.replaceAll(s -> Colors.color(s));
			lore2.replaceAll(s -> Colors.name(s, p));
			meta2.setLore(lore2);
			maca.setItemMeta(meta2);
			p.getInventory().addItem(maca);
			p.sendMessage(Files.caixasConfig.getString("itens.i2.message").replace('&', '§'));

			break;
		case 3:
			
			ItemStack stack3 = new ItemStack(Material.valueOf(Files.caixasConfig.getString("itens.i3.type")));
			ItemMeta meta3 = stack3.getItemMeta();
			meta3.setDisplayName(Files.caixasConfig.getString("itens.i3.name").replace('&', '§'));
			if (Files.caixasConfig.getString("itens.i3.enchant") != "") {
				String[] enchant = Files.caixasConfig.getString("itens.i3.enchant").split(":");
				meta3.addEnchant(Enchantment.getByName(enchant[0]), Integer.parseInt(enchant[1]), true);
			}
			List<String> lore3 = Files.caixasConfig.getStringList("itens.i3.lore");
			lore3.replaceAll(s -> Colors.color(s));
			lore3.replaceAll(s -> Colors.name(s, p));
			meta3.setLore(lore3);
			stack3.setItemMeta(meta3);
			p.getInventory().addItem(stack3);
			p.sendMessage(Files.caixasConfig.getString("itens.i3.message").replace('&', '§'));

			break;
			
		case 4:
			ItemStack stack4 = new ItemStack(Material.valueOf(Files.caixasConfig.getString("itens.i4.type")));
			ItemMeta meta4 = stack4.getItemMeta();
			meta4.setDisplayName(Files.caixasConfig.getString("itens.i4.name").replace('&', '§'));
			if (Files.caixasConfig.getString("itens.i4.enchant") != "") {
				String[] enchant = Files.caixasConfig.getString("itens.i4.enchant").split(":");
				meta4.addEnchant(Enchantment.getByName(enchant[0]), Integer.parseInt(enchant[1]), true);
			}
			List<String> lore4 = Files.caixasConfig.getStringList("itens.i4.lore");
			lore4.replaceAll(s -> Colors.color(s));
			lore4.replaceAll(s -> Colors.name(s, p));
			meta4.setLore(lore4);
			stack4.setItemMeta(meta4);
			p.getInventory().addItem(stack4);
			p.sendMessage(Files.caixasConfig.getString("itens.i4.message").replace('&', '§'));
			break;
		case 5:
			ItemStack stack5 = new ItemStack(Material.valueOf(Files.caixasConfig.getString("itens.i5.type")));
			ItemMeta meta5 = stack5.getItemMeta();
			meta5.setDisplayName(Files.caixasConfig.getString("itens.i5.name").replace('&', '§'));
			if (Files.caixasConfig.getString("itens.i5.enchant") != "") {
				String[] enchant = Files.caixasConfig.getString("itens.i5.enchant").split(":");
				meta5.addEnchant(Enchantment.getByName(enchant[0]), Integer.parseInt(enchant[1]), true);
			}
			List<String> lore5 = Files.caixasConfig.getStringList("itens.i5.lore");
			lore5.replaceAll(s -> Colors.color(s));
			lore5.replaceAll(s -> Colors.name(s, p));
			meta5.setLore(lore5);
			stack5.setItemMeta(meta5);
			p.getInventory().addItem(stack5);
			p.sendMessage(Files.caixasConfig.getString("itens.i5.message").replace('&', '§'));
			break;
		case 6:
			ItemStack stack6 = new ItemStack(Material.valueOf(Files.caixasConfig.getString("itens.i6.type")));
			ItemMeta meta6 = stack6.getItemMeta();
			meta6.setDisplayName(Files.caixasConfig.getString("itens.i6.name").replace('&', '§'));
//			if (Files.caixasConfig.getString("itens.i6.enchant") != "") {
//				String[] enchant = Files.caixasConfig.getString("itens.i6.enchant").split(":");
//				meta6.addEnchant(Enchantment.getByName(enchant[0]), Integer.parseInt(enchant[1]), true);
//			}
			meta6.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
			meta6.addEnchant(Enchantment.FIRE_ASPECT, 3, true);
			meta6.addEnchant(Enchantment.DURABILITY, 1, true);
			List<String> lore6 = Files.caixasConfig.getStringList("itens.i6.lore");
			lore6.replaceAll(s -> Colors.color(s));
			lore6.replaceAll(s -> Colors.name(s, p));
			meta6.setLore(lore6);
			stack6.setItemMeta(meta6);
			p.getInventory().addItem(stack6);
			p.sendMessage(Files.caixasConfig.getString("itens.i6.message").replace('&', '§'));
			break;
		case 7:
			ItemStack stack7 = new ItemStack(Material.valueOf(Files.caixasConfig.getString("itens.i7.type")));
			ItemMeta meta7 = stack7.getItemMeta();
			meta7.setDisplayName(Files.caixasConfig.getString("itens.i7.name").replace('&', '§'));
//			if (Files.caixasConfig.getString("itens.i7.enchant") != "") {
//				String[] enchant = Files.caixasConfig.getString("itens.i7.enchant").split(":");
//				meta7.addEnchant(Enchantment.getByName(enchant[0]), Integer.parseInt(enchant[1]), true);
//			}
			meta7.addEnchant(Enchantment.DIG_SPEED, 3, true);
			meta7.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 2, true);
			meta7.addEnchant(Enchantment.DURABILITY, 4, true);
			List<String> lore7 = Files.caixasConfig.getStringList("itens.i7.lore");
			lore7.replaceAll(s -> Colors.color(s));
			lore7.replaceAll(s -> Colors.name(s, p));
			meta7.setLore(lore7);
			stack7.setItemMeta(meta7);
			p.getInventory().addItem(stack7);
			p.sendMessage(Files.caixasConfig.getString("itens.i7.message").replace('&', '§'));
			break;
		case 8:
			ItemStack stack8 = new ItemStack(Material.valueOf(Files.caixasConfig.getString("itens.i8.type")));
			ItemMeta meta8 = stack8.getItemMeta();
			meta8.setDisplayName(Files.caixasConfig.getString("itens.i8.name").replace('&', '§'));
//			if (Files.caixasConfig.getString("itens.i8.enchant") != "") {
//				String[] enchant = Files.caixasConfig.getString("itens.i8.enchant").split(":");
//				meta8.addEnchant(Enchantment.getByName(enchant[0]), Integer.parseInt(enchant[1]), true);
//			}
			meta8.addEnchant(Enchantment.DIG_SPEED, 3, true);
			meta8.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 4, true);
			meta8.addEnchant(Enchantment.DURABILITY, 2, true);
			List<String> lore8 = Files.caixasConfig.getStringList("itens.i8.lore");
			lore8.replaceAll(s -> Colors.color(s));
			lore8.replaceAll(s -> Colors.name(s, p));
			meta8.setLore(lore8);
			stack8.setItemMeta(meta8);
			p.getInventory().addItem(stack8);
			p.sendMessage(Files.caixasConfig.getString("itens.i8.message").replace('&', '§'));
			break;
		case 9:
			ItemStack stack9 = new ItemStack(Material.valueOf(Files.caixasConfig.getString("itens.i9.type")));
			ItemMeta meta9 = stack9.getItemMeta();
			meta9.setDisplayName(Files.caixasConfig.getString("itens.i9.name").replace('&', '§'));
			if (Files.caixasConfig.getString("itens.i9.enchant") != "") {
				String[] enchant = Files.caixasConfig.getString("itens.i9.enchant").split(":");
				meta9.addEnchant(Enchantment.getByName(enchant[0]), Integer.parseInt(enchant[1]), true);
			}
			List<String> lore9 = Files.caixasConfig.getStringList("itens.i9.lore");
			lore9.replaceAll(s -> Colors.color(s));
			lore9.replaceAll(s -> Colors.name(s, p));
			meta9.setLore(lore9);
			stack9.setItemMeta(meta9);
			p.getInventory().addItem(stack9);
			p.sendMessage(Files.caixasConfig.getString("itens.i9.message").replace('&', '§'));
			break;
		case 10:
			ItemStack stack10 = new ItemStack(Material.valueOf(Files.caixasConfig.getString("itens.i10.type")));
			ItemMeta meta10 = stack10.getItemMeta();
			meta10.setDisplayName(Files.caixasConfig.getString("itens.i10.name").replace('&', '§'));
//			if (Files.caixasConfig.getString("itens.i10.enchant") != "") {
//				String[] enchant = Files.caixasConfig.getString("itens.i10.enchant").split(":");
//				meta10.addEnchant(Enchantment.getByName(enchant[0]), Integer.parseInt(enchant[1]), true);
//			}
			meta10.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			List<String> lore10 = Files.caixasConfig.getStringList("itens.i10.lore");
			lore10.replaceAll(s -> Colors.color(s));
			lore10.replaceAll(s -> Colors.name(s, p));
			meta10.setLore(lore10);
			stack10.setItemMeta(meta10);
			p.getInventory().addItem(stack10);
			p.sendMessage(Files.caixasConfig.getString("itens.i10.message").replace('&', '§'));
			break;
		default:
			break;
		}

	}
	
	@SuppressWarnings("deprecation")
	public void sendEffects(Player p, Integer id) {
		p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_AMBIENT, 1.0F, 1.0F);
		p.playEffect(p.getLocation(), Effect.DRAGON_BREATH, 2);
		p.sendTitle("§6§lEvento §b§lPáscoa", "§6Você encontrou um ITEM EXCLUSIVO!", 20, 100, 20);
	}

	public void pegarItens(Integer numerocaixa, Player jogador) {
		int argint = numerocaixa;
		Player p = jogador;
		Random ran = new Random();
		int choice10 = ran.nextInt(10) + 1;

		switch (argint) {
		case 1:

			if (choice10 == 6) {
				if (p.getInventory().firstEmpty() == -1) {
					p.sendMessage("§aSeu inventario esta cheio, o item foi dropado.");
					p.getWorld().dropItemNaturally(p.getLocation(), SpecialItems.giveSpawnIronGolem());
					return;
				}
				p.getInventory().addItem(SpecialItems.giveSpawnIronGolem());
				return;
			}

			ItemStack itemc = new ItemStack(
					Material.getMaterial((Files.caixasConfig.getString("c1" + ".i" + choice10 + "-type"))),
					Files.caixasConfig.getInt("c" + argint + ".i" + choice10 + "-amount"));

			if (p.getInventory().firstEmpty() == -1) {
				p.getWorld().dropItemNaturally(p.getLocation(), itemc);
				p.sendMessage("§cSeu inventario está cheio, o item foi dropado.");
				return;
			}

			p.getInventory().addItem(itemc);

			break;
		case 2:
			
			ItemStack itemc2 = new ItemStack(
					Material.getMaterial((Files.caixasConfig.getString("c2" + ".i" + choice10 + "-type"))),
					Files.caixasConfig.getInt("c" + argint + ".i" + choice10 + "-amount"));

			if (p.getInventory().firstEmpty() == -1) {
				p.getWorld().dropItemNaturally(p.getLocation(), itemc2);
				p.sendMessage("§cSeu inventário está cheio, o item foi dropado.");
				return;
			}

			p.getInventory().addItem(itemc2);

			break;
		case 3:
						
			if(choice10 == 4) {
				if (p.getInventory().firstEmpty() == -1) {
					p.sendMessage("§aSeu inventario esta cheio, o item foi dropado.");
					p.getWorld().dropItemNaturally(p.getLocation(), SpecialItems.giveSpawnZombiePigman());
					return;
				}
				p.getInventory().addItem(SpecialItems.giveSpawnZombiePigman());
				return;
			}
			
			if(choice10 == 9) {
				if(p.getInventory().firstEmpty() == -1) {
					p.sendMessage("§aSeu inventario esta cheio, o item foi dropado.");
					p.getWorld().dropItemNaturally(p.getLocation(), SpecialItems.giveSharpness());
					return;
				}
				p.getInventory().addItem(SpecialItems.giveSharpness());
				return;
			}
			
			ItemStack itemc3 = new ItemStack(
					Material.getMaterial((Files.caixasConfig.getString("c3" + ".i" + choice10 + "-type"))),
					Files.caixasConfig.getInt("c" + argint + ".i" + choice10 + "-amount"));

			if (p.getInventory().firstEmpty() == -1) {
				p.getWorld().dropItemNaturally(p.getLocation(), itemc3);
				p.sendMessage("§cSeu inventário está cheio, o item foi dropado.");
				return;
			}

			p.getInventory().addItem(itemc3);
			
			break;
		case 4:
			
			if(choice10 == 9) {
				if (p.getInventory().firstEmpty() == -1) {
					p.getWorld().dropItemNaturally(p.getLocation(), SpecialItems.giveProtection());
					p.sendMessage("§cSeu invent�rio está cheio, o item foi dropado.");
					return;
				}
				p.getInventory().addItem(SpecialItems.giveProtection());
				break;
			}
			
			ItemStack itemc4 = new ItemStack(
					Material.getMaterial((Files.caixasConfig.getString("c4" + ".i" + choice10 + "-type"))),
					Files.caixasConfig.getInt("c" + argint + ".i" + choice10 + "-amount"));

			if (p.getInventory().firstEmpty() == -1) {
				p.getWorld().dropItemNaturally(p.getLocation(), itemc4);
				p.sendMessage("§cSeu inventário está cheio, o item foi dropado.");
				return;
			}
			p.getInventory().addItem(itemc4);
			break;
		default:
			break;
		}

		// Antes de sortear um item, vamos dropar um item exclusivo
		// Se for maior ou igual a 11, sorteia novamenta entre os 3 itens melhores

//		if (choice >= 100 - Files.caixasConfig.getDouble("c" + argint + ".i1-chance")) {
//			ItemStack item1 = new ItemStack(Material.valueOf(Files.caixasConfig.getString("c" + argint + ".i1-type")),
//					Files.caixasConfig.getInt("c" + argint + ".i1-amount"));
//			p.sendMessage("�aPrimeiro item");
//			p.getInventory().addItem(item1);
//		} else if (choice >= 100 - Files.caixasConfig.getDouble("c" + argint + ".i2-chance")) {
//			ItemStack item2 = new ItemStack(Material.valueOf(Files.caixasConfig.getString("c" + argint + ".i2-type")),
//					Files.caixasConfig.getInt("c" + argint + ".i2-amount"));
//			p.sendMessage("�bSegundo item");
//			p.getInventory().addItem(item2);
//		}
//		
//		else if (choice >= 100 - Files.caixasConfig.getDouble("c" + argint + ".i3-chance")) {
//			ItemStack item2 = new ItemStack(Material.valueOf(Files.caixasConfig.getString("c" + argint + ".i3-type")),
//					Files.caixasConfig.getInt("c" + argint + ".i3-amount"));
//			p.sendMessage("�cTerceiro item");
//			p.getInventory().addItem(item2);
//		}
//		
//		else if (choice >= 100 - Files.caixasConfig.getDouble("c" + argint + ".i4-chance")) {
//			ItemStack item2 = new ItemStack(Material.valueOf(Files.caixasConfig.getString("c" + argint + ".i4-type")),
//					Files.caixasConfig.getInt("c" + argint + ".i4-amount"));
//			p.sendMessage("�dQuarto item");
//			p.getInventory().addItem(item2);
//		}
//		
//		else if (choice >= 100 - Files.caixasConfig.getDouble("c" + argint + ".i5-chance")) {
//			ItemStack item2 = new ItemStack(Material.valueOf(Files.caixasConfig.getString("c" + argint + ".i5-type")),
//					Files.caixasConfig.getInt("c" + argint + ".i5-amount"));
//			p.sendMessage("�eQuinto item");
//			p.getInventory().addItem(item2);
//		}
//		
//		else if (choice >= 100 - Files.caixasConfig.getDouble("c" + argint + ".i6-chance")) {
//			ItemStack item2 = new ItemStack(Material.valueOf(Files.caixasConfig.getString("c" + argint + ".i6-type")),
//					Files.caixasConfig.getInt("c" + argint + ".i6-amount"));
//			p.sendMessage("�fSexto item");
//			p.getInventory().addItem(item2);
//		}
//		
//		else if (choice >= 100 - Files.caixasConfig.getDouble("c" + argint + ".i7-chance")) {
//			ItemStack item2 = new ItemStack(Material.valueOf(Files.caixasConfig.getString("c" + argint + ".i7-type")),
//					Files.caixasConfig.getInt("c" + argint + ".i7-amount"));
//			p.sendMessage("�1Setimo item");
//			p.getInventory().addItem(item2);
//		}
//		
//		else if (choice >= 100 - Files.caixasConfig.getDouble("c" + argint + ".i8-chance")) {
//			ItemStack item2 = new ItemStack(Material.valueOf(Files.caixasConfig.getString("c" + argint + ".i8-type")),
//					Files.caixasConfig.getInt("c" + argint + ".i8-amount"));
//			p.sendMessage("�2Oitavo item");
//			p.getInventory().addItem(item2);
//		}
//		
//		else if (choice >= 100 - Files.caixasConfig.getDouble("c" + argint + ".i9-chance")) {
//			ItemStack item2 = new ItemStack(Material.valueOf(Files.caixasConfig.getString("c" + argint + ".i9-type")),
//					Files.caixasConfig.getInt("c" + argint + ".i9-amount"));
//			p.sendMessage("�3Nono item");
//			p.getInventory().addItem(item2);
//		}
//		
//		
//		else {
//			ItemStack item3 = new ItemStack(Material.valueOf(Files.caixasConfig.getString("c" + argint + ".i10-type")),
//					Files.caixasConfig.getInt("c" + argint + ".i10-amount"));
//			p.sendMessage("�4Decimo item");
//			p.getInventory().addItem(item3);
//		}
	}

}

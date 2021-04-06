package dmax.percentage;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	String prefix = "§f[§dPercentage§f] ";
	
	@Override
	public void onEnable() {
		System.out.println(prefix + "§aInicializado!");
		Files.base(this);
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		getCommand("caixa").setExecutor(new Commands());
		getCommand("giveitemexclusivo").setExecutor(new Commands());
		getCommand("presents").setExecutor(new Commands());
	}

	@Override
	public void onDisable() {
		System.out.println(prefix + "§aDesligado!");
		HandlerList.unregisterAll();
	}
}

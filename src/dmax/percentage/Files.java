package dmax.percentage;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Files {
	
	public static File configFile;
	public static FileConfiguration config;
	
	public static File caixasFile;
	public static FileConfiguration caixasConfig;
	
	public static void base(Main main) {
		if(!main.getDataFolder().exists()) {
			main.getDataFolder().mkdir();
		}
		
		configFile = new File(main.getDataFolder(), "config.yml");
		if(!configFile.exists()) {
			main.saveResource("config.yml", false);
		}
		config = YamlConfiguration.loadConfiguration(configFile);
		
		caixasFile = new File(main.getDataFolder(), "caixas.yml");
		if(!caixasFile.exists()) {
			main.saveResource("caixas.yml", false);
		}
		caixasConfig = YamlConfiguration.loadConfiguration(caixasFile);
		
	}

}
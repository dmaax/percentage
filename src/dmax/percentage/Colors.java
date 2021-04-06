package dmax.percentage;

import org.bukkit.entity.Player;

public class Colors {
		
	public static String color(String s){
		return s.replace('&', '§');
	}
	
	public static String name(String s, Player p){
		String s1 = s;
		s1 = s1.replace("%player%", p.getName());
		return s1;
	}
	
//	public static List<String> color(List<String> lore){
//	    return lore.stream().map(this::color).collect(Collectors.toList());
//	}

}

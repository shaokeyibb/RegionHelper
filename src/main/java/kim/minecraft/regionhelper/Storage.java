package kim.minecraft.regionhelper;


import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    static JavaPlugin plugin;

    List<Region> regions = new ArrayList<>();

    public Storage(JavaPlugin plugin) {
        Storage.plugin = plugin;
        init();
    }

    private static void init() {
        plugin.saveDefaultConfig();
        FileConfiguration file = plugin.getConfig();
    }

    public void saveAll() throws IOException {
        FileConfiguration file = new YamlConfiguration();
        final int[] index = {0};
        regions.forEach((region) -> {
            file.set(String.valueOf(index[0]),null);
            file.set(index[0] +".Loc1",null);
            file.set(index[0] +".Loc1.x",String.valueOf(region.loc1.getBlockX()));
            file.set(index[0] +".Loc1.y",String.valueOf(region.loc1.getBlockY()));
            file.set(index[0] +".Loc1.z",String.valueOf(region.loc1.getBlockZ()));
            file.set(index[0] +".Loc2",null);
            file.set(index[0] +".Loc2.x",String.valueOf(region.loc2.getBlockX()));
            file.set(index[0] +".Loc2.y",String.valueOf(region.loc2.getBlockY()));
            file.set(index[0] +".Loc2.z",String.valueOf(region.loc2.getBlockZ()));
            file.set(index[0] +".Message",region.getMessage());
            index[0]++;
        });
        file.save(new File(plugin.getDataFolder(),"config.yml"));
    }
}

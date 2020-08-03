package kim.minecraft.regionhelper;


import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class Storage {

    @Getter
    private final Map<String, Region> regions;

    private final Map<UUID, SelectPos> posMap = new HashMap<>();

    public Storage() {
        RegionHelper.getInstance().saveDefaultConfig();
        regions = new HashMap<>();
    }

    public void init() {
        FileConfiguration file = RegionHelper.getInstance().getConfig();
        file.getKeys(false).forEach((index) -> {
            Region.createRegion(index, UUID.fromString(file.getString(index + ".User")),
                    new Location(Bukkit.getWorld(file.getString(index + ".Loc1.world")),
                            file.getInt(index + ".Loc1.x"),
                            64,
                            file.getInt(index + ".Loc1.z")),
                    new Location(Bukkit.getWorld(file.getString(index + ".Loc2.world")),
                            file.getInt(index + ".Loc2.x"),
                            64,
                            file.getInt(index + ".Loc2.z")),
                    file.getString(index + ".Message"));
        });
    }

    public void saveAll() throws IOException {
        FileConfiguration file = new YamlConfiguration();
        regions.forEach((index, region) -> {
            file.set(index, null);
            file.set(index + ".User", region.getUser().toString());
            file.set(index + ".Loc1", null);
            file.set(index + ".Loc1.world", region.getLoc1().getWorld().getName());
            file.set(index + ".Loc1.x", region.getLoc1().getBlockX());
            file.set(index + ".Loc1.z", region.getLoc1().getBlockZ());
            file.set(index + ".Loc2", null);
            file.set(index + ".Loc2.world", region.getLoc2().getWorld().getName());
            file.set(index + ".Loc2.x", region.getLoc2().getBlockX());
            file.set(index + ".Loc2.z", region.getLoc2().getBlockZ());
            file.set(index + ".Message", region.getMessage());
        });
        file.save(new File(RegionHelper.getInstance().getDataFolder(), "config.yml"));
    }

    public void startAutoSave() {
        new BukkitRunnable() {
            @SneakyThrows
            @Override
            public void run() {
                saveAll();
                RegionHelper.getInstance().getLogger().info("Autosave complete.");
            }
        }.runTaskTimerAsynchronously(RegionHelper.getInstance(), 0, 1200L);
    }

    public SelectPos getPos(UUID playerUUID) {
        if (!posMap.containsKey(playerUUID)) {
            posMap.put(playerUUID, new SelectPos());
        }
        return posMap.get(playerUUID);
    }

    public static class SelectPos {
        @Getter
        @Setter
        private Location pos1;
        @Getter
        @Setter
        private Location pos2;
    }
}

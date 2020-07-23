package kim.minecraft.regionhelper;

import lombok.SneakyThrows;
import org.bukkit.plugin.java.JavaPlugin;

public final class RegionHelper extends JavaPlugin {

    Storage storage = null;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Loading "+getDescription().getName()+" ver."+getDescription().getVersion());
        storage = new Storage(this);
    }

    @SneakyThrows
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        storage.saveAll();
    }
}

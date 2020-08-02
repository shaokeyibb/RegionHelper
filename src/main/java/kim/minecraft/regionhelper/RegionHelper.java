package kim.minecraft.regionhelper;

import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;


public final class RegionHelper extends JavaPlugin {

    @Getter
    private Storage storage;

    @Getter
    private static RegionHelper instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Loading " + getDescription().getName() + " v." + getDescription().getVersion());
        instance = this;
        storage = new Storage();
        storage.init();

        //Region.createRegion("helloworld",UUID.fromString("6102cb35-f707-4689-aa0c-4accf95aa227"),new Location(Bukkit.getWorld("world"),0,0,0),new Location(Bukkit.getWorld("world"),10,0,10),"welcome");

        storage.startAutoSave();

        Bukkit.getPluginManager().registerEvents(new Logic(),this);

        Bukkit.getPluginCommand("regionhelper").setExecutor(new CommandHandler());
    }

    @SneakyThrows
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        storage.saveAll();
    }
}

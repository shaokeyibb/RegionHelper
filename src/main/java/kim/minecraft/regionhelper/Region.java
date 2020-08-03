package kim.minecraft.regionhelper;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class Region {

    public final static Material TOOL = Material.WOODEN_SHOVEL;
    public final static String ADMIN_PREMISSION = "RegionHelper.admin";

    @Getter
    private final String index;
    @Getter
    private final UUID user;
    @Getter
    private final Location loc1;
    @Getter
    private final Location loc2;
    @Getter
    @Setter
    private String message;

    private Region(String index, UUID user, Location loc1, Location loc2, String message) {
        this.index = index;
        this.user = user;
        this.loc1 = loc1;
        this.loc2 = loc2;
        this.message = message;
    }

    public static void createRegion(String index, UUID user, Location loc1, Location loc2, String message) {
        RegionHelper.getInstance().getStorage().getRegions().put(index, new Region(index, user, loc1, loc2, message));
    }


    @Nullable
    public static Region getRegionThatPlayerJoinInRightNow(Player player) {
        Location loc = player.getLocation();
        int playerX = loc.getBlockX();
        int playerZ = loc.getBlockZ();
        for (Region region : RegionHelper.getInstance().getStorage().getRegions().values()) {
            Location loc1 = region.getLoc1();
            Location loc2 = region.getLoc2();
            int lowX = loc2.getBlockX() - loc1.getBlockX() >= 0 ? loc1.getBlockX() : loc2.getBlockX();
            int highX = loc2.getBlockX() - loc1.getBlockX() <= 0 ? loc1.getBlockX() : loc2.getBlockX();
            int lowZ = loc2.getBlockZ() - loc1.getBlockZ() >= 0 ? loc1.getBlockZ() : loc2.getBlockZ();
            int highZ = loc2.getBlockZ() - loc1.getBlockZ() <= 0 ? loc1.getBlockZ() : loc2.getBlockZ();
            if (playerX >= lowX && playerX <= highX) {
                if (playerZ == lowZ || playerZ == highZ) {
                    return region;
                }
            } else if (playerZ >= lowZ && playerZ <= highZ) {
                if (playerX == lowX || playerX == highX) {
                    return region;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Region{index=" + index + " ,user=" + user + ", loc1=" + loc1 + " ,loc2=" + loc2 + " ,message=" + message + "}";
    }
}

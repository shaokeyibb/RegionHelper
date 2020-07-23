package kim.minecraft.regionhelper;

import org.bukkit.Location;
import org.bukkit.Material;

public class Region {

    public final static Material TOOL = Material.WOODEN_SHOVEL;
    public final static String PERMISSION = "RegionHelper.use";

    public Location loc1;
    public Location loc2;
    public String message;

    public Region(Location loc1,Location loc2,String message){
        this.loc1 = loc1;
        this.loc2 = loc2;
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}

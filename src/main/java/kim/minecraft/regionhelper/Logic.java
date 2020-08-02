package kim.minecraft.regionhelper;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;


public class Logic implements Listener {

    @EventHandler
    public void onMoveIn(PlayerMoveEvent e) {
        Region region = Region.getRegionThatPlayerJoinInRightNow(e.getPlayer());
        if (region != null) {
            e.getPlayer().sendMessage(region.getMessage());
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
            RegionHelper.getInstance().getStorage().getPos(e.getPlayer().getUniqueId()).setPos1(e.getClickedBlock().getLocation());
            e.getPlayer().sendMessage("点1已设置");
        } else if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            RegionHelper.getInstance().getStorage().getPos(e.getPlayer().getUniqueId()).setPos2(e.getClickedBlock().getLocation());
            e.getPlayer().sendMessage("点2已设置");
        }
    }
}

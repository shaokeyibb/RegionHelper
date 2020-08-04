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
            if (RegionHelper.getInstance().getStorage().getInRegion(e.getPlayer().getUniqueId()) == null
                    || RegionHelper.getInstance().getStorage().getInRegion(e.getPlayer().getUniqueId()) != region) {
                RegionHelper.getInstance().getStorage().setInRegion(e.getPlayer().getUniqueId(), region);
                e.getPlayer().sendMessage(region.getMessage());
            }
        } else {
            if (RegionHelper.getInstance().getStorage().getInRegion(e.getPlayer().getUniqueId()) != null) {
                RegionHelper.getInstance().getStorage().setInRegion(e.getPlayer().getUniqueId(), null);
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getItem() != null
                && e.getItem().getType() == Region.TOOL
                && e.getAction() == Action.LEFT_CLICK_BLOCK) {
            RegionHelper.getInstance().getStorage().getPos(e.getPlayer().getUniqueId()).setPos1(e.getClickedBlock().getLocation());
            e.getPlayer().sendMessage("点1已设置");
            e.setCancelled(true);
        } else if (e.getItem() != null
                && e.getItem().getType() == Region.TOOL
                && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            RegionHelper.getInstance().getStorage().getPos(e.getPlayer().getUniqueId()).setPos2(e.getClickedBlock().getLocation());
            e.getPlayer().sendMessage("点2已设置");
            e.setCancelled(true);
        }
    }
}

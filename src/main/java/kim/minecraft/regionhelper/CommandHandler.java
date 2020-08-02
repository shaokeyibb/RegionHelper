package kim.minecraft.regionhelper;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandHandler implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) return false;

        if (args.length >= 2) {
            if (args[0].equalsIgnoreCase("create")) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("Not Support on Console");
                    return true;
                } else {
                    Player player = (Player) sender;
                    if (RegionHelper.getInstance().getStorage().getPos(player.getUniqueId()).getPos1() != null
                            && RegionHelper.getInstance().getStorage().getPos(player.getUniqueId()).getPos2() != null) {
                        if (args.length == 3) {
                            Region.createRegion(args[1],
                                    player.getUniqueId(),
                                    RegionHelper.getInstance().getStorage().getPos(player.getUniqueId()).getPos1(),
                                    RegionHelper.getInstance().getStorage().getPos(player.getUniqueId()).getPos2(),
                                    args[2]);
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        player.sendMessage("&c尚未完成圈点，请使用 " + Region.TOOL + "在二维平面上圈点");
                        return true;
                    }
                }
            } else if (args[0].equalsIgnoreCase("remove")) {
                if (args.length == 2) {
                    if (!(sender instanceof Player)) {
                        RegionHelper.getInstance().getStorage().getRegions().remove(args[1]);
                        sender.sendMessage("删除区域成功！");
                    } else {
                        Player player = (Player) sender;
                        if (RegionHelper.getInstance().getStorage().getRegions().get(args[1]).getUser().equals(player.getUniqueId()) || player.hasPermission(Region.ADMIN_PERMISSION)) {
                            RegionHelper.getInstance().getStorage().getRegions().remove(args[1]);
                            player.sendMessage("删除区域成功！");
                        } else {
                            player.sendMessage("你没有权限删除别人的区域！");
                        }
                    }
                    return true;
                } else {
                    return false;
                }
            } else if (args[0].equalsIgnoreCase("setmessage")) {
                if (args.length == 3) {
                    if (!(sender instanceof Player)) {
                        RegionHelper.getInstance().getStorage().getRegions().get(args[1]).setMessage(args[2]);
                        sender.sendMessage("设置区域信息成功！");
                        return true;
                    }else {
                        Player player = (Player) sender;
                        if (RegionHelper.getInstance().getStorage().getRegions().get(args[1]).getUser().equals(player.getUniqueId()) || player.hasPermission(Region.ADMIN_PERMISSION)) {
                            RegionHelper.getInstance().getStorage().getRegions().get(args[1]).setMessage(args[2]);
                            player.sendMessage("设置区域消息成功！");
                        } else {
                            player.sendMessage("你没有权限设置别人区域的消息！");
                        }
                    }
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}

package me.StevenLawson.TotalFreedomMod.Commands;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
@CommandPermissions(level = AdminLevel.ALL, source = SourceType.ONLY_IN_GAME)
@CommandParameters(description = "Tightly hug those who you love :).", usage = "/<command> <player>", aliases = "thug")
public class Command_tightlyhug extends TFM_Command
{
@Override
public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
{
if(args.length != 1)
{
return false;
}
Player target = Bukkit.getPlayer(args[0]);
if(target == null)
{
sender.sendMessage("The player " + args[0] + " is not online...");
return true;
}
sender_p.sendMessage(ChatColor.RED + "You tightly huggle " + target.getName() + "," + ChatColor.LIGHT_PURPLE + " AWWWWWW <3");
target.sendMessage(ChatColor.RED + sender_p.getName() + " tightly huggles you," + ChatColor.LIGHT_PURPLE + " AWWWWWW <3");
TFM_Util.bcastMsg(ChatColor.RED + sender_p.getName() + " has tightly hugged " + target.getName() + ChatColor.LIGHT_PURPLE + " AWWWWWW <3");
sender_p.teleport(target.getLocation().add(1, 0, 0));
sender_p.getLocation().setYaw(-target.getLocation().getYaw());
sender_p.getLocation().setPitch(-target.getLocation().getPitch());
sender_p.playSound(sender_p.getLocation(), Sound.CAT_MEOW, 10, 10);
target.playSound(target.getLocation(), Sound.CAT_MEOW, 10, 10);
return true;
}
}

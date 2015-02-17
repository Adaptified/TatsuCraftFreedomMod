package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_Util;
import me.StevenLawson.TotalFreedomMod.TotalFreedomMod;
import net.minecraft.util.org.apache.commons.lang3.ArrayUtils;
import net.minecraft.util.org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH)
@CommandParameters(description = "Smitten a user who isn't following the rules", usage = "/<command> [playername] [reason]")
public class Command_smite extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length < 1)
        {
            return false;
        }

        final Player player = getPlayer(args[0]);

        if (player == null)
        {
            playerMsg(TotalFreedomMod.PLAYER_NOT_FOUND);
            return true;
        }
        else if (args.length > 1)
        {
            final String reason = StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " ");
            smite(player, reason);
            return true;
        }

        else
        {
         TFM_Util.playerMsg(sender, "Please, try to include a reason!", ChatColor.RED);
        }

        return true;
    }

    public static void smite(final Player player, final String reason)
    {
        TFM_Util.bcastMsg(String.format("%s has been a naughty, naughty gal.\nThey have thus been smitten!\n" + ChatColor.GOLD + " Reason: %s", player.getName(), reason), ChatColor.RED);
        String full = String.format(ChatColor.RED + "%s has been smitten for %s", player.getName(), reason);
        player.setGameMode(GameMode.SURVIVAL);
        player.getInventory().clear();
        final Location targetPos = player.getLocation();
        final World world = player.getWorld();
        for (int x = -1; x <= 1; x++)
        {
            for (int z = -1; z <= 1; z++)
            {
                final Location strike_pos = new Location(world, targetPos.getBlockX() + x, targetPos.getBlockY(), targetPos.getBlockZ() + z);
                world.strikeLightning(strike_pos);
            }
        }
        player.setHealth(0.0);
    }

    public static void smite(final Player player)
    {
        TFM_Util.bcastMsg(player.getName() + " has been a naughty, naughty gal.\n", ChatColor.RED);
        player.setGameMode(GameMode.SURVIVAL);
        player.getInventory().clear();
        final Location targetPos = player.getLocation();
        final World world = player.getWorld();
        for (int x = -1; x <= 1; x++)
        {
            for (int z = -1; z <= 1; z++)
            {
                final Location strike_pos = new Location(world, targetPos.getBlockX() + x, targetPos.getBlockY(), targetPos.getBlockZ() + z);
                world.strikeLightning(strike_pos);
            }
        }

        //Kill:
        player.setHealth(0.0);
    }
}
// This command is based on FOP's smite:  https://bitbucket.org/Camzie99/freedomopmod/raw/4a7df8f581789ff647b4c31e7dbe0b20fc11babf/src/me/StevenLawson/TotalFreedomMod/Commands/Command_smite.java

package pt.dnx.ccore.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import pt.dnx.ccore.players.Rank;

public class MessageUtil {

	public static String PREFIX = colorize("&6Clay&fNetwork &7� ");
    public static String DEV_PREFIX = colorize("&6Clay &c&lDeveloper's &7� ");
    public static String STAFF_PREFIX = colorize("&c&lSTAFF &7�");

    public static void sendNoPermissionMessage(Player p, Rank rank)
    {

        p.sendMessage(PREFIX + "&cVoce nao tem permissao!");
        p.sendMessage(PREFIX + "&eVoce precisa de ser: " + rank.getTag());

    }

    public static void sendSomethingIsBrokenMessage(Player p)
    {

        p.sendMessage(DEV_PREFIX + "&4ERRO! Nao esta disponivel!");

    }

    public static void sendBlankMessage(Player p, String... msg)
    {

        for (String message : msg)
        {

            p.sendMessage(colorize(message));

        }

    }

    public static void sendRawMessage(Player p, String... msg)
    {

        for (String message : msg)
        {

            p.sendMessage(PREFIX + colorize(message));

        }

    }

    public static void sendRawMessage(CommandSender sender, String... msg)
    {

        for (String message : msg)
        {

            sender.sendMessage(PREFIX + colorize(message));

        }

    }

    public static void sendDeveloperMessage(Player p, String... msg)
{

    for (String message : msg)
    {

        p.sendMessage(DEV_PREFIX + colorize(message));

    }

}

    public static void sendDeveloperMessage(CommandSender sender, String... msg)
    {

        for (String message : msg)
        {

            sender.sendMessage(DEV_PREFIX + colorize(message));

        }

    }

    /*public static void sendStaffMessage(Player p, String... msg)
    {

        for (String message : msg)
        {

            AmethystPlayer ap = new AmethystPlayer(p);

            if (ap.isStaffChatEnabled()) {

                p.sendMessage(STAFF_PREFIX + colorize(message));
            }

        }

    }*/

    public static void broadcastTitleMessage(String title, String subtitle, int fadeIn, int stay, int fadeOut)
    {

        for (Player p : Bukkit.getOnlinePlayers())
        {

            sendTitleMessage(p, title, subtitle, fadeIn, stay, fadeOut);

        }

    }

    public static void sendHoveringMessage(Player player, String message, String hover)
    {
        IChatBaseComponent comp = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + PREFIX + colorize(message) + "\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"" + colorize(hover) + "\"}}");
        PacketPlayOutChat packet = new PacketPlayOutChat(comp);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    public static void sendClickableMessage(Player player, String message, String command)
    {
        IChatBaseComponent comp = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + PREFIX + colorize(message) + "\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"" + command + "\"}}");
        PacketPlayOutChat packet = new PacketPlayOutChat(comp);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    public static void sendClickableAndHoveringMessage(Player player, String message, String hover, String command)
    {
        IChatBaseComponent comp = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + PREFIX + colorize(message) + "\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"" + colorize(hover) + "\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"" + command + "\"}}");
        PacketPlayOutChat packet = new PacketPlayOutChat(comp);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    public static void sendActionBarMessage(Player p, String message)
    {
        IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + colorize(message) + "\"}");
        PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc,(byte) 2);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(ppoc);
    }

    public static void sendTitleMessage(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut)
    {
        CraftPlayer craftplayer = (CraftPlayer) player;
        PlayerConnection connection = craftplayer.getHandle().playerConnection;
        IChatBaseComponent titleJSON = IChatBaseComponent.ChatSerializer.a("{'text': '" + colorize(title) + "'}");
        IChatBaseComponent subtitleJSON = IChatBaseComponent.ChatSerializer.a("{'text': '" + colorize(subtitle) + "'}");
        PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleJSON, fadeIn, stay, fadeOut);
        PacketPlayOutTitle subtitlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, subtitleJSON);
        connection.sendPacket(titlePacket);
        connection.sendPacket(subtitlePacket);
    }


    public static String colorize(String input)
    {

        return ChatColor.translateAlternateColorCodes('&', input);

    }

}

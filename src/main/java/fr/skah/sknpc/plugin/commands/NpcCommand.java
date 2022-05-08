package fr.skah.sknpc.plugin.commands;

/*
 *  * @Created on 2022 - 01:06
 *  * @Project SK-NPC
 *  * @Author Jimmy  / vSKAH#0075
 */

import fr.skah.sknpc.api.model.NpcModel;
import fr.skah.sknpc.plugin.SkNpcPlugin;
import fr.skah.sknpc.plugin.manager.NpcManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class NpcCommand implements CommandExecutor {


    private final SkNpcPlugin npcPlugin;

    public NpcCommand(SkNpcPlugin npcPlugin) {
        this.npcPlugin = npcPlugin;
    }


    //Npc Create -> Entrer le nom dans le chat
    //Npc Edit -> ouvre un menu permettant de modifier le npc
    //Npc tphere -> tp le npc a sa propre position
    //Npc tpto player -> tp le npc à un autre joueur


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sendInformations(sender);
            sender.sendMessage("§cThis command can used only with player.");
            return false;
        }

        final Player player = (Player) sender;

        if (args[0].equalsIgnoreCase("create")) {
            final CompletableFuture<NpcModel> future = new NpcModel().buildFromConfig(npcPlugin.getConfig());
            future.thenAcceptAsync(npcModel -> {
                npcModel.setLocation(player.getLocation());
                npcPlugin.getNpcController().createNpc(npcModel, player).thenAccept(entityPlayer1 -> {
                    NpcManager.getInstance().load(npcModel);
                    npcPlugin.getNpcController().showNpc(entityPlayer1, npcModel);
                    player.sendMessage("§aVous venez de créer un npc par défaut");
                });
            }, Executors.newCachedThreadPool());
        }

        if (args[0].equalsIgnoreCase("delete")) {
            
        }

        return false;
    }


    private void sendHelp(CommandSender commandSender) {

    }

    private void sendInformations(CommandSender commandSender) {
        commandSender.sendMessage("§aPlugin Name : §2SK-NPC");
        commandSender.sendMessage("§aPlugin Version : §2" + npcPlugin.getDescription().getVersion());
        commandSender.sendMessage("§aPlugin Author : §2SKAH#7513");
    }

}

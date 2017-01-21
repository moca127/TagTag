package kr.mocha.command;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import kr.mocha.manager.TagManager;

/**
 * Created by user on 17. 1. 20.
 */
public class AddTagCommand extends Command {

    public AddTagCommand(){
        super("addtag", "add tag for player", "/addtag <Player> <tag>");
        this.setPermission("tag.op.cmd");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        try{
            Player player = Server.getInstance().getPlayer(args[0]);
            TagManager manager = new TagManager(player);
            if(manager.hasTag(args[1])) sender.sendMessage("player already has this tag");
            else {
                manager.addTag(args[1]);
                sender.sendMessage("successful added "+player.getName()+"'s tag");
                return true;
            }
        }catch (Exception e) {sender.sendMessage(this.getUsage());}
        return false;
    }
}

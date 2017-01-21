package kr.mocha.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import kr.mocha.TagTag;
import kr.mocha.manager.TagManager;

/**
 * Created by user on 17. 1. 21.
 */
public class DelTagCommand extends Command {

    public DelTagCommand() {
        super("deltag", "delete player's tag", "/deltag <player> <index>");
        setPermission("tag.op.cmd");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        try{
            Player player = TagTag.getInstance().getServer().getPlayer(args[0]);
            TagManager manager = new TagManager(player);
            manager.del(Integer.parseInt(args[1]));
            sender.sendMessage(TextFormat.YELLOW+"deleted player's tag!");
            player.sendMessage(TextFormat.YELLOW+"Deleted your tag!");
        }catch (Exception e){
            sender.sendMessage(TextFormat.RED+getUsage());
        }
        return false;
    }

}

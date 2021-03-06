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
public class AddTagCommand extends Command {

    public AddTagCommand() {
        super("addtag", "add tag for player", "/addtag <player> <tag>");
        setPermission("tag.op.cmd");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        try {
            Player player = TagTag.getInstance().getServer().getPlayer(args[0]);
            TagManager manager = new TagManager(player);

            if(manager.has(args[1])) sender.sendMessage(TextFormat.RED+"player is already has this tag");
            else {
                manager.add(args[1]);
                sender.sendMessage(TextFormat.YELLOW+"Successful added tag!");
                player.sendMessage(TextFormat.YELLOW+"added your tag!");
                return true;
            }
        }catch (Exception e){
            sender.sendMessage(TextFormat.RED+getUsage());
        }
        return false;
    }

}

package kr.mocha.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import kr.mocha.TagTag;
import kr.mocha.manager.NameManager;

/**
 * Created by user on 17. 1. 21.
 */
public class AddNameCommand extends Command {

    public AddNameCommand() {
        super("addname", "add name for player", "/addname <player> <name>");
        setPermission("tag.op.cmd");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        try {
            Player player = TagTag.getInstance().getServer().getPlayer(args[0]);
            NameManager manager = new NameManager(player);

            if(manager.has(args[1])) sender.sendMessage(TextFormat.RED+"player is already has this name");
            else {
                manager.add(args[1]);
                sender.sendMessage(TextFormat.YELLOW+"Successful added name!");
                player.sendMessage(TextFormat.YELLOW+"added your name!");
                return true;
            }
        }catch (Exception e){
            sender.sendMessage(TextFormat.RED+getUsage());
        }
        return false;
    }

}

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
public class DelNameCommand extends Command {

    public DelNameCommand() {
        super("delname", "delete player's name", "/delname <player> <index>");
        setPermission("tag.op.cmd");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        try{
            Player player = TagTag.getInstance().getServer().getPlayer(args[0]);
            NameManager manager = new NameManager(player);
            manager.del(Integer.parseInt(args[1]));
            sender.sendMessage(TextFormat.YELLOW+"deleted player's name!");
            player.sendMessage(TextFormat.YELLOW+"Deleted your name!");
        }catch (Exception e){
            sender.sendMessage(TextFormat.RED+getUsage());
        }
        return false;
    }

}

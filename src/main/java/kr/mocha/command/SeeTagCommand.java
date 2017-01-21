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
public class SeeTagCommand extends Command {

    public SeeTagCommand() {
        super("seetag", "see player's tag", "/seetag <player>");
        setPermission("tag.op.cmd");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        try{
            Player player = TagTag.getInstance().getServer().getPlayer(args[0]);
            TagManager manager = new TagManager(player);
            sender.sendMessage("=== "+player.getName()+"'s tags ===");
            int i = 0;
            for(String s : manager.gets()){
                sender.sendMessage(i+". "+s);
                i++;
            }
        }catch (Exception e){
            sender.sendMessage(TextFormat.RED+getUsage());
        }
        return false;
    }

}

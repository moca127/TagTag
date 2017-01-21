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
public class SeeNameCommand extends Command {

    public SeeNameCommand() {
        super("seename", "see player's name", "/seename <player>");
        setPermission("tag.op.cmd");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        try{
            Player player = TagTag.getInstance().getServer().getPlayer(args[0]);
            NameManager manager = new NameManager(player);
            sender.sendMessage("=== "+player.getName()+"'s names ===");
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

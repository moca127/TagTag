package kr.mocha.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import kr.mocha.manager.TagManager;

/**
 * Created by user on 17. 1. 21.
 */
public class SelectTagCommand extends Command {

    public SelectTagCommand() {
        super("selecttag", "select my tag", "/selecttag <index>");
        setPermission("tag.user.cmd");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(!sender.isPlayer()) sender.sendMessage(TextFormat.RED+"this command use only player");
        else {
            Player player = (Player) sender;
            TagManager manager = new TagManager(player);
            if(isNum(args[0])) {
                try{manager.set(Integer.valueOf(args[0]));}
                catch (IndexOutOfBoundsException e){sender.sendMessage("number is large");}
                player.sendMessage(TextFormat.YELLOW+"Successful change your tag!");
            }else if(manager.has(args[0])){
                manager.set(args[0]);
                player.sendMessage(TextFormat.YELLOW+"Successful change your tag!");
            }else {
                sender.sendMessage(TextFormat.RED+"Don't have this tag!");
            }
        }
        return false;
    }

    private boolean isNum(String s){
        try {
            Integer.parseInt(s);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}

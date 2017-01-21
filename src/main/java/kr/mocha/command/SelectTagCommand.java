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
        super("selecttag", "select my tag", "/selecttag <tag index or tag>");
        this.setPermission("tag.user.cmd");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        try{
            if(sender.isPlayer()) {
                Player player = (Player) sender;
                TagManager manager = new TagManager(player);
                if (isNumber(args[0]) && Integer.valueOf(args[0]) < manager.getTags().size()) {
                    int i = Integer.valueOf(args[0]);
                    manager.setTag(manager.getTags().get(i));
                    sender.sendMessage(TextFormat.ESCAPE+"eSuccessful change my tag!");
                    return true;
                }else if(manager.hasTag(args[0])) {
                    manager.setTag(args[0]);
                    sender.sendMessage(TextFormat.ESCAPE+"eSuccessful change my tag!");
                    return true;
                }else {
                    sender.sendMessage("don't have this tag!");
                }
            }else sender.sendMessage(TextFormat.RED+"this command use only player");
        }catch (Exception e){
            sender.sendMessage(this.getUsage());
        }
        return false;
    }

    private boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}

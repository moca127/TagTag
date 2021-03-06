package kr.mocha.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import kr.mocha.manager.TagManager;

/**
 * Created by user on 17. 1. 21.
 */
public class MyTagCommand extends Command {

    public MyTagCommand() {
        super("mytag", "see my tag", "/mytag");
        this.setPermission("tag.user.cmd");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(!sender.isPlayer())
            sender.sendMessage(TextFormat.RED+"this command is use only player!");
        else {
            Player player = (Player) sender;
            TagManager manager = new TagManager(player);
            player.sendMessage(TextFormat.GREEN+"=== My tags ===");

            int i = 0;
            for (String s : manager.gets()) {
                sender.sendMessage(i+". "+s);
                i++;
            }
        }
        return false;
    }
}

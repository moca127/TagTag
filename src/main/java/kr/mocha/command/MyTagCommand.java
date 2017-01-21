package kr.mocha.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import kr.mocha.manager.TagManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 17. 1. 20.
 */
public class MyTagCommand extends Command {

    public MyTagCommand() {
        super("mytag","see my tags", "/mytag");
        this.setPermission("tag.user.cmd");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(!sender.isPlayer()) sender.sendMessage(this.getUsage());
        else {
            TagManager manager = new TagManager((Player) sender);
            List<String> tags = manager.getTags();

            int i = 0;
            String r = "";

            for(String s : tags) {
                if(i < tags.size()-1)
                    r += s + ", ";
                else
                    r += s;
                i++;
            }
            sender.sendMessage(TextFormat.GREEN+"=== My tag ===");
            sender.sendMessage(TextFormat.BLUE+r);
        }
        return false;
    }

}

package kr.mocha.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import kr.mocha.manager.TagManager;

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

        TagManager manager = new TagManager((Player) sender);
        List<String> tags = manager.getTags();

        sender.sendMessage(TextFormat.BLUE+"=== mytag ===");

        for(int i = 0; i < tags.size(); i++)
                sender.sendMessage("("+i+") "+tags.get(i));

        return true;
    }

}

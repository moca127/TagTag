package kr.mocha;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerLoginEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import kr.mocha.command.*;
import kr.mocha.manager.NameManager;
import kr.mocha.manager.TagManager;

import java.io.File;

/**
 * Created by user on 17. 1. 21.
 */
public class TagTag extends PluginBase implements Listener {
    private static TagTag instance;
    public Config tag, name;

    @Override
    public void onEnable() {
        new File(getDataFolder()+File.separator+"player").mkdirs();
        instance = this;
        this.tag = new Config(getDataFolder()+File.separator+"tag.yml", Config.YAML);
        this.name = new Config(getDataFolder()+File.separator+"name.yml", Config.YAML);
        this.getServer().getPluginManager().registerEvents(this, this);
        this.commandRegister();
        super.onEnable();
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        if(!name.exists(event.getPlayer().getName())) {
            new NameManager(event.getPlayer()).make();
        }
        if(!tag.exists(event.getPlayer().getName())) {
            new TagManager(event.getPlayer()).make();
        }
    }


    public void commandRegister() {
        this.getServer().getCommandMap().register("mytag", new MyTagCommand());
        this.getServer().getCommandMap().register("myname", new MyNameCommand());
        this.getServer().getCommandMap().register("addtag", new AddTagCommand());
        this.getServer().getCommandMap().register("addname", new AddNameCommand());
        this.getServer().getCommandMap().register("selecttag", new SelectTagCommand());
        this.getServer().getCommandMap().register("selectname", new SelectNameCommand());
        this.getServer().getCommandMap().register("deltag", new DelTagCommand());
        this.getServer().getCommandMap().register("delname", new DelNameCommand());
        this.getServer().getCommandMap().register("seetag", new SeeTagCommand());
        this.getServer().getCommandMap().register("seename", new SeeNameCommand());
    }

    public static TagTag getInstance() {
        return instance;
    }
}

package kr.mocha;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import kr.mocha.command.AddTagCommand;
import kr.mocha.command.MyTagCommand;
import kr.mocha.command.SelectTagCommand;
import kr.mocha.event.EventListener;

import java.io.File;
import java.util.LinkedHashMap;

/**
 * Created by user on 17. 1. 20.
 */
public class TagTag extends PluginBase {
    private static TagTag instance;
    public Config config, tag, name;

    @Override
    public void onEnable() {
        this.getDataFolder().mkdirs();
        new File(getDataFolder()+File.separator+"player").mkdirs();
        this.config = new Config(new File(getDataFolder()+File.separator+"config.yml"), Config.YAML,
                new LinkedHashMap<String, Object>(){
                    {
                        put("default", TextFormat.YELLOW+"[USER]");
                    }
                });
        this.tag = new Config(new File(getDataFolder()+File.separator+"tag.json"), Config.JSON);
        this.name = new Config(new File(getDataFolder()+File.separator+"name.json"), Config.JSON);
        instance = this;
        this.getServer().getPluginManager().registerEvents(new EventListener(), this);
        this.getServer().getCommandMap().register("addtag", new AddTagCommand());
        this.getServer().getCommandMap().register("selecttag", new SelectTagCommand());
        this.getServer().getCommandMap().register("mytag", new MyTagCommand());
        super.onEnable();
    }

    public static TagTag getInstance() {
        return instance;
    }
}

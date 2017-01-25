package kr.mocha.manager;

import cn.nukkit.Player;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import kr.mocha.TagTag;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 17. 1. 21.
 */
public class NameManager extends Manager {
    public Config name;

    public NameManager(Player player){
        super(player);
        this.name = TagTag.getInstance().name;
        String c = File.separator;
        this.config = new Config(
                TagTag.getInstance().getDataFolder()+c+"player"+c+player.getName()+".json", Config.JSON
        );
    }

    @Override
    public void make() {
        List<String> list = new ArrayList<String>();
        list.add(player.getName());
        name.set(player.getName(), player.getName());
        config.set("names", list);
        name.save();
        config.save();
        set(player.getName());
    }

    @Override
    public void add(String... args) {
        List<String> list = config.getList("names");

        for(String s : args) {
            if(!has(s)) list.add(s);
        }

        config.set("names", list);
        config.save();
    }

    @Override
    public void del(int arg) throws ArrayIndexOutOfBoundsException {
        List<String> list = config.getList("names");
        list.remove(arg);
        config.set("names", list);
        config.save();
        if(name.getString(player.getName()).equals(arg)) {
            name.set(player.getName(), list.get(0));
            name.save();
        }
    }

    @Override
    public boolean has(String arg) {
        List<String> list = config.getList("names");
        return list.contains(arg);
    }

    @Override
    public void set(String arg) {
        if(has(arg)) {
            name.set(player.getName(), arg);
            name.save();
            String tag = TagTag.getInstance().tag.getString(player.getName());
            player.setDisplayName(tag+" "+arg+TextFormat.WHITE);
            player.setNameTag(player.getDisplayName());
        }
    }

    public void set(int index) throws ArrayIndexOutOfBoundsException {
        List<String> list = config.getList("names");
        name.set(player.getName(), list.get(index));
        name.save();
        String tag = TagTag.getInstance().tag.getString(player.getName());
        player.setDisplayName(tag+" "+list.get(index)+TextFormat.WHITE);
        player.setNameTag(player.getDisplayName());
    }

    @Override
    public String get() {
        return name.getString(player.getName());
    }

    @Override
    public List<String> gets() {
        return config.getList("names");
    }

}

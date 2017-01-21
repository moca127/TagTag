package kr.mocha.event;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerLoginEvent;
import cn.nukkit.utils.Config;
import kr.mocha.TagTag;
import kr.mocha.manager.TagManager;


/**
 * Created by user on 17. 1. 20.
 */
public class EventListener implements Listener {
    public final String defaultTag = TagTag.getInstance().config.getString("default");
    public Config tag = TagTag.getInstance().tag;

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        String name = player.getName();
        TagManager manager = new TagManager(player);

        if(!tag.exists(name)) {
            manager.makeTags(defaultTag);
        }
        manager.setTag(tag.getString(name));
    }
}

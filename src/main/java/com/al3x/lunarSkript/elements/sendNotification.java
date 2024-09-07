package com.al3x.lunarSkript.elements;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.module.notification.Notification;
import com.lunarclient.apollo.module.notification.NotificationModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.time.Duration;
import java.util.Optional;

public class sendNotification extends Effect {

    static {
        Skript.registerEffect(sendNotification.class,
                "send [lunar] notification with title %string% description %string% [duration %-number%] to %player%"
        );
    }

    private NotificationModule notificationModule = Apollo.getModuleManager().getModule(NotificationModule.class);

    private Expression<String> title;
    private Expression<String> description;
    private Expression<Number> duration;
    private Expression<Player> player;

    @Override
    protected void execute(Event event) {
        if (player == null) return;

        String titleText = title.getSingle(event);
        String descriptionText = description.getSingle(event);
        Duration displayDuration = duration != null ? Duration.ofSeconds(duration.getSingle(event).intValue()) : Duration.ofSeconds(5); // Default 5 seconds

        Notification notification = Notification.builder()
                .titleComponent(Component.text(titleText, NamedTextColor.GREEN))
                .descriptionComponent(Component.text(descriptionText, NamedTextColor.GRAY))
                .displayTime(displayDuration)
                .build();

        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(player.getSingle(event).getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> notificationModule.displayNotification(apolloPlayer, notification));
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return null;
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        title = (Expression<String>) expressions[0];
        description = (Expression<String>) expressions[1];
        duration = (Expression<Number>) expressions[2]; // Optional duration
        player = (Expression<Player>) expressions[3];
        return true;
    }
}

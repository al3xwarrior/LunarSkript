package com.al3x.lunarSkript.elements;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.module.nametag.Nametag;
import com.lunarclient.apollo.module.nametag.NametagModule;
import com.lunarclient.apollo.recipients.Recipients;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class updateNametag extends Effect {

    static {
        Skript.registerEffect(updateNametag.class,
                "set [lunar] nametag of %player% to %strings%"
        );
    }

    private NametagModule nametagModule = Apollo.getModuleManager().getModule(NametagModule.class);

    private Expression<String> lines;
    private Expression<Player> targetPlayer;

    @Override
    protected void execute(Event event) {
        Player target = targetPlayer.getSingle(event);
        if (target == null) return;

        List<Component> nametagLines = new ArrayList<>();
        String[] linesArray = lines.getArray(event);

        for (String line : linesArray) {
            Component component = Component.text()
                    .content(line)
                    .decorate(TextDecoration.ITALIC)
                    .color(NamedTextColor.GRAY)
                    .build();
            nametagLines.add(component);
        }

        nametagModule.overrideNametag(
                Recipients.ofEveryone(),
                target.getUniqueId(),
                Nametag.builder()
                        .lines(nametagLines) // set the nametag lines dynamically
                        .build()
        );
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return null;
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        targetPlayer = (Expression<Player>) expressions[0];
        lines = (Expression<String>) expressions[1];
        return true;
    }
}

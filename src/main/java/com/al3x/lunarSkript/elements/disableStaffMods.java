package com.al3x.lunarSkript.elements;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.module.beam.BeamModule;
import com.lunarclient.apollo.module.staffmod.StaffMod;
import com.lunarclient.apollo.module.staffmod.StaffModModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Optional;

public class disableStaffMods extends Effect {

    static {
        Skript.registerEffect(disableStaffMods.class,
                "disable [all] staffmods for %player%"
        );
    }

    private StaffModModule staffModModule = Apollo.getModuleManager().getModule(StaffModModule.class);

    private Expression<Player> player;

    @Override
    protected void execute(Event event) {
        Player target = player.getSingle(event);

        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(target.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> staffModModule.disableStaffMods(apolloPlayer, Collections.singletonList(StaffMod.XRAY)));
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return null;
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        player = (Expression<Player>) expressions[0];
        return true;
    }
}
